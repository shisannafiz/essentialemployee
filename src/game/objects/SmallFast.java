package game.objects;

import game.main.Game;
import game.main.GameObject;
import game.main.Handler;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmallFast extends GameObject {
	
	Handler handler;

	public SmallFast(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 5;
		velY = 5;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 20, 20);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(x <= 0 || x >= Game.WIDTH - 25) velX *= -1;
		if(y <= 0 || y >= Game.HEIGHT - 60) velY *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.CYAN, 20, 20, 0.08f, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(x, y, 20, 20);
	}

}
