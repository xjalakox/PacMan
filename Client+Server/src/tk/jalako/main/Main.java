package tk.jalako.main;
import javax.swing.JFrame;

import menu.Menu;


public class Main {

	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame("Pacman Reloaded");
		frame.add(game);
		frame.setIconImage(Menu.icon);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		game.start();
	}

}
