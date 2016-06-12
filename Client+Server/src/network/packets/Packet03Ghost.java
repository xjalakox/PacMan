package network.packets;

import network.Client;
import network.Server;
import network.packets.Packet.PacketTypes;

public class Packet03Ghost extends Packet {

	private int x, y, id;

	public Packet03Ghost(byte[] data) {
		super(PacketTypes.MOVE.getId());
		String[] message = readData(data).split(",");
		id = Integer.parseInt(message[0]);
		x = Integer.parseInt(message[1]);
		y = Integer.parseInt(message[2]);
	}

	public Packet03Ghost(int id, int x, int y) {
		super(PacketTypes.MOVE.getId());
		this.id = id;
		this.x = x;
		this.y = y;
	}

	public void send(Client client) {
		client.send(getData());
	}

	public void send(Server server) {
		server.sendToAll(getData());
	}

	public byte[] getData() {
		return ("03" + id + "," + x + "," + y).getBytes();
	}


	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getId(){
		return id;
	}

}
