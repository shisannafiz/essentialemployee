package game.objects;

import game.main.Game;
import game.main.GameObject;
import game.main.Handler;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Bullet extends GameObject {
	
	private Handler handler;
	Random r = new Random();

	public Bullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = (r.nextInt(5 - -5) + -5);
		velY = 5;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 25, 25);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		//if(y <= 0 || y >= Simulation.HEIGHT - 50) velY *= -1;
		//if(x <= 0 || x >= Simulation.WIDTH - 15) velX *= -1;
		
		if(y >= Game.HEIGHT) handler.removeObject(this);
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.PINK, 25, 25, 0.2f, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.PINK);
		g.fillRect(x, y, 25, 25);
	}

}
