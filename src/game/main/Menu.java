package game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Random;

import game.main.Game.STATE;
import game.objects.BigSlow;
import game.objects.ID;
import game.objects.MedNormal;
import game.objects.Player;
import game.objects.SmallFast;
import game.play.HUD;


public class Menu extends MouseAdapter{

	private Game game;
	private HUD hud;
	private Handler handler;
	private Random r = new Random();
//	private char c = U+1F9C0;
//	private char cheese = new String(Character.toChars(u1f9c0));
//	String s = Character.toString((char)c);

	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.hud = hud;
		this.handler = handler;
	}
		
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.Menu) {
			//play button
			if(mouseOver(mx, my, 145, 140, 208, 64)) {
				game.gameState = STATE.Game;
//				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.addObject(new Player(75, 125, ID.Player, handler));
				handler.clearEnemies();
//				for(int i = 0; i < 15; i++) {
//					handler.addObject(new BigSlow(r.nextInt(Game.WIDTH - 55), r.nextInt(Game.HEIGHT - 85), ID.MedNormal, handler));
//				}
			}
			
			//help button
			if(mouseOver(mx, my, 145, 230, 208, 64)) {
				game.gameState = STATE.Help;
			}
			
			//quit button
			if(mouseOver(mx, my, 145, 320, 208, 64)) {
				System.exit(1);
			}
		}
		
		//back button for help
		if(game.gameState == STATE.Help) {
			if(mouseOver(mx, my, 145, 340, 208, 64)) {
				game.gameState = STATE.Menu;
				return;
			}
		}
		
		if(game.gameState == STATE.End) {
			//try again button
			if(mouseOver(mx, my, 650, 230, 200, 64)) {
				game.gameState = STATE.Game;
				hud.setLevel(0);
				hud.setScore(0);
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemies();
			}
			//main menu button
			if(mouseOver(mx, my, 650, 320, 200, 64)) {
				game.gameState = STATE.Menu;
				hud.setLevel(0);
				hud.setScore(0);
				handler.clearEnemies();
			}
		}
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			} else return false;
		} else return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if(game.gameState == STATE.Menu) {
			Font f = new Font("arial", 1, 40);
			Font f2 = new Font("arial", 2, 20);
			Font f3 = new Font("arial", 1, 25);
			Font f4 = new Font("arial", 4, 17);
			Font f5 = new Font("arial", 1, 18);
						
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Game.WIDTH/2, Game.HEIGHT);
			
			g.setFont(f);
			g.setColor(Color.WHITE);
			g.drawString("Essential Employee", 63, 80);
		
			g.setFont(f2);
			g.setColor(Color.WHITE);
			g.drawString("Practice Social Distancing", 135, 110);
			
			g.setFont(f3);
			g.drawRect(144, 139, 210, 66);
			g.drawRect(145, 140, 208, 64);
			g.drawString("Play", 225, 180);
			
			g.drawRect(144, 229, 210, 66);
			g.drawRect(145, 230, 208, 64);
			g.drawString("Help", 225, 270);
			
			g.drawRect(144, 319, 210, 66);
			g.drawRect(145, 320, 208, 64);
			g.drawString("Quit", 225, 360);
			
			g.setFont(f4);
			g.drawString("Created by Shisan Nafiz ø", 153, 425);
			
			g.setFont(f5);
			g.setColor(Color.BLACK);
			g.drawString("We are in the midst of a pandemic", 525, 80); 
			g.drawString("You are an essential employee at a hospital ", 525, 130);
			g.drawString("where the infected are treated", 525, 150);
			g.drawString("You have to go to the hospital, do your work,", 525, 200);
			g.drawString("and come back home all while avoiding contact ", 525, 220);
			g.drawString("with those who are carrying the virus", 525, 240);
			g.drawString("Everyone contracted the virus except for you", 525, 290);
			g.drawString("except for you", 525, 310);
			g.drawString("No one can be trusted including friends and family", 525, 360);
			g.drawString("Stay distant and stay clean!", 525, 410); 
			
//			g.setColor(Color.WHITE);
//			g.drawRect(Game.WIDTH/4, 0, 1, 500);
//			g.drawRect(Game.WIDTH/4 - 185, 85, 370, 1);
//			g.drawRect(Game.WIDTH/4 - 115, 120, 230, 1);
//			g.drawRect(Game.WIDTH/4 - 25, 185, 50, 1);		
//			g.drawRect((Game.WIDTH/4) - 105, 215, 210, 1);
//			g.drawRect(Game.WIDTH/4 - 100, 430, 200, 1);
			
		} else if(game.gameState == STATE.Help) {
			Font f = new Font("arial", 1, 55);
			Font f2 = new Font("arial", 1, 25);
			Font f3 = new Font("arial", 1, 22);
			Font f4 = new Font("arial", 1, 20);
			
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, Game.WIDTH/2, Game.HEIGHT);
			
			g.setFont(f);
			g.setColor(Color.BLACK);
			g.drawString("Help", 185, 95);
			
			g.setFont(f3);
			g.drawString("W - Up", 200, 150);
			g.drawString("S - Down", 200, 180);
			g.drawString("A - Left", 200, 210);
			g.drawString("D - Right", 200, 240);
			g.drawString("P - Pause", 200, 270);
			g.drawString("X - Action", 200, 300);
			
			g.setFont(f4);
			g.setColor(Color.WHITE);
			g.fillRect(635, 65, 32, 32);
			g.drawString("Player", 720, 90);
			g.setColor(Color.RED);
			g.fillRect(635, 142, 30, 30);
			g.drawString("Medium Normal", 720, 165);
			g.setColor(Color.GREEN);
			g.fillRect(630, 220, 40, 40);
			g.drawString("Big Slow", 720, 247);
			g.setColor(Color.CYAN);
			g.fillRect(638, 310, 20, 20);
			g.drawString("Small Fast", 720, 328);
			g.setColor(Color.YELLOW);
			g.fillRect(635, 380, 25, 25);
			g.drawString("Smart Chaser", 720, 400);
			
			g.setFont(f2);	
			g.setColor(Color.BLACK);
			g.drawRect(144, 339, 202, 66);
			g.drawRect(145, 340, 200, 64);
			g.drawString("Back", 217, 380);
			
//			g.drawRect(Game.WIDTH/4 - 60, 100, 110, 1);
//			g.drawRect(Game.WIDTH/4 - 5, 0, 1, 500);
			
		} else if(game.gameState == STATE.End) {
			Font f = new Font("arial", 1, 70);
			Font f2 = new Font("arial", 1, 25);
			Font f3 = new Font("arial", 1, 25);
			Font f4 = new Font("arial", 1, 40);
			
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Game.WIDTH/2, Game.HEIGHT);
			
			
			g.setFont(f);
			g.setColor(Color.WHITE);
			g.drawString("Game Over", 60, 150);
			
			g.setFont(f2);
			g.setColor(Color.RED);
			g.drawString("You've been infected.. and died..", 60, 200);
			
			g.setFont(f3);
			g.setColor(Color.WHITE);
			g.drawString("You lost with a score of: " + hud.getScore(), 80, 300);
			g.drawString("You reached level: " + hud.getLevel(), 80, 340);
			
			g.setFont(f4);
			g.setColor(Color.BLACK);
			g.drawString("Thank You", 640, 110);
			g.drawString("For Playing!", 630, 160);
			
			g.setFont(f2);	
			g.drawRect(649, 229, 202, 66);
			g.drawRect(650, 230, 200, 64);

			g.drawString("Try Again", 692, 272);
			
			g.setFont(f2);	
			g.drawRect(649, 319, 202, 66);
			g.drawRect(650, 320, 200, 64);
			g.drawString("Main Menu", 685, 362);
			
//			g.drawRect(Game.WIDTH/4 - 5, 0, 1, 500);
//			g.drawRect(Game.WIDTH/4 - 185, 160, 370, 1);			
//			g.drawRect((Game.WIDTH/4)*3, 0, 1, 500);
//			g.drawRect(((Game.WIDTH/4)*3) - 60, 310, 120, 1);
		
		}
	}
	
}
