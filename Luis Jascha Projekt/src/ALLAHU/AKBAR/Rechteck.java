package ALLAHU.AKBAR;

import java.awt.Color;
import java.awt.Graphics;

public class Rechteck extends Objekt {

	public Rechteck(int x, int y, int w, int h, Color c, boolean f�llStatus) {
		super(x, y, w, h, c,f�llStatus);
	}

	public void paint(Graphics g) {
		g.setColor(c);
		g.fillRect(x, y, w, h);
		

	}

	public void setwh(Graphics g) {
		
	}

	public void setxy(Graphics g, int x, int y) {
		
	}

}
