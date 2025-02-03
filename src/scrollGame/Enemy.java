package scrollGame;



import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;



public abstract class Enemy {
	
	GamePanel panel;
	
	int life;
	int pointsEarned;
    int attackRange=75;
	int x,y,width,height,yspeed,startX,chasespeed;
	int spriteWidth,spriteHeight,spriteShiftY,spriteShiftXLeft,spriteShiftXRight;
	
	final int attackDelay=25;
	int attackCount=0;
	
	Rectangle checkFall = new Rectangle(0,0,50,50);
	Rectangle hitBox;
	
	BufferedImage sprite,idleLeft,idleRight;
	BufferedImage[] attackSpritesLeft,attackSpritesRight,walkSpritesLeft,walkSpritesRight;	
	Animation attackLeftAnimation,attackRightAnimation,walkAnimationLeft,walkAnimationRight;
		
	boolean isAttacking=false;
	boolean attack=false;
		
	String direction = "left";
	String actualState="moving";
	
	
	public abstract void setEnemyImg();
	
	public void setEnemy(int qnt) {
		
		yspeed+=3;		
		y += yspeed ;
		x = (startX + qnt);
		hitBox.x = x;
		
		
		//vertical collision
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
		
		// enemy chase		
		int speed = chasespeed;		
		if(x>202.5 && x <450) {
			
			direction="left";
			if(actualState != "attacking")actualState = "moving";
			
			walkAnimationLeft.update();
			walkAnimationRight.update();
			
			// check horyzontal collision
			hitBox.x -= speed;
			for(Wall wall:panel.walls) {
				if(hitBox.intersects(wall.getHitBox())) {				
					hitBox.x += speed;
					while(!wall.getHitBox().intersects(hitBox)) hitBox.x -= Math.signum(speed);
					hitBox.x += Math.signum(speed);
					speed = 0;
					hitBox.x = x;
				}
			}
			
			//don't suicide
			checkFall.setLocation(x-50, y);
			boolean canMove=false;
			for(int i=0; i<10; i++) {
				for(Wall wall:panel.walls) if (wall.getHitBox().intersects(checkFall)) canMove=true;
				checkFall.setLocation(x-50, y+(i*50) );
			}
			if(canMove) startX-=speed;
			
		} // end major if
		
		
		if(x>0 && x<198.5) {
			
			direction="right";
			if(actualState != "attacking")actualState = "moving";
			
			walkAnimationLeft.update();
			walkAnimationRight.update();

			// check horyzontal collision
			hitBox.x += speed;
			for(Wall wall:panel.walls) {
				if(hitBox.intersects(wall.getHitBox())) {				
					hitBox.x -= speed;
					while(!wall.getHitBox().intersects(hitBox)) hitBox.x += Math.signum(speed);
					hitBox.x -= Math.signum(speed);
					speed = 0;
					hitBox.x = x;
				}
			}
			
			//don't suicide
			checkFall.setLocation(x+50, y);
			boolean canMove=false;
			for(int i=0; i<10; i++) {
				for(Wall wall:panel.walls) if (wall.getHitBox().intersects(checkFall)) canMove=true;
				checkFall.setLocation(x+50, y+(i*50));
			}
			if(canMove) startX+=speed;	
			
		}	// end major if
		
		//ATTACK
		//200 is always the x position of the player
		if((x >= 200-attackRange && x <= 200+attackRange) && panel.player.y+panel.player.HEIGHT==y+height) 
			attackPlayer();
		else actualState = "moving";
			
		
		//update position
		x = (startX + qnt);
		hitBox.x = x;
		hitBox.y = y;
		
	}
	
	
	
	public void damaged() {
		life--;		
	}
	
	public void attackPlayer() {
		
		attackCount++;
		actualState = "attacking";
		
		attackLeftAnimation.update();
		attackRightAnimation.update();
		
		if(attackCount>=attackDelay) {
			attackCount=0;
			panel.player.loseLife();
		}
	}	// end method
	
	
	public void draw(Graphics2D gtd) {

		switch(direction) {
			
		case "right":
				if (actualState == "moving") sprite = walkAnimationRight.getSprite();
				else if(actualState == "attacking") sprite = attackRightAnimation.getSprite();
					 else sprite = idleRight;
				gtd.drawImage(sprite, x- ((spriteWidth-width)/2) + spriteShiftXRight, y-((spriteHeight-height)/2)+spriteShiftY, null);
				break;
				
			case "left":
				if (actualState == "moving") sprite = walkAnimationLeft.getSprite();
				else if(actualState == "attacking") sprite = attackLeftAnimation.getSprite();
					 else sprite = idleLeft;
				gtd.drawImage(sprite, x- ((spriteWidth-width)/2) + spriteShiftXLeft, y-((spriteHeight-height)/2)+spriteShiftY, null);

				break;				
		}




	}	// end method



	
}	// end class

