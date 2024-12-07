package entity;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.mathmonGamePanel;

public class NPC_Monster4 extends Entity{
	public NPC_Monster4(mathmonGamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 2;
		HP = 75;
		minDamage = 15;
		maxDamage = 18;
		monNum = 0;
		
		getImage();
	}
	
	public void getImage() {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/npc/sting1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
