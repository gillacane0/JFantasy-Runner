package scrollGame;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.imageio.ImageIO;

public class Wall {
	
	int x,y,startX;
	final int dimension = 50;
	public  BufferedImage texture;  	
	Rectangle hitbox;
		
	public Wall(int x, int y) {
		
		startX = x;
		this.x = x;
		this.y = y;
		hitbox = new Rectangle(x,y,dimension,dimension);
		this.setTexture();
	
	}  // end constructor

	
	public Rectangle getHitBox() {
		return this.hitbox;
	} // end method

	
	public  void setTexture() {
		try {
			texture = ImageIO.read(this.getClass().getResourceAsStream("resources/Images/brick.jpg"));
		} catch (IOException e) {e.printStackTrace();}
	
	} // end method
	

	public int setWall(int cameraX) {
		x = startX + cameraX;
		hitbox.x = x;
		return x;
	
	} // end method
	
	
	
	public static void makeWalls(int offset,GamePanel panel) {
		
		Random randTerrain = new Random();
		int indTerrain = randTerrain.nextInt(9);

		Random randPerk = new Random();
		int indRandPerk = randPerk.nextInt(4); 
		
		Random randPerkType = new Random();
		int indRandPerkType = randPerkType.nextInt(2);
		
		boolean hearthGenerated  = (indRandPerk == 0 && indRandPerkType==0);
		boolean chestGenerated = (indRandPerk == 0 && indRandPerkType==1);
		
		if (indTerrain == 0) {
			panel.addWallToPanel(new Wall(offset,550));
			panel.addWallToPanel(new Wall(offset+200,400));
			panel.addWallToPanel(new Wall(offset+400,500));
			panel.addWallToPanel(new Wall(offset+600,400));
			
			if(hearthGenerated) {
				Heart heart = new Heart(600,0,panel);
				panel.addBonusToPanel(heart);
			} 
			else if (chestGenerated) {
				Chest chest = new Chest(600,0,panel);
				panel.addBonusToPanel(chest);
			}			
		}
		
		else if (indTerrain == 1) {
			for(int i=0; i<5; i++) panel.addWallToPanel(new Wall(offset + i*50,600));								
			panel.addWallToPanel(new Wall(offset + 500, 600));
			panel.addWallToPanel(new Wall(offset + 550, 600));
			panel.addWallToPanel(new Wall(offset + 600, 600));
			panel.addWallToPanel(new Wall(offset + 650, 600));
			panel.addWallToPanel(new Wall(offset + 700, 600));
			panel.addWallToPanel(new Wall(offset + 750, 600));			
			
			if(hearthGenerated) {
				Heart heart = new Heart(600,0,panel);
				panel.addBonusToPanel(heart);
			} 
			else if (chestGenerated) {
				Chest chest = new Chest(600,0,panel);
				panel.addBonusToPanel(chest);
			}
		}
		
		
		else if (indTerrain == 2) {			
			for(int i=0; i<14; i++) panel.addWallToPanel(new Wall(offset + i*50,600));
			for(int i=0; i<12; i++) panel.addWallToPanel(new Wall(offset + 50 +i*50,550));
			for(int i=0; i<10; i++) panel.addWallToPanel(new Wall(offset + 100+ i*50,500));
			for(int i=0; i<8; i++) panel.addWallToPanel(new Wall(offset + 150 +i*50,450));
			for(int i=0; i<6; i++) panel.addWallToPanel(new Wall(offset + 200 +i*50,400));
			
			if(hearthGenerated) {
				Heart heart = new Heart(600,0,panel);
				panel.addBonusToPanel(heart);
			} 
			else if (chestGenerated) {
				Chest chest = new Chest(600,0,panel);
				panel.addBonusToPanel(chest);
			}
		}
		
		
		else if (indTerrain ==3) {
			for(int i=0; i<5; i++) panel.addWallToPanel(new Wall(offset + i*50,600));
			for(int i=0; i<5; i++) panel.addWallToPanel(new Wall(offset + 450 +i*50,600));
			panel.addWallToPanel(new Wall(offset + 200, 550 ));
			panel.addWallToPanel(new Wall(offset + 200, 500 ));
			panel.addWallToPanel(new Wall(offset + 200, 450 ));
			panel.addWallToPanel(new Wall(offset + 450, 550 ));
			panel.addWallToPanel(new Wall(offset + 450, 500 ));
			panel.addWallToPanel(new Wall(offset + 450, 450 ));
			
			if(hearthGenerated) {
				Heart heart = new Heart(600,0,panel);
				panel.addBonusToPanel(heart);
			} 
			else if (chestGenerated) {
				Chest chest = new Chest(600,0,panel);
				panel.addBonusToPanel(chest);
			}
		}

		
		else if (indTerrain == 4) {
			for(int i=0; i<5; i++) panel.addWallToPanel(new Wall(offset + i*50,600));
			for(int i=0; i<4; i++) panel.addWallToPanel(new Wall(offset + 50 +i*50,550));
			for(int i=0; i<3; i++) panel.addWallToPanel(new Wall(offset + 100+ i*50,500));
			for(int i=0; i<2; i++) panel.addWallToPanel(new Wall(offset + 150 +i*50,450));
			for(int i=0; i<4; i++) panel.addWallToPanel(new Wall(offset + 500 +i*50,600));
			
			if(hearthGenerated) {
				Heart heart = new Heart(600,0,panel);
				panel.addBonusToPanel(heart);
			} 
			else if (chestGenerated) {
				Chest chest = new Chest(600,0,panel);
				panel.addBonusToPanel(chest);
			}
		}
		
		else if (indTerrain == 5) {
			for(int i=0; i<5; i++) panel.addWallToPanel(new Wall(offset + i*50,600));
			for(int i=0; i<3; i++) panel.addWallToPanel(new Wall(offset + 100 +i*50,550));
			for(int i=0; i<2; i++) panel.addWallToPanel(new Wall(offset + 150+ i*50,500));
			for(int i=0; i<7; i++) panel.addWallToPanel(new Wall(offset + 350 +i*50,600));
			for(int j=0; j<5; j++) 
				for (int i=0; i<4; i++) panel.addWallToPanel(new Wall(offset + 350+ i*50,350 + 50*j));
			panel.addWallToPanel(new Wall(offset+500,300));
			panel.addWallToPanel(new Wall(offset+450,300));
			panel.addWallToPanel(new Wall(offset+500,250));
			
			if(hearthGenerated) {
				Heart heart = new Heart(600,0,panel);
				panel.addBonusToPanel(heart);
			} 
			else if (chestGenerated) {
				Chest chest = new Chest(600,0,panel);
				panel.addBonusToPanel(chest);
			}
		}
	
		
		else if (indTerrain == 6) {
			for(int i=0; i<14; i++) panel.addWallToPanel(new Wall(offset + i*50,600));
			for(int i=0; i<7; i++) panel.addWallToPanel(new Wall(offset + 200 + i*50,450));
			
			if(hearthGenerated) {
				Heart heart = new Heart(600,0,panel);
				panel.addBonusToPanel(heart);
			} 
			else if (chestGenerated) {
				Chest chest = new Chest(600,0,panel);
				panel.addBonusToPanel(chest);
			}
		}
	

		else if ( indTerrain == 7) {
			for(int i=0;i<3;i++) panel.addWallToPanel(new Wall(offset+i*200,600));		
			
			if(hearthGenerated) {
				Heart heart = new Heart(600,0,panel);
				panel.addBonusToPanel(heart);
			} 
			else if (chestGenerated) {
				Chest chest = new Chest(600,0,panel);
				panel.addBonusToPanel(chest);
			}		
		}
	
		
		else if ( indTerrain == 8) {			
			for(int i = 0; i<14; i++) panel.addWallToPanel(new Wall(offset + i*50,600));
			
			if(hearthGenerated) {
				Heart heart = new Heart(600,0,panel);
				panel.addBonusToPanel(heart);
			} 
			else if (chestGenerated) {
				Chest chest = new Chest(600,0,panel);
				panel.addBonusToPanel(chest);
			}
		}
	
		
	} // end method
		
	public void draw(Graphics2D gtd) {	
		gtd.drawImage(texture, x, y, null);	
	}	// end method

} // end class