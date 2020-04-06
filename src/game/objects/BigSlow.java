package game.objects;

import game.main.Game;
import game.main.GameObject;
import game.main.Handler;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BigSlow extends GameObject {
	
	Handler handler;

	public BigSlow(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 1;
		velY = 1;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 48, 48);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(x <= 0 || x >= Game.WIDTH - 54) velX *= -1;
		if(y <= 0 || y >= Game.HEIGHT - 88) velY *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.GREEN, 48, 48, 0.05f, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, 40, 40);
		g.setColor(Color.GREEN);
		g.drawRect(x, y, 40, 40);
	}

}
