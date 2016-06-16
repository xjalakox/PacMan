package ALLAHU.AKBAR;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Objekt {

	protected int x, y, w, h;
	protected Color c;
	protected boolean f�llStatus;

	public Objekt(int x, int y, int w, int h, Color c, boolean f�llStatus) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.c = c;
		this.f�llStatus = f�llStatus;
	}

	public abstract void paint(Graphics g);

	public abstract void setxy(Graphics g, int x,int y);

	public abstract void setwh(Graphics g);

	public void l�schen(Graphics g) {
		g.clearRect(x, y, w, h);
	}

}
