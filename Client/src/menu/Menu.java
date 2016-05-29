package menu;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class Menu {
	
	public Menu(){
		JFrame f = new JFrame("Pacman Reloaded");
		f.setBounds(0, 0, 1200, 900);
		f.setVisible(true);
		
		try {
			final Image image1 = ImageIO.read(Menu.class.getResourceAsStream("/Background/background.png"));
		
		JLabel background = new JLabel(new ImageIcon(image1));
		background.setBounds(0, 0, 1200, 900);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		
	}
	
	public static void main(String[] args) {
		Menu m = new Menu();
	}
	
}
