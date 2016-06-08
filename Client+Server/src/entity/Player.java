package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.net.InetAddress;

import main.Game;
import main.Handler;
import main.Id;
import main.KeyInput;
import tile.Tile;

public class Player extends Entity {
	int frame = 0, frameDelay = 0;
	private KeyInput key;
	private String username;

	public Player(String username, int x, int y, int w, int h, Id id, KeyInput key) {
		super(x, y, w, h, id, Game.handler);
		this.key = key;
		this.username = username;
	}
	
	public Player(String username, int x, int y, int w, int h, Id id) {
		super(x, y, w, h, id,Game.handler);
		this.username = username;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Game.playerSprite[0].getBufferedImage(), x, y, w, h,null);
		g.drawImage(Game.playerSprite[1].getBufferedImage(), x+24, y, w, h,null);
		g.drawImage(Game.playerSprite[2].getBufferedImage(), x, y+24, w, h,null);
		g.drawImage(Game.playerSprite[3].getBufferedImage(), x+24, y+24, w, h,null);
		
		g.drawRect(getX(),getY(),getW()*2,getH()*2);
	}

	@Override
	public void tick() {
		for(Tile t : Handler.tile){
			if(t.getId()==Id.Collision){
				if(t.getBoundsBottom().intersects(getBounds())){
					key.up = false;
				}
			}
		}
		if (key != null&&key.key_enable) {
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
		return new Rectangle(getX(),getY(),getW(),getH());
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
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
}