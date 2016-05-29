package tk.jalako.network;

import java.util.ArrayList;

import javax.swing.JFrame;

import org.gnet.packet.Packet;
import org.gnet.server.ClientModel;
import org.gnet.server.GNetServer;
import org.gnet.server.ServerEventListener;

import tk.jalako.network.packets.askForPositionPacket;

public class Server {

	static ArrayList<User> users = new ArrayList<User>();

	public static void main(String[] args) {

		/*
		 * JFrame jf = new JFrame("Server!"); jf.setSize(1280/2,720/2);
		 * jf.add(new servergameLoop()); jf.setLocationRelativeTo(null);
		 * jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * jf.setVisible(true);
		 */

		final String host = "80.82.219.161";
		final int port = 1337;
		final GNetServer netserver = new GNetServer(host, port);

		netserver.addEventListener(new ServerEventListener() {

			@Override
			protected void packetReceived(ClientModel server, Packet packet) {
				if (packet.getPacketName().equals("loginPacket")) {

					String name = (String) packet.getEntry("playerName");

					User user = new User(name, server);
					user.setName(name);

					if (!users.contains(user)) {
						users.add(user);
						user.setConnected(true);
					}

					for (User user2 : users) {
						if (user != user2) {
							Packet p2 = new Packet("log", 2);
							p2.addEntry("name", user2.getName());
							p2.addEntry("connected", user2.isConnected());
							server.sendPacket(p2);
						}
					}

					if (users.contains(user)) {
						Packet p = new Packet("log", 2);
						p.addEntry("name", user.getName());
						p.addEntry("connected", user.isConnected());
						sendToAllClients(p);
					}

					System.out.println(name + " ist dem Server beigetreten");

				}

				if (packet.getPacketName().equals("positionPacket")) {

					askForPositionPacket askPacket = new askForPositionPacket();

					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//server.sendPacket(askPacket);
					sendToAllClients(askPacket);
				}

				/*
				 * if (packet.getPacketName().equals("loginPacket")) {
				 * 
				 * String name = (String) packet.getEntry("playerName"); String
				 * mood = (String) packet.getEntry("test");
				 * 
				 * System.out.println("Name: " + name);
				 * System.out.println("Test: " + mood);
				 * System.out.println("Uuid: " + client.getUuid());
				 * 
				 * return; }
				 */
			}

			@Override
			protected void errorMessage(String arg0) {

			}

			@Override
			protected void debugMessage(String arg0) {

			}

			@Override
			protected void clientDisconnected(ClientModel arg0) {

			}

			@Override
			protected void clientConnected(ClientModel client) {

			}
		});

		netserver.bind();
		netserver.start();

	}

	private static void sendToAllClients(Packet packet) {
		for (User user : users) {
			user.getServer().sendPacket(packet);
		}
	}

}
