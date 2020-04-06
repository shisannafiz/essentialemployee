package game.main;

import java.awt.Graphics;
import java.util.LinkedList;

import game.objects.ID;
import game.objects.Player;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public void clearEnemies() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			if(tempObject.getID() == ID.Player) {
				object.clear();
				addObject(new Player(tempObject.getX(), tempObject.getY(), ID.Player, this));
			}
		}
	}

}
