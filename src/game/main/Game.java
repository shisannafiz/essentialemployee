package game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

import game.objects.ID;
import game.objects.Particle;
import game.play.HUD;
import game.play.KeyInput;
import game.play.Navigator;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 6722640387316353634L;
	
//	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	public static final int WIDTH = 1000, HEIGHT = WIDTH / 10 * 5;

	private Thread thread;
	private boolean running = false;
	public static boolean paused = false;
	
	private Handler handler;
	private HUD hud;
	private Navigator nav;
	private Menu menu;
	private Random r;
	
	public enum STATE {
		Menu,
		Help,
		Game,
		End
	}
	
	public STATE gameState = STATE.Menu;
		
	public Game() {
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);

		new Window(WIDTH, HEIGHT, "Essential Employee", this);
		
		nav = new Navigator(handler, hud); 
		r = new Random();
		
//		if(gameState == STATE.Game) {
//
//		} else {
//			for(int i = 0; i < 20; i++) {
//				handler.addObject(new Particle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.Particle, handler));
//			}
//		}
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running) {
        	long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >=1) {
            	tick();
                delta--;
            }
            if(running)
                render();
            	frames++;
            
            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
//                System.out.println("FPS: "+ frames);
                frames = 0;
            }
        }
        stop();
    }
	
	private void tick() {
		
		if(gameState == STATE.Game) {
			if(!paused) {
				hud.tick();
				nav.tick();
				handler.tick();
				
				if(HUD.HEALTH <= 0) {
					HUD.HEALTH = 100;
					gameState = STATE.End;
					handler.clearEnemies();
					for(int i = 0; i < 20; i++) {
						handler.addObject(new Particle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.Particle, handler));
					}
				}
			}
		} else if(gameState == STATE.Menu || gameState == STATE.End) {
			menu.tick();
			handler.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
//		g.setColor(Color.WHITE);
//		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		if(paused) {
			Font f = new Font("arial", 1, 30);
			g.setFont(f);
			g.setColor(Color.WHITE);
			g.drawString("PAUSED", 440, 230);                 
		}
				
		if(gameState == STATE.Game) {
			hud.render(g);
			nav.render(g);
		} else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End) {
			menu.render(g);
		} 
		
		g.dispose();
		bs.show();
	}
	
	//limit a variable between a min and a max
	public static int clamp(int var, int min, int max) {
		if(var >= max) return var = max;
		else if(var <= min) return var = min;
		else return var;
	}
	
	public static void main(String[] args) {
		new Game();
	}

}
