package tk.jalako.network;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class gameLoop extends JPanel {
		
	
	@Override
	public void paintComponent(Graphics g) {

		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawString("Client", 100, 100);
		
		if(Client.pname != null){
			g.drawString("Spielername: " + Client.pname + "", 150, 150);
			g.drawString("isConnected: " + Client.connected, 150, 175);
		}

		repaint();
	}
}
