package scrollGame;

public class MainFrame extends javax.swing.JFrame{
	
	
	public MainFrame() {
				
		GamePanel panel = new GamePanel();
		panel.setLocation(0,0);
		panel.setSize(this.getSize());
		panel.setVisible(true);
		this.add(panel);

		addKeyListener(new KeyChecker(panel));
		
	}	// end constructor
	

} // end class
