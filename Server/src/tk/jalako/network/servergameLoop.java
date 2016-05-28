package tk.jalako.network;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class servergameLoop extends JPanel {
		
	
	@Override
	public void paint(Graphics g) {

		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawString("Server", 100, 100);
		
		for(User users: Server.users){
			g2d.drawString(users.getName(), 200, 200);
		}
		
		repaint();
		
		
	}
}
