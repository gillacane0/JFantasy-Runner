package scrollGame;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class SpritesHandler {
	

BufferedImage[] playerRunRight = new BufferedImage[3];
BufferedImage[] playerRunLeft = new BufferedImage[3];
BufferedImage[] healSpritesRight = new BufferedImage[8];
BufferedImage[] healSpritesLeft = new BufferedImage[8];
BufferedImage[] playerHurtLeft = new BufferedImage[8];
BufferedImage[] playerHurtRight = new BufferedImage[8];

BufferedImage[] skeletonWalkRight = new BufferedImage[16];
BufferedImage[] skeletonWalkLeft = new BufferedImage[16];
BufferedImage[] skeletonAttackRight = new BufferedImage[12];
BufferedImage[] skeletonAttackLeft = new BufferedImage[12];

BufferedImage[] lichWalkLeft = new BufferedImage[8];
BufferedImage[] lichWalkRight = new BufferedImage[8];
BufferedImage[] lichAttackLeft = new BufferedImage[13];
BufferedImage[] lichAttackRight = new BufferedImage[13];

public SpritesHandler() {

    	
	try {		

	for(int i=0;i<3;i++) {
		playerHurtLeft[i] = ImageIO.read(this.getClass().getResourceAsStream("resources/Sprites/playerHurt/hurt"+(i+1)+"L.png"));
		playerHurtRight[i] = ImageIO.read(this.getClass().getResourceAsStream("resources/Sprites/playerHurt/hurt"+(i+1)+".png"));
		
	}	
				
	for(int i=0;i<8;i++) {
		healSpritesRight[i] = ImageIO.read(this.getClass().getResourceAsStream("resources/Sprites/playerHealing/Health1_"+i+".png"));
		healSpritesLeft[i] = ImageIO.read(this.getClass().getResourceAsStream("resources/Sprites/playerHealing/Health2_"+i+".png"));
        }

	
	for(int i=0;i<12;i++) {
		skeletonAttackRight[i] = ImageIO.read(this.getClass().getResourceAsStream("resources/Sprites/SkeletonAttack/attack_"+i+".png"));
		skeletonAttackLeft[i] = ImageIO.read(this.getClass().getResourceAsStream("resources/Sprites/SkeletonAttack/attack_"+i+"L.png"));	
	}	
	
	
	for(int i=0;i<8;i++) {
		lichWalkRight[i] = ImageIO.read(getClass().getResourceAsStream("resources/Sprites/lichWalk/lichWalk"+i+".png"));
		lichWalkLeft[i] = ImageIO.read(this.getClass().getResourceAsStream("resources/Sprites/lichWalk/lichWalk"+i+"L.png"));
	
	}


	for(int i=0;i<16;i++) {
		skeletonWalkRight[i] = ImageIO.read(this.getClass().getResourceAsStream("resources/Sprites/SkeletonWalk/walk_"+i+".png"));
		skeletonWalkLeft[i] = ImageIO.read(this.getClass().getResourceAsStream("resources/Sprites/SkeletonWalk/walk_"+i+"L.png"));
	
	}

	
	
	
	for(int i=0;i<13;i++) {
		lichAttackRight[i] = ImageIO.read(this.getClass().getResourceAsStream("resources/Sprites/lichAttack/Necromancer_creativekind-Sheet_"+(i+33)+".png"));
		lichAttackLeft[i] = ImageIO.read(this.getClass().getResourceAsStream("resources/Sprites/lichAttack/Necromancer_creativekind-Sheet_"+(i+33)+"L.png"));
	
	}

	
	playerRunRight[0]= ImageIO.read(this.getClass().getResourceAsStream("resources/Sprites/new/RunR1.png"));
	playerRunRight[1]= ImageIO.read(this.getClass().getResourceAsStream("resources/Sprites/new/RunR2.png"));
	playerRunRight[2]= ImageIO.read(this.getClass().getResourceAsStream("resources/Sprites/new/RunR3.png"));
	
	playerRunLeft[0]= ImageIO.read(this.getClass().getResourceAsStream("resources/Sprites/new/RunL1.png"));
	playerRunLeft[1]= ImageIO.read(this.getClass().getResourceAsStream("resources/Sprites/new/RunL2.png"));
	playerRunLeft[2]= ImageIO.read(this.getClass().getResourceAsStream("resources/Sprites/new/RunL3.png"));	
	
	} catch (IOException e) {e.printStackTrace();} 

}	// end constructor
	

	public BufferedImage[] gethealSpritesLeft() {
		return healSpritesLeft;
	}
	public BufferedImage[] gethealSpritesRight() {
		return healSpritesRight;
	}
	public BufferedImage[] getSkeletonAttackSpritesLeft() {
		return skeletonAttackLeft;
	}
	public BufferedImage[] getSkeletonAttackSpritesRight() {
		return skeletonAttackRight;
	}
	public BufferedImage[] getSkeletonWalkSpritesLeft() {
		return skeletonWalkLeft;
	}
	public BufferedImage[] getSkeletonWalkSpritesRight() {
		return skeletonWalkRight;
	}
	public BufferedImage[] getLichWalkSpritesLeft() {
		return lichWalkLeft;
	}
	public BufferedImage[] getLichWalkSpritesRight() {
		return lichWalkRight;
	}
	public BufferedImage[] getLichAttackSpritesLeft() {
		return lichAttackLeft;
	}
	public BufferedImage[] getLichAttackSpritesRight() {
		return lichAttackRight;
	}
	public BufferedImage[] getPlayerRunLeft() {
		return playerRunLeft;
	}
	public BufferedImage[] getPlayerRunRight() {
		return playerRunRight;
	}
	public BufferedImage[] getPlayerHurtLeft() {
		return playerHurtLeft;
	}
	public BufferedImage[] getPlayerHurtRight() {
		return playerHurtRight;
	}

} // end class



