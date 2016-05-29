package tk.jalako.network.packets;

import org.gnet.packet.Packet;

public class positionPacket extends Packet {

	public positionPacket(int x, int y) {
		super("positionPacket", 2);
		addEntry("xPos",x);
		addEntry("yPos",y);
	}

}
