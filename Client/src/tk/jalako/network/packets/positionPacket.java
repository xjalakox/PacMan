package tk.jalako.network.packets;

import org.gnet.packet.Packet;

public class positionPacket extends Packet {

	public positionPacket(String username,int x, int y) {
		super("positionPacket", 3);
		addEntry("username", username);
		addEntry("xPos",x);
		addEntry("yPos",y);
	}

}
