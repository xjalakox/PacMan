package main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import entity.Entity;
import entity.Player;
import tile.Tile;
import tile.asd;

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
		for (int i = 0; i < entity.size(); i++) {
			if (entity.get(i).isRemoved())
				entity.remove(i);
		}
	}

	public void addEntity(Entity en) {
		entity.add(en);
	}

	public void removeEntity(Entity e) {
		for (Entity en : entity) {
			if (e.equals(en))
				en.remove();
		}
	}

	public void addTile(Tile ti) {
		tile.add(ti);
	}

	public void createLevel() {
		//addTile(new asd(24,24, 24, 24, Id.testtile, false, (long) 0));
		generateLevel("map.json");
		//addTile(new asd(24,24, 48,48, Id.testtile, false, (long) 410));
		//addTile(new asd(72,24, 48,48, Id.testtile, false, (long) 411));
		//addTile(new asd(120,24, 48,48, Id.testtile, false, (long) 442));
		//addTile(new asd(168,24, 48,48, Id.testtile, false, (long) 412));
		//addTile(new asd(216,24, 48,48, Id.testtile, false, (long) 441));
		//addTile(new asd(264,24, 48,48, Id.testtile, false, (long) 409));
	}

	private void generateLevel(String path) {
		int a = 0;
		int b = 0;

		JSONObject levelData = JSONDecoder.loadData(path);

		for (int j = 0; j <= 2; j++) {
			JSONArray data = (JSONArray) ((JSONObject) ((JSONArray) levelData.get("layers")).get(j)).get("data");

			for (int i = 0; i < data.size(); i++) {
				long ids = (long) data.get(i);
				if (i % 100 == 0) {
					b++;
					a = 0;
				}

				if(ids==0){
					System.out.println("FUCK YOU LITTLE CUNT YOU JUST COSTED ME 1 HOUR OF MY FUCKING TIME");
				}else{
					addTile(new asd(a * 24, b * 24, 24, 24, Id.testtile, false, (long) data.get(i)-1));
			
				}
				a++;
			}
			b = 0;

		}
	}

	public void removePlayer(String username) {
		if (!username.equals(Game.player.getUsername())) {
			for (Entity en : entity) {
				if (en.getId() == Id.player){
					if (username.equals(((Player) en).getUsername())){
						en.remove();
					}
				}
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
