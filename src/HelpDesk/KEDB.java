package HelpDesk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
/**
 * @author Timur Burkholz (562205)
 * @version 2017-06-16
 */
public class KEDB {
	static JFrame kGui=new JFrame();
	private final JComboBox cType = new JComboBox();
	private final JComboBox cHsType = new JComboBox();
	private final JComboBox cCis = new JComboBox();
	private final JTable tableLoesung = new JTable();
	private final JTable tableCis = new JTable();
	private final JLabel lTitel = new JLabel("Nach L\u00F6sungsvorschl\u00E4gen suchen");
	private final JLabel lTitelneu = new JLabel("Ticketverlauf der Cis anzeigen");
	private final JButton btnZurck = new JButton("Zur\u00FCck");
	private final JSeparator separator = new JSeparator();
	private final JLabel lblNeuenLsungsvorschlagAnlegen = new JLabel("Neuen L\u00F6sungsvorschlag anlegen");
	private final JTextField textFehler = new JTextField();
	private final JComboBox cNeuTyp = new JComboBox();
	private final JTextField textBetrof = new JTextField();
	private final JTextArea textNeuLoesung = new JTextArea();
	private final JLabel lLV = new JLabel("L\u00F6sungsvorschlag");
	private final JButton btnAnlegen = new JButton("anlegen");
	/**
	 * Create the application.
	 */
public KEDB() {
	init();
	}
	/**
	 * Initialize the contents of the frame.
	 */		
	private void init() {
		kGui.setBounds(100, 100, 883, 515);
		kGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		kGui.getContentPane().setLayout(null);
		cType.setBorder(new TitledBorder(null, "Bitte w\u00E4hlen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					do_comboBox_actionPerformed(e);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		cType.setBounds(47, 50, 85, 44);	
		kGui.getContentPane().add(cType);
		cHsType.setBorder(new TitledBorder(null, "Bitte w\u00E4hlen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cHsType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					do_comboBox_1_actionPerformed(arg0);
					} catch (Exception e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
					}
				}
			});
		cHsType.setBounds(166, 50, 86, 44);
		cType.addItem("Hardware");
		cType.addItem("Software");	
		kGui.getContentPane().add(cHsType);
		cCis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					do_comboBox_2_actionPerformed(arg0);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});			
		cCis.setBorder(new TitledBorder(null, "CI-ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cCis.setBounds(47, 285, 85, 44);				
		kGui.getContentPane().add(cCis);
		tableLoesung.setBorder(null);
		tableLoesung.setBounds(47, 105, 463, 139);
		kGui.getContentPane().add(tableLoesung);
		tableCis.setBounds(142, 285, 368, 147);			
		kGui.getContentPane().add(tableCis);
		String table="`db136045x2588076`.`CIs`";
		String columnID="`id`";
			try {
				String[] s = Connection.conHP.getContentOfColumn(table, columnID);
				cCis.setModel(new javax.swing.DefaultComboBoxModel(s));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		lTitel.setBounds(47, 11, 190, 28);			
		kGui.getContentPane().add(lTitel);
		lTitelneu.setBounds(47, 255, 190, 28);
		kGui.getContentPane().add(lTitelneu);
		btnZurck.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					do_btnZurck_actionPerformed(arg0);
					}
				});
		btnZurck.setBounds(702, 442, 89, 23);				
		kGui.getContentPane().add(btnZurck);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.BLACK);
		separator.setBounds(558, 11, 1, 454);				
		kGui.getContentPane().add(separator);
		textBetrof.setBorder(new TitledBorder(null, "Betroffen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textBetrof.setBounds(713, 120, 117, 36);
		textBetrof.setColumns(10);
		textFehler.setBorder(new TitledBorder(null, "Fehlername", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textFehler.setBounds(586, 120, 117, 36);
		textFehler.setColumns(10);
		separator.setVisible(true);
		kGui.getContentPane().add(separator);
		lblNeuenLsungsvorschlagAnlegen.setBounds(595, 50, 218, 28);			
		kGui.getContentPane().add(lblNeuenLsungsvorschlagAnlegen);			
		kGui.getContentPane().add(textFehler);
		cNeuTyp.setBounds(586, 168, 101, 36);			
		kGui.getContentPane().add(cNeuTyp);			
		kGui.getContentPane().add(textBetrof);
		textNeuLoesung.setBounds(595, 257, 205, 91);			
		kGui.getContentPane().add(textNeuLoesung);
		lLV.setBounds(595, 230, 101, 28);			
		kGui.getContentPane().add(lLV);
		btnAnlegen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btnAnlegen_actionPerformed(e);
				}
				});
		btnAnlegen.setBounds(756, 365, 89, 23);			
		kGui.getContentPane().add(btnAnlegen);
		cNeuTyp.addItem("Hardware");
		cNeuTyp.addItem("Software");			
	}
	/*
	 * Wenn gesuchtes Hardware/Software-Teil ausgewählt ist in ComboBox, wird in einer Table Fehlername und Loesung in Table dargestellt
	 */
	protected void do_comboBox_1_actionPerformed(ActionEvent arg0) throws Exception {
		String betrof=String.valueOf(cHsType.getSelectedItem());
		DefaultTableModel tb1=new DefaultTableModel(0,0);
		String header[]=new String[]{"Fehler","Loesungsansatz"};
		tb1.setColumnIdentifiers(header);
		tableLoesung.setModel(tb1);
		tb1.addRow(header);
		ArrayList<String> fehl=null;
		fehl=Connection.conKE.getDataSetsSingleColList("SELECT `Fehler` FROM `db136045x2588052`.`KEDB` WHERE `Betroffen`='"+betrof+"'");
		ArrayList<String> loes=Connection.conKE.getDataSetsSingleColList("SELECT `Loesungsvorschlag` FROM `db136045x2588052`.`KEDB` WHERE `Betroffen`='"+betrof+"'");
		String[] fehler=new String[fehl.size()];
		fehler=fehl.toArray(fehler);
		String[] loesung=new String[loes.size()];
		loesung=loes.toArray(loesung);
		for (int i = 0; i < fehler.length; i++) {			
	        tb1.addRow(new Object[] {fehler[i], loesung[i]}
	        );}	
		}	
	/*
	 * Wenn Hardware oder Software ausgewählt werden jeweiligen Typen in ComboBox gefüllt
	 */
	protected void do_comboBox_actionPerformed(ActionEvent e) throws Exception {
		if(cType.getSelectedItem()=="Hardware"){
				ArrayList<String> meh=Connection.conKE.getDataSetsSingleColList("SELECT `Betroffen` FROM `db136045x2588052`.`KEDB` WHERE `Hardware`='1'");
				cHsType.setModel(new DefaultComboBoxModel(meh.toArray()));
		}
		if(cType.getSelectedItem()=="Software") {
					String[] soft = Connection.conKE.getDataSetsSingleCol("SELECT `Betroffen` FROM `db136045x2588052`.`KEDB` WHERE `Software`='1'");
					cHsType.setModel(new javax.swing.DefaultComboBoxModel(soft));				
			}
		}
		
	/*
	 * Wenn Cid ausgewählt, wird Ticketverlauf dieser CIs dargestellt
	 */
	protected void do_comboBox_2_actionPerformed(ActionEvent arg0) throws Exception {
		String id=String.valueOf(cCis.getSelectedItem());
		DefaultTableModel dtm=new DefaultTableModel(0,0);
		String header[]=new String[]{"Beschreibung","Loesung"};
		dtm.setColumnIdentifiers(header);
		tableCis.setModel(dtm);
		dtm.addRow(header);
		ArrayList<String> idT=null;
		try{
			idT=Connection.conHP.getDataSetsSingleColList("SELECT Beschreibung FROM db136045x2588076.Ticket WHERE CIs_id="+id+";");
			ArrayList<String> beschreibung=Connection.conHP.getDataSetsSingleColList("SELECT Solution FROM db136045x2588076.Ticket WHERE CIs_id="+id+";");
			String[] tickets=new String[idT.size()];
			tickets=idT.toArray(tickets);
			String[] beschr=new String[beschreibung.size()];
			beschr=beschreibung.toArray(beschr);
			for (int i = 0; i < tickets.length; i++) {
			
				dtm.addRow(new Object[] { tickets[i], beschr[i]}
	        );}
		if (tickets.length==0 && beschr.length==0) {
				dtm.addRow(new Object[] {"Kein Ticket vorhanden","Leer"});
			}	
		}
		catch(SQLException e){
			} 
	}
	/*
	 * Zurück zur conGui
	 */
	protected void do_btnZurck_actionPerformed(ActionEvent arg0) {
		kGui.setVisible(false);
		ConnectedGui.conGui.setVisible(true);
	}
	/*
	 * Neuen Eintrag in KEDB anlegen
	 */
	protected void do_btnAnlegen_actionPerformed(ActionEvent e) {
		String eingabe1=textFehler.getText();
		String eingabe2=null;
		String eingabe3=null;
		if (cNeuTyp.getSelectedItem()=="Hardware") {
			eingabe2="1";
			eingabe3="0";
		}
		if(cNeuTyp.getSelectedItem()=="Software") {
			eingabe2="0";
			eingabe3="1";
			}
		String eingabe4=textBetrof.getText();
		String eingabe5=textNeuLoesung.getText();
		
		String val="'"+eingabe1+"',"+"'"+eingabe2+"',"+"'"+eingabe3+"',"+"'"+eingabe4+"',"+"'"+eingabe5+"'";
			try {
				Connection.conKE.insertDataSet("INSERT INTO `db136045x2588052`.`KEDB` ( `Fehler`, `Hardware`, `Software`, `Betroffen`, `Loesungsvorschlag`)"+" VALUES ("+val+")");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(kGui, "Loesungsvorschlag eingetragen");
			kGui.setVisible(false);
			ConnectedGui.conGui.setVisible(true);
		
	}
}


