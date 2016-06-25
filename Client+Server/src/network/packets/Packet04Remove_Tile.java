package network.packets;

import network.Client;
import network.Server;
import tile.Tile;

public class Packet04Remove_Tile extends Packet {

	private String username;
	private Tile t;

	public Packet04Remove_Tile(byte[] data) {
		super(PacketTypes.MOVE.getId());
		String[] message = readData(data).split(",");
		username = message[0];

	}

	public Packet04Remove_Tile(String username, String movement) {
		super(PacketTypes.MOVE.getId());
		this.username = username;
	}

	public void send(Client client) {
		client.send(getData());
	}

	public void send(Server server) {
		server.sendToAll(getData());
	}

	public byte[] getData() {
		return ("04" + username).getBytes();
	}

	public String getUsername() {
		return username;
	}
}
