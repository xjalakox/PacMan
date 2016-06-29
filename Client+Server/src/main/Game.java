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
import entity.Entity;
import entity.Ghost;
import entity.Player;
import graphics.ImageLoader;
import graphics.Sprite;
import graphics.SpriteSheet;
import network.Client;
import network.packets.Packet00Login;
import network.packets.Packet02Move;
import sound.SoundManager;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 300;
	public static final int HEIGHT = 250;
	public static final int SCALE = 4;

	private int fps, ups;
	private BufferedImage background;

	private boolean running = false;
	private Thread thread;
	private int frames;

	public static Handler handler = new Handler();
	public static Sprite[] playerSprite = new Sprite[4];
	public static Sprite[] ghostSprite = new Sprite[32];
	public static KeyInput key = new KeyInput();
	public static Player player;
	public static Ghost ghost;
	public static String username;

	public static Sprite[] sprites = new Sprite[641];

	private SpriteSheet spriteSheet;

	public static Client client;
	private BufferedImage level;
	private String choice;
	private boolean pacman;
	private String name;

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
		for (Entity e : Handler.entity) {
			if (e.getId() == Id.player) {
				new Packet02Move(((Player) e).getUsername(), ((Player) e).getX(), ((Player) e).getY()).send(client);
			}
			if (e.getId() == Id.ghost) {
				new Packet02Move(((Ghost) e).getUsername(), ((Ghost) e).getX(), ((Ghost) e).getY()).send(client);
			}
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
		g.fillRect(0, 0, 300 * 4, 250 * 4);
		g.drawRect(0, 0, WIDTH * SCALE + 100, HEIGHT * SCALE + 100);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH * SCALE + 100, HEIGHT * SCALE + 100);
		handler.render(g);

		g.setColor(Color.BLUE);
		g.setFont(new Font("Verdana", Font.BOLD, 23));

		g.dispose();
		bs.show();
	}

	public void init() {
		SoundManager sm = new SoundManager();
		sm.playSound(3);
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

		if (pacman) {
			player = new Player(name, 140, 100, 24, 24, Id.player, key);
			client.setConnection(name, "80.82.219.161:1337");
			handler.addEntity(player);
			new Packet00Login(client.getUsername(), player.getX(), player.getY(), "pacman").send(client);
		} else if (!pacman) {
			ghost = new Ghost(name, 530, 100, 24, 24, Id.ghost, key);
			client.setConnection(name, "80.82.219.161:1337");
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
		double delta = 0;
		double ns = 1000000000.0 / 60.0;
		int ticks = 0;
		long timer = System.currentTimeMillis();
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
			}
		}
		stop();
	}

	public Game(boolean pacman, String name) {
		this.pacman = pacman;
		this.name = name;
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
