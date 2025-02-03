package scrollGame;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;



public class GamePanel extends javax.swing.JPanel implements ActionListener{

	MainFrame frame;	
	GamePlayer player;
    GameData gameData = new GameData();
    SpritesHandler sprites = new SpritesHandler();
    GamePanel panelPointer = this;

    
    Image backgroundImage,FullLife,OneLifeLost,TwoLifeLost,RecordBanner;
    Rectangle canSpawn = new Rectangle(0,0,50,50);;
    Timer gameTimer;
    
	ArrayList <Wall> walls = new ArrayList<>();
	ArrayList<Enemy> enemies = new  ArrayList<>();
	ArrayList<Enemy> toRemoveEnemy = new  ArrayList<>();
	ArrayList <Wall> toRemoveWalls = new ArrayList<>();
	ArrayList<Bonus> perks = new ArrayList<>();
	ArrayList<Bonus> toRemovePerks = new ArrayList<>();
	
		
	int backgroundStartX,cameraX,backgroundWidth;
	int distancePoints,points;
    int wallsCreationOffset = -150;


    
	public GamePanel() {
	
		// initialize panel
		player = new GamePlayer (400,300,this);
		setPanelImages();
		playSoundtrack();
		reset();        
    
		backgroundWidth = backgroundImage.getWidth(this);		
        
		// main loop
		gameTimer = new Timer();
		gameTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				
				// add walls while player is running 
				if( walls.get(walls.size()-1).x <800) { 
					wallsCreationOffset += 700;
					Wall.makeWalls(wallsCreationOffset, panelPointer);;
				} 
				
				
				
				// update player position
				player.setPlayer();
				
				
				for(Bonus perk:perks) {
					perk.setBonus();				
					if( (perk.x<player.x+30 && perk.x>player.x-30) && (player.y >perk.y-90 && player.y < perk.y-10)) {
						boolean boostedPlayer = perk.powerUp();
						if (boostedPlayer) toRemovePerks.add(perk);

					}
				} // end for
				
			
				perks.removeAll(toRemovePerks);
				
				for(Wall wall:walls) wall.setWall(cameraX);
				
				
				// remove "old" walls
				for(int i=0 ; i<walls.size(); i++) {
					if(walls.get(i).x < -800) toRemoveWalls.add(walls.get(i));
				}				
				walls.removeAll(toRemoveWalls);
				
				
				Random rand = new Random();
				int ind = rand.nextInt(220);				
				if (ind == 1) panelPointer.spawnEnemy();				
				

				for(Enemy enemy:enemies) {
					enemy.setEnemy(cameraX);					
					if(enemy.y>700) toRemoveEnemy.add(enemy);
					
				} // end for	
					
				enemies.removeAll(toRemoveEnemy);
											
				// death code
				if(player.lifes<=0) reset();	
						                         
				
                backgroundStartX = cameraX % backgroundWidth;				
				distancePoints=(cameraX/-10)+15;
				
				repaint();
				
			}
		}, 0 , 17);
	
	
	}	// end constructor 
	

	
	public void reset() {
		points+=distancePoints;
		if( points > gameData.getPointsRecordInt()) gameData.updatePointsRecord(points);		
		points=0;
		enemies.clear();
		toRemoveEnemy.clear();		
		walls.clear();
		perks.clear();
		toRemovePerks.clear();	
		player.lifes=3;
		player.x = 200;
		player.y = 50;
		cameraX = 150;
		player.xspeed = 0;
		wallsCreationOffset = -150;
		Wall.makeWalls(wallsCreationOffset, this);		
	}
	
	
	public void spawnEnemy() {
		
		Random rand = new Random();
		int ind = rand.nextInt(10);				
		boolean spawn=false;
		
		//don't spawn if enemy would fall on deaf ears
		for(int i=0; i<16; i++) {
			for(Wall wall:walls) if (wall.getHitBox().intersects(canSpawn)) spawn=true;
			canSpawn.setLocation(600,i*50);
		}			
		if(spawn && ind<3)enemies.add(new Lich(600,100,this));
		else if(spawn && ind >=3) enemies.add(new Skeleton(600,100,this));
	
	} // end method

	
	
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
		                
		Graphics2D gtd = (Graphics2D) g;
                
        g.drawImage(backgroundImage,backgroundStartX-200,0,this);
        g.drawImage(backgroundImage,backgroundStartX+backgroundWidth-200,0,this);               
               
        switch(player.lifes){
              case 3:
            	  g.drawImage(FullLife,25,5,null);
                  break;
              case 2:
                  g.drawImage(OneLifeLost,25,5,null);
                  break;                   
              case 1:
            	  g.drawImage(TwoLifeLost,25,5,null);
            	  break;            
               }
               
        g.drawImage(RecordBanner,475,5,null);               
       
        g.setFont(new Font("TimesRoman", Font.PLAIN, 26)); 
        g.setColor(Color.WHITE);
        g.drawString(""+(distancePoints+points), 550, 75);
        
        g.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        g.setColor(Color.WHITE);
        g.drawString("RECORD MAX: "+gameData.getPointsRecordInt(), 510, 26);
               
        player.draw(gtd);

		for(Bonus perk:perks) perk.draw(gtd);
		
		for(Wall wall:walls) wall.draw(gtd);
		
		for(Enemy enemy:enemies) enemy.draw(gtd);
		
	
	}	// end method


    public void playSoundtrack(){          
    	MusicPlayer musicplayer = new MusicPlayer();
    	musicplayer.startMusic();
    } 	// end method
        
        
    public void keyPressed(KeyEvent e) {
			
		if(e.getKeyCode()== KeyEvent.VK_LEFT) {			
			player.keyLeft = true;
			player.direction ="left";
		}
		
		if(e.getKeyCode()== KeyEvent.VK_RIGHT) {		
			player.keyRight = true;
			player.direction ="right";			
		}
		
		if(e.getKeyCode()== KeyEvent.VK_UP) player.keyUp = true;
		
		
		// player attack code
		if( (e.getKeyChar() == 'z' || e.getKeyChar() == 'Z') && !player.isHealing) { 			
			
			
			player.isAttacking = true;
			repaint();
			
			for(Enemy enemy:enemies) if( (enemy.x>=100 && enemy.x<=300) && (player.y+player.HEIGHT == enemy.y+enemy.height)) {					
		
				enemy.damaged();
				if(enemy.life<=0) {
					toRemoveEnemy.add(enemy);
					points+=enemy.pointsEarned;
				}				
			}		
		
		}	
		enemies.removeAll(toRemoveEnemy);	
			
				
	} // end method
		

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_LEFT) {
			player.keyLeft = false;
		}

		if(e.getKeyCode()== KeyEvent.VK_RIGHT) {
			player.keyRight = false;

		}

		if(e.getKeyCode()== KeyEvent.VK_UP) player.keyUp = false;
		if(e.getKeyChar() == 'z' || e.getKeyChar() == 'Z') {
			player.isAttacking = false;
			repaint();			
			
		}
	
	
	}  // end method

	public void addWallToPanel(Wall wall) {
		walls.add(wall);
	}
	
	public void addBonusToPanel(Bonus bonus) {
		perks.add(bonus);
	}
	
	public void setPanelImages() {
        
		try {
            backgroundImage =  ImageIO.read(this.getClass().getResourceAsStream("resources/Images/cave.jpg"));
            FullLife = ImageIO.read(this.getClass().getResourceAsStream("resources/Images/FullLife.jpg"));
            OneLifeLost = ImageIO.read(this.getClass().getResourceAsStream("resources/Images/LifeLost1.jpg"));
            TwoLifeLost = ImageIO.read(this.getClass().getResourceAsStream("resources/Images/LifeLost2.jpg"));
            RecordBanner = ImageIO.read(this.getClass().getResourceAsStream("resources/Images/RecordBanner.jpg"));

        } catch (IOException e) {e.printStackTrace();}
	
	} // end method
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}	// end class



