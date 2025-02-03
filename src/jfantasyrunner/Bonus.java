package jfantasyrunner;

import java.awt.Graphics2D;


import java.awt.Rectangle;
import java.awt.image.BufferedImage;



public abstract class Bonus {

		
	int x,y,startX,yspeed;
	final int WIDTH=50,HEIGHT=50;
	int spriteShiftX,spriteShiftY;
	
	GamePanel panel;
	Rectangle hitBox =  new Rectangle(x,y,WIDTH,HEIGHT);;
	BufferedImage sprite;
	
	public abstract void setBonusImg();
	
	public abstract boolean powerUp();
	
	public void setBonus() {
		
		yspeed+=3;		
		y += yspeed ;		
		x = startX + panel.cameraX;
		hitBox.x = x;
		hitBox.y += yspeed;		
		
		for(Wall wall:panel.walls) {
			if(hitBox.intersects(wall.getHitBox())) {
				hitBox.y -= yspeed;
				while(!wall.getHitBox().intersects(hitBox)) hitBox.y += Math.signum(yspeed);
				hitBox.y -= Math.signum(yspeed);
				yspeed = 0;
				y = hitBox.y;
			}	
		}	
	}	// end method

	
	public void draw(Graphics2D gtd) {
		gtd.drawImage(sprite,x+spriteShiftX, y+spriteShiftY, null);
	}
	
	
}	// end class