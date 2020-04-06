package game.objects;

import game.main.Game;
import game.main.GameObject;
import game.main.Handler;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Particle extends GameObject {
	
	private Handler handler;
	
	Random r = new Random();
	
	private Color clr;
	
	public Particle(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = (r.nextInt(1 - -1) + -1);
		velY = (r.nextInt(1 - -1) + -1);
		if(velX == 0) velX = 1;
		if(velY == 0) velY = 1;
		
		clr = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 50) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 15) velX *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, clr, 16, 16, 0.1f, handler));
	}

	public void render(Graphics g) {
		g.setColor(clr);
		g.fillRect(x, y, 16, 16);
	}

}
