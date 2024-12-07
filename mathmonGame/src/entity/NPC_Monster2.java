package entity;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.mathmonGamePanel;

public class NPC_Monster2 extends Entity{
	public NPC_Monster2(mathmonGamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 2;
		HP = 150;
		minDamage = 6;
		maxDamage = 18;
		monNum = 2;
		
		getImage();
	}
	
	public void getImage() {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/npc/ember1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
