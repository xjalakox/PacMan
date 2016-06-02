package tk.jalako.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.net.InetAddress;

import tk.jalako.main.Game;
import tk.jalako.main.Id;
import tk.jalako.main.KeyInput;

public class PlayerMP extends Entity {

	public InetAddress ipAddress;
	public int port;
	private KeyInput key;

	public PlayerMP(String username, int x, int y, int w, int h, Id id, KeyInput key, InetAddress ipAddress, int port) {
		super(x, y, w, h, id,Game.handler);
		this.key = key;
		this.ipAddress = ipAddress;
		this.port = port;
		// TODO Auto-generated constructor stub
	}
	
	public PlayerMP(String username, int x, int y, int w, int h, Id id, InetAddress ipAddress, int port) {
		super(x, y, w, h, id,Game.handler);
		this.ipAddress = ipAddress;
		this.port = port;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g) {
		//System.out.println("WHY?");
		g.setColor(Color.BLUE);
		g.fillRect(x, y, w, h);
		//System.out.println("ok?");

	}

	@Override
	public void tick() {
		if (key != null) {
			if (KeyInput.up) {
				y -= 3;
			} else if (KeyInput.down) {
				y += 3;
			} else if (KeyInput.right) {
				x += 3;
			} else if (KeyInput.left) {
				x -= 3;
			}
		}
	}

}
