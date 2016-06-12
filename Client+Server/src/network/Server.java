package network;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import entity.Entity;
import entity.Ghost;
import entity.Player;
import main.Id;
import net.NetServer;
import net.NetUser;
import network.packets.Packet;
import network.packets.Packet00Login;
import network.packets.Packet01Disconnect;
import network.packets.Packet02Move;
import network.packets.Packet03Ghost;
import network.packets.Packet.PacketTypes;

public class Server extends NetServer {

	private Map<NetUser, Player> players;
	private List<Entity> entity;

	public Server(int port, int packetSize) {
		super(port, packetSize);
		super.start();
	}

	@Override
	protected void init() {
		JFrame frame = new JFrame();
		frame.setTitle("Server");
		frame.setSize(240, 180);
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(new WindowInput(this));
		frame.setVisible(true);

		players = new HashMap<NetUser, Player>();

		entity = new ArrayList<Entity>();
		entity.add(new Ghost(200, 200, 24, 24, Id.ghost, 0));

	}

	public static void main(String[] args) {
		if (args != null)
			new Server(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		else
			new Server(1402, 64);
	}

	@Override
	protected void parsePacket(byte[] data, InetAddress address, int port) {
		String message = new String(data).trim();
		PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
		switch (type) {
		default:
		case INVALID:
			break;
		case LOGIN:
			Packet00Login packet00 = new Packet00Login(data);
			System.out.println("[" + address.getHostAddress() + ":" + ":" + port + "] " + packet00.getUsername()
					+ " ist verbunden.");
			NetUser user = new NetUser(packet00.getUsername(), address, port);
			Player player = new Player(packet00.getUsername(), packet00.getX(), packet00.getY(), 24, 24, Id.player);
			users.add(user);
			players.put(user, player);
			packet00.send(this);
			for (NetUser u : users) {
				if (players.get(u) != null && !u.equals(user)) {
					super.send(new Packet00Login(players.get(u).getUsername(), players.get(u).getX(),
							players.get(u).getY()).getData(), user);
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
		case GHOST:
			Packet03Ghost packet03 = new Packet03Ghost(data);

			packet03.send(this);
		}
	}

	@Override
	public void tick() {
		for(Entity e : entity) {
			if(e instanceof Ghost) {
				Packet03Ghost packet = new Packet03Ghost(((Ghost) e).getNetId(), e.getX(), e.getY());
				packet.send(this);
			}
		}

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
}
