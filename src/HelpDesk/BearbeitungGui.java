package HelpDesk;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dbHelper.*;

import java.awt.Font;
import java.awt.TextArea;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;

import com.mysql.jdbc.Statement;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

/**
 * @author Timur Burkholz (562205)
 * @version 2017-06-28
 */

public class BearbeitungGui {
	static JFrame bGui=new JFrame();
	private final JLabel lblNewLabel = new JLabel("Tickets bearbeiten");
	private final JComboBox cTicket = new JComboBox();
	private final JComboBox cStatus = new JComboBox();
	private final JButton btnAnwenden = new JButton("anwenden");
	private final JButton btnNewButton = new JButton("zur\u00FCck");
	private final JTextPane tBeschreibung = new JTextPane();
	private final JLabel label = new JLabel("");
	private final JTextArea LoesungE = new JTextArea();
	private final JLabel lblNeueTicketsEinpflegen = new JLabel("Neue Tickets einpflegen");
	private final JComboBox cNeueTicket = new JComboBox();
	private final JComboBox BearbeiterBox = new JComboBox();
	private final JButton btnEin = new JButton("Einpflegen");
	
	/**
	 * Create the application.
	 */
	
public BearbeitungGui() {
	init();
}
	/**
	 * Initialize the contents of the frame.
	 */
private void init() {
	bGui.setBounds(100, 100, 726, 449);
	bGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	bGui.getContentPane().setLayout(null);
	lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
	lblNewLabel.setBounds(47, 11, 124, 28);
	
	bGui.getContentPane().add(lblNewLabel);
	cTicket.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			do_comboBox_actionPerformed(arg0);
		}
	});
	cTicket.setBorder(new TitledBorder(null, "Tickets ausw\u00E4hlen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	cTicket.setBounds(47, 62, 124, 44);
	
	bGui.getContentPane().add(cTicket);
	cStatus.setBorder(new TitledBorder(null, "Status setzen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	cStatus.setBounds(220, 62, 124, 44);
	
	bGui.getContentPane().add(cStatus);
	btnAnwenden.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			do_btnAnwenden_actionPerformed(arg0);
		}
	});
	btnAnwenden.setBounds(294, 334, 97, 23);
	
	bGui.getContentPane().add(btnAnwenden);
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			do_btnNewButton_actionPerformed(e);
		}
	});
	btnNewButton.setBounds(401, 334, 97, 23);
	
	bGui.getContentPane().add(btnNewButton);
	
	String table="`db136045x2588076`.`Ticket`";
	String column="`idTicket`";

	try {
		String[] tickets = Connection.conHP.getContentOfColumn(table, column);
	
	/*	Noch nicht integriert, Zusammenführung mit Ticketstatus und HelpDesk-DB
	 *  Vorgansweise mit Apache lang3 library
	    String[] both=(String[])ArrayUtils.addAll(neuTicket,tickets);
		Alternative mit Stream (ab java 8)
		String[] both=Stream.of(neuTicket,tickets).flatMap(Stream::of).toArray(String[]::new);
	
	*/	cTicket.setModel(new javax.swing.DefaultComboBoxModel(tickets));
		
		
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	tBeschreibung.setBorder(new TitledBorder(null, "Beschreibung", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	tBeschreibung.setBounds(47, 134, 298, 55);
	
	bGui.getContentPane().add(tBeschreibung);
	label.setBounds(220, 30, 199, 23);
	
	bGui.getContentPane().add(label);
	LoesungE.setBorder(new TitledBorder(null, "L\u00F6sungsweg protokollieren", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	LoesungE.setBounds(47, 211, 298, 112);
	
	bGui.getContentPane().add(LoesungE);
	/*
	 * ComboBox cStatus mit Stats aus HD-DB
	 */
	cStatus.addItem("Angenommen");
	cStatus.addItem("Offen");
	cStatus.addItem("Problembehandlung");
	cStatus.addItem("Geloest");
	cStatus.addItem("Geschlossen");
	lblNeueTicketsEinpflegen.setBounds(474, 19, 124, 20);
	
	bGui.getContentPane().add(lblNeueTicketsEinpflegen);
	cNeueTicket.setBorder(new TitledBorder(null, "Neue Tickets", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	cNeueTicket.setBounds(470, 58, 128, 48);
	
	bGui.getContentPane().add(cNeueTicket);
	BearbeiterBox.setBorder(new TitledBorder(null, "Bearbeiter ausw\u00E4hlen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	BearbeiterBox.setBounds(474, 134, 124, 60);
	
	bGui.getContentPane().add(BearbeiterBox);
	btnEin.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
				do_btnEin_actionPerformed(arg0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});
	btnEin.setBounds(473, 211, 125, 28);
	
	bGui.getContentPane().add(btnEin);
	
	String tableEin="`db136045x2588076`.`Bearbeiter`";
	String columnEin="`Nachname`";
	/*
	 * Holt sich Bearbeiternamen aus Bearbeitertabelle und Ticket-Ids mit Status 1(Neu)
	 * ComboBoxen werden gefüllt
	 */
	try {
		String[] neuTicketsB = Connection.conHP.getContentOfColumn(tableEin, columnEin);
		BearbeiterBox.setModel(new javax.swing.DefaultComboBoxModel(neuTicketsB));
		String[] neuTicket=Connection.conHP.getDataSetsSingleCol("SELECT idTicket FROM db136045x2588076.Ticket WHERE Status='1';");
		cNeueTicket.setModel(new javax.swing.DefaultComboBoxModel(neuTicket));
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
		
}
	/*
	 * Zurück zur ConGui
	 */
	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		bGui.setVisible(false);
		ConnectedGui.conGui.setVisible(true);
	}
	/*
	 * Ausgewählte und eingetragene Werte werden in HelpDesk-Datenbank übernommen
	 */
	protected void do_btnAnwenden_actionPerformed(ActionEvent arg0) {
		String id = String.valueOf(cTicket.getSelectedItem());
		String loesung=LoesungE.getText();
		try {
			Connection.conHP.updateDataSet("UPDATE `db136045x2588076`.`Ticket` SET `Solution`='"+loesung+"' WHERE `idTicket`='"+id+"'");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		if(cStatus.getSelectedItem()=="Angenommen")
			try {
				Connection.conHP.updateDataSet("UPDATE `db136045x2588076`.`Ticket` SET `Status`='2' WHERE `idTicket`='"+id+"'");
				JOptionPane.showMessageDialog(bGui,"Ticketstatus ge�ndert");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		if(cStatus.getSelectedItem()=="Offen")
			try {
				Connection.conHP.updateDataSet("UPDATE `db136045x2588076`.`Ticket` SET `Status`='3' WHERE `idTicket`='"+id+"'");
				JOptionPane.showMessageDialog(bGui,"Ticketstatus ge�ndert");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		if(cStatus.getSelectedItem()=="Problembehandlung")
			try {
				Connection.conHP.updateDataSet("UPDATE `db136045x2588076`.`Ticket` SET `Status`='4' WHERE `idTicket`='"+id+"'");
				JOptionPane.showMessageDialog(bGui,"Ticketstatus ge�ndert");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		if(cStatus.getSelectedItem()=="Geloest")
			/*
			 * Wenn Status geloest gesetzt wird, wird die Lösungsbeschreibung in die KEDB übernommen
			 * Dabei werden Fehler aus Beschreibung, Loesung aus TextArea und Betroffen aus dem HW/SW-Type übernommen und übergeben
			 */
			try {
				Connection.conHP.updateDataSet("UPDATE `db136045x2588076`.`Ticket` SET `Status`='5' WHERE `idTicket`='"+id+"'");
				JOptionPane.showMessageDialog(bGui,"Ticketstatus ge�ndert");
				String beschreibung=tBeschreibung.getText();
				String solution=LoesungE.getText();
				ArrayList<String> sw=Connection.conHP.getDataSetsSingleColList("SELECT SW FROM db136045x2588076.Ticket WHERE idTicket='"+id+"';");
				String software=Arrays.toString(sw.toArray()).replace("[", "").replace("]", "");
				ArrayList<String> hw=Connection.conHP.getDataSetsSingleColList("SELECT HW FROM db136045x2588076.Ticket WHERE idTicket='"+id+"';");
				String hardware=Arrays.toString(hw.toArray()).replace("[", "").replace("]", "");
				if(!software.equals("1")&&!hardware.equals("1")){
					JOptionPane.showMessageDialog(bGui, "Fehlerhafte Angabe im Ticket->Nur Hardware ODER Software!");
					}
				if(!software.equals("1")&&hardware.equals("1")){
					ArrayList<String> betroff=Connection.conHP.getDataSetsSingleColList("SELECT SWType FROM db136045x2588076.SW Where idSw='"+software+"'");
					String betroffen=Arrays.toString(betroff.toArray()).replace("[", "").replace("]", "");
					Connection.conKE.updateDataSet("INSERT INTO db136045x2588052.KEDB (Fehler,Hardware,Software,Betroffen,Loesungsvorschlag) VALUES ('"+beschreibung+"','0','1','"+betroffen+"','"+solution+"')");	
					}
				if(!hardware.equals("1")&&software.equals("1")){
					ArrayList<String> betroff=Connection.conHP.getDataSetsSingleColList("SELECT HWType FROM db136045x2588076.HW Where idHw='"+hardware+"'");
					String betroffen=Arrays.toString(betroff.toArray()).replace("[", "").replace("]", "");
					Connection.conKE.updateDataSet("INSERT INTO db136045x2588052.KEDB (Fehler,Hardware,Software,Betroffen,Loesungsvorschlag) VALUES ('"+beschreibung+"','1','0','"+betroffen+"','"+solution+"')");	
					}
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(bGui, "Fehler bereits in KEDB");
				e1.printStackTrace();
			} 
		if(cStatus.getSelectedItem()=="Geschlossen")
			try {
				Connection.conHP.updateDataSet("UPDATE `db136045x2588076`.`Ticket` SET `Status`='6' WHERE `idTicket`='"+id+"'");
				JOptionPane.showMessageDialog(bGui,"Ticketstatus ge�ndert");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
	}
	/*
	 * Füllt Beschreibung und Loesung aus dem Ticket, durch wählen einer TicketID
	 * Status wird im Label farblich angezeigt
	 */
	protected void do_comboBox_actionPerformed(ActionEvent arg0) {
		LoesungE.setText("");
		String idTicket = String.valueOf(cTicket.getSelectedItem());
		try {
			ArrayList<String> besch = Connection.conHP.getSingleDataSetList("SELECT Ticket.Beschreibung FROM db136045x2588076.Ticket WHERE idTicket="+idTicket);
			String list=Arrays.toString(besch.toArray()).replace("[", "").replace("]", "");
			tBeschreibung.setText(list);
			ArrayList<String> stat=Connection.conHP.getSingleDataSetList("SELECT Ticket.Status FROM db136045x2588076.Ticket WHERE idTicket="+idTicket);
			String status=Arrays.toString(stat.toArray()).replace("[", "").replace("]", "");
			if(status .equals("1")){
				label.setText("Status: Neu");
				label.setForeground(Color.BLUE);
			}
			if(status .equals("2")){
				label.setText("Status: Offen");
				label.setForeground(Color.BLUE);
			}
			if(status .equals("3")){
				label.setText("Status: Angenommen");
				label.setForeground(Color.BLUE);
			}
			if(status .equals("4")){
				label.setText("Status: Problembehandlung");
				label.setForeground(Color.BLUE);
		}
			if(status .equals("5")){
				label.setText("Status: Gelöst");
				label.setForeground(Color.BLUE);
		}
			if(status .equals("6")){
				label.setText("Status: Geschlossen");
				label.setForeground(Color.BLUE);
		}
			label.setVisible(true);
			ArrayList<String> loesung=Connection.conHP.getSingleDataSetList("SELECT Ticket.Solution FROM db136045x2588076.Ticket WHERE idTicket="+idTicket);
			String loesungen=Arrays.toString(loesung.toArray()).replace("[", "").replace("]", "");
			LoesungE.setText(loesungen);
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	 
	}
	/*
	 * Neuen Tickets wird ein Bearbeiter zugeweist
	 */
	protected void do_btnEin_actionPerformed(ActionEvent arg0) throws Exception {
		String idTicket = String.valueOf(cNeueTicket.getSelectedItem());
		String bearbeiter=String.valueOf(BearbeiterBox.getSelectedItem());
		ArrayList<String> idBearbeiter=Connection.conHP.getSingleDataSetList("SELECT idMitarbeiter FROM db136045x2588076.Bearbeiter WHERE Nachname='"+bearbeiter+"';");
        String bearb=Arrays.toString(idBearbeiter.toArray()).replace("[", "").replace("]", "");
		Connection.conHP.updateDataSet("UPDATE db136045x2588076.Ticket SET Bearbeiter_idMitarbeiter='"+bearb+"' WHERE idTicket='"+idTicket+"'");
		Connection.conHP.updateDataSet("UPDATE db136045x2588076.Ticket SET Status='2' WHERE idTicket='"+idTicket+"'");
		JOptionPane.showMessageDialog(bGui,"Bearbeiter hinzugefügt, Status geändert");	
	}
}
