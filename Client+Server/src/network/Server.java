package network;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Id;
import main.Menu;
import net.NetServer;
import net.NetUser;
import network.packets.Packet;
import network.packets.Packet.PacketTypes;
import network.packets.Packet00Login;
import network.packets.Packet01Disconnect;
import network.packets.Packet02Move;
import network.packets.Packet03Move_Enabled;
import entity.Entity;
import entity.Ghost;
import entity.Player;

public class Server extends NetServer implements ActionListener {

	private Map<NetUser, Entity> players;
	private List<Entity> entity;
	private JButton bla;
	private int tick;

	public Server(int port, int packetSize) {
		super(port, packetSize);
		super.start();
	}

	@Override
	protected void init() {

		InputStream s = Menu.class.getResourceAsStream("font.ttf");
		Image icon = null;
		Font pixel = null;
		try {
			icon = ImageIO.read(Menu.class.getResourceAsStream("/logo.png"));
			pixel = Font.createFont(Font.TRUETYPE_FONT, s).deriveFont(Font.BOLD, 30);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		players = new HashMap<NetUser, Entity>();

		JFrame frame = new JFrame();
		frame.setTitle("Server");
		frame.setSize(240, 180);
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(new WindowInput(this));
		frame.setVisible(true);
		frame.setFont(pixel);
		frame.setIconImage(icon);

		JPanel p = new JPanel();
		p.setBounds(0, 0, 240, 180);
		frame.add(p);

		JButton bla = new JButton("Spiel starten");
		bla.setFont(pixel);
		bla.setBounds(0, 0, 240, 180);
		p.add(bla);
		bla.addActionListener(this);

		frame.pack();

	}

	public static void main(String[] args) {
		if (args != null)
			new Server(1337, 1024);
		else
			new Server(1402, 64);
	}

	@Override
	public void parsePacket(byte[] data, InetAddress address, int port) {
		String message = new String(data).trim();
		PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
		switch (type) {
		default:
		case INVALID:
			break;
		case LOGIN:
			Packet00Login packet00 = new Packet00Login(data);
			if (packet00.getChoice().equalsIgnoreCase("ghost")) {
				System.out.println("[" + address.getHostAddress() + ":" + ":" + port + "] " + packet00.getUsername()
						+ " ist verbunden als " + packet00.getChoice());
				NetUser user = new NetUser(packet00.getUsername(), address, port);
				Player player = new Player(packet00.getUsername(), packet00.getX(), packet00.getY(), 24, 24, Id.player);
				users.add(user);
				players.put(user, player);
				packet00.send(this);
				for (NetUser u : users) {
					if (players.get(u) != null && !u.equals(user)) {
						super.send(new Packet00Login(players.get(u).getUsername(), players.get(u).getX(),
								players.get(u).getY(), "pacman").getData(), user);
					}
				}
			} else if (packet00.getChoice().equalsIgnoreCase("pacman")) {
				System.out.println("[" + address.getHostAddress() + ":" + ":" + port + "] " + packet00.getUsername()
						+ " ist verbunden als: " + packet00.getChoice());
				NetUser user = new NetUser(packet00.getUsername(), address, port);
				Ghost player = new Ghost(packet00.getUsername(), packet00.getX(), packet00.getY(), 24, 24, Id.ghost);
				users.add(user);
				players.put(user, player);
				packet00.send(this);
				for (NetUser u : users) {
					if (players.get(u) != null && !u.equals(user)) {
						super.send(new Packet00Login(players.get(u).getUsername(), players.get(u).getX(),
								players.get(u).getY(), "ghost").getData(), user);
					}
				}
			}
			break;
		case DISCONNECT:
			Packet01Disconnect packet01 = new Packet01Disconnect(data);
			packet01.send(this);
			break;
		case MOVE:
			Packet02Move packet02 = new Packet02Move(data);
			packet02.send(this);
			break;
		case MOVE_ENABLED:
			Packet03Move_Enabled packet03 = new Packet03Move_Enabled(data);
			packet03.send(this);
		}
	}

	@Override
	public void tick() {
	}

	@Override
	protected void shutdown() {
		for (NetUser user : users) {
			new Packet01Disconnect(user.getUsername()).send(this);
		}
	}

	private class WindowInput implements WindowListener {

		private NetServer server;

		public WindowInput(NetServer server) {
			this.server = server;
		}

		@Override
		public void windowActivated(WindowEvent e) {

		}

		@Override
		public void windowClosed(WindowEvent e) {

		}

		@Override
		public void windowClosing(WindowEvent e) {
			server.stop();
		}

		@Override
		public void windowDeactivated(WindowEvent e) {

		}

		@Override
		public void windowDeiconified(WindowEvent e) {

		}

		@Override
		public void windowIconified(WindowEvent e) {

		}

		@Override
		public void windowOpened(WindowEvent e) {

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (NetUser u : users) {
			System.out.println(u.getUsername() + " hat Movement Packet gesendet bekommen!");
			send(new Packet03Move_Enabled(players.get(u).getUsername(), "true").getData(), u);

		}

	}
}
