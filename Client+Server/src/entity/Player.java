package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.net.InetAddress;

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
		
		g.drawRect(getX()+10,getY()+10,getW()*2-20,getH()*2-20);
	}

	@Override
	public void tick() {
		if(KeyInput.quit) new Packet01Disconnect(Game.player.getUsername()).send(Game.client);
		for(Tile t : Handler.tile){
			if(t.getId()==Id.Wall){
				if(t.getBoundsBottom().intersects(getBounds())){
					key.up = false;
				}
				if(t.getBoundsTop().intersects(getBounds())){
					key.down = false;
				}
				if(t.getBoundsLeft().intersects(getBounds())){
					key.right = false;
				}
				if(t.getBoundsRight().intersects(getBounds())){
					key.left = false;
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
		return new Rectangle(getX()+10,getY()+10,getW()*2-20,getH()*2-20);
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