package entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import main.Handler;
import main.Id;

public abstract class Entity {
	protected int facing;
	protected int x, y, w, h;
	protected int velX, velY;
	protected boolean removed;
	protected String username;

	protected Id id;

	protected Handler handler;
	protected boolean keyInputEnabled;

	protected Entity(int x, int y, int w, int h, Id id, Handler handler, String username) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.id = id;
		this.handler = handler;
		this.username = username;
		removed = false;
		keyInputEnabled = false;
	}

	public abstract void render(Graphics g);

	public abstract void tick();

	public int getFacing() {
		return facing;
	}

	public void setFacing(int facing) {
		this.facing = facing;
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

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public Rectangle getBounds() {
		return null;
	}

	public void remove() {
		removed = true;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setMovementEnabled(boolean b) {
		this.keyInputEnabled = true;

	}

	public boolean isRemoved() {
		return removed;
	}

	public String getUsername() {
		return username;
	}

	public Rectangle getBoundsNormal() {
		return new Rectangle(getX(), getY(), getW() * 2, getH() * 2);
	}
}
