package tk.jalako.network.packets;

import tk.jalako.network.Client;
import tk.jalako.network.Server;

public class Packet01Disconnect extends Packet {

	private String username;

	public Packet01Disconnect(byte[] data) {
		super(PacketTypes.DISCONNECT.getId());
		username = readData(data);
	}

	public Packet01Disconnect(String username) {
		super(PacketTypes.DISCONNECT.getId());
		this.username = username;
	}

	public void send(Client client) {
		client.send(getData());
	}

	public void send(Server server) {
		server.sendToAll(getData());
	}

	public byte[] getData() {
		return ("01" + username).getBytes();
	}

	public String getUsername() {
		return username;
	}

}
