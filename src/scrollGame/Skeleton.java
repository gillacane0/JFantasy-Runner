package scrollGame;

import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Skeleton extends Enemy{
	
	public Skeleton(int x, int y, GamePanel panel) {
		
		super();
		this.x = x;
		this.y = y;
		this.panel = panel;		
		chasespeed=4;
		life=2;
		pointsEarned=20;
		width=50;
		height=50;
		spriteWidth = 50;
		spriteHeight = 50;
		
		isAttacking=false;
			
		hitBox = new Rectangle(x,y,width,height);
		startX = x - panel.cameraX;	
		
		setEnemyImg();


	}

	@Override
	public void setEnemyImg() {
		try {
			idleLeft = ImageIO.read(this.getClass().getResourceAsStream("resources/Sprites/skeletonIdle/skeletonIdleLeft.png"));
			idleRight = ImageIO.read(this.getClass().getResourceAsStream("resources/Sprites/skeletonIdle/skeletonIdleRight.png"));
			
			sprite = idleLeft;

			attackLeftAnimation = new Animation(panel.sprites.getSkeletonAttackSpritesLeft());
			attackRightAnimation = new Animation(panel.sprites.getSkeletonAttackSpritesRight());
			
			walkAnimationLeft = new Animation(panel.sprites.getSkeletonWalkSpritesLeft());
			walkAnimationRight = new Animation(panel.sprites.getSkeletonWalkSpritesRight());
			walkAnimationLeft.delay=5;		
			walkAnimationRight.delay=5;
			
			spriteShiftY=-25;
			spriteShiftXLeft=-60;
		
		} catch (IOException e) {e.printStackTrace();}
		
	} // end method

} // end class
