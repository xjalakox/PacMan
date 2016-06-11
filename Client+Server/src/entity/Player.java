package entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import main.Game;
import main.Handler;
import main.Id;
import main.KeyInput;
import network.packets.Packet01Disconnect;
import tile.Tile;

public class Player extends Entity {
	int frame = 0, frameDelay = 0;
	private KeyInput key;
	private String username;
	private boolean down = true;
	private boolean up = true;
	private boolean right = true;
	private boolean left = true;

	public Player(String username, int x, int y, int w, int h, Id id, KeyInput key) {
		super(x, y, w, h, id, Game.handler);
		this.key = key;
		this.username = username;
	}

	public Player(String username, int x, int y, int w, int h, Id id) {
		super(x, y, w, h, id, Game.handler);
		this.username = username;
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
		if (key != null && key.key_enable) {
			if (KeyInput.up && up) {
				if (!collisionu()) {
					up = false;
				}else{
					up = true;
				}
				setVelX(0);
				setVelY(-3);
			} else if (KeyInput.down && down) {
				if (!collisiond()) {
					down = false;
				}else{
					down = true;
				}
				setVelX(0);
				setVelY(3);
			} else if (KeyInput.right && right) {
				if (!collisionr()) {
					right = false;
				}else{
					right = true;
				}
				setVelY(0);
				setVelX(3);
			} else if (KeyInput.left && left) {
				if (!collisionl()) {
					left = false;
				}else{
					left = true;
				}
				setVelY(0);
				setVelX(-3);
			}
		}

		for (Tile t : Handler.tile) {
			if (t.getId() == Id.Wall) {
				if (t.getBoundsBottom().intersects(getBounds())) {
					key.up = false;
					if (!up) {
						up = true;
						setVelX(0);
						setVelY(0);
					}

				}
				if (t.getBoundsTop().intersects(getBounds())) {
					KeyInput.down = false;
					if (!down) {
						down = true;
						setVelX(0);
						setVelY(0);
					}
				}
				if (t.getBoundsLeft().intersects(getBounds())) {
					key.right = false;
					if (!right) {
						right = true;
						setVelX(0);
						setVelY(0);
					}

				}
				if (t.getBoundsRight().intersects(getBounds())) {
					key.left = false;
					if (!left) {
						left = true;
						setVelX(0);
						setVelY(0);
					}

				}
			}
		}

		x += velX;
		y += velY;

		if (KeyInput.quit)
			new Packet01Disconnect(Game.player.getUsername()).send(Game.client);
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

	public String getUsername() {
		return username;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}