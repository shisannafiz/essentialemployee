package game.objects;

import game.main.GameObject;
import game.main.Handler;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartChaser extends GameObject {
	
	Handler handler;
	private GameObject player;

	public SmartChaser(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getID() == ID.Player) player = handler.object.get(i);
		}
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		float diffX = x - player.getX() - 8;
		float diffY = y - player.getY() - 8;
		int distance = (int) Math.sqrt(Math.pow((x-player.getX()), 2) + Math.pow((y-player.getY()), 2));

		velX = (int) Math.round(((-1.0/distance) * diffX));
		velY = (int) Math.round(((-1.0/distance) * diffY));
				
		handler.addObject(new Trail(x, y, ID.Trail, Color.YELLOW, 24, 24, 0.05f, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, 25, 25);
	}

}
