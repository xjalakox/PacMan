package tk.jalako.tile;

import java.awt.Color;
import java.awt.Graphics;

import tk.jalako.main.Handler;
import tk.jalako.main.Id;

public class asd extends Tile{

	public asd(int x, int y, int w, int h, Id id, Handler handler) {
		super(x, y, w, h, id, handler);

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, w, h);
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
