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

	Keys key = new Keys();
	Rechteck r = new Rechteck(0, 0, 250, 250, Color.BLUE,true);
	Kreis k = new Kreis(25,250,500,500,Color.YELLOW,true);
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
		
		k.paint(g);
		r.paint(g);
		
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
