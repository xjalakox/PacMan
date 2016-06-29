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

public class Player extends Entity {
	private KeyInput key;
	private String username;
	private boolean down = true;
	private boolean up = true;
	private boolean right = true;
	private boolean left = true;
	private int deaths = 0;
	private int frametimer = 0;
	private int ticks, seconds, minutes;
	private boolean renderend;
	private boolean visible = true;
	private int SpawnPosX[] = new int[4];
	private int SpawnPosY[] = new int[4];
	private boolean dead;
	private boolean ticking = true;

	public Player(String username, int x, int y, int w, int h, Id id, KeyInput key) {
		super(x, y, w, h, id, Game.handler, username);
		this.key = key;
		this.username = username;
		SpawnPosX[0] = 143;
		SpawnPosX[1] = 143;
		SpawnPosX[2] = 1004;
		SpawnPosX[3] = 1004;

		SpawnPosY[0] = 49;
		SpawnPosY[1] = 910;
		SpawnPosY[2] = 910;
		SpawnPosY[3] = 49;
	}

	public Player(String username, int x, int y, int w, int h, Id id) {
		super(x, y, w, h, id, Game.handler, username);
		this.username = username;
	}

	@Override
	public void render(Graphics g) {
		if (renderend) {
			// TODO setFont(Game.pixel);
			g.clearRect(440, 400, 310, 300);
			g.drawRect(440, 400, 310, 300);
			g.setColor(Color.RED);
			g.drawString("Pacman ist " + deaths + " mal gestorben und hat", 450, 500);
			g.drawString(minutes + " Minuten und " + seconds + " Sekunden", 450, 550);
			g.drawString("gebraucht um das Spiel zu gewinnen", 450, 600);
		} else if (visible) {

			g.drawImage(Game.playerSprite[0].getBufferedImage(), x, y, w, h, null);
			g.drawImage(Game.playerSprite[1].getBufferedImage(), x + 24, y, w, h, null);
			g.drawImage(Game.playerSprite[2].getBufferedImage(), x, y + 24, w, h, null);
			g.drawImage(Game.playerSprite[3].getBufferedImage(), x + 24, y + 24, w, h, null);

			g.setColor(Color.RED);
			g.drawRect(getX(), getY(), getW() * 2, getH() * 2);

			// Das hier ist nur zum ausprobieren ob die Schrift da rein passt
			// und so...
			g.clearRect(440, 400, 310, 300);
			g.drawRect(440, 400, 310, 300);
			g.setColor(Color.RED);
			g.drawString("Pacman ist " + deaths + " mal gestorben und hat", 450, 500);
			g.drawString(minutes + " Minuten und " + seconds + " Sekunden", 450, 550);
			g.drawString("gebraucht um das Spiel zu gewinnen", 450, 600);
		} else {

		}
	}

	@Override
	public void tick() {
		if (ticking) {
			if (ticks >= 60) {
				seconds++;
				ticks = 0;
			} else {
				ticks++;
			}

			if (seconds >= 60) {
				minutes++;
				seconds = 0;
			}

			if (frametimer > 0) {
				setX(-200);
				setY(-200);
				frametimer--;
			} else if (dead) {
				int spawnpos;
				spawnpos = (int) (Math.random() * 4);
				setX(SpawnPosX[spawnpos]);
				setY(SpawnPosY[spawnpos]);
				setVisible(true);
				dead = false;
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
					if (en.getBoundsNormal().intersects(getBoundsNormal())) {
						// TODO Animation des Todes machen
						if (frametimer > 0) {

						} else {
							dead = true;
							setVisible(false);
							frametimer = 180;
							deaths++;
						}
					}
				}

			}

			for (Tile tile : Handler.tile) {
				if (tile.getId() == Id.point) {
					if (tile.getBounds().intersects(getBounds())) {
						tile.remove();
						Handler.sm.playSound(4);
					}
				}
			}

			x += velX;
			y += velY;

			if (KeyInput.quit) {
				new Packet01Disconnect(Game.player.getUsername()).send(Game.client);
			}
		}
	}

	private void setVisible(boolean b) {
		visible = b;

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

	public void renderend(boolean renderend) {
		this.renderend = renderend;
	}
	
	public void setticking(boolean ticking) {
		this.ticking = ticking;
	}

}