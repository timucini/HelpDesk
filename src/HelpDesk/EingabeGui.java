package HelpDesk;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.mysql.jdbc.Statement;

import dbHelper.*;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.awt.event.ActionEvent;

/**
 * @author Timur Burkholz (562205)
 * @version 2017-06-28
 */
public class EingabeGui {
	static JFrame eGui=new JFrame();
	private final JLabel lblEingabeTicket = new JLabel("Eingabe Ticket");
	private final JTextField textMitarbeiter = new JTextField();
	private final JComboBox cHw = new JComboBox();
	private final JTextArea textBeschreibung = new JTextArea();
	private final JComboBox cBetrof = new JComboBox();
	private final JButton bEinf = new JButton("Einf\u00FCgen");
	private final JOptionPane ticketErfolg=new JOptionPane();
	private final JButton bBack = new JButton("zur\u00FCck");
	private final JComboBox cCid = new JComboBox();
	private final JComboBox cBearbeiter = new JComboBox();
	
	
	/**
	 * Create the application.
	 */
	public EingabeGui() {
		init();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void init() {
		eGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		eGui.setBounds(100, 100, 491, 300);
		eGui.getContentPane().setLayout(null);
		
		lblEingabeTicket.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblEingabeTicket.setBounds(23, 27, 127, 28);
		
		eGui.getContentPane().add(lblEingabeTicket);
		textMitarbeiter.setColumns(10);
		textMitarbeiter.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Mitarbeiter", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textMitarbeiter.setBounds(274, 66, 105, 39);
		
		eGui.getContentPane().add(textMitarbeiter);
		cHw.setBorder(new TitledBorder(null, "Bitte ausw\u00E4hlen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cHw.setBounds(23, 116, 105, 39);
		
		eGui.getContentPane().add(cHw);
		textBeschreibung.setBorder(new TitledBorder(null, "Beschreibung", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textBeschreibung.setBounds(148, 116, 204, 72);
		cHw.addItem("Hardware");
		cHw.addItem("Software");
		
		eGui.getContentPane().add(textBeschreibung);
		cBetrof.setBorder(new TitledBorder(null, "Bitte ausw\u00E4hlen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cBetrof.setBounds(23, 166, 105, 39);

		
		eGui.getContentPane().add(cBetrof);
		bEinf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					do_bEinf_actionPerformed(arg0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		bEinf.setBounds(227, 199, 118, 39);
		
		eGui.getContentPane().add(bEinf);
		bBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_bBack_actionPerformed(arg0);
			}
		});
		bBack.setBounds(376, 215, 89, 23);
		
		eGui.getContentPane().add(bBack);
		cHw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_comboBox_actionPerformed(arg0);
			}
		});
		cCid.setBorder(new TitledBorder(null, "Ci-ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cCid.setBounds(23, 65, 105, 40);
		
		eGui.getContentPane().add(cCid);
		cBearbeiter.setBorder(new TitledBorder(null, "Bearbeiter", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		eGui.getContentPane().add(cBearbeiter);
		cBearbeiter.setBounds(148, 62, 105, 43);
		/*
		 * Füllt die ComboBox cCid mit den Cids aus der HD-Datenbank
		 */
		String table="`db136045x2588076`.`CIs`";
		String column="`id`";
		try {
			String[] s = Connection.conHP.getContentOfColumn(table, column);
			cCid.setModel(new javax.swing.DefaultComboBoxModel(s));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*
		 * Füllt die ComboBox cBearbeiter mit den Bearbeiternamen aus der HP-DB.Bearbeiter
		 */
		String tableBearbeiter="`db136045x2588076`.`Bearbeiter`";
		String columNachname="`Nachname`";
		try {
			String[] bearb=Connection.conHP.getContentOfColumn(tableBearbeiter, columNachname);
			cBearbeiter.setModel(new javax.swing.DefaultComboBoxModel(bearb));
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		}
		
	/*
	 * Nimmt die ausgewählten und eingetragenen Werte aus den ComboBoxen,TextAreas und fügt diese als
	 * neues Ticket in die HelpDesk Datenbank ein
	 */
	protected void do_bEinf_actionPerformed(ActionEvent arg0) throws Exception {
	
        
        String eingabeCI=String.valueOf(cCid.getSelectedItem());
        String hardware="1";
        String software="1";
        if (cHw.getSelectedItem()=="Software") {
        	String eingabeSw=String.valueOf(cBetrof.getSelectedItem());
        	ArrayList<String> idSW=Connection.conHP.getSingleDataSetList("SELECT idSW FROM db136045x2588076.SW WHERE SWType='"+eingabeSw+"';");
        	software=Arrays.toString(idSW.toArray()).replace("[", "").replace("]", "");
        	} 
        if (cHw.getSelectedItem()=="Hardware"){
        	String eingabeHW=String.valueOf(cBetrof.getSelectedItem());
        	ArrayList<String> idHW=Connection.conHP.getSingleDataSetList("SELECT idHW FROM db136045x2588076.HW WHERE HWType='"+eingabeHW+"';");
        	hardware=Arrays.toString(idHW.toArray()).replace("[", "").replace("]", "");
        	}
        String eingabeBearbeiter=String.valueOf(cBearbeiter.getSelectedItem());
        ArrayList<String> idBearbeiter=Connection.conHP.getSingleDataSetList("SELECT idMitarbeiter FROM db136045x2588076.Bearbeiter WHERE Nachname='"+eingabeBearbeiter+"';");
        String bearbeiter=Arrays.toString(idBearbeiter.toArray()).replace("[", "").replace("]", "");
        String eingabeMitarbeiter=textMitarbeiter.getText();
        String eingabeBeschreibung=textBeschreibung.getText();
        
        String eingabe="'"+eingabeCI+"',"+"'"+software+"',"+"'"+hardware+"',"+"'"+bearbeiter+"',"+"'"+eingabeBeschreibung+"','3'"+",'"+eingabeMitarbeiter+"'";     
  
        try {
			Connection.conHP.insertDataSet("INSERT INTO `db136045x2588076`.`Ticket` ( `CIs_id`, `SW`, `HW`,`Bearbeiter_idMitarbeiter`, `Beschreibung`, `Status`,`Mitarbeiter`)"+" VALUES ("+eingabe+")");
			JOptionPane.showMessageDialog(eGui, "Ticket eingetragen");
			eGui.setVisible(false);
			ConnectedGui.conGui.setVisible(true);
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(eGui, "Ticket-Eingabe fehlerhaft!");
			}
		}
	/*
	 * Bei Klick zurück zur ConnectedGui
	 */

	protected void do_bBack_actionPerformed(ActionEvent arg0) {
		eGui.setVisible(false);
		ConnectedGui.conGui.setVisible(true);
	}
	
	/*
	 * Nach Auswahl in ComboBox cHW mit Hardware wird ComboBox cBetrof mit HardwareTypen von der HelpDesk gefüllt
	 * Bei Softwareauswahl werden die SWTypen genommen
	 */
	protected void do_comboBox_actionPerformed(ActionEvent arg0) {
		String tableHW="`db136045x2588076`.`HW`";
		String tableSW="`db136045x2588076`.`SW`";
		String columnHW="`HWType`";
		String columnSW="`SWType`";
		if(cHw.getSelectedItem()=="Hardware") {
			try {
				String[] hardware = Connection.conHP.getContentOfColumn(tableHW, columnHW);
				List<String> listHardware=new ArrayList<String>(Arrays.asList(hardware));
				listHardware.removeAll(Collections.singleton(null));
				String[] hard=listHardware.toArray(new String[listHardware.size()]);
				cBetrof.setModel(new javax.swing.DefaultComboBoxModel(hard));
			
			} catch (Exception e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}
		if(cHw.getSelectedItem()=="Software") {
			try {
				String[] software = Connection.conHP.getContentOfColumn(tableSW, columnSW);
				List<String> listSoftware=new ArrayList<String>(Arrays.asList(software));
				listSoftware.removeAll(Collections.singleton(null));
				String[] soft=listSoftware.toArray(new String[listSoftware.size()]);
				cBetrof.setModel(new javax.swing.DefaultComboBoxModel(soft));
				
			 } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 	}
			}
	}
}


