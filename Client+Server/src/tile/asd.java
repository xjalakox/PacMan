package tile;

import java.awt.Color;
import java.awt.Graphics;

import main.Handler;
import main.Id;

public class asd extends Tile {

	public asd(int x, int y, int w, int h, Id id, boolean solid, long arrayzahl) {
		super(x, y, w, h, id, solid, arrayzahl);

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(main.Game.sprites[(int) arrayzahl].getBufferedImage(), x,y,w,h, null);

	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

}
