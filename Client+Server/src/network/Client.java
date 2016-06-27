package network;

import java.net.InetAddress;

import main.Game;
import main.Id;
import net.NetClient;
import network.packets.Packet;
import network.packets.Packet.PacketTypes;
import network.packets.Packet00Login;
import network.packets.Packet01Disconnect;
import network.packets.Packet02Move;
import network.packets.Packet03Move_Enabled;
import entity.Ghost;
import entity.Player;

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
			if (packet00.getChoice().equalsIgnoreCase("pacman")) {
				if (!username.equals(packet00.getUsername())) {
					System.out.println("Anderer Spieler als PACMAN verbunden.");
					Game.handler.addEntity(
							new Player(packet00.getUsername(), packet00.getX(), packet00.getY(), 24, 24, Id.player));
				}
			} else if (packet00.getChoice().equalsIgnoreCase("ghost")) {
				if (!username.equals(packet00.getUsername())) {
					System.out.println("Anderer Spieler als GHOST verbunden.");
					Game.handler.addEntity(
							new Ghost(packet00.getUsername(), packet00.getX(), packet00.getY(), 24, 24, Id.ghost));
				}
			}
			break;
		case DISCONNECT:
			Packet01Disconnect packet01 = new Packet01Disconnect(data);
			Game.handler.removePlayer(packet01.getUsername());
			break;
		case MOVE:
			Packet02Move packet02 = new Packet02Move(data);
			Game.handler.setPlayerPosition(packet02.getUsername(), packet02.getX(), packet02.getY());
			break;
		case MOVE_ENABLED:
			Packet03Move_Enabled packet03 = new Packet03Move_Enabled(data);
			Game.handler.setPlayerKeyinputEnabled(packet03.getUsername(), true);

		}
	}

	@Override
	protected void shutdown() {

	}
}
