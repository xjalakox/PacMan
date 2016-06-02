package menu;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Menu {
	
	public Menu(){
		JFrame f = new JFrame("Pacman Reloaded");
		f.setBounds(0, 0, 1200, 900);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
		
		
		try {
			final Image image1 = ImageIO.read(Menu.class.getResourceAsStream("/Background/background.png"));
		
		JPanel mainpanel = new JPanel();
		mainpanel.setBounds(0, 0, 1200, 800);
		f.add(mainpanel);
			
		JLabel background = new JLabel(new ImageIcon(image1));
		background.setBounds(0, 0, 1200, 900);
		mainpanel.add(background);
		
		
		} catch (IOException e) {
		}
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		Menu m = new Menu();
	}
	
}
