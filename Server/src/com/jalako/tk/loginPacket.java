package com.jalako.tk;

import org.gnet.packet.Packet;

public class loginPacket extends Packet {

	public loginPacket(String name, String test) {
		super("loginPacket", 2);
		addEntry("playerName",name);
		addEntry("test",test);
	}

}
