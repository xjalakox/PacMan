package tk.jalako.main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import tk.jalako.entity.Entity;
import tk.jalako.entity.Player;
import tk.jalako.tile.Tile;

public class Handler {
	public static List<Entity> entity = new ArrayList<Entity>();
	public static List<Tile> tile = new ArrayList<Tile>();

	public void render(Graphics g) {
		for (Tile ti : tile) {
			ti.render(g);
		}
		for (Entity en : entity) {
			en.render(g);
		}
	}

	public void tick() {
		for (Tile ti : tile) {
			ti.tick();
		}
		for (Entity en : entity) {
			en.tick();
		}
		for(int i = 0; i < entity.size(); i++) {
			if(entity.get(i).isRemoved()) entity.remove(i);
		}
	}

	public void addEntity(Entity en) {
		entity.add(en);
	}
	
	public void removeEntity(Entity e) {
		for(Entity en: entity) {
			if(e.equals(en)) en.remove();
		}
	}

	public void addTile(Tile ti) {
		tile.add(ti);
	}

	public void createLevel() {

	}

	public void removePlayer(String username) {
		if (!username.equals(Game.player.getUsername())) {
			for (Entity en : entity) {
				if (en.getId() == Id.player) if(username.equals(((Player)en).getUsername())) en.remove();
			}
		}
	}
	
	public Entity getPlayer(String username) {
		for (Entity entity : Handler.entity) {
			if (entity.getId() == Id.player)
				if (((Player) entity).getUsername().equals(username)) {
					return entity;
				}
		}
		return null;
	}

	public void setPlayerPosition(String username, int x, int y) {
		if (!username.equals(Game.player.getUsername()))
			((Player) getPlayer(username)).setPosition(x, y);
	}

}
