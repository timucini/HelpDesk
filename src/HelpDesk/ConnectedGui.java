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
import javax.swing.JMenuBar;
/**
 * @author Timur Burkholz (562205)
 * @version 2017-06-28
 */
public class ConnectedGui {
	
	static JFrame conGui=new JFrame();
	private final  JButton bNeu = new JButton("Hier klicken");
	private final  JLabel lblNeuesTicketAnlegen = new JLabel("Neues Ticket anlegen");
	private final  JLabel lblTicketBearbeiten = new JLabel("Ticket bearbeiten");
	private final  JButton bBearbeiten = new JButton("Hier klicken");
	private final  JLabel lblTicketstatus = new JLabel("Ticketstatus");
	private final  JButton bStatus = new JButton("Hier klicken");
	private final  JLabel lblLsungsvorschlge = new JLabel("L\u00F6sungsvorschl\u00E4ge");
	private final  JButton bLoesung = new JButton("Hier klicken");
	private final JLabel lblDbVerbunden = new JLabel("Db nicht verbunden");
	private final JMenuBar menuBar = new JMenuBar();

	/**
	 * Create the application.
	 */
	public ConnectedGui() {
	init();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void init() {
		conGui=new JFrame();
		conGui.setBounds(100, 100, 458, 294);
		conGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		conGui.getContentPane().setLayout(null);
		bNeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_bNeu_actionPerformed(arg0);
			}
		});
		
		bNeu.setBounds(188, 29, 169, 29);		
		conGui.getContentPane().add(bNeu);
		lblNeuesTicketAnlegen.setBounds(51, 29, 127, 29);		
		conGui.getContentPane().add(lblNeuesTicketAnlegen);
		lblTicketBearbeiten.setBounds(51, 87, 127, 29);		
		conGui.getContentPane().add(lblTicketBearbeiten);
		bBearbeiten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_bBearbeiten_actionPerformed(arg0);
			}
		});
		bBearbeiten.setBounds(188, 87, 169, 29);		
		conGui.getContentPane().add(bBearbeiten);
		lblTicketstatus.setBounds(51, 147, 127, 29);		
		conGui.getContentPane().add(lblTicketstatus);
		bStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_bStatus_actionPerformed(e);
			}
		});
		bStatus.setBounds(188, 147, 169, 29);
		conGui.getContentPane().add(bStatus);
		lblLsungsvorschlge.setBounds(51, 203, 127, 35);		
		conGui.getContentPane().add(lblLsungsvorschlge);
		bLoesung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_bLoesung_actionPerformed(arg0);
			}
		});
		bLoesung.setBounds(188, 206, 169, 29);
		conGui.getContentPane().add(bLoesung);
		menuBar.setBounds(0, 0, 442, 19);		
		conGui.getContentPane().add(menuBar);
		menuBar.add(lblDbVerbunden);
		lblDbVerbunden.setForeground(Color.RED);
		
		/*
		 * Wenn Verbindungen steht, wird Label in Menubar Grün und geändert
		 */
		if(Connection.conHP.isConnected()&&Connection.conKE.isConnected()) {
		lblDbVerbunden.setForeground(Color.GREEN);
		lblDbVerbunden.setText("DB verbunden");
		}
	}
		
	/* 
	 * Actionlistener auf den Buttons -> Weiterleitung an die anderen Gui-Klassen
	 */	
	protected void do_bNeu_actionPerformed(ActionEvent arg0) {
		conGui.setVisible(false);
		EingabeGui eGui=new EingabeGui();
		EingabeGui.eGui.setVisible(true);
	}
	protected void do_bBearbeiten_actionPerformed(ActionEvent arg0) {
		conGui.setVisible(false);
		BearbeitungGui bGui=new BearbeitungGui();
		BearbeitungGui.bGui.setVisible(true);
		
	}
	protected void do_bStatus_actionPerformed(ActionEvent e) {
		Gui.frame.setVisible(false);
		StatusGui sGui=new StatusGui();
		StatusGui.sGui.setVisible(true);
	}
	protected void do_bLoesung_actionPerformed(ActionEvent arg0) {
		conGui.setVisible(false);
		KEDB kGui=new KEDB();
		KEDB.kGui.setVisible(true);
	}
}
