package tk.jalako.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import tk.jalako.main.Game;
import tk.jalako.main.Id;
import tk.jalako.main.KeyInput;

public class Player extends Entity {
	int frame = 0, frameDelay = 0;
	private KeyInput key;
	private String username;

	public Player(String username, int x, int y, int w, int h, Id id, KeyInput key) {
		super(x, y, w, h, id, Game.handler);
		this.key = key;
		this.username = username;
	}

	@Override
	public void render(Graphics g) {

		g.setColor(Color.BLUE);
		g.fillRect(getX(), getY(), getW(), getH());
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

	private boolean collision() {
		return false;

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(getX() + 7, getY() + 65, getW() - 14, getH() - 65);
	}

	public Rectangle getBoundsBottom() {
		return new Rectangle(getX() + 7, getY() + 65, getW() - 14, getH() - 65);
	}

	public Rectangle getBoundsTop() {
		return new Rectangle(getX() * 2, getY() * 2 - 10, getW() * 2, getH() * 2 - 54);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle(getX() * 2 + 64, getY() * 2, getW() * 2 - 54, getH() * 2);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle(getX() * 2 - 10, getY() * 2, getW() * 2 - 54, getH() * 2);
	}

	public String getUsername() {
		return username;
	}
}