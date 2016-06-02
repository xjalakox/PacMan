package tk.jalako.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class NetServer implements Runnable {

	private Thread thread;
	private boolean running;
	private DatagramSocket socket;
	private int packetSize;
	protected List<NetUser> users;

	public NetServer(int port, int packetSize) {
		this.packetSize = packetSize;
		users = new ArrayList<NetUser>();
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		shutdown();
		System.exit(0);
	}

	@Override
	public void run() {
		init();
		while (running) {
			byte[] data = new byte[packetSize];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
		}
	}

	protected abstract void init();

	protected abstract void parsePacket(byte[] data, InetAddress address, int port);

	protected abstract void shutdown();

	public void send(byte[] data, InetAddress ipAddress, int port) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
		try {
			this.socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void send(byte[] data, NetUser user) {
		DatagramPacket packet = new DatagramPacket(data, data.length, user.getAddress(), user.getPort());
		try {
			this.socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendToAll(byte[] data) {
		for (NetUser user : users) {
			send(data, user);
		}
	}

	public void sendToMap(byte[] data, Map<NetUser, ?> map) {
		for (NetUser user : users) {
			if (map.get(user) != null) {
				send(data, user);
			}
		}
	}

	public NetUser getUser(String username) {
		for (NetUser user : users) {
			if (user.getUsername().equalsIgnoreCase(username)) {
				return user;
			}
		}
		return null;
	}

	public int getUserIndex(String username) {
		int index = 0;
		for (NetUser user : users) {
			if (user.getUsername().equalsIgnoreCase(username)) {
				break;
			}
			index++;
		}
		return index;
	}
}
