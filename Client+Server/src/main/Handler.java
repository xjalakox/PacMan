package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import tile.Collision;
import tile.Corner_bottomleft;
import tile.Corner_bottomright;
import tile.Corner_topleft;
import tile.Corner_topright;
import tile.Tile;
import tile.Wall_bottom;
import tile.Wall_left;
import tile.Wall_right;
import tile.Wall_top;
import entity.Entity;
import entity.Player;

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

	public void createLevel(BufferedImage image) {
		//generateLevel("map.json");
		
		
		for(int y=0; y<42; y++){
            for(int x=0;x<42;x++)
            {
                image.getRGB(x,y);
                int pixel = image.getRGB(x, y);
                 
                int r = (pixel >> 16) & 0xFF;
                int g = (pixel >> 8) & 0xFF;
                int b = (pixel) & 0xFF;
                
                if(r==132&&g==255&&b==0) addTile(new Wall_left(x*24+94,y*24,24,24,Id.Wall,true));
                if(r==18&&g==0&&b==255) addTile(new Wall_right(x*24+94,y*24,24,24,Id.Wall,true));
                if(r==255&&g==0&&b==0) addTile(new Wall_top(x*24+94,y*24,24,24,Id.Wall,true));
                if(r==255&&g==252&&b==0) addTile(new Wall_bottom(x*24+94,y*24,24,24,Id.Wall,true));
                if(r==240&&g==0&&b==255) addTile(new Corner_topleft(x*24+94,y*24,24,24,Id.Wall,true));
                if(r==0&&g==253&&b==255) addTile(new Corner_topright(x*24+94,y*24,24,24,Id.Wall,true));
                if(r==255&&g==204&&b==0) addTile(new Corner_bottomleft(x*24+94,y*24,24,24,Id.Wall,true));
                if(r==0&&g==255&&b==156) addTile(new Corner_bottomright(x*24+94,y*24,24,24,Id.Wall,true));
                if(r==255&&g==255&&b==255) addTile(new Collision(x*24+94,y*24,24,24,Id.No_Collision,true));
                
                
//                if(r==255&&g==109&&b==0) addTile(new spawn_left(x*24+94,y*24,24,24,Id.Collision,true));
//                if(r==0&&g==174&&b==255) addTile(new spawn_right(x*24+94,y*24,24,24,Id.Collision,true));
//                if(r==255&&g==0&&b==96) addTile(new spawn_cornertopleft(x*24+94,y*24,24,24,Id.Collision,true));
//                if(r==255&&g==85&&b==0) addTile(new spawn_cornertopright(x*24+94,y*24,24,24,Id.Collision,true));
//                if(r==0&&g==255&&b==126) addTile(new spawn_corner_bottomleft(x*24+94,y*24,24,24,Id.Collision,true));
//                if(r==216&&g==255&&b==0) addTile(new spawn_corner_bottomright(x*24+94,y*24,24,24,Id.Collision,true));
//                if(r==138&&g==0&&b==255) addTile(new spawn_top(x*24+94,y*24,24,24,Id.Collision,true));
//                if(r==255&&g==0&&b==150) addTile(new spawn_bottom(x*24+94,y*24,24,24,Id.Collision,true));
//                if(r==0&&g==255&&b==222) addTile(new spawn_openingleft(x*24+94,y*24,24,24,Id.Collision,true));
//                if(r==0&&g==234&&b==255) addTile(new spawn_openingright(x*24+94,y*24,24,24,Id.Collision,true));
                
            }
		}
	}

	private void generateLevel(String path) {
		int a = 0;
		int b = 0;

		JSONObject levelData = JSONDecoder.loadData(path);

		for (int j = 0; j <= 2; j++) {
			JSONArray data = (JSONArray) ((JSONObject) ((JSONArray) levelData.get("layers")).get(j)).get("data");

			for (int i = 0; i < data.size(); i++) {
				long ids = (long) data.get(i);
				if (i % 42 == 0) {
					b++;
					a = 0;
				}

				if(ids!=0){
					//addTile(new asd(b * 24, a * 24, 24, 24, Id.testtile, false, (long) data.get(i)-1));
			
				}
				a++;
			}
			//a = 0; 
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
