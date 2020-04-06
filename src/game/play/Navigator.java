package game.play;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import game.main.Game;
import game.main.Handler;
import game.objects.BigSlow;
import game.objects.Boss;
import game.objects.ID;
import game.objects.MedNormal;
import game.objects.Portal;
import game.objects.SmallFast;
import game.objects.SmartChaser;

public class Navigator {
	
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	public static int count = 0; 
	public static boolean timer = true;
	public static boolean obj = false;	//objective 
	public static boolean time = true;	//dodging only
	public static enum STAGE {
		LeaveHome,	
		WalkToTrain, 	
		WalkToHospital, 	
		WalkToHome, 	
		Train,			
		Move,	
		EnterHospital, 
		toMeet,
		Meet,	
		toField,
		Field,	
		toICU,
		ICU,
		toBreak,
		Break,	
		toOutside,	
		Outside,	
		Victory,	
		GameOver	
	}
	public static STAGE level = STAGE.LeaveHome;
	
	public Navigator(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick() {
		
		if(timer) count++; 
		// ~60 per second 
		//~600 for 10 seconds
		//~900 for 15 seconds
				
		if(count == 50) {
			level = STAGE.LeaveHome;
			obj = true;
		} else if(count == 150) {
			level = STAGE.WalkToTrain;
			obj = true;
		} else if(count == 250) {  
			level = STAGE.Train;
			time = true;
		} else if (count == 850) {
			level = STAGE.WalkToHospital;
			obj = true;
		} else if (count == 950) {
			level = STAGE.EnterHospital;
			obj = true;
		} else if (count == 1050) {
			level = STAGE.Meet;
			obj = true;
		} else if (count == 1650) { 
			level = STAGE.toField;
			obj = true;
		} else if (count == 1750) { //1800
			level = STAGE.Field;
			time = true;
		} else if (count == 3550) {
			level = STAGE.toBreak;
			obj = true;
		}else if (count == 3650) { //1200
			level = STAGE.Break;
			obj = true; 
		} else if (count == 4250) {
			level = STAGE.toICU;
			obj = true;
		}	else if (count == 4350) { //3600
			level = STAGE.ICU;
			time = true;
		} else if (count == 7950) {
			level = STAGE.toMeet;
			obj = true;
		} else if (count == 8050) {
			level = STAGE.Meet;
			obj = true;
		} else if (count == 8650) {
			level = STAGE.toOutside;
			obj = true; 
		} else if (count == 8750) {
			level = STAGE.Outside;
			time = true; 
		} else if(count == 9650) {
			level = STAGE.WalkToTrain;
			obj = true;
		} else if(count == 9750) { //600 
			level = STAGE.Train;
			time = true;
		} else if (count == 10350) {
			level = STAGE.WalkToHome;
		} else if (count == 10400) {
			level = STAGE.Victory;
			obj = true;
		} else if (count == 10500) {
			level = STAGE.GameOver;
			time = true;
		} 
		
		// MOVE - WALK - MEET - BREAK
		if(obj) { 
			obj = false;
			timer = false;
			count += 50;
			hud.setLevel(hud.getLevel() + 1);
			handler.clearEnemies();
			handler.addObject(new Portal(r.nextInt(Game.WIDTH - 100), r.nextInt(Game.HEIGHT - 100), ID.Portal, handler));
			
			if(level == STAGE.Move) {
				
			} else if(level == STAGE.WalkToTrain || level == STAGE.WalkToHospital || level == STAGE.WalkToHome) {
				for(int i = 0; i < 4; i++) { handler.addObject(new MedNormal(r.nextInt(Game.WIDTH - 35), r.nextInt(Game.HEIGHT - 70), ID.MedNormal, handler)); }
			} else if(level == STAGE.Meet) {
				for(int i = 0; i < 12; i++) { handler.addObject(new BigSlow(r.nextInt(Game.WIDTH - 55), r.nextInt(Game.HEIGHT - 85), ID.MedNormal, handler)); }
			} else if(level == STAGE.Break) {
				int i;
				for(i = 0; i < 3; i++) { handler.addObject(new MedNormal(r.nextInt(Game.WIDTH - 35), r.nextInt(Game.HEIGHT - 70), ID.MedNormal, handler)); }
				for(i = 0; i < 5; i++) { handler.addObject(new BigSlow(r.nextInt(Game.WIDTH - 55), r.nextInt(Game.HEIGHT - 85), ID.MedNormal, handler)); }
			}	
		}
		
		//TRAIN - FIELD - ICU - OUT - OVER
		if(time) {
			time = false;
			timer = true;
			hud.setLevel(hud.getLevel() + 1);
			handler.clearEnemies();
		
			 if(level == STAGE.Train) {
				for(int i = 0; i < 8; i++) { handler.addObject(new MedNormal(r.nextInt(Game.WIDTH - 35), r.nextInt(Game.HEIGHT - 70), ID.MedNormal, handler)); }
			 } else if(level == STAGE.Field) {
				int i;
				for(i = 0; i < 3; i++) { handler.addObject(new MedNormal(r.nextInt(Game.WIDTH - 35), r.nextInt(Game.HEIGHT - 70), ID.MedNormal, handler)); }
				for(i = 0; i < 5; i++) { handler.addObject(new BigSlow(r.nextInt(Game.WIDTH - 55), r.nextInt(Game.HEIGHT - 85), ID.MedNormal, handler)); }
				for(i = 0; i < 3; i++) { handler.addObject(new SmallFast(r.nextInt(Game.WIDTH - 25), r.nextInt(Game.HEIGHT - 60), ID.MedNormal, handler)); }
			 } else if(level == STAGE.ICU) {
				int i;
				for(i = 0; i < 4; i++) { handler.addObject(new MedNormal(r.nextInt(Game.WIDTH - 35), r.nextInt(Game.HEIGHT - 70), ID.MedNormal, handler)); }
				for(i = 0; i < 4; i++) { handler.addObject(new BigSlow(r.nextInt(Game.WIDTH - 55), r.nextInt(Game.HEIGHT - 85), ID.MedNormal, handler)); }
				for(i = 0; i < 4; i++) { handler.addObject(new SmallFast(r.nextInt(Game.WIDTH - 25), r.nextInt(Game.HEIGHT - 60), ID.MedNormal, handler)); }
			 } else if(level == STAGE.Outside) {
				for(int i = 0; i < 8; i++) { handler.addObject(new SmartChaser(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartChaser, handler)); }
			 } else if(level == STAGE.GameOver) { handler.addObject(new Boss((Game.WIDTH / 2) - 65, -100, ID.Boss, handler)); } 
		}
				
	}

	public void render(Graphics g) {
		Font f = new Font("arial", 1, 20);
		g.setFont(f);
		g.setColor(Color.WHITE);
		if(level == STAGE.LeaveHome) {
			g.drawString("Leave your house", 15, 450);
//			g.drawString("Go to the hospital", 800, 50);
		} else if(level == STAGE.WalkToTrain) {
			g.drawString("Go inside the train", 15, 450);
		} else if(level == STAGE.Train) {
			g.drawString("Wait until your stop and get out", 15, 450);
		} else if(level == STAGE.WalkToHospital) {
			g.drawString("Enter the hospital", 15, 450);
		} else if(level == STAGE.EnterHospital || level == STAGE.toMeet) {
			g.drawString("Go to the conference room", 15, 450);
		} else if(level == STAGE.Meet) {
			g.drawString("Wait until the meeting is over and leave", 15, 450);
		} else if(level == STAGE.toField) {
			g.drawString("Get on to the field", 15, 450);
		} else if(level == STAGE.Field) {
			g.drawString("Stay on the field until your break", 15, 450);
		} else if(level == STAGE.toBreak) {
			g.drawString("Go to the cafe ", 15, 450);
		} else if(level == STAGE.Break) {
			g.drawString("Take your break", 15, 450);
		} else if(level == STAGE.toICU) {
			g.drawString("Go to the ICU ", 15, 450);
		} else if(level == STAGE.ICU) {
			g.drawString("Stay in the ICU until your shift is over ", 15, 450);
		} else if(level == STAGE.toOutside) {
			g.drawString("Leave the hospital.. but be careful.. your friends are outside", 15, 450);
		} else if(level == STAGE.Outside) {
			g.drawString("Run away from your friends until they leave", 15, 450);
		} else if(level == STAGE.WalkToTrain) {
			g.drawString("Go inside the train", 15, 450);
		} else if(level == STAGE.Train) {
			g.drawString("Wait until your stop and get out", 15, 450);
		} else if(level == STAGE.WalkToHome) {
			g.drawString("Finally go home ", 15, 450);
		} else if(level == STAGE.Victory) {
			g.drawString("YOU WON!", 15, 450);
		} else if(level == STAGE.GameOver) {
			g.drawString("Your significant other is home", 15, 450);
		} 
	}
}

//handler.addObject(new MedNormal(r.nextInt(Game.WIDTH - 35), r.nextInt(Game.HEIGHT - 70), ID.MedNormal, handler));
//handler.addObject(new BigSlow(r.nextInt(Game.WIDTH - 54), r.nextInt(Game.HEIGHT - 88), ID.BigSlow, handler));
//handler.addObject(new SmallFast(r.nextInt(Game.WIDTH - 25), r.nextInt(Game.HEIGHT - 60), ID.SmallFast, handler));
//handler.addObject(new SmartChaser(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartChaser, handler));

