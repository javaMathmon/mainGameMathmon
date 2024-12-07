package entity;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.mathmonGamePanel;

public class NPC_Monster3 extends Entity{
	public NPC_Monster3(mathmonGamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 2;
		HP = 100;
		minDamage = 10;
		maxDamage = 20;
		monNum = 3;
		
		getImage();
	}
	
	public void getImage() {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/npc/aqua1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
