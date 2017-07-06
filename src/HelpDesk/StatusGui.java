package HelpDesk;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import com.mysql.jdbc.Statement;
import dbHelper.*;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;

/**
 * @author Timur Burkholz (562205)
 * @version 2017-06-16
 */
public class StatusGui {
	static JFrame sGui=new JFrame();
	private final JLabel lblNewLabel = new JLabel("Ticketstatus");
	private final JTable table_1 = new JTable();
	private final JButton btnZurck_1 = new JButton("zur\u00FCck");
	private final JLabel lAnzahlTickets = new JLabel("Anzahl Tickets:");
	private final JLabel lAnzahl = new JLabel("Anzahl offener Tickets:");
	private final JLabel lAnzahlProb = new JLabel("Anzahl Problembehandlungen:");
	private final JLabel lgelT = new JLabel("Anzahl geschlossener Tickets:");	
	/**
	 * Create the application.
	 */
	public StatusGui(){
		init();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	public void init() {
	sGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	sGui.setBounds(100, 100, 450, 416);
	sGui.getContentPane().setLayout(null);
	lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
	lblNewLabel.setBounds(89, 11, 101, 22);
	sGui.getContentPane().add(table_1);
	sGui.getContentPane().add(lblNewLabel);
	table_1.setBounds(89, 58, 241, 155);	
	sGui.getContentPane().add(table_1);	
	String columnStatus="`Status`";	
	String table1="`db136045x2588076`.`Ticket`";
	String column="`idTicket`";	
	String[] ids = null;
	/*
	 * Alle Ids aus Ticket
	 */
	try {
		ids = Connection.conHP.getContentOfColumn(table1, column);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String[] stat=null;
	/*
	 * Alle Stats der Tickets
	 */	
	try {
		stat=Connection.conHP.getContentOfColumn(table1, columnStatus);
		                                                                                                                                                                                                  
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	/*
	 * Table wird gefüllt mit TicketIds und den zugehörigen Stats
	 */
	DefaultTableModel dtm=new DefaultTableModel(0,0);
	String header[]=new String[]{"Ticket-ID","Status"};
	dtm.setColumnIdentifiers(header);
	table_1.setModel(dtm);	
	for (int index =0; index < stat.length; index++){
	    stat[index] = stat[index].replace("1", "Neu").replace("2","Offen").replace("3","Angenommen").replace("4","Problembehandlung").replace("5", "Gelöst").replace("6", "Geschlossen");
	}   
	for (int i = 0; i < ids.length; i++) {
        dtm.addRow(new Object[] { ids[i], stat[i]}
        );}
	btnZurck_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			do_btnZurck_1_actionPerformed(arg0);
		}
	});
	btnZurck_1.setBounds(335, 343, 89, 23);	
	sGui.getContentPane().add(btnZurck_1);
	JScrollPane js=new JScrollPane(table_1);
	js.setBounds(71, 58, 291, 155);
	js.setVisible(true);
	sGui.getContentPane().add(js);
	lAnzahlTickets.setBounds(71, 248, 208, 22);	
	sGui.getContentPane().add(lAnzahlTickets);
	lAnzahl.setBounds(71, 269, 208, 28);	
	sGui.getContentPane().add(lAnzahl);
	lAnzahlProb.setBounds(71, 301, 208, 22);	
	sGui.getContentPane().add(lAnzahlProb);
	lgelT.setBounds(71, 328, 208, 22);	
	sGui.getContentPane().add(lgelT);	
	/*
	 * Statistische Auswertung(einfach)
	 */
	try {
		ArrayList<String> tickets=Connection.conHP.getDataSetsSingleColList("SELECT idTicket FROM db136045x2588076.Ticket");
		lAnzahlTickets.setText("Anzahl Tickets:"+tickets.size());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		ArrayList<String> ticketsO=Connection.conHP.getDataSetsSingleColList("SELECT idTicket FROM db136045x2588076.Ticket WHERE Status='1'");
		lAnzahl.setText("Anzahl neuer Tickets:"+ticketsO.size());
		lAnzahl.setForeground(Color.RED);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		ArrayList<String> ticketsP=Connection.conHP.getDataSetsSingleColList("SELECT idTicket FROM db136045x2588076.Ticket WHERE Status='4'");
		lAnzahlProb.setText("Anzahl Problembehandlungen:"+ticketsP.size());
		lAnzahlProb.setForeground(Color.BLUE);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		ArrayList<String> ticketsP=Connection.conHP.getDataSetsSingleColList("SELECT idTicket FROM db136045x2588076.Ticket WHERE Status='6'");
		lgelT.setText("Anzahl geschlossener Tickets:"+ticketsP.size());
		lgelT.setForeground(Color.GREEN);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	/*
	 * Zurück zur ConGui
	 */
	protected void do_btnZurck_1_actionPerformed(ActionEvent arg0) {
		sGui.setVisible(false);
		ConnectedGui.conGui.setVisible(true);
	}
}

