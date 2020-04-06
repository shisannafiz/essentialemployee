package game.play;

import game.main.Game;
import game.main.Game.STATE;
import game.main.GameObject;
import game.main.Handler;
import game.objects.ID;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	
	Game game;
		
	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
		
		this.game = game;
				
		keyDown[0] = false; //W
		keyDown[1] = false; //A
		keyDown[2] = false; //S
		keyDown[3] = false; //D
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player) {
				if(key == KeyEvent.VK_W) { tempObject.setVelY(-5); keyDown[0] = true; }
				if(key == KeyEvent.VK_A) { tempObject.setVelX(-5); keyDown[1] = true; }
				if(key == KeyEvent.VK_S) { tempObject.setVelY(5); keyDown[2] = true; }
				if(key == KeyEvent.VK_D) { tempObject.setVelX(5); keyDown[3] = true; }
			}
		}
		if(key == KeyEvent.VK_P) {
			if(game.gameState == STATE.Game) {
				if(Game.paused) Game.paused = false;
				else Game.paused = true;	
			}
	
		}
		if(key == KeyEvent.VK_SPACE) HUD.HEALTH = 200;
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player) {
				if(key == KeyEvent.VK_W) keyDown[0] = false; 
				if(key == KeyEvent.VK_A) keyDown[1] = false; 
				if(key == KeyEvent.VK_S) keyDown[2] = false; 
				if(key == KeyEvent.VK_D) keyDown[3] = false; 
				
				//vertical movement
				if(!keyDown[0] && !keyDown[2]) tempObject.setVelY(0); 
				
				//horizontal movement
				if(!keyDown[1] && !keyDown[3]) tempObject.setVelX(0);
			}
		}
	}
}
