package menu;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


 
/**
 * @author Darimont
 */
public class PanelWithImage extends JFrame {
	
	private JLabel gif;
 
    public PanelWithImage() {
        super("PanelWithImage");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
 
        gif = new JLabel(new ImageIcon(getClass().getResource("/res/loadingscreen.gif")));
        

        JPanel panel = new JPanel();
        panel.add(gif);
        add(panel);
 
        pack();
        setVisible(true);

    }
 
    /**
     * @param args
     */
    public static void main(String[] args) {
        new PanelWithImage();
    }
 
}