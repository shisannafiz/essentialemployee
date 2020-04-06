package game.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import game.main.Game;
import game.main.GameObject;
import game.main.Handler;

public class Boss extends GameObject {
	
	private Handler handler;
	Random r = new Random();
	
	private int timer = 160;
	private int timer2 = 50;

	public Boss(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 0;
		velY = 1;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 96, 96);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(timer <= 0) velY = 0;
		else timer--;
		
		if(timer <= 0) timer2--;
		if(timer2 <= 0) {
			if(velX == 0) velX = 9;
			
//			if(velX > 0) velX += 1;
//			else if(velX < 0) velX -= 0.5;
			int spawn = r.nextInt(10);
			if(spawn == 0) handler.addObject(new Bullet(x + 48, y + 48, ID.Bullet, handler));
		}
		   
//		if(y <= 0 || y >= Simulation.HEIGHT - 50) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 100) velX *= -1;
//		
//		handler.addObject(new Trail(x, y, ID.Trail, Color.GREEN, 48, 48, 0.1f, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, 96, 96);
//		g.setColor(Color.PINK);
//		g.drawRect(x, y, 96, 96);
	}

}
