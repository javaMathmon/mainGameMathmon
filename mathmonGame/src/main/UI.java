package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Formatter;

import object.OBJ_PowerSurge;
import object.OBJ_WoodPanel;

public class UI {
	mathmonGamePanel gp;
	Font gameText;
	BufferedImage powerSurgeImage, woodPanelImage;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter;
	public boolean monsterFound = false;
	public boolean gameFinished = false;
	public boolean playerWin = false;
	
	double playTime;
	
	public UI(mathmonGamePanel gp) {
		this.gp = gp;
		
		gameText = new Font("Arial", Font.PLAIN, 20);
		OBJ_PowerSurge powerSurge = new OBJ_PowerSurge();
		powerSurgeImage = powerSurge.image;
		
		OBJ_WoodPanel woodPanel = new OBJ_WoodPanel();
		woodPanelImage = woodPanel.image;
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
		
	}
	
	public void draw(Graphics2D g2) {
		
		if(gameFinished == true) {
			String text;
			int textLength;
			int x;
			int y;
			
			if(playerWin == true) {
				text = "Stage finished";
			}else {
				text = "Game over";
			}
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 - (gp.tileSize*3);
			g2.drawString(text, x, y);
			
			text = "Total time: " + playTime;
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 - (gp.tileSize*2);
			g2.drawString(text, x, y);
			
			gp.gameThread = null;
		}else {
			g2.setFont(gameText);
			g2.setColor(Color.white);
			g2.drawImage(woodPanelImage, 600, 10, 50, 100, null);
			g2.drawImage(powerSurgeImage, 640, 20, gp.tileSize, gp.tileSize, null);
			g2.drawString("" + gp.player.powerSurge, 660, 50);
			
			//Time
			playTime += (double)1/60;
			Formatter format = new Formatter();
			format.format("%.2f", playTime%60);
			String playMin, playSec;
			if(playTime/60 < 10) {
				playMin = "Time: 0" + (int)playTime/60;
			}else {
				playMin = "Time: " + (int)playTime/60;
			}
			if(playTime%60 < 10) {
				playSec = ":0" + format;
			}else {
				playSec = ":" + format;
			}
			g2.drawString(playMin + playSec, 50, 90);
			
			//Message
			if(messageOn == true) {
				g2.drawString(message, 660, 90);
				//Thread.sleep(1000);
				messageCounter++;
				
				if(messageCounter > 120) {
					messageCounter = 0;
					messageOn = false;
				}
			}
		}
	}
}
