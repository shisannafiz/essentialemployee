package game.play;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.main.Game;

public class HUD {

	public static int HEALTH = 100;
	
	private int greenValue = 0;
	
	private int score = 0;
	private int level = 1;
	
	public void tick() {
		HEALTH = Game.clamp(HEALTH, 0, 100);
		greenValue = Game.clamp(greenValue, 0, 255);
		greenValue = HEALTH * 2;
		score++;
	}
	
	public void render(Graphics g) {
		Font f = new Font("arial", 1, 20);
		g.setFont(f);
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		g.setColor(new Color(75, greenValue, 0));
		g.fillRect(15, 15, HEALTH * 2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
				
		g.drawString("Score: " + score, 15, 70);
		g.drawString("Stage: " + Navigator.level, 16, 110);
		g.drawString("Level: " + level, 16, 90);		

		
//		g.setColor(Color.blue);
//		g.fillOval(890, 360, 50, 50);
//		g.setColor(Color.white);
//		g.drawOval(890, 360, 50, 50);
//		g.setColor(Color.white);
//		g.drawOval(897, 367, 35, 35);
//		g.setColor(Color.white);
//		g.drawOval(905, 375, 20, 20);
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return this.level;
	}
}
