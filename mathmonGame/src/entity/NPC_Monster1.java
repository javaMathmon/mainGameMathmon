package entity;

import java.io.IOException;

import javax.imageio.ImageIO;
import main.mathmonGamePanel;

public class NPC_Monster1 extends Entity{

	public NPC_Monster1(mathmonGamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 2;
		HP = 120;
		minDamage = 10;
		maxDamage = 25;
		monNum = 1;
		
		getImage();
	}
	
	public void getImage() {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/npc/terra1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
