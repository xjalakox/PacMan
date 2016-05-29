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

import org.gnet.client.ClientEventListener;
import org.gnet.client.GNetClient;
import org.gnet.client.ServerModel;
import org.gnet.packet.Packet;

import tk.jalako.entity.Entity;
import tk.jalako.entity.Player;
import tk.jalako.graphics.Sprite;
import tk.jalako.network.packets.loginPacket;
import tk.jalako.network.packets.positionPacket;

public class Game extends Canvas implements Runnable {

	public static final int WIDTH = 320;
	public static final int HEIGHT = 180;
	public static final int SCALE = 4;

	private int fps, ups;

	// public static SpriteSheet sheet,sheet2;
	//
	// public static Camera cam;
	//
	// public static Sprite[] player = new Sprite[96];
	// public static Sprite bg;
	private BufferedImage background;

	private boolean running = false;
	private Thread thread;
	private int frames;

	private String pname;

	public static Handler handler;
	public static Sprite[] player = new Sprite[96];
	public static KeyInput key = new KeyInput();
	
	public static Player[] player2 = new Player[25];
	private int playerAnzahl = 0;

	synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this, "Thread");
		thread.start();
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
		handler.tick();
		key.tick();
		for (Entity e : Handler.entity) {
			if (e.getId() == Id.player) {
				// cam.tick(e);
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

		// g2d.translate(cam.getX(), cam.getY());
		handler.render(g);
		// g2d.translate(-cam.getX(), -cam.getY());

		g.setColor(Color.BLUE);
		g.setFont(new Font("Verdana", Font.BOLD, 23));

		g.dispose();
		bs.show();
	}

	public void init() {
		
		initNetwork();
		handler = new Handler();

		// cam = new Camera();
		//
		// sheet = new SpriteSheet("/4LinkGreen1.png");
		// sheet2 = new SpriteSheet("/background_grass.png");
		//
		// bg = new Sprite(sheet2,1,1,21,15);
		//
		// for(int i=0;i<12;i++){
		// player[i]=new Sprite(sheet,i+1,1,2,2);
		// }
		// for(int i=0;i<12;i++){
		// player[i+12]=new Sprite(sheet, i+1,5,2,2);
		// }
		//
		// for(int i=0;i<12;i++){
		// player[i+24]=new Sprite(sheet, i+1,2,2,2);
		// }
		// for(int i=0;i<12;i++){
		// player[i+36]=new Sprite(sheet, i+1,6,2,2);
		// }
		//
		// for(int i=0;i<12;i++){
		// player[i+48]=new Sprite(sheet, i+1,3,2,2);
		// }
		// for(int i=0;i<12;i++){
		// player[i+60]=new Sprite(sheet, i+1,7,2,2);
		// }
		//
		// for(int i=0;i<12;i++){
		// player[i+72]=new Sprite(sheet, i+1,4,2,2);
		// }
		// for(int i=0;i<12;i++){
		// player[i+84]=new Sprite(sheet, i+1,8,2,2);
		// }
		//
		//
		// ImageLoader loader = new ImageLoader();
		// background = loader.loadImage("/background_grass.png");
		key = new KeyInput();
		addKeyListener(key);
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
			//	System.out.println("FPS: " + fps);
			//	System.out.println("Ticks: " + ups);
			}
		}
		stop();
	}
	
	private void initNetwork() {

		final String host = "80.82.219.161";
		final int port = 1337;
		final GNetClient netclient = new GNetClient(host, port);
		
		netclient.setDebugging(false);

		netclient.addEventListener(new ClientEventListener() {
			
			@Override
			protected void packetReceived(ServerModel client, Packet packet) {
				

				if (packet.getPacketName().equals("log")) {

					String username = (String) packet.getEntry("name");
					
					if(username.equals(pname)){
						handler.addEntity(new Player(username, 250,250,64,64,Id.player,Game.key));
					}else{
						handler.addEntity(new Player(username, 250,250,64,64,Id.player,null));
					}
					
					/*if(username.equals(pname)){
						player2[playerAnzahl] = new Player(username, 250,250,64,64,Id.player,Game.key);
						handler.addEntity(player2[playerAnzahl++]);
					}
					else {
						player2[playerAnzahl] = new Player(username, 250, 250, 64, 64, Id.player, null);
						handler.addEntity(player2[playerAnzahl++]);
					}*/
					
					
				}

				if (packet.getPacketName().equals("askForPositionPacket")) {
					
					positionPacket p = new positionPacket(handler.getPlayer(pname).getX(),handler.getPlayer(pname).getY());
					
					
					
					client.sendPacket(p);
					
				}
			}

			@Override
			protected void errorMessage(String arg0) {

			}

			@Override
			protected void debugMessage(String arg0) {

			}

			@Override
			protected void clientDisconnected(ServerModel arg0) {

			}

			@Override
			protected void clientConnected(ServerModel client) {

				pname = JOptionPane.showInputDialog("Name eingeben");

				loginPacket login = new loginPacket(pname);

				client.sendPacket(login);

				positionPacket pPacket = new positionPacket(250, 250);

				client.sendPacket(pPacket);

			}
		});

		netclient.bind();
		netclient.start();

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