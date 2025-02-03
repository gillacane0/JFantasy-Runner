package scrollGame;

import java.awt.image.BufferedImage;

public class Animation {

	int count,delay,framesNumber,currentFrame,timesPlayed;
	BufferedImage[] frames;
	
	
	public Animation(BufferedImage[] frames) {
		this.frames=frames;
		count = 0;
		delay = 2;
		framesNumber = frames.length;	
		timesPlayed=0;
	}	// end-constructor
	
	
	public void update() {		
		count++;
		if(count == delay) {
			currentFrame++;
			count=0;
		}
	
		if(currentFrame >= framesNumber) {
			currentFrame = 0;
			timesPlayed++;
		}
		
	}	// end-method
	
	public void resetAnimation() {
		count = 0;
		timesPlayed = 0;
		currentFrame = 0;
	}	// end-method
	
	public BufferedImage getSprite() {
		return frames[currentFrame];
	}	// end-method
	
}	// end class
