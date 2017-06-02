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

	static DBCon conHP=new DBCon();
	static DBCon conTS=new DBCon();
	static DBCon conKE=new DBCon();
	JFrame frame;
	private final JLabel lblWillkommenImHelpdesk = new JLabel("Willkommen im HelpDesk");
	private final JLabel lblMadeByMatthias = new JLabel("Made by Matthias Cohn and Timur Burkholz");
	private final JButton btnNewButton = new JButton("New button");
	
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
		frame.setBounds(100, 100, 800, 366);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		lblWillkommenImHelpdesk.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblWillkommenImHelpdesk.setBounds(97, 34, 238, 29);
		
		frame.getContentPane().add(lblWillkommenImHelpdesk);
		lblMadeByMatthias.setBounds(545, 302, 229, 14);
		
		frame.getContentPane().add(lblMadeByMatthias);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnNewButton_actionPerformed(arg0);
			}
		});
		btnNewButton.setBounds(301, 152, 137, 42);
		
		frame.getContentPane().add(btnNewButton);
	}

	protected static void do_btnNewButton_actionPerformed(ActionEvent arg0) {
	
	//Verbindung zur Helpdesk Datendank
		conHP.setConnection("mysql.webhosting47.1blu.de:3306", "s136045_2588076", "HelpDesk1!=0");
	try {
		conHP.connect();
		System.out.println("Verbindungen steht zur HP-DB");
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		System.out.println("Fehler bei Verbindung zur HP-DB");
	}
	//Verbindung zur Ticketstatus DB
		conTS.setConnection("mysql.webhosting47.1blu.de;3306", "s136045_2588057", "TicketStatus1!=0");
	try {
		conTS.connect();
		System.out.println("Verbindungen steht zur TS-DB");
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		System.out.println("Fehler bei Verbindung zur TS-DB");
	}
	//Verbindung zur KEDB
		conKE.setConnection("mysql.webhosting47.1blu.de;3306", "s136045_2588052", "KEDB1!=0");
		try {
			conKE.connect();
			System.out.println("Verbindungen steht zur KEDB");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Fehler bei Verbindung zur KEDB");
		}
		
	}
}