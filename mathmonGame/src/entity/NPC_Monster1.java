package entity;

import main.mathmonGamePanel;

public class NPC_Monster1 extends Entity{

	public NPC_Monster1(mathmonGamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 2;
		HP = 100;
		minDamage = 10;
		maxDamage = 20;
	}
}
