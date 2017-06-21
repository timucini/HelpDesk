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
import java.awt.Color;


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
	private final JLabel lblDbVerbunden = new JLabel("Db nicht verbunden");


	public conGui() {
	init();
	}
	
	private void init() {
		cGui=new JFrame();
		cGui.setBounds(100, 100, 458, 351);
		cGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cGui.getContentPane().setLayout(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnNewButton_actionPerformed(arg0);
			}
		});
		
		btnNewButton.setBounds(188, 29, 89, 29);
		
		cGui.getContentPane().add(btnNewButton);
		lblNeuesTicketAnlegen.setBounds(51, 29, 111, 29);
		
		cGui.getContentPane().add(lblNeuesTicketAnlegen);
		lblTicketBearbeiten.setBounds(51, 87, 113, 29);
		
		cGui.getContentPane().add(lblTicketBearbeiten);
		btnHierKlicken.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnHierKlicken_actionPerformed(arg0);
			}
		});
		btnHierKlicken.setBounds(188, 87, 89, 29);
		
		cGui.getContentPane().add(btnHierKlicken);
		lblTicketstatus.setBounds(51, 147, 119, 29);
		
		cGui.getContentPane().add(lblTicketstatus);
		btnHierKlicken_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnHierKlicken_1_actionPerformed(e);
			}
		});
		btnHierKlicken_1.setBounds(188, 147, 89, 29);
		
		cGui.getContentPane().add(btnHierKlicken_1);
		lblLsungsvorschlge.setBounds(51, 203, 111, 35);
		
		cGui.getContentPane().add(lblLsungsvorschlge);
		btnHierKlicken_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnHierKlicken_2_actionPerformed(arg0);
			}
		});
		btnHierKlicken_2.setBounds(188, 206, 89, 29);
		
		cGui.getContentPane().add(btnHierKlicken_2);
		lblDbVerbunden.setForeground(Color.RED);
		lblDbVerbunden.setBounds(81, 281, 196, 19);
		
		cGui.getContentPane().add(lblDbVerbunden);
		

		lblDbVerbunden.setForeground(Color.GREEN);
		lblDbVerbunden.setText("DB verbunden");
		}
	
	protected void do_btnNewButton_actionPerformed(ActionEvent arg0) {
		cGui.setVisible(false);
		einGui eGui=new einGui();
		einGui.eGui.setVisible(true);
	}
	protected void do_btnHierKlicken_actionPerformed(ActionEvent arg0) {
		cGui.setVisible(false);
		beGui bGui=new beGui();
		beGui.bGui.setVisible(true);
		
	}
	protected void do_btnHierKlicken_1_actionPerformed(ActionEvent e) {
		Gui.frame.setVisible(false);
		statusGui sGui=new statusGui();
		statusGui.sGui.setVisible(true);
	}
	protected void do_btnHierKlicken_2_actionPerformed(ActionEvent arg0) {
		cGui.setVisible(false);
		KEDB kGui=new KEDB();
		KEDB.kGui.setVisible(true);
		
	}
}
