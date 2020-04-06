package game.objects;

import game.main.Game;
import game.main.GameObject;
import game.main.Handler;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Portal extends GameObject {
	
	Handler handler;

	public Portal(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 50, 50);
	}

	public void tick() {

	}

	public void render(Graphics g) {
//		g.setColor(Color.blue);
//		g.fillOval(890, 360, 50, 50);
//		g.setColor(Color.white);
//		g.drawOval(890, 360, 50, 50);
//		g.setColor(Color.white);
//		g.drawOval(897, 367, 35, 35);
//		g.setColor(Color.white);
//		g.drawOval(905, 375, 20, 20);
		g.setColor(Color.WHITE);
		g.fillRect(x, y, 50, 50);
	}

}
