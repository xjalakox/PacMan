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

	Image image1, image3;

	Color hautfarbe = new Color(255, 211, 155);
	Kreis kopf = new Kreis(450, 150, 150, 150, hautfarbe, true);
	Rechteck körper = new Rechteck(440, 320, 170, 300, hautfarbe, true);
	Rechteck hals = new Rechteck(500, 250, 50, 100, hautfarbe, true);
	Rechteck arm_links = new Rechteck(380, 320, 50, 260, hautfarbe, true);
	Rechteck arm_rechts = new Rechteck(620, 320, 50, 260, hautfarbe, true);
	Rechteck bein_links = new Rechteck(440, 600, 70, 260, hautfarbe, true);
	Rechteck bein_rechts = new Rechteck(540, 600, 70, 260, hautfarbe, true);
	private boolean a, b, c, d;

	public Zeichnung() {
		SoundManager m = new SoundManager();
		m.playSound(1);
		try {
			image1 = ImageIO.read(Zeichnung.class.getResourceAsStream("/bg.jpg"));
			image3 = ImageIO.read(Zeichnung.class.getResourceAsStream("/unnamed.png"));
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

	public void mensch(Graphics g) {
		g.drawImage(image1, 0, 0, 1000, 1000, null);
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
			arm_links.setxy(g, 180, 320);
			arm_links.setwh(g, 260, 50);
			mensch(g);
			g.drawImage(image3, 440, 140, 175, 175,null);
			pause(200);
			arm_links.setxy(g, 380, 320);
			arm_links.setwh(g, 50, 260);
			mensch(g);

		}
		if (b) {
			b = false;
			arm_rechts.setxy(g, 570, 320);
			arm_rechts.setwh(g, 260, 50);
			mensch(g);
			g.drawImage(image3, 615, 140, -175, 175, null);
			pause(200);
			arm_rechts.setxy(g, 620, 320);
			arm_rechts.setwh(g, 50, 260);
			mensch(g);
		}
		if (c) {
			c = false;
			bein_links.setxy(g, 240, 600);
			bein_links.setwh(g, 260, 70);
			mensch(g);
			g.drawImage(image3, 440, 140, 175, 175, null);
			pause(200);
			bein_links.setxy(g, 440, 600);
			bein_links.setwh(g, 70, 260);
			mensch(g);
		}
		if (d) {
			d = false;
			bein_rechts.setxy(g, 540, 600);
			bein_rechts.setwh(g, 260, 70);
			mensch(g);
			g.drawImage(image3, 615, 140, -175, 175, null);
			pause(200);
			bein_rechts.setxy(g, 540, 600);
			bein_rechts.setwh(g, 70, 260);
			mensch(g);
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
