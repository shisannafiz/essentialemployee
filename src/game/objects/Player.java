package game.objects;

import game.main.Game;
import game.main.GameObject;
import game.main.Handler;
import game.play.HUD;
import game.play.Navigator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject {
	
	Random r = new Random();
	Handler handler;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 1, Game.WIDTH - 40);
		y = Game.clamp(y, 0, Game.HEIGHT - 70);
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32, 32, 0.05f, handler));
		
		collision();
	}
	
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.BigSlow || tempObject.getID() == ID.MedNormal || tempObject.getID() == ID.SmallFast || tempObject.getID() == ID.SmartChaser || tempObject.getID() == ID.Bullet || tempObject.getID() == ID.Boss) {
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 2;
				}
			}
			if(tempObject.getID() == ID.Portal) {
				if(getBounds().intersects(tempObject.getBounds())) {
					handler.clearEnemies();
					Navigator.timer = true;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.draw(getBounds());
	
		g.setColor(Color.WHITE);
		g.fillRect(x, y, 32, 32);
	}
}
