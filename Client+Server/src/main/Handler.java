package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import tile.Collision;
import tile.Corner_bottomleft;
import tile.Corner_bottomright;
import tile.Corner_topleft;
import tile.Corner_topright;
import tile.Points;
import tile.Tile;
import tile.Wall_bottom;
import tile.Wall_left;
import tile.Wall_right;
import tile.Wall_top;
import tile.spawn_bottom;
import tile.spawn_cornerbottomleft;
import tile.spawn_cornerbottomright;
import tile.spawn_cornertopleft;
import tile.spawn_cornertopright;
import tile.spawn_left;
import tile.spawn_openingleft;
import tile.spawn_openingright;
import tile.spawn_right;
import tile.spawn_top;
import entity.Entity;
import entity.Ghost;
import entity.Player;

public class Handler {
	public static List<Entity> entity = new ArrayList<Entity>();
	public static List<Tile> tile = new ArrayList<Tile>();
	private int t;

	public void render(Graphics g) {
		for (Tile ti : tile) {
			ti.render(g);
		}
		for (Entity en : entity) {
			en.render(g);
		}
	}

	public void tick() {
		t = 0;
		for (Tile ti : tile) {
			ti.tick();
			if (ti.getId() == Id.point) {
				t++;
			}
		}
		if(t==0){
			System.out.println("yes");
		}else{
			System.out.println(t);
		}
		for (Entity en : entity) {
			en.tick();
		}
		for (int i = 0; i < entity.size(); i++) {
			if (entity.get(i).isRemoved())
				entity.remove(i);
		}
		for (int i = 0; i < tile.size(); i++) {
			if (tile.get(i).isRemoved())
				tile.remove(i);
		}
	}

	public void addEntity(Entity en) {
		entity.add(en);
		System.out.println(en.getId() + " hinzugefügt");
	}

	public void removeEntity(Entity e) {
		for (Entity en : entity) {
			if (e.equals(en))
				en.remove();
		}
	}

	public void removeTile(Tile ti) {
		for (Tile t : tile) {
			if (t.equals(ti))
				t.remove();
		}
	}

	public void addTile(Tile ti) {
		tile.add(ti);
	}

	public void createLevel(BufferedImage image) {

		for (int y = 0; y < 42; y++) {
			for (int x = 0; x < 42; x++) {
				image.getRGB(x, y);
				int pixel = image.getRGB(x, y);

				int r = (pixel >> 16) & 0xFF;
				int g = (pixel >> 8) & 0xFF;
				int b = (pixel) & 0xFF;

				if (r == 132 && g == 255 && b == 0)
					addTile(new Wall_left(x * 24 + 94, y * 24, 24, 24, Id.Wall, true));
				if (r == 18 && g == 0 && b == 255)
					addTile(new Wall_right(x * 24 + 94, y * 24, 24, 24, Id.Wall, true));
				if (r == 255 && g == 0 && b == 0)
					addTile(new Wall_top(x * 24 + 94, y * 24, 24, 24, Id.Wall, true));
				if (r == 255 && g == 252 && b == 0)
					addTile(new Wall_bottom(x * 24 + 94, y * 24, 24, 24, Id.Wall, true));
				if (r == 240 && g == 0 && b == 255)
					addTile(new Corner_topleft(x * 24 + 94, y * 24, 24, 24, Id.Wall, true));
				if (r == 0 && g == 253 && b == 255)
					addTile(new Corner_topright(x * 24 + 94, y * 24, 24, 24, Id.Wall, true));
				if (r == 255 && g == 204 && b == 0)
					addTile(new Corner_bottomleft(x * 24 + 94, y * 24, 24, 24, Id.Wall, true));
				if (r == 0 && g == 255 && b == 156)
					addTile(new Corner_bottomright(x * 24 + 94, y * 24, 24, 24, Id.Wall, true));
				if (r == 255 && g == 255 && b == 255)
					addTile(new Collision(x * 24 + 94, y * 24, 24, 24, Id.No_Collision, true));
				if (r == 103 && g == 75 && b == 148)
					addTile(new Collision(x * 24 + 94, y * 24, 24, 24, Id.No_Collision, true));
				if (r == 103 && g == 75 && b == 148)
					addTile(new Points(x * 24 + 94 - 24, y * 24 - 24, 48, 48, Id.point, true));

				if (r == 255 && g == 109 && b == 0)
					addTile(new spawn_left(x * 24 + 94, y * 24, 24, 24, Id.Wall, true));
				if (r == 0 && g == 174 && b == 255)
					addTile(new spawn_right(x * 24 + 94, y * 24, 24, 24, Id.Wall, true));
				if (r == 255 && g == 0 && b == 96)
					addTile(new spawn_cornertopleft(x * 24 + 94, y * 24, 24, 24, Id.Wall, true));
				if (r == 255 && g == 85 && b == 0)
					addTile(new spawn_cornertopright(x * 24 + 94, y * 24, 24, 24, Id.Wall, true));
				if (r == 0 && g == 255 && b == 126)
					addTile(new spawn_cornerbottomleft(x * 24 + 94, y * 24, 24, 24, Id.Wall, true));
				if (r == 216 && g == 255 && b == 0)
					addTile(new spawn_cornerbottomright(x * 24 + 94, y * 24, 24, 24, Id.Wall, true));
				if (r == 138 && g == 0 && b == 255)
					addTile(new spawn_top(x * 24 + 94, y * 24, 24, 24, Id.Wall, true));
				if (r == 255 && g == 0 && b == 150)
					addTile(new spawn_bottom(x * 24 + 94, y * 24, 24, 24, Id.Wall, true));
				if (r == 0 && g == 255 && b == 222)
					addTile(new spawn_openingleft(x * 24 + 94, y * 24, 24, 24, Id.Wall, true));
				if (r == 0 && g == 234 && b == 255)
					addTile(new spawn_openingright(x * 24 + 94, y * 24, 24, 24, Id.Wall, true));

			}
		}
	}

	public void removePlayer(String username) {
		if (!username.equals(Game.player.getUsername())) {
			for (Entity en : entity) {
				if (en.getId() == Id.player) {
					if (username.equals(((Player) en).getUsername())) {
						en.remove();
					}
				}
				if (en.getId() == Id.player) {
					if (username.equals(((Player) en).getUsername())) {
						en.remove();
					}
				}
			}
		}
	}

	public Entity getPlayer(String username) {
		/*
		 * for (Entity entity : Handler.entity) { if (entity.getId() ==
		 * Id.player) { if (((Player) entity).getUsername().equals(username)) {
		 * return entity; } } if (entity.getId() == Id.ghost) { if (((Ghost)
		 * entity).getUsername().equals(username)) { return entity; } } }
		 */
		for (Entity e : Handler.entity) {
			if (e.getUsername().equalsIgnoreCase(username)) {
				return e;
			}
		}
		return null;
	}

	public Id getPlayerId(String username) {
		for (Entity e : Handler.entity) {
			if (e.getUsername().equalsIgnoreCase(username)) {
				return e.getId();
			}
		}
		return null;
	}

	public void setPlayerPosition(String username, int x, int y) {
		if (Game.player != null && !username.equals(Game.player.getUsername())) {
			getPlayer(username).setPosition(x, y);
			// System.out.println("1:Set Pos of " + username + "(" +
			// getPlayer(username).getId() + ")");
		}
		if (Game.ghost != null && !username.equals(Game.ghost.getUsername())) {
			getPlayer(username).setPosition(x, y);
			// System.out.println("2:Set Pos of " + username + "(" +
			// getPlayer(username).getId() + ")");
		}
	}

	public void setPlayerKeyinputEnabled(String username, boolean b) {
		for (Entity entity : Handler.entity) {
			entity.setMovementEnabled(b);
		}

	}

}
