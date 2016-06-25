package tile;

import java.awt.Color;
import java.awt.Graphics;

import main.Game;
import main.Handler;
import main.Id;

public class spawn_top extends Tile {

	public spawn_top(int x, int y, int w, int h, Id id, boolean solid) {
		super(x, y, w, h, id, solid);

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Game.sprites[156].getBufferedImage(),x,y,w,h,null);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

}
