package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import entity.Entity;
import entity.Ghost;
import entity.Player;
import graphics.ImageLoader;
import graphics.Sprite;
import graphics.SpriteSheet;
import network.Client;
import network.packets.Packet00Login;
import network.packets.Packet01Disconnect;
import network.packets.Packet02Move;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 300;
	public static final int HEIGHT = 250;
	public static final int SCALE = 4;

	private int fps, ups;
	private BufferedImage background;

	private boolean running = false;
	private int test;
	private Thread thread;
	private int frames;

	public static Handler handler = new Handler();
	public static Sprite[] playerSprite = new Sprite[4];
	public static KeyInput key = new KeyInput();
	public static Player player;
	public static Ghost ghost;
	public static String username;

	public static Sprite[] sprites = new Sprite[641];

	private SpriteSheet spriteSheet;

	public static Client client;
	private BufferedImage level;
	private String choice;

	synchronized void start() {
		// Thread starten
		if (running)
			return;
		running = true;
		thread = new Thread(this, "Thread");
		thread.start();

		// Network CLIENT starten
		client = new Client(this, 1024);
		client.start();

	}

	private synchronized void stop() {

		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
		}
	}

	public void tick() {
		if (test == 1) {
			test = 0;
			for (Entity e : Handler.entity) {
				if (e.getId() == Id.player) {
					new Packet02Move(((Player) e).getUsername(), ((Player) e).getX(), ((Player) e).getY()).send(client);
				}
				if (e.getId() == Id.ghost) {
					new Packet02Move(((Ghost) e).getUsername(), ((Ghost) e).getX(), ((Ghost) e).getY()).send(client);
				}
			}
		} else {
			test++;
		}
		handler.tick();
		key.tick();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(4);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.BLACK);
		g.fillRect(0,0,300*4,250*4);

		// g.drawImage(background,0 , 0, getWidth(), getHeight(), this);
		g.drawRect(0, 0, WIDTH * SCALE + 100, HEIGHT * SCALE + 100);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH * SCALE + 100, HEIGHT * SCALE + 100);
		// g.drawImage(background, -200, -175, background.getWidth() / 2,
		// background.getHeight() / 2, null);
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

		spriteSheet = new SpriteSheet("/spritesheet.png");

		playerSprite[0] = new Sprite(spriteSheet, 1, 6 * 48 + 1, 47, 47);
		playerSprite[1] = new Sprite(spriteSheet, 49, 6 * 48 + 1, 47, 47);
		playerSprite[2] = new Sprite(spriteSheet, 1, 7 * 48 + 1, 47, 47);
		playerSprite[3] = new Sprite(spriteSheet, 49, 7 * 48 + 1, 47, 47);

		int z = 0;
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 32; j++) {
				sprites[z] = new Sprite(spriteSheet, j * 48 + 1, i * 48 + 1, 47, 47);
				z++;
			}

		}

		try {
			level = ImageIO.read(getClass().getResource("/colision.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ImageLoader loader = new ImageLoader();
		background = loader.loadImage("/map.PNG");

		int t = JOptionPane.showConfirmDialog(null, "Als PacMan spielen?", "Pacman oder Geist", JOptionPane.YES_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null);

		if (t == 0) {
			System.out.println("pacman");
			String name = JOptionPane.showInputDialog(this, "Name");
			player = new Player(name, 140, 100, 24, 24, Id.player, key);
			client.setConnection(name, JOptionPane.showInputDialog(this, "IPAddress"));
			handler.addEntity(player);
			new Packet00Login(client.getUsername(), player.getX(), player.getY(), "pacman").send(client);
		} else if (t == 1) {
			System.out.println("ghost");
			String name = JOptionPane.showInputDialog(this, "Name");
			ghost = new Ghost(name, 530, 100, 24, 24, Id.ghost, key);
			client.setConnection(name, JOptionPane.showInputDialog(this, "IPAddress"));
			handler.addEntity(ghost);
			new Packet00Login(client.getUsername(), ghost.getX(), ghost.getY(), "ghost").send(client);
		}

		handler.createLevel(level);
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
