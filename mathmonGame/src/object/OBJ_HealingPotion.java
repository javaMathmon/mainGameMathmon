package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;

public class OBJ_HealingPotion extends SuperObject {
	
	public OBJ_HealingPotion() {
		name = "Healing Potion";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/sapphire.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		Entity entity = new Entity(null);
		entity.healValue = 1;
		collision = true;
	}
}