package tk.jalako.tile;

import java.awt.Graphics;

import tk.jalako.main.Handler;
import tk.jalako.main.Id;

public abstract class Tile {
	protected int facing;
	protected int x, y, w, h;
	protected int velX, velY;

	protected Id id;

	protected Handler handler;

	protected Tile(int x, int y, int w, int h, Id id, Handler handler) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.id = id;
		this.handler = handler;
	}

	public abstract void render(Graphics g);

	public abstract void tick();
}
