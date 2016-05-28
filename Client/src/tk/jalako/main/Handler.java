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
	
	public void render(Graphics g){
		for(Tile ti:tile){
			ti.render(g);
		}
		for(Entity en:entity){
			en.render(g);
		}
	}
	
	public void tick(){
		for(Tile ti:tile){
			ti.tick();
		}
		for(Entity en:entity){
			en.tick();
		}
	}
	
	public void addEntity(Entity en){
		entity.add(en);
	}
	
	public void addTile(Tile ti) {
		tile.add(ti);
	}
	
	public void createLevel(){
		addEntity(new Player(250,250,64,64,Id.player,Game.key));
	}

}
