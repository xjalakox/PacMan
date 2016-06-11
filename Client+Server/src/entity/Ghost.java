package entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import main.Game;
import main.Handler;
import main.Id;
import tile.Tile;

public class Ghost extends Entity {
	int frame = 0, frameDelay = 0;
	private boolean down = true;
	private boolean up = true;
	private boolean right = true;
	private boolean left = true;

	public Ghost(int x, int y, int w, int h, Id id) {
		super(x, y, w, h, id, Game.handler);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Game.playerSprite[0].getBufferedImage(), x, y, w, h, null);
		g.drawImage(Game.playerSprite[1].getBufferedImage(), x + 24, y, w, h, null);
		g.drawImage(Game.playerSprite[2].getBufferedImage(), x, y + 24, w, h, null);
		g.drawImage(Game.playerSprite[3].getBufferedImage(), x + 24, y + 24, w, h, null);

		g.drawRect(getX() + 5, getY() + 5, getW() * 2 - 10, getH() * 2 - 10);
	}

	@Override
	public void tick() {
		
	}

	private boolean collisionr() {
		for (Tile t : Handler.tile) {
			if (t.getId() == Id.Wall) {
				if (t.getBoundsLeft().intersects(getBounds())) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean collisionl() {
		for (Tile t : Handler.tile) {
			if (t.getId() == Id.Wall) {
				if (t.getBoundsRight().intersects(getBounds())) {
					return true;
				}
			}
		}
		return false;

	}

	private boolean collisionu() {
		for (Tile t : Handler.tile) {
			if (t.getId() == Id.Wall) {
				if (t.getBoundsBottom().intersects(getBounds())) {
					return true;
				}
			}
		}
		return false;

	}

	private boolean collisiond() {
		for (Tile t : Handler.tile) {
			if (t.getId() == Id.Wall) {
				if (t.getBoundsTop().intersects(getBounds())) {
					return true;
				}
			}
		}
		return false;

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(getX() + 5, getY() + 5, getW() * 2 - 10, getH() * 2 - 10);
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
}