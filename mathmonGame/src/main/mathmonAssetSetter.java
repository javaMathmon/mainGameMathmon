package main;

import entity.NPC_Monster1;
import entity.NPC_Monster2;
import entity.NPC_Monster3;
import entity.NPC_Monster4;
import object.OBJ_HealingPotion;
import object.OBJ_PowerSurge;
import object.OBJ_TimeWarp;

public class mathmonAssetSetter {
	
	mathmonGamePanel gp;
	
	public mathmonAssetSetter(mathmonGamePanel gp) {
		this.gp = gp;
	}

	public void setObject() {
		gp.obj[0] = new OBJ_PowerSurge();
		gp.obj[0].worldX = 5 * gp.tileSize;
		gp.obj[0].worldY = 45 * gp.tileSize;
		
		gp.obj[1] = new OBJ_PowerSurge();
		gp.obj[1].worldX = 10 * gp.tileSize;
		gp.obj[1].worldY = 40 * gp.tileSize;
		
		gp.obj[2] = new OBJ_HealingPotion();
		gp.obj[2].worldX = 15 * gp.tileSize;
		gp.obj[2].worldY = 22 * gp.tileSize;
		
		gp.obj[3] = new OBJ_HealingPotion();
		gp.obj[3].worldX = 7 * gp.tileSize;
		gp.obj[3].worldY = 30 * gp.tileSize;
		
		gp.obj[4] = new OBJ_TimeWarp();
		gp.obj[4].worldX = 3 * gp.tileSize;
		gp.obj[4].worldY = 48 * gp.tileSize;
		
		gp.obj[5] = new OBJ_TimeWarp();
		gp.obj[5].worldX = 19 * gp.tileSize;
		gp.obj[5].worldY = 24 * gp.tileSize;
	}
	
	public void setMonster() { //i%4 to so kung anong sagot, yun yung monster dapat.
//		for(int i=0;i<gp.monsterCount;i++) {
//			gp.monster[0] = new NPC_Monster4(gp);
//			gp.monster[i].worldX = 27 * gp.tileSize;
//			gp.monster[0].worldY = 20 * gp.tileSize;
//		}
		gp.monster[0] = new NPC_Monster4(gp);
		gp.monster[0].worldX = 19 * gp.tileSize;
		gp.monster[0].worldY = 8 * gp.tileSize;
		
		gp.monster[1] = new NPC_Monster1(gp);
		gp.monster[1].worldX = 27 * gp.tileSize;
		gp.monster[1].worldY = 20 * gp.tileSize;
		
		gp.monster[2] = new NPC_Monster2(gp);
		gp.monster[2].worldX = 25 * gp.tileSize;
		gp.monster[2].worldY = 29 * gp.tileSize;
		
		gp.monster[3] = new NPC_Monster3(gp);
		gp.monster[3].worldX = 34 * gp.tileSize;
		gp.monster[3].worldY = 17 * gp.tileSize;
	}
}
