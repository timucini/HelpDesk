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
import java.util.Arrays;
import java.awt.event.ActionEvent;


public class einGui {
	static JFrame eGui=new JFrame();
	private final JLabel lblEingabeTicket = new JLabel("Eingabe Ticket");
	private final JTextField textField_1 = new JTextField();
	private final JTextField textField_2 = new JTextField();
	private final JComboBox comboBox = new JComboBox();
	private final JTextArea textArea = new JTextArea();
	private final JComboBox comboBox_1 = new JComboBox();
	private final JButton btnNewButton = new JButton("Einf\u00FCgen");
	private final JOptionPane ticketErfolg=new JOptionPane();
	private final JButton btnZurck = new JButton("zur\u00FCck");
	private final JComboBox comboBox_2 = new JComboBox();
	
	
	
	public einGui() {
		init();
	}
	private void init() {
		eGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		eGui.setBounds(100, 100, 491, 300);
		eGui.getContentPane().setLayout(null);
		
		lblEingabeTicket.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblEingabeTicket.setBounds(23, 27, 127, 28);
		
		eGui.getContentPane().add(lblEingabeTicket);
		textField_1.setColumns(10);
		textField_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ci-ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textField_1.setBounds(177, 11, 105, 39);
		
		eGui.getContentPane().add(textField_1);
		textField_2.setColumns(10);
		textField_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Mitarbeiter", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textField_2.setBounds(247, 66, 105, 39);
		
		eGui.getContentPane().add(textField_2);
		comboBox.setBorder(new TitledBorder(null, "Bitte ausw\u00E4hlen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		comboBox.setBounds(23, 116, 105, 39);
		
		eGui.getContentPane().add(comboBox);
		textArea.setBorder(new TitledBorder(null, "Beschreibung", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textArea.setBounds(148, 116, 204, 72);
		comboBox.addItem("Telefon");
		comboBox.addItem("PCs");
		
		eGui.getContentPane().add(textArea);
		comboBox_1.setBorder(new TitledBorder(null, "Bitte ausw\u00E4hlen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		comboBox_1.setBounds(23, 166, 105, 39);
		comboBox_1.addItem("Hardware");
		comboBox_1.addItem("Software");
		
		eGui.getContentPane().add(comboBox_1);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnNewButton_actionPerformed(arg0);
			}
		});
		btnNewButton.setBounds(227, 199, 118, 39);
		
		eGui.getContentPane().add(btnNewButton);
		btnZurck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnZurck_actionPerformed(arg0);
			}
		});
		btnZurck.setBounds(376, 215, 89, 23);
		
		eGui.getContentPane().add(btnZurck);
		comboBox_2.setBorder(new TitledBorder(null, "Ci-ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		comboBox_2.setBounds(23, 65, 105, 40);
		
		eGui.getContentPane().add(comboBox_2);
		String table="`db136045x2588076`.`CIs`";
		String column="`id`";
		try {
			String[] s = Connection.conHP.getContentOfColumn(table, column);
			comboBox_2.setModel(new javax.swing.DefaultComboBoxModel(s));
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}
	protected void do_btnNewButton_actionPerformed(ActionEvent arg0) {
	
        
        String eingabe2=String.valueOf(comboBox_2.getSelectedItem());;
        String eingabe3=textField_2.getText();
        String eingabe4= "0";
        String eingabe5= "0";
        String eingabe6 = "0";
        String eingabe7 = "0";
        String eingabe8=textArea.getText();
        
        
        if(comboBox.getSelectedItem()=="Telefon") 
        	eingabe4="1";
        else
        	eingabe4="0";
        	eingabe5="1";
        if(comboBox.getSelectedItem()=="PCs")
        	eingabe5="1";
        	eingabe4="0";
        	
        	
        	
        if(comboBox_1.getSelectedItem()=="Hardware") 
            eingabe4="1";
        else
            eingabe4="0";
            eingabe5="1";
        if(comboBox_1.getSelectedItem()=="Software")
            eingabe5="1";
            eingabe4="0";
            
        String val="'"+eingabe2+"',"+"'"+eingabe3+"',"+"'"+eingabe4+"',"+"'"+eingabe5+"',"+"'"+eingabe6+"',"+"'"+eingabe7+"',"+"'"+eingabe8+"'";
          
		try {
			
			Connection.conHP.insertDataSet("INSERT INTO `db136045x2588076`.`Ticket` ( `CIs_id`, `Mitarbeiter`, `Telefon`, `PCs`, `Hardware`, `Software`, `Beschreibung`)"+" VALUES ("+val+")");
			//Connection.conTS.insertDataSet("INSERT INTO `db136045x2588057`.`Ticketstatus` ( `CIs_id`, `Mitarbeiter`, `Telefon`, `PCs`, `Hardware`, `Software`, `Beschreibung`)"+" VALUES ("+val+")");
			JOptionPane.showMessageDialog(eGui, "Ticket eingetragen");
			eGui.setVisible(false);
			conGui.cGui.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
}
	protected void do_btnZurck_actionPerformed(ActionEvent arg0) {
		eGui.setVisible(false);
		conGui.cGui.setVisible(true);
	}
}

