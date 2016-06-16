package ALLAHU.AKBAR;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Zeichnung extends JFrame implements KeyListener {

	Image image1;
	
	Color hautfarbe = new Color(255, 211, 155);
	Kreis kopf = new Kreis(450,150,150,150,hautfarbe,true);
	Rechteck körper = new Rechteck(440, 320, 170, 300, hautfarbe,true);
	Rechteck hals = new Rechteck(500, 250, 50, 100, hautfarbe,true);
	Rechteck arm_links = new Rechteck(380, 320, 50, 260, hautfarbe,true);
	Rechteck arm_rechts = new Rechteck(620, 320, 50, 260, hautfarbe,true);
	Rechteck bein_links = new Rechteck(440, 600, 70, 260, hautfarbe,true);
	Rechteck bein_rechts = new Rechteck(540, 600, 70, 260, hautfarbe,true);
	private boolean a, b, c, d;

	public Zeichnung() {
		try {
			image1 = ImageIO.read(Zeichnung.class.getResourceAsStream("/bg.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		addKeyListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 1000);
		setTitle("The Dancing faggot");
		setLocationRelativeTo(null);
		setVisible(true);

	}
	
	public void mensch(Graphics g){
		kopf.paint(g);
		körper.paint(g);
		hals.paint(g);
		arm_links.paint(g);
		arm_rechts.paint(g);
		bein_links.paint(g);
		bein_rechts.paint(g);
	}

	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		g.drawImage(image1, 0, 0, 1000, 1000, null);
		
		mensch(g);
		if (a) {
			a = false;
			arm_links.setxy(g, 380-200, 320);
			arm_links.setwh(g,260,50);
			g.drawImage(image1, 0, 0, 1000, 1000, null);
			mensch(g);
		}
		if (b) {
			b = false;
		}
		if (c) {
			c = false;
		}
		if (d) {
			d = false;
		}
		

	}

	public void pause(int zeit) {
		try {
			Thread.sleep(zeit);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 49) {
			a = true;
			repaint();
		} else if (e.getKeyCode() == 50) {
			b = true;
			repaint();
		} else if (e.getKeyCode() == 51) {
			c = true;
			repaint();
		} else if (e.getKeyCode() == 52) {
			d = true;
			repaint();
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
