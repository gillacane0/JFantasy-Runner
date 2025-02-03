package scrollGame;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.util.Timer;


public class GamePlayer {

	GamePanel panel;
	
	int x,y,spriteOffsetY,spriteOffsetX;
	
	final int WIDTH = 48;
	final int HEIGHT = 95;
	final int SPRITEWIDTH = 256;
	final int SPRITEHEIGHT = 128;

	int lifes = 3;	
	double xspeed,yspeed;
	boolean keyLeft,keyRight,keyUp,isAttacking,isHealing,isHurted;

	String direction;   
	
	Rectangle hitBox;
	
	BufferedImage sprite,attackLeft,attackRight;	
 	
	BufferedImage[] healingSpritesLeft,healingSpritesRight,runSpritesLeft,runSpritesRight;	
	Animation healingAnimationLeft,healingAnimationRight,runAnimationLeft,runAnimationRight;
	Animation hurtAnimationLeft,hurtAnimationRight;
 	
	
	public GamePlayer(int x, int y, GamePanel panel) {
		
		this.x = x;
		this.y = y;
		this.panel = panel;		
		direction="right";
		hitBox = new Rectangle(x,y,WIDTH,HEIGHT);		
		setImages();

	}	// END CONSTRUCTOR
	
	public void setPlayer() {
		
		// speed control code
		if(keyLeft && keyRight || !keyLeft && !keyRight) xspeed*= 0.8;		
		else if (keyLeft && !keyRight) xspeed -- ;
		else if (!keyLeft && keyRight) xspeed ++ ;
		
		if(xspeed >0 && xspeed < 0.55) xspeed = 0;
		if(xspeed <0 && xspeed > -0.55) xspeed = 0;

		if(xspeed>7) xspeed = 7;
		if(xspeed<-7) xspeed = -7;
		
		// jump code
		if(keyUp) {
			hitBox.y ++;
			for(Wall wall:panel.walls) if(wall.getHitBox().intersects(hitBox)) yspeed = -10;		
			hitBox.y --;
		}		
		yspeed += 0.3;
		
		runAnimationLeft.update();	
		runAnimationRight.update();	
		
		//Horyzontal collision
		hitBox.x += xspeed;
		for(Wall wall:panel.walls) {
			if(hitBox.intersects(wall.getHitBox())) {				
				hitBox.x -= xspeed;
				while(!wall.getHitBox().intersects(hitBox)) hitBox.x += Math.signum(xspeed);
				hitBox.x -= Math.signum(xspeed);
				panel.cameraX += (x - hitBox.x);
				xspeed = 0;
				hitBox.x = x;
			}
		}
		
		//Vertical collision
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
		
		// healing animation sprites 
		if(isHealing) {
			healingAnimationLeft.update();
			healingAnimationRight.update();
			if(healingAnimationLeft.timesPlayed>=1 || healingAnimationRight.timesPlayed>=1) {
				isHealing=false;
				healingAnimationLeft.resetAnimation();
				healingAnimationRight.resetAnimation();
			}
		}
		
		if(isHurted) {
			hurtAnimationLeft.update();
			hurtAnimationRight.update();
			if(hurtAnimationLeft.timesPlayed>=1 || hurtAnimationRight.timesPlayed>=1) {
				isHurted=false;
				hurtAnimationLeft.resetAnimation();
				hurtAnimationRight.resetAnimation();
			}
		}
		
		panel.cameraX -= xspeed;
		y += yspeed;
		
		hitBox.x = x;
		hitBox.y = y;
		
		
		//Death code
		if (y > 800) panel.reset();
		
	}
	
	
	public void loseLife() {
		lifes--;
		isHurted = true;
		panel.repaint();
	}

	
	public void earnLife() {		
		isHealing=true;
		lifes++;        
	}	// end method
	
	
	public void setImages() {
				
		try {

            attackLeft = ImageIO.read(this.getClass().getResourceAsStream("resources/Sprites/new/AttackLeft.png"));
            attackRight = ImageIO.read(this.getClass().getResourceAsStream("resources/Sprites/new/AttackRight.png"));

		}catch (IOException e) {e.printStackTrace();}
		
		
		healingAnimationLeft = new Animation(panel.sprites.gethealSpritesLeft());
		healingAnimationRight = new Animation(panel.sprites.gethealSpritesRight());
		healingAnimationLeft.delay = 5;
		healingAnimationRight.delay = 5;
		
		runAnimationLeft = new Animation(panel.sprites.getPlayerRunLeft());
		runAnimationRight = new Animation(panel.sprites.getPlayerRunRight());
		runAnimationLeft.delay=7;
		runAnimationRight.delay=7;
		
		hurtAnimationLeft = new Animation(panel.sprites.getPlayerHurtLeft());
		hurtAnimationRight = new Animation(panel.sprites.getPlayerHurtRight());
		hurtAnimationLeft.delay=1;
		hurtAnimationRight.delay=1;
		
	}
	
	
	public void draw(Graphics2D gtd) {
		
		// idle
		if((keyLeft && keyRight || !keyLeft && !keyRight) && direction=="right" && !isAttacking) sprite = runAnimationRight.frames[0];
		if((keyLeft && keyRight || !keyLeft && !keyRight) && direction=="left" && !isAttacking) sprite = runAnimationLeft.frames[0];
		
		// attack
		if(isAttacking && direction=="left") sprite = attackLeft;
		if(isAttacking && direction=="right") sprite = attackRight;
		
		// run
		if(!isAttacking && !isHealing && keyLeft) sprite = runAnimationLeft.getSprite();
		if (!isAttacking && !isHealing && keyRight) sprite = runAnimationRight.getSprite();
		
		// healing
		if(isHealing && direction == "left") {
			sprite = healingAnimationLeft.getSprite();
			spriteOffsetY = -18;
		}
		else if (isHealing && direction == "right") {
			sprite = healingAnimationRight.getSprite();
			spriteOffsetY = -18;			
		}
		
		if(isHurted && direction == "left") {
			sprite = hurtAnimationLeft.getSprite();
			spriteOffsetY = -18;			
			
		} 
		else if (isHurted && direction == "right") {
			sprite = hurtAnimationRight.getSprite();
			spriteOffsetY = -18;			
		
		}  
		
		gtd.drawImage(sprite, x- ((SPRITEWIDTH-WIDTH)/2), y-((SPRITEHEIGHT-HEIGHT)/2)+spriteOffsetY, null);

		spriteOffsetY = 0;
		
	}

}
