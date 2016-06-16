package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ALLAHU.AKBAR.SoundManager;





public class Menu {
	
	public String username;
	
	
	public Menu(){
		
		//////////////////////////////////////////GENERALS/////////////////////////////////////////////////////
		SoundManager sm = new SoundManager();
		sm.playSound(0);
		
		InputStream s =  Menu.class.getResourceAsStream("font.ttf");
		Image icon = null;
        Font pixel = null;
		try {
			icon = ImageIO.read(Menu.class.getResourceAsStream("/logo.png"));
			pixel = Font.createFont(Font.TRUETYPE_FONT, s).deriveFont(Font.BOLD, 30);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//////////////////////////////////////////GENERALS/////////////////////////////////////////////////////
		
		
		JFrame f = new JFrame("Pacman Reloaded");
		f.setBounds(0, 0, 1200, 800);
		f.setIconImage(icon);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
		

        
        /////////////////////////////////LOBBY-SCREEN///////////////////////////////////////
        JPanel lobbypanel = new JPanel();
        lobbypanel.setBounds(0, 0, 1200, 800);
        lobbypanel.setLayout(null);
        lobbypanel.setVisible(false);
        f.add(lobbypanel);
        
		JLabel username_label = new JLabel();
		username_label.setBounds(270, -17, 200, 100);
		username_label.setFont(pixel);
		username_label.setForeground(Color.WHITE);
		lobbypanel.add(username_label);
		
		JLabel background2 = new JLabel(new ImageIcon(getClass().getResource("/res/backgrounds/lobby.png")));
		background2.setBounds(0, 0, 1200, 800);
		lobbypanel.add(background2);
        /////////////////////////////////LOBBY-SCREEN///////////////////////////////////////
        
        
		
		
        /////////////////////////////////START-SCREEN///////////////////////////////////////
		JPanel mainpanel = new JPanel();
		mainpanel.setBounds(0, 0, 1200, 800);
		mainpanel.setBackground(Color.BLACK);
		mainpanel.setLayout(null);
		f.add(mainpanel);
			
		JLabel background = new JLabel();
		background.setBounds(0, 0, 1200, 800);
		mainpanel.add(background);
		
		JLabel username_title = new JLabel("Username:");
		username_title.setBounds(0,0,0,0);
		
		JTextField username_field = new JTextField();
		username_field.setBounds(450, 500, 300, 40);
		username_field.setFont(pixel);
		mainpanel.add(username_field);
		
		JLabel start = new JLabel();
		start.setIcon(new ImageIcon(getClass().getResource("/res/buttons/start_default.png")));
		start.setBounds(450, 600, 350, 130);
		start.addMouseListener(new MouseAdapter() {
									
					boolean sgHover = false;
							
					public void mouseEntered(MouseEvent evt) {
						start.setIcon(new ImageIcon(getClass().getResource("/res/buttons/start_hover.png")));
						sgHover = true;
						sm.playSound(2);
					}
					public void mouseExited(MouseEvent evt) {
						start.setIcon(new ImageIcon(getClass().getResource("/res/buttons/start_default.png")));
						sgHover = false;
					}
					public void mousePressed(MouseEvent evt) {
						start.setIcon(new ImageIcon(getClass().getResource("/res/buttons/start_pressed.png")));
						sm.stopSound(0);
						sm.playSound(3);
						mainpanel.setVisible(false);
						lobbypanel.setVisible(true);
						username = username_field.getText();
						username_label.setText(username);
					}
					public void mouseReleased(MouseEvent evt) {
						if (sgHover == true) {
							start.setIcon(new ImageIcon(getClass().getResource("/res/buttons/start_hover.png")));
						}
					}
		});
		mainpanel.add(start);
		
		JLabel title = new JLabel(new ImageIcon(getClass().getResource("/res/backgrounds/title.png")));
		title.setBounds(150, 60, 900, 150);
		mainpanel.add(title);
		
		JLabel gif = new JLabel(new ImageIcon(getClass().getResource("/res/backgrounds/loadingscreen.gif")));
		gif.setBounds(0, -100, 1200, 800);
		mainpanel.add(gif);
		/////////////////////////////////START-SCREEN///////////////////////////////////////
		
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		Menu m = new Menu();
	}

	
}
