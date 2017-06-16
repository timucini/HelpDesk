package HelpDesk;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import dbHelper.*;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Gui {

	
	static JFrame frame=new JFrame();
	private final JLabel lblWillkommenImHelpdesk = new JLabel("Willkommen im HelpDesk");
	private final JLabel lblMadeByMatthias = new JLabel("Made by Matthias Cohn and Timur Burkholz");
	private final JButton btnNewButton = new JButton("Connect");
	
	
	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 435, 283);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		lblWillkommenImHelpdesk.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblWillkommenImHelpdesk.setBounds(97, 34, 238, 29);
		
		frame.getContentPane().add(lblWillkommenImHelpdesk);
		lblMadeByMatthias.setBounds(89, 172, 324, 14);
		
		frame.getContentPane().add(lblMadeByMatthias);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnNewButton_actionPerformed(arg0);
			}
		});
		btnNewButton.setBounds(97, 94, 137, 42);
		
		frame.getContentPane().add(btnNewButton);
		}
        
		public void do_btnNewButton_actionPerformed(ActionEvent arg0) {		
			
			Connection con=new Connection();
			
	
			frame.setVisible(false); 
			conGui cGui=new conGui();
			conGui.cGui.setVisible(true);
	
	
	}
}