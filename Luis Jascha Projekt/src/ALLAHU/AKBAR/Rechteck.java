package ALLAHU.AKBAR;

import java.awt.Color;
import java.awt.Graphics;

public class Rechteck extends Objekt {

	public Rechteck(int x, int y, int w, int h, Color c, boolean füllStatus) {
		super(x, y, w, h, c, füllStatus);
	}

	public void paint(Graphics g) {
		g.setColor(c);
		g.fillRect(x, y, w, h);
		System.out.println(w);

	}

	public void setwh(Graphics g, int w, int h) {
		löschen(g);
		this.w = w;
		this.h = h;
		paint(g);
	}

	public void setxy(Graphics g, int x, int y) {
		löschen(g);
		this.x = x;
		this.y = y;
		paint(g);
	}

}
