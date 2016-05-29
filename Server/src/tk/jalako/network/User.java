package tk.jalako.network;

import org.gnet.server.ClientModel;

public class User {

	private String name;
	private ClientModel server;
	private boolean connected = false;
	
	public User(String name, ClientModel server) {
		this.name = name;
		this.server = server;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}
	
	public boolean isConnected() {
		return connected;
	}
	
	public ClientModel getServer() {
		return server;
	}

}
