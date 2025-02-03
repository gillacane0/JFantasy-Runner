
package scrollGame;

import java.awt.Dimension;


import java.awt.Toolkit;

import javax.swing.JFrame;



public class MainGUI {
	
   public static void main(String[] args) {     
       javax.swing.SwingUtilities.invokeLater(new Runnable() {
           public void run() {
        	   MainFrame frame = new MainFrame();
        	   frame.setSize(700,700);		
        	   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                		
        	   frame.setLocation((int)(screenSize.getWidth()/2 - frame.getSize().getWidth()/2), (int)(screenSize.getHeight()/2 - frame.getSize().getHeight()/2 ));
                	
        	   frame.setResizable(false);
        	   frame.setTitle("JFantasy Runner");
        	   frame.setVisible(true);
        	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        

           }
       }); 
   }
   

}  // end class