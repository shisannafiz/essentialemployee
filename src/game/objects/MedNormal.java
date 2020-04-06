package game.objects;

import game.main.Game;
import game.main.GameObject;
import game.main.Handler;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class MedNormal extends GameObject {
	
	Handler handler;

	public MedNormal(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 5;
		velY = 5;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 30, 30);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(x <= 0 || x >= Game.WIDTH - 35) velX *= -1;
		if(y <= 0 || y >= Game.HEIGHT - 70) velY *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.RED, 30, 30, 0.1f, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, 30, 30);
	}

}
