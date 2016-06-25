package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import main.Game;
import main.Handler;
import main.Id;
import main.KeyInput;
import network.packets.Packet01Disconnect;
import tile.Tile;

public class Ghost extends Entity {
	int frame = 0, frameDelay = 0;
	private boolean down = true;
	private boolean up = true;
	private boolean right = true;
	private boolean left = true;
	private KeyInput key;
	private String username;
	private int test, test2, test3;

	private int netid;
	private boolean keyInputEnabled = false;

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
		if (test2 == 20) {
			test2 = 0;
			System.out.println(test);
			test = (int) (Math.random() * 4);
			test3 = test;
			if (test == 0) {
				g.drawImage(Game.sprites[390].getBufferedImage(), x, y, w, h, null);
				g.drawImage(Game.sprites[391].getBufferedImage(), x + 24, y, w, h, null);
				g.drawImage(Game.sprites[422].getBufferedImage(), x, y + 24, w, h, null);
				g.drawImage(Game.sprites[423].getBufferedImage(), x + 24, y + 24, w, h, null);
			} else if (test == 1) {
				g.drawImage(Game.sprites[560].getBufferedImage(), x, y, w, h, null);
				g.drawImage(Game.sprites[561].getBufferedImage(), x + 24, y, w, h, null);
				g.drawImage(Game.sprites[470].getBufferedImage(), x, y + 24, w, h, null);
				g.drawImage(Game.sprites[471].getBufferedImage(), x + 24, y + 24, w, h, null);
			} else if (test == 2) {

				g.drawImage(Game.sprites[390].getBufferedImage(), x, y, w, h, null);
				g.drawImage(Game.sprites[391].getBufferedImage(), x + 24, y, w, h, null);
				g.drawImage(Game.sprites[422].getBufferedImage(), x, y + 24, w, h, null);
				g.drawImage(Game.sprites[423].getBufferedImage(), x + 24, y + 24, w, h, null);
			} else {
				g.drawImage(Game.sprites[560].getBufferedImage(), x, y, w, h, null);
				g.drawImage(Game.sprites[561].getBufferedImage(), x + 24, y, w, h, null);
				g.drawImage(Game.sprites[470].getBufferedImage(), x, y + 24, w, h, null);
				g.drawImage(Game.sprites[471].getBufferedImage(), x + 24, y + 24, w, h, null);
			}
		} else {
			if (test == 0) {
				g.drawImage(Game.sprites[390].getBufferedImage(), x, y, w, h, null);
				g.drawImage(Game.sprites[391].getBufferedImage(), x + 24, y, w, h, null);
				g.drawImage(Game.sprites[422].getBufferedImage(), x, y + 24, w, h, null);
				g.drawImage(Game.sprites[423].getBufferedImage(), x + 24, y + 24, w, h, null);
			} else if (test == 1) {
				g.drawImage(Game.sprites[560].getBufferedImage(), x, y, w, h, null);
				g.drawImage(Game.sprites[561].getBufferedImage(), x + 24, y, w, h, null);
				g.drawImage(Game.sprites[501].getBufferedImage(), x, y + 24, w, h, null);
				g.drawImage(Game.sprites[502].getBufferedImage(), x + 24, y + 24, w, h, null);
			} else if (test == 2) {

				g.drawImage(Game.sprites[390].getBufferedImage(), x, y, w, h, null);
				g.drawImage(Game.sprites[391].getBufferedImage(), x + 24, y, w, h, null);
				g.drawImage(Game.sprites[422].getBufferedImage(), x, y + 24, w, h, null);
				g.drawImage(Game.sprites[423].getBufferedImage(), x + 24, y + 24, w, h, null);
			} else {
				g.drawImage(Game.sprites[560].getBufferedImage(), x, y, w, h, null);
				g.drawImage(Game.sprites[561].getBufferedImage(), x + 24, y, w, h, null);
				g.drawImage(Game.sprites[470].getBufferedImage(), x, y + 24, w, h, null);
				g.drawImage(Game.sprites[471].getBufferedImage(), x + 24, y + 24, w, h, null);
			}

			test2++;
		}
		
		/*
		 * g.drawImage(Game.playerSprite[0].getBufferedImage(), x, y, w, h,
		 * null); g.drawImage(Game.playerSprite[1].getBufferedImage(), x + 24,
		 * y, w, h, null); g.drawImage(Game.playerSprite[2].getBufferedImage(),
		 * x, y + 24, w, h, null);
		 * g.drawImage(Game.playerSprite[3].getBufferedImage(), x + 24, y + 24,
		 * w, h, null);
		 */

		// g.drawRect(getX() + 5, getY() + 5, getW() * 2 - 10, getH() * 2 - 10);
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
				setVelX(-3);
				setVelY(0);
			}
			if (right) {
				setVelX(3);
				setVelY(0);
			}
			if (up) {
				setVelY(-3);
				setVelX(0);
			}
			if (down) {
				setVelY(3);
				setVelX(0);
			}
		}

		for (Entity en : Handler.entity) {
			if (en.getId() == Id.player) {
				if (en.getBounds().intersects(getBounds())) {
					en.remove();
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

	public void setMovementEnabled(boolean b) {
		this.keyInputEnabled = true;

	}
}