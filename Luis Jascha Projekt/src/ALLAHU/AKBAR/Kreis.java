package ALLAHU.AKBAR;

import java.awt.Color;
import java.awt.Graphics;

public class Kreis extends Objekt {

	public Kreis(int x, int y, int w, int h, Color c, boolean f�llStatus) {
		super(x, y, w, h, c, f�llStatus);
	}

	public void paint(Graphics g) {
		if (f�llStatus) {
			g.fillOval(x, y, w, h);
			g.drawString("ALAHU AKBAR", x+30,y+30);
		} else {
			g.drawOval(x, y, w, h);

		}
	}

	public void setxy(Graphics g, int x, int y) {
		l�schen(g);
		if (f�llStatus) {

		}
	}

	@Override
	public void setwh(Graphics g) {

	}

}


