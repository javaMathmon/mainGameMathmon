package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.mathmonGamePanel;

public class Entity {
	
	//public int x, y;
	mathmonGamePanel gp;
	public int worldX, worldY;
	public int speed;
	public int HP;
	public int baseDamage, minDamage, maxDamage;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public Rectangle solidArea;
	
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	
	public Entity(mathmonGamePanel gp) {
		this.gp = gp;
	}
}