package tile;

import java.awt.Color;
import java.awt.Graphics;

import main.Game;
import main.Id;

public class Points extends Tile {

	public Points(int x, int y, int w, int h, Id id, boolean solid) {
		super(x, y, w, h, id, solid);
		
	}

	public void render(Graphics g) {
		g.drawImage(Game.sprites[409].getBufferedImage(),x,y,w,h,null);
		g.setColor(Color.red);
		//g.drawRect(x+10,y+10,w-20,h-20);
	}

	public void tick() {
		
	}

}
