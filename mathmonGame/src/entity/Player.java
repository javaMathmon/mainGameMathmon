package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import battle.mathmonBattle;
import main.KeyHandler;
import main.mathmonChooseAPlayer;
import main.mathmonGamePanel;


public class Player extends Entity{
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	public int powerSurge, healingPotion, timeWarp, lives, level;
	public mathmonChooseAPlayer playerPic = new mathmonChooseAPlayer();
	public Player(mathmonGamePanel gp, KeyHandler keyH) {
		super(gp);
		this.keyH =keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		HP = 100;
		lives = 3;
		level = 1;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 3;
		direction = "down";
	}
	public void getPlayerImage() {
		
			if (mathmonChooseAPlayer.selectedPlayer == "Liora") {
				try {
					up1 = ImageIO.read(getClass().getResourceAsStream("/player/girl_up_1.png"));
					up2 = ImageIO.read(getClass().getResourceAsStream("/player/girl_up_2.png"));
					down1 = ImageIO.read(getClass().getResourceAsStream("/player/girl_down_1.png"));
					down2 = ImageIO.read(getClass().getResourceAsStream("/player/girl_down_2.png"));
					left1 = ImageIO.read(getClass().getResourceAsStream("/player/girl_left_1.png"));
					left2 = ImageIO.read(getClass().getResourceAsStream("/player/girl_left_2.png"));
					right1 = ImageIO.read(getClass().getResourceAsStream("/player/girl_right_1.png"));
					right2 = ImageIO.read(getClass().getResourceAsStream("/player/girl_right_2.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}if (mathmonChooseAPlayer.selectedPlayer == "Axion") {
				try {
					up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
					up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
					down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
					down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
					left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
					left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
					right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
					right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	
	public void update() {
		if(keyH.upPressed == true || keyH.downPressed == true 
				|| keyH.leftPressed == true || keyH.rightPressed == true) {
			if (keyH.upPressed == true) {
				direction = "up";
			}else if (keyH.downPressed == true) {
				direction = "down";
			}else if (keyH.leftPressed == true) {
				direction = "left";
			}else if (keyH.rightPressed == true) {
				direction = "right";
			}
			
			// Check tile collision
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			// Check object collision
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			//Check NPC collision
			int npcIndex = gp.cChecker.checkEntity(this, gp.monster);
			interactNPC(npcIndex);
			
			//if collision is false, player can move
			if(collisionOn == false) {
				switch(direction) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
				}
			}
			
			spriteCounter++;
			if (spriteCounter >10) {
				if (spriteNum ==1) {
					spriteNum = 2;
				}else if (spriteNum ==2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}/*else {
			spriteNum = 1;
		}*/ // para daw nakatayo lang pag nakatigil 
	}
	
	public void pickUpObject(int index) {
		if(index != 999) {
			String objectName = gp.obj[index].name;
			
			switch(objectName) {
			case("Power Surge"):
				powerSurge++;
				gp.obj[index] = null;
				//System.out.println("Power Surge: " + powerSurge);
				gp.ui.showMessage("Power Surge found!");
				break;
			case("Healing Potion"):
				healingPotion++;
				gp.obj[index] = null;
				//System.out.println("Healing Potion: " + healingPotion);
				gp.ui.showMessage("Healing Potion found!");
				break;
			case("Time Warp"):
				timeWarp++;
				gp.obj[index] = null;
				//System.out.println("Time Warp: " + timeWarp);
				gp.ui.showMessage("Time Warp found!");
				break;
			}
			gp.playSE(4);
		}
	}
	
	public void interactNPC(int i) {
		if(i != 999) {
			mathmonBattle newBattle = null;
			try {
				newBattle = new mathmonBattle(gp, i, Math.abs(i%4), this);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			gp.gameState = gp.pauseState;
			gp.music.stop();
			newBattle.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			//newBattle.startBattleThread();
			newBattle.setVisible(true);
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if (spriteNum==1) {
				image = up1;
			}if (spriteNum==2) {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum==1) {
				image = down1;
			}if (spriteNum==2) {
				image = down2;
			}	
			break;
		case "left":
			if (spriteNum==1) {
				image = left1;
			}if (spriteNum==2) {
				image = left2;
			}
			break;
		case "right":
			if (spriteNum==1) {
				image = right1;
			}if (spriteNum==2) {
				image = right2;
			}
			break;
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	
	}
}