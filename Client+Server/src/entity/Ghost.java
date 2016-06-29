package entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import main.Game;
import main.Handler;
import main.Id;
import main.KeyInput;
import network.packets.Packet01Disconnect;
import tile.Tile;

public class Ghost extends Entity {
	private boolean down = true;
	private boolean up = true;
	private boolean right = true;
	private boolean left = true;
	private KeyInput key;
	private String username;

	private int netid;
	public static boolean bomb;

	public Ghost(String username, int x, int y, int w, int h, Id id, KeyInput key) {
		super(x, y, w, h, id, Game.handler, username);
		this.key = key;
		this.username = username;
	}

	public Ghost(String username, int x, int y, int w, int h, Id id) {
		super(x, y, w, h, id, Game.handler, username);
		this.username = username;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Game.sprites[390].getBufferedImage(), x, y, w, h, null);
		g.drawImage(Game.sprites[391].getBufferedImage(), x + 24, y, w, h, null);
		g.drawImage(Game.sprites[422].getBufferedImage(), x, y + 24, w, h, null);
		g.drawImage(Game.sprites[423].getBufferedImage(), x + 24, y + 24, w, h, null);

		g.drawRect(getX(), getY(), getW() * 2, getH() * 2);

	}

	@Override
	public void tick() {

		if (key != null && key.key_enable && keyInputEnabled) {
			if (!collisionu()) {
				if (KeyInput.up) {
					up = true;
					down = false;
				}
			} else {
				up = false;
				setVelY(0);
			}

			if (!collisiond()) {
				if (KeyInput.down) {
					down = true;
					up = false;
				}
			} else {
				down = false;
				setVelY(0);
			}

			if (!collisionr()) {
				if (KeyInput.right) {
					right = true;
					left = false;
					up = false;
					down = false;
				}
			} else {
				right = false;
				setVelX(0);
			}

			if (!collisionl()) {
				if (KeyInput.left) {
					left = true;
					right = false;
					up = false;
					down = false;
				}
			} else {
				left = false;
				setVelX(0);
			}

			if (left) {
				if (bomb) {
					setVelX(-2);
					setVelY(0);
				} else {
					setVelX(-4);
					setVelY(0);
				}
			}
			if (right) {
				if (bomb) {
					setVelX(2);
					setVelY(0);
				} else {
					setVelX(4);
					setVelY(0);
				}
			}
			if (up) {
				if (bomb) {
					setVelY(-2);
					setVelX(0);
				} else {
					setVelY(-4);
					setVelX(0);
				}
			}
			if (down) {
				if (bomb) {
					setVelY(2);
					setVelX(0);

				} else {
					setVelY(4);
					setVelX(0);
				}
			}
		}

		for (Tile tile : Handler.tile) {
			if (tile.getId() == Id.point) {
				for (Entity en : Handler.entity) {
					if (en.getId() == Id.player) {
						if (tile.getBounds().intersects(en.getBounds())) {
							tile.remove();
						}
					}
				}
			}
		}

		x += velX;
		y += velY;

		if (KeyInput.quit) {
			new Packet01Disconnect(Game.player.getUsername()).send(Game.client);
		}
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
		return new Rectangle(getX() - 3, getY() - 3, getW() * 2 + 6, getH() * 2 + 6);
	}

	public int getNetId() {
		return netid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}