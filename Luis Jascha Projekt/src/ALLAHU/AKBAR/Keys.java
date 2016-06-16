package ALLAHU.AKBAR;

import java.awt.event.*;

public class Keys implements KeyListener {

	public static boolean a, b, c, d;

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 49) {
			a = true;
		} else if (e.getKeyCode() == 50) {
			b = true;
		} else if (e.getKeyCode() == 51) {
			c = true;
		} else if (e.getKeyCode() == 52) {
			d = true;
		}
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}

}