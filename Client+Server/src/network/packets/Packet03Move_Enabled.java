package network.packets;

import network.Client;
import network.Server;
import network.packets.Packet.PacketTypes;

public class Packet03Move_Enabled extends Packet {

	private String username;
	private String movement;

	public Packet03Move_Enabled(byte[] data) {
		super(PacketTypes.MOVE.getId());
		String[] message = readData(data).split(",");
		username = message[0];
		movement = message[1];

	}

	public Packet03Move_Enabled(String username, String movement) {
		super(PacketTypes.MOVE.getId());
		this.username = username;
		this.movement = movement;
	}

	public void send(Client client) {
		client.send(getData());
	}

	public void send(Server server) {
		server.sendToAll(getData());
	}

	public byte[] getData() {
		return ("03" + username + "," + movement).getBytes();
	}

	public String getUsername() {
		return username;
	}

	public String getMovement() {
		return movement;
	}
}
