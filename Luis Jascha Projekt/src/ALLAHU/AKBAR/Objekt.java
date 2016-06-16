package ALLAHU.AKBAR;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Objekt {

	protected int x, y, w, h;
	protected Color c;
	protected boolean füllStatus;

	public Objekt(int x, int y, int w, int h, Color c, boolean füllStatus) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.c = c;
		this.füllStatus = füllStatus;
	}

	public abstract void paint(Graphics g);

	public abstract void setxy(Graphics g, int x,int y);

	public abstract void setwh(Graphics g, int w,int h);

	public void löschen(Graphics g) {
		g.clearRect(x, y, w, h);
	}
	
	public int getX(){
		return x;
	}

	public int getY() {
		return y;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

}
