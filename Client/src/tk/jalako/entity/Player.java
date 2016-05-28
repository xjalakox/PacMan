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
	private int anim;

	public Player(int x, int y, int w, int h, Id id, KeyInput key) {
		super(x, y, w, h, id, Game.handler);
		this.key = key;
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.BLUE);
		g.fillRect(getX(), getY(), getW(), getH());
	}

	@Override
	public void tick() {
		System.out.println(KeyInput.up);
		if (!collision()) {
				if (KeyInput.up) {
					if (key.running) {
						y -= 6;
					} else {
						y -= 3;
					}
					animate();
				} else if (KeyInput.down) {
					if (key.running) {
						y += 6;
					} else {
						y += 3;
					}
					animate();
				} else if (KeyInput.right) {
					if (key.running) {
						x += 6;
					} else {
						x += 3;
					}
					animate();
				} else if (KeyInput.left) {
					if (key.running) {
						x -= 6;
					} else {
						x -= 3;
					}
					animate();
				} else {
				}
			}
	}

	private boolean collision() {
		return false;

	}

	public void animate() {
		frameDelay++;
		if (frameDelay >= 4 && key.running) {
			frame++;
			if (frame >= 9) {
				frame = 1;
			}
			frameDelay = 0;
		} else if (frameDelay >= 8) {
			frame++;
			if (frame >= 9) {
				frame = 1;
			}
			frameDelay = 0;
		}
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
}