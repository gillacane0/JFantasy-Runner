package scrollGame;

import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Lich extends Enemy{

	public Lich(int x, int y, GamePanel panel) {
		
		super();
		this.x = x;
		this.y = y;
		this.panel = panel;				
		chasespeed=4;
		life = 5;
		pointsEarned=50;
		width=50;
		height=100;
		spriteWidth = 128;
		spriteHeight = 128;
		spriteShiftY=-75;	
		
		isAttacking=false;
				
		hitBox = new Rectangle(x,y,width,height);
		startX = x - panel.cameraX;	
		setEnemyImg();

		
	}

	@Override
	public void setEnemyImg() {
		try {
			
			idleLeft = ImageIO.read(this.getClass().getResourceAsStream("resources/Sprites/lichIdle/idleLeft.png"));
			idleRight = ImageIO.read(this.getClass().getResourceAsStream("resources/Sprites/lichIdle/idleRight.png"));
			sprite = idleLeft;		
		
			walkAnimationLeft = new Animation(panel.sprites.getLichWalkSpritesLeft());
			walkAnimationRight = new Animation(panel.sprites.getLichWalkSpritesRight());
			walkAnimationLeft.delay=5;		
			walkAnimationRight.delay=5;	
			
			attackLeftAnimation = new Animation(panel.sprites.getLichAttackSpritesLeft());
			attackRightAnimation = new Animation(panel.sprites.getLichAttackSpritesRight());	
			attackLeftAnimation.delay=5;
			attackRightAnimation.delay=5;
		
			spriteShiftXLeft=-80;
			spriteShiftXRight=-50;
		
		} catch (IOException e) {e.printStackTrace();}
		
	}	// end method


} // end class

