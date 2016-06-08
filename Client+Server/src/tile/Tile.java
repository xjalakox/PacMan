package tile;

import java.awt.Graphics;
import java.awt.Rectangle;

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

	public Rectangle getBoundsBottom() {
		return new Rectangle(getX(),getY(),getW(),getH());
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}
	
	
}
