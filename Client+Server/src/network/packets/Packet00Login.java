package network.packets;

import network.Client;
import network.Server;

public class Packet00Login extends Packet {

	private String username, choice;
	private int x, y;

	public Packet00Login(byte[] data) {
		super(PacketTypes.LOGIN.getId());
		String[] message = readData(data).split(",");
		username = message[0];
		x = Integer.parseInt(message[1]);
		y = Integer.parseInt(message[2]);
		choice = message[3];
	}

	public Packet00Login(String username, int x, int y, String choice) {
		super(PacketTypes.LOGIN.getId());
		this.username = username;
		this.x = x;
		this.y = y;
		this.choice = choice;
	}

	public void send(Client client) {
		client.send(getData());
	}

	public void send(Server server) {
		server.sendToAll(getData());
	}

	public byte[] getData() {
		return ("00" + username + "," + x + "," + y + "," + choice).getBytes();
	}

	public String getUsername() {
		return username;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public String getChoice() {
		return choice;
	}

}
