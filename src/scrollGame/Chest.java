package scrollGame;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Chest extends Bonus{

	public Chest(int x, int y, GamePanel panel) {
		super();
		this.x=x;
		this.y=y;
		this.panel=panel;
		startX=x-panel.cameraX;
		spriteShiftY=10;
		setBonusImg();
}


	@Override
	public void setBonusImg() {
	
		try {
			sprite = ImageIO.read(this.getClass().getResourceAsStream("resources/Images/chest.png"));
		} catch (IOException e) {e.printStackTrace();}

	}	// end method


	@Override
	public boolean powerUp() {
	
		panel.points+=50;
		return true;
		
	}	// end method


	
}
