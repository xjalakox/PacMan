package tk.jalako.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import tk.jalako.entity.Entity;
import tk.jalako.entity.Player;
import tk.jalako.graphics.ImageLoader;
import tk.jalako.graphics.Sprite;
import tk.jalako.graphics.SpriteSheet;
import tk.jalako.network.Client;
import tk.jalako.network.packets.Packet00Login;
import tk.jalako.network.packets.Packet01Disconnect;
import tk.jalako.network.packets.Packet02Move;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 320;
	public static final int HEIGHT = 180;
	public static final int SCALE = 4;

	private int fps, ups;
	private BufferedImage background;

	private boolean running = false;
	private Thread thread;
	private int frames;

	private String pname;

	public static Handler handler = new Handler();
	public static Sprite[] playerSprite = new Sprite[4];
	public static KeyInput key = new KeyInput();
	public static Player player;

	private SpriteSheet sprites;

	private Client client;

	synchronized void start() {
		// Thread starten
		if (running)
			return;
		running = true;
		thread = new Thread(this, "Thread");
		thread.start();

		// Network CLIENT starten
		client = new Client(this, 64);
		client.start();

	}

	private synchronized void stop() {
		new Packet01Disconnect(player.getUsername()).send(client);
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
		}
	}

	public void tick() {
		handler.tick();
		key.tick();
		for (Entity e : Handler.entity) {
			if (e.getId() == Id.player) {
				new Packet02Move(((Player) e).getUsername(), ((Player) e).getX(), ((Player) e).getY()).send(client);
			}
		}
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(4);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;

		// g.drawImage(background,0 , 0, getWidth(), getHeight(), this);
		g.drawRect(0, 0, WIDTH * SCALE + 100, HEIGHT * SCALE + 100);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH * SCALE + 100, HEIGHT * SCALE + 100);
		g.drawImage(background,0,0,background.getWidth(),background.getHeight(),null);
		// g2d.translate(cam.getX(), cam.getY());
		handler.render(g);
		// g2d.translate(-cam.getX(), -cam.getY());
		

		g.setColor(Color.BLUE);
		g.setFont(new Font("Verdana", Font.BOLD, 23));

		g.dispose();
		bs.show();
	}

	public void init() {
		key = new KeyInput();
		addKeyListener(key);
		
		sprites = new SpriteSheet("/pacman.png");
		
		
		
		playerSprite[0] = new Sprite(sprites,1,6*48+1,47,47);
		playerSprite[1] = new Sprite(sprites,49,6*48+1,47,47);
		playerSprite[2] = new Sprite(sprites,1,7*48+1,47,47);
		playerSprite[3] = new Sprite(sprites,49,7*48+1,47,47);
		
		ImageLoader loader = new ImageLoader();
	    background = loader.loadImage("/map.PNG");
		
		

		player = new Player(JOptionPane.showInputDialog(this, "Username"), 250, 250, 24, 24, Id.player, key);
		handler.addEntity(player);
		client.setConnection(player.getUsername(), JOptionPane.showInputDialog(this, "IPAddress"));
		new Packet00Login(client.getUsername(), player.getX(), player.getY()).send(client);
		
	
		
		handler.createLevel();
	}

	@Override
	public void run() {
		init();
		requestFocus();
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0;
		double ns = 1000000000.0 / 60.0;
		int ticks = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta > 1) {
				tick();
				ticks++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {

				timer += 1000;
				fps = frames;
				ups = ticks;
				ticks = 0;
				frames = 0;
				// System.out.println("FPS: " + fps);
				// System.out.println("Ticks: " + ups);
			}
		}
		stop();
	}

	public Game() {
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);

	}

	public static int getFrameWidth() {
		return WIDTH * SCALE;
	}

	public static int getFrameHeight() {
		return HEIGHT * SCALE;
	}

}