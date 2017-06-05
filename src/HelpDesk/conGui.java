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


public class conGui {
	
	static JFrame cGui=new JFrame();
	private final  JButton btnNewButton = new JButton("Hier klicken");
	private final  JLabel lblNeuesTicketAnlegen = new JLabel("Neues Ticket anlegen");
	private final  JLabel lblTicketBearbeiten = new JLabel("Ticket bearbeiten");
	private final  JButton btnHierKlicken = new JButton("Hier klicken");
	private final  JLabel lblTicketstatus = new JLabel("Ticketstatus");
	private final  JButton btnHierKlicken_1 = new JButton("Hier klicken");
	private final  JLabel lblLsungsvorschlge = new JLabel("L\u00F6sungsvorschl\u00E4ge");
	private final  JButton btnHierKlicken_2 = new JButton("Hier klicken");


	public conGui() {
	init();
	}
	
	private void init() {
		cGui=new JFrame();
		cGui.setBounds(100, 100, 435, 283);
		cGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cGui.getContentPane().setLayout(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnNewButton_actionPerformed(arg0);
			}
		});
		
		btnNewButton.setBounds(224, 29, 89, 29);
		
		cGui.getContentPane().add(btnNewButton);
		lblNeuesTicketAnlegen.setBounds(89, 29, 111, 29);
		
		cGui.getContentPane().add(lblNeuesTicketAnlegen);
		lblTicketBearbeiten.setBounds(87, 87, 113, 29);
		
		cGui.getContentPane().add(lblTicketBearbeiten);
		btnHierKlicken.setBounds(224, 87, 89, 29);
		
		cGui.getContentPane().add(btnHierKlicken);
		lblTicketstatus.setBounds(89, 147, 119, 29);
		
		cGui.getContentPane().add(lblTicketstatus);
		btnHierKlicken_1.setBounds(224, 147, 89, 29);
		
		cGui.getContentPane().add(btnHierKlicken_1);
		lblLsungsvorschlge.setBounds(89, 200, 111, 35);
		
		cGui.getContentPane().add(lblLsungsvorschlge);
		btnHierKlicken_2.setBounds(224, 206, 89, 29);
		
		cGui.getContentPane().add(btnHierKlicken_2);
	}
	protected void do_btnNewButton_actionPerformed(ActionEvent arg0) {
		cGui.setVisible(false);
		einGui eGui=new einGui();
		einGui.eGui.setVisible(true);
	}
}
