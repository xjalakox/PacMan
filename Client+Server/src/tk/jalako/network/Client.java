package tk.jalako.network;

import java.net.InetAddress;

import tk.jalako.entity.Player;
import tk.jalako.main.Game;
import tk.jalako.main.Id;
import tk.jalako.net.NetClient;
import tk.jalako.network.packets.Packet;
import tk.jalako.network.packets.Packet.PacketTypes;
import tk.jalako.network.packets.Packet00Login;
import tk.jalako.network.packets.Packet01Disconnect;
import tk.jalako.network.packets.Packet02Move;

public class Client extends NetClient {
	
	public Client(Game game, int packetSize) {
		super(game, packetSize);
	}

	@Override
	protected void init() {
		
	}

	@Override
	protected void parsePacket(byte[] data, InetAddress address, int port) {
		String message = new String(data).trim();
		PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
		switch (type) {
		default:
		case INVALID:
			break;
		case LOGIN:
			Packet00Login packet00 = new Packet00Login(data);
			if(!username.equals(packet00.getUsername()))
			Game.handler.addEntity(new Player(packet00.getUsername(),packet00.getX(),packet00.getY(),24,24,Id.player));
			break;
		case DISCONNECT:
			Packet01Disconnect packet01 = new Packet01Disconnect(data);
			Game.handler.removePlayer(packet01.getUsername());
			break;
		case MOVE:
			Packet02Move packet02 = new Packet02Move(data);
			Game.handler.setPlayerPosition(packet02.getUsername(), packet02.getX(), packet02.getY());
			break;
		}
	}

	@Override
	protected void shutdown() {
		
	}
}
