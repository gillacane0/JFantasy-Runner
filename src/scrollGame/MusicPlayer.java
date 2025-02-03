package scrollGame;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MusicPlayer {

    InputStream inputStream;
    BufferedInputStream bufferedInputStream;
    long totalLength;

    // different from GamePlayer !
    Player player;
    
    Thread playThread;
    
    MusicPlayer(){
      	playThread = new Thread(runnablePlay);
      	
    } //end constructor


	  Runnable runnablePlay=new Runnable() {
	      @Override
	      public void run() {
	          try {
	        	  inputStream = this.getClass().getResourceAsStream("resources/Music/ost2.mp3");
	        	  bufferedInputStream=new BufferedInputStream(inputStream);
	              player=new Player(bufferedInputStream);
	              totalLength=inputStream.available();
	              player.play();//starting music
	          } catch (FileNotFoundException e) {
	              e.printStackTrace();
	          } catch (JavaLayerException e) {
	              e.printStackTrace();
	          } catch (IOException e) {
	              e.printStackTrace();
	          }
	      }
	  };
	    
	    public void startMusic() {
		  playThread.start();
	  }
	
	
} // end class
