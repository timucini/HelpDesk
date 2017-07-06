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
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * @author Timur Burkholz (562205)
 * @version 2017-06-16
 */

public class Gui {
	static JFrame frame=new JFrame();
	private final JLabel lblWillkommenImHelpdesk = new JLabel("Willkommen im HelpDesk");
	private final JButton btnConnect = new JButton("Connect");
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mHilfe = new JMenu("Hilfe");
	private final JMenuItem mAbout = new JMenuItem("About");
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
		lblWillkommenImHelpdesk.setBounds(89, 32, 238, 29);
		
		frame.getContentPane().add(lblWillkommenImHelpdesk);
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnNewButton_actionPerformed(arg0);
			}
		});
		btnConnect.setBounds(122, 118, 137, 42);
		frame.getContentPane().add(btnConnect);
		menuBar.setBounds(0, 0, 419, 21);
		frame.getContentPane().add(menuBar);
		menuBar.add(mHilfe);
		mAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_mAbout_actionPerformed(arg0);
			}
		});
		
		mHilfe.add(mAbout);
		}
        
		/*
		 * Ruft die Main in connection auf, damit werden die Connections zu den DBs gesetzt
		 * die ConnectedGui wird aufgerufen und conGui visible, während die GUI geschlossen wird
		 */
	public void do_btnNewButton_actionPerformed(ActionEvent arg0) {		
		Connection.main(null);
		frame.setVisible(false); 
		ConnectedGui conGui=new ConnectedGui();
		ConnectedGui.conGui.setVisible(true);
	}
	protected void do_mAbout_actionPerformed(ActionEvent arg0) {
		JOptionPane.showMessageDialog(frame, "Author: Timur Burkholz (562205)\n @version 2017-06-16","About", 1);
	}
}