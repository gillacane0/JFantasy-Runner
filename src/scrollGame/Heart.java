package scrollGame;

import java.io.IOException;
import javax.imageio.ImageIO;


public  class Heart extends Bonus{
	
	public Heart(int x, int y, GamePanel panel) {
			super();
			this.x=x;
			this.y=y;
			this.panel=panel;
			startX=x-panel.cameraX;
			setBonusImg();
	}
	
	
	@Override
	public void setBonusImg() {
		
		try {
			sprite = ImageIO.read(this.getClass().getResourceAsStream("resources/Images/heart.png"));
		} catch (IOException e) {e.printStackTrace();}
	
	}	// end method

	
	@Override
	public boolean powerUp() {
		
		if(panel.player.lifes<3) {
			panel.player.earnLife();
			return true;
		}
		else return false;
	
	}	// end method




}