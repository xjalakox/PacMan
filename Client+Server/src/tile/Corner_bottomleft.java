package tile;

import java.awt.Graphics;

import main.Game;
import main.Id;

public class Corner_bottomleft extends Tile {

	public Corner_bottomleft(int x, int y, int w, int h, Id id, boolean solid) {
		super(x, y, w, h, id, solid);

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Game.sprites[292].getBufferedImage(),x,y,w,h,null);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

}
