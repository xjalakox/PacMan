package tk.jalako.network.packets;

import org.gnet.packet.Packet;

public class askForPositionPacket extends Packet {

	public askForPositionPacket() {
		super("askForPositionPacket", 1);
		addEntry("ask",true);
	}

}
