package ALLAHU.AKBAR;

import java.awt.Color;
import java.awt.Graphics;

public class Kreis extends Objekt {

	public Kreis(int x, int y, int w, int h, Color c, boolean füllStatus) {
		super(x, y, w, h, c, füllStatus);
	}

	public void paint(Graphics g) {
		if (füllStatus) {
			g.fillOval(x, y, w, h);
			g.drawString("ALAHU AKBAR", x+30,y+30);
		} else {
			g.drawOval(x, y, w, h);

		}
	}

	public void setxy(Graphics g, int x, int y) {
		löschen(g);
		if (füllStatus) {

		}
	}

	@Override
	public void setwh(Graphics g) {

	}

}


