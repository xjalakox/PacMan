package com.jalako.tk;

import org.gnet.packet.Packet;
import org.gnet.server.ClientModel;
import org.gnet.server.GNetServer;
import org.gnet.server.ServerEventListener;

public class Server {

	public static void main(String[] args) {

		final String host = "127.0.0.1";
		final int port = 1337;
		final GNetServer netserver = new GNetServer(host, port);

		netserver.addEventListener(new ServerEventListener() {

			@Override
			protected void packetReceived(ClientModel client, Packet packet) {

				if (packet.getPacketName().equals("loginPacket")) {

					String name = (String) packet.getEntry("playerName");
					String mood = (String) packet.getEntry("test");

					System.out.println("Name: " + name);
					System.out.println("Test: " + mood);
					System.out.println("Uuid: " + client.getUuid());

					return;
				}
			}

			@Override
			protected void errorMessage(String arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			protected void debugMessage(String arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			protected void clientDisconnected(ClientModel arg0) {
				// TODO Auto-generated method stub

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
