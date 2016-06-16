package ALLAHU.AKBAR;

import java.awt.Color;
import java.awt.Graphics;

public class Rechteck extends Objekt {

	public Rechteck(int x, int y, int w, int h, Color c, boolean f�llStatus) {
		super(x, y, w, h, c, f�llStatus);
	}

	public void paint(Graphics g) {
		g.setColor(c);
		g.fillRect(x, y, w, h);
		System.out.println(w);

	}

	public void setwh(Graphics g, int w, int h) {
		l�schen(g);
		this.w = w;
		this.h = h;
		paint(g);
	}

	public void setxy(Graphics g, int x, int y) {
		l�schen(g);
		this.x = x;
		this.y = y;
		paint(g);
	}

}
