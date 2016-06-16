package ALLAHU.AKBAR;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Zeichnung extends JFrame {

	Color hautfarbe = new Color(255, 211, 155);
	Keys key = new Keys();
	Kreis kopf = new Kreis(450,150,150,150,hautfarbe,true);
	Rechteck körper = new Rechteck(440, 320, 170, 300, hautfarbe,true);
	Rechteck hals = new Rechteck(500, 250, 50, 100, hautfarbe,true);
	Rechteck arm_links = new Rechteck(380, 320, 50, 260, hautfarbe,true);
	Rechteck arm_rechts = new Rechteck(620, 320, 50, 260, hautfarbe,true);
	Rechteck bein_links = new Rechteck(440, 600, 70, 260, hautfarbe,true);
	Rechteck bein_rechts = new Rechteck(540, 600, 70, 260, hautfarbe,true);
	

	Image image1;

	public Zeichnung() {
		try {
			image1 = ImageIO.read(Zeichnung.class.getResourceAsStream("/bg.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 1000);
		setTitle("The Dancing faggot");
		setLocationRelativeTo(null);
		setVisible(true);
		addKeyListener(key);

		
	}

	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		g.drawImage(image1, 0,0, 1000,1000,null);
		
		kopf.paint(g);
		körper.paint(g);
		hals.paint(g);
		arm_links.paint(g);
		arm_rechts.paint(g);
		bein_links.paint(g);
		bein_rechts.paint(g);
	}

	public void pause(int zeit) {
		try {
			Thread.sleep(zeit);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
