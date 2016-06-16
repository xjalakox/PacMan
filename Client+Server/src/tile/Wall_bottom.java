package tile;

import java.awt.Color;
import java.awt.Graphics;

import main.Game;
import main.Handler;
import main.Id;

public class Wall_bottom extends Tile {

	public Wall_bottom(int x, int y, int w, int h, Id id, boolean solid) {
		super(x, y, w, h, id, solid);

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Game.sprites[289].getBufferedImage(),x,y,w,h,null);
		g.setColor(Color.GREEN);
		g.fillRect(x+6,y+20,w-12,h-20);
		g.setColor(Color.YELLOW);
		g.fillRect(x,y+6,w-20,h-12);
		g.setColor(Color.BLUE);
		g.fillRect(x+20,y+6,w-20,h-12);
		g.setColor(Color.RED);
		g.fillRect(x+6,y,w-12,h-20);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

}
