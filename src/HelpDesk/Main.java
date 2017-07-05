package HelpDesk;

import java.awt.EventQueue;

/**
 * @author Timur Burkholz (562205)
 * @version 2017-06-16
 */



public class Main {
	
/* 
 * Die Mainklasse ruft die Gui auf
 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
