package tk.jalako.network;

import java.util.ArrayList;

import javax.swing.JFrame;

import org.gnet.packet.Packet;
import org.gnet.server.ClientModel;
import org.gnet.server.GNetServer;
import org.gnet.server.ServerEventListener;

public class Server {
	
	static ArrayList<User> users = new ArrayList<User>();

	public static void main(String[] args) {

		JFrame jf = new JFrame("Server!");
		jf.setSize(1280/2,720/2);
		jf.add(new servergameLoop());
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	
		final String host = "127.0.0.1";
		final int port = 1337;
		final GNetServer netserver = new GNetServer(host, port);

		netserver.addEventListener(new ServerEventListener() {

			@Override
			protected void packetReceived(ClientModel server, Packet packet) {
				
				if (packet.getPacketName().equals("loginPacket")) {
					
					String name = (String) packet.getEntry("playerName");
					
					User user = new User(name);
					user.setName(name);
					
					if(!users.contains(user)){
						users.add(user);
						user.setConnected(true);
					}
					if(users.contains(user)){
						Packet p = new Packet("log",2);
						p.addEntry("name", user.getName());
						p.addEntry("connected", user.isConnected());
						server.sendPacket(p);
					}
					
					System.out.println(name + " ist dem Server beigetreten");
					return;
					
				}

				/*if (packet.getPacketName().equals("loginPacket")) {

					String name = (String) packet.getEntry("playerName");
					String mood = (String) packet.getEntry("test");

					System.out.println("Name: " + name);
					System.out.println("Test: " + mood);
					System.out.println("Uuid: " + client.getUuid());

					return;
				}*/
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

				Packet loggedin = new Packet("logged", 1);
				loggedin.addEntry("num", 1);
				client.sendPacket(loggedin);

			}
		});

		netserver.bind();
		netserver.start();

	}

}
