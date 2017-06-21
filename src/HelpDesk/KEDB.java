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

public class KEDB {
	static JFrame kGui=new JFrame();
	private final JComboBox comboBox = new JComboBox();
	private final JComboBox comboBox_1 = new JComboBox();
	private final JComboBox comboBox_2 = new JComboBox();
	private final JTable table_1 = new JTable();
	private final JTable table_2 = new JTable();
	private final JLabel lblNewLabel = new JLabel("Nach L\u00F6sungsvorschl\u00E4gen suchen");
	private final JLabel lblNewLabel_1 = new JLabel("Ticketverlauf der Cis anzeigen");
	private final JButton btnZurck = new JButton("Zur\u00FCck");
	private final JSeparator separator = new JSeparator();
	private final JLabel lblNeuenLsungsvorschlagAnlegen = new JLabel("Neuen L\u00F6sungsvorschlag anlegen");
	private final JTextField textField = new JTextField();
	private final JComboBox comboBox_3 = new JComboBox();
	private final JTextField textField_1 = new JTextField();
	private final JTextArea textArea = new JTextArea();
	private final JLabel lblNewLabel_2 = new JLabel("L\u00F6sungsvorschlag");
	private final JButton btnAnlegen = new JButton("anlegen");
	
	
public KEDB() {
	textField_1.setBorder(new TitledBorder(null, "Betroffen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	textField_1.setBounds(713, 120, 117, 36);
	textField_1.setColumns(10);
	textField.setBorder(new TitledBorder(null, "Fehlername", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	textField.setBounds(586, 120, 117, 36);
	textField.setColumns(10);
	init();
	}
		
	private void init() {
		 kGui.setBounds(100, 100, 883, 515);
			kGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			kGui.getContentPane().setLayout(null);
			comboBox.setBorder(new TitledBorder(null, "Bitte w\u00E4hlen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						do_comboBox_actionPerformed(e);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			comboBox.setBounds(47, 50, 85, 44);
			
			kGui.getContentPane().add(comboBox);
			comboBox_1.setBorder(new TitledBorder(null, "Bitte w\u00E4hlen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			comboBox_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						do_comboBox_1_actionPerformed(arg0);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			comboBox_1.setBounds(166, 50, 86, 44);
			comboBox.addItem("Hardware");
			comboBox.addItem("Software");
			
			kGui.getContentPane().add(comboBox_1);
			comboBox_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						do_comboBox_2_actionPerformed(arg0);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
			
			comboBox_2.setBorder(new TitledBorder(null, "CI-ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			comboBox_2.setBounds(47, 285, 85, 44);
			
			
			
			kGui.getContentPane().add(comboBox_2);
			table_1.setBorder(null);
			table_1.setBounds(47, 105, 463, 139);
			JScrollPane js=new JScrollPane();
			js.setBounds(47, 105, 442, 139);
			js.setVisible(true);
			kGui.getContentPane().add(js);
			
			kGui.getContentPane().add(table_1);
			table_2.setBounds(142, 285, 368, 147);
			JScrollPane js2=new JScrollPane();
			js.setBounds(142, 285, 368, 147);
			js.setVisible(true);
			kGui.getContentPane().add(js2);
			
			kGui.getContentPane().add(table_2);
			String table="`db136045x2588076`.`CIs`";
			String columnID="`id`";
			try {
				String[] s = Connection.conHP.getContentOfColumn(table, columnID);
				comboBox_2.setModel(new javax.swing.DefaultComboBoxModel(s));
				lblNewLabel.setBounds(47, 11, 190, 28);
				
				kGui.getContentPane().add(lblNewLabel);
				lblNewLabel_1.setBounds(47, 255, 190, 28);
				
				kGui.getContentPane().add(lblNewLabel_1);
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
				
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			separator.setVisible(true);
			kGui.getContentPane().add(separator);
			lblNeuenLsungsvorschlagAnlegen.setBounds(595, 50, 218, 28);
			
			kGui.getContentPane().add(lblNeuenLsungsvorschlagAnlegen);
			
			kGui.getContentPane().add(textField);
			comboBox_3.setBounds(586, 168, 101, 36);
			
			kGui.getContentPane().add(comboBox_3);
			
			kGui.getContentPane().add(textField_1);
			textArea.setBounds(595, 257, 205, 91);
			
			kGui.getContentPane().add(textArea);
			lblNewLabel_2.setBounds(595, 230, 101, 28);
			
			kGui.getContentPane().add(lblNewLabel_2);
			btnAnlegen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btnAnlegen_actionPerformed(e);
				}
			});
			btnAnlegen.setBounds(756, 365, 89, 23);
			
			kGui.getContentPane().add(btnAnlegen);
			comboBox_3.addItem("Hardware");
			comboBox_3.addItem("Software");
			
}
	protected void do_comboBox_1_actionPerformed(ActionEvent arg0) throws Exception {
		String betrof=String.valueOf(comboBox_1.getSelectedItem());
		DefaultTableModel tb1=new DefaultTableModel(0,0);
		String header[]=new String[]{"Fehler","Lösungsansatz"};
		tb1.setColumnIdentifiers(header);
		table_1.setModel(tb1);
		tb1.addRow(header);
		ArrayList<String> fehl=null;
		fehl=Connection.conKE.getSingleDataSetList("SELECT `Fehler` FROM `db136045x2588052`.`KEDB` WHERE `Betroffen`='"+betrof+"'");
		ArrayList<String> loes=Connection.conKE.getSingleDataSetList("SELECT `Loesungsvorschlag` FROM `db136045x2588052`.`KEDB` WHERE `Betroffen`='"+betrof+"'");
		String[] fehler=new String[fehl.size()];
		fehler=fehl.toArray(fehler);
		String[] loesung=new String[loes.size()];
		loesung=loes.toArray(loesung);
		for (int i = 0; i < fehler.length; i++) {
			
	        tb1.addRow(new Object[] {fehler[i], loesung[i]}
	        );}
	
		}
	
		
		
	
		
		
		
	
	protected void do_comboBox_actionPerformed(ActionEvent e) throws Exception {
		if(comboBox.getSelectedItem()=="Hardware"){
				ArrayList<String> meh=Connection.conKE.getSingleDataSetList("SELECT `Betroffen` FROM `db136045x2588052`.`KEDB` WHERE `Hardware`='1'");
				comboBox_1.setModel(new DefaultComboBoxModel(meh.toArray()));
		}
		if(comboBox.getSelectedItem()=="Software") {
					String[] soft = Connection.conKE.getSingleDataSet("SELECT `Betroffen` FROM `db136045x2588052`.`KEDB` WHERE `Software`='1'");
					comboBox_1.setModel(new javax.swing.DefaultComboBoxModel(soft));

			
				
			}
		}
	
		
	
	protected void do_comboBox_2_actionPerformed(ActionEvent arg0) throws Exception {
		String id=String.valueOf(comboBox_2.getSelectedItem());
		DefaultTableModel dtm=new DefaultTableModel(0,0);
		String header[]=new String[]{"Ticket-ID","Beschreibung"};
		dtm.setColumnIdentifiers(header);
		table_2.setModel(dtm);
		dtm.addRow(header);
		ArrayList<String> idT=null;
		try{
		idT=Connection.conHP.getSingleDataSetList("SELECT idTicket FROM db136045x2588076.Ticket WHERE CIs_id="+id+";");
		ArrayList<String> beschreibung=Connection.conHP.getSingleDataSetList("SELECT Beschreibung FROM db136045x2588076.Ticket WHERE CIs_id="+id+";");
		String[] tickets=new String[idT.size()];
		tickets=idT.toArray(tickets);
		String[] beschr=new String[beschreibung.size()];
		beschr=beschreibung.toArray(beschr);
		for (int i = 0; i < tickets.length; i++) {
			
	        dtm.addRow(new Object[] { tickets[i], beschr[i]}
	        );}
	
		}
		catch(SQLException e){
			idT=null;
			String leer[]=new String[]{"Keine Tickets vorhanden","leer"};
			dtm.addRow(leer);
			
		
		
} 
}
	protected void do_btnZurck_actionPerformed(ActionEvent arg0) {
		kGui.setVisible(false);
		conGui.cGui.setVisible(true);
	}
	protected void do_btnAnlegen_actionPerformed(ActionEvent e) {
		String eingabe1=textField.getText();
		String eingabe2=null;
		String eingabe3=null;
		if (comboBox_3.getSelectedItem()=="Hardware") {
			eingabe2="1";
			eingabe3="0";
		}
		if(comboBox_3.getSelectedItem()=="Software") {
			eingabe2="0";
			eingabe3="1";
			}
		String eingabe4=textField_1.getText();
		String eingabe5=textArea.getText();
		
		String val="'"+eingabe1+"',"+"'"+eingabe2+"',"+"'"+eingabe3+"',"+"'"+eingabe4+"',"+"'"+eingabe5+"'";
        
			
			try {
				Connection.conKE.insertDataSet("INSERT INTO `db136045x2588052`.`KEDB` ( `Fehler`, `Hardware`, `Software`, `Betroffen`, `Loesungsvorschlag`)"+" VALUES ("+val+")");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(kGui, "Lösungsvorschlag eingetragen");
			kGui.setVisible(false);
			conGui.cGui.setVisible(true);
		
	}
}


