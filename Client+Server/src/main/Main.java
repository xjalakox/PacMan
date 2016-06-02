package main;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import menu.Menu;


public class Main {

	public static void main(String[] args) {
		Image icon = null;
		try {
			icon = ImageIO.read(Menu.class.getResourceAsStream("/logo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Game game = new Game();
		JFrame frame = new JFrame("Pacman Reloaded");
		frame.add(game);
		frame.setIconImage(icon);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		game.start();
	}

}
