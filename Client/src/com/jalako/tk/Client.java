package com.jalako.tk;

import javax.swing.JOptionPane;

import org.gnet.client.ClientEventListener;
import org.gnet.client.GNetClient;
import org.gnet.client.ServerModel;
import org.gnet.packet.Packet;

public class Client {

	public static void main(String[] args) {
		final String host = "127.0.0.1";
		final int port = 1337;
		final GNetClient netclient = new GNetClient(host, port);

		netclient.addEventListener(new ClientEventListener() {

			@Override
			protected void packetReceived(ServerModel client, Packet packet) {
				
				if(packet.getPacketName().equals("logged")){
					int num = (int) packet.getEntry("num");
					System.out.println(num);
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
			protected void clientDisconnected(ServerModel arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			protected void clientConnected(ServerModel server) {

				String name = JOptionPane.showInputDialog("Name eingeben");
				Packet login = new Packet("loginPacket", 2);
				login.addEntry("playerName", name);
				login.addEntry("test", "test123");
				server.sendPacket(login);

			}
		});

		netclient.bind();
		netclient.start();
	}

}
