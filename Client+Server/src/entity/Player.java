package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import main.Game;
import main.Handler;
import main.Id;
import main.KeyInput;
import main.Menu;
import main.SoundManager;
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
	private int leben = 3;
	private int frametimer = 0;
	private boolean renderend;
	private boolean visible = true;


	public Player(String username, int x, int y, int w, int h, Id id, KeyInput key) {
		super(x, y, w, h, id, Game.handler, username);
		this.key = key;
		this.username = username;
	}

	public Player(String username, int x, int y, int w, int h, Id id) {
		super(x, y, w, h, id, Game.handler, username);
		this.username = username;
	}

	@Override
	public void render(Graphics g) {
		if (renderend) {
			g.drawString("test", 500, 500);
		} else if(visible) {

			g.drawImage(Game.playerSprite[0].getBufferedImage(), x, y, w, h, null);
			g.drawImage(Game.playerSprite[1].getBufferedImage(), x + 24, y, w, h, null);
			g.drawImage(Game.playerSprite[2].getBufferedImage(), x, y + 24, w, h, null);
			g.drawImage(Game.playerSprite[3].getBufferedImage(), x + 24, y + 24, w, h, null);

			g.setColor(Color.RED);
			// g.drawRect(getX() -3 , getY() -3 , getW() * 2 + 6, getH() * 2 +
			// 6);
		} else {
			
		}
	}

	@Override
	public void tick() {
		if (frametimer > 0) {
			frametimer--;
		}else{
			setVisible(true);
		}
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
			if (en.getId() == Id.ghost) {
				if (en.getBounds().intersects(getBounds())) {
					System.out.println(frametimer);
					// Animation des Todes machen || Leben -1
					if (frametimer > 0) {
						
					} else {
						setVisible(false);
						if (leben <= 0) {
							end();
						} else {
							frametimer = 180;
							leben--;
						}
					}
				}
			}

		}

		for (Tile tile : Handler.tile) {
			if (tile.getId() == Id.point) {
				if (tile.getBounds().intersects(getBounds())) {
					tile.remove();
					Menu.sm.playSound(4);
				}
			}
		}

		x += velX;
		y += velY;

		if (KeyInput.quit) {
			new Packet01Disconnect(Game.player.getUsername()).send(Game.client);
		}
	}

	private void setVisible(boolean b) {
		visible = b;

	}

	private void end() {

		renderend = true;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}