package tile;

import java.awt.Color;
import java.awt.Graphics;

import main.Id;

public class Collision extends Tile {

	public Collision(int x, int y, int w, int h, Id id, boolean solid) {
		super(x, y, w, h, id, solid);

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, w, h);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

}
