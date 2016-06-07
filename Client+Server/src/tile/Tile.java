package tile;

import java.awt.Graphics;

import main.Handler;
import main.Id;

public abstract class Tile {
	protected int facing;
	protected int x, y, w, h;
	protected int velX, velY;
	protected boolean solid;
	protected long arrayzahl;

	protected Id id;

	protected Handler handler;

	protected Tile(int x, int y, int w, int h, Id id, boolean solid) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.id = id;
		this.solid = solid;
	}

	public abstract void render(Graphics g);

	public abstract void tick();

	public Id getId() {
		return id;
	}
}
