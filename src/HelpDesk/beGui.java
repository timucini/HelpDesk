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
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;

import com.mysql.jdbc.Statement;
import javax.swing.JTextPane;

public class beGui {
	static JFrame bGui=new JFrame();
	private final JLabel lblNewLabel = new JLabel("Tickets bearbeiten");
	private final JComboBox comboBox = new JComboBox();
	private final JComboBox comboBox_1 = new JComboBox();
	private final JButton btnAnwenden = new JButton("anwenden");
	private final JButton btnNewButton = new JButton("zur\u00FCck");
	private final JTextPane textPane = new JTextPane();
	private final JLabel label = new JLabel("");
	
public beGui() {
	init();
}
private void init() {
	bGui.setBounds(100, 100, 435, 283);
	bGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	bGui.getContentPane().setLayout(null);
	lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
	lblNewLabel.setBounds(47, 11, 124, 28);
	
	bGui.getContentPane().add(lblNewLabel);
	comboBox.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			do_comboBox_actionPerformed(arg0);
		}
	});
	comboBox.setBorder(new TitledBorder(null, "Tickets ausw\u00E4hlen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	comboBox.setBounds(47, 62, 124, 44);
	
	bGui.getContentPane().add(comboBox);
	comboBox_1.setBorder(new TitledBorder(null, "Status setzen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	comboBox_1.setBounds(220, 62, 124, 44);
	
	bGui.getContentPane().add(comboBox_1);
	btnAnwenden.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			do_btnAnwenden_actionPerformed(arg0);
		}
	});
	btnAnwenden.setBounds(193, 199, 97, 23);
	
	bGui.getContentPane().add(btnAnwenden);
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			do_btnNewButton_actionPerformed(e);
		}
	});
	btnNewButton.setBounds(300, 199, 97, 23);
	
	bGui.getContentPane().add(btnNewButton);
	
	String table="`db136045x2588076`.`Ticket`";
	String column="`idTicket`";
	try {
		String[] s = Connection.conHP.getContentOfColumn(table, column);
		comboBox.setModel(new javax.swing.DefaultComboBoxModel(s));
		
		
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	comboBox.addItem("Bitte wählen");
	comboBox_1.addItem("Geöffnet");
	comboBox_1.addItem("Bearbeitet");
	comboBox_1.addItem("Geschlossen");
	textPane.setBorder(new TitledBorder(null, "Beschreibung", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	textPane.setBounds(47, 134, 298, 55);
	
	bGui.getContentPane().add(textPane);
	label.setBounds(220, 30, 124, 23);
	
	bGui.getContentPane().add(label);
		
}

	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		bGui.setVisible(false);
		conGui.cGui.setVisible(true);
	}
	protected void do_btnAnwenden_actionPerformed(ActionEvent arg0) {
		String id = String.valueOf(comboBox.getSelectedItem());
		if(comboBox_1.getSelectedItem()=="Geöffnet")
			try {
				Connection.conHP.updateDataSet("UPDATE `db136045x2588076`.`Ticket` SET `Status`='Geöffnet' WHERE `idTicket`='"+id+"'");
				JOptionPane.showMessageDialog(bGui,"Ticketstatus geändert");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		if(comboBox_1.getSelectedItem()=="Bearbeitet")
			try {
				Connection.conHP.updateDataSet("UPDATE `db136045x2588076`.`Ticket` SET `Status`='Bearbeitet' WHERE `idTicket`='"+id+"'");
				JOptionPane.showMessageDialog(bGui,"Ticketstatus geändert");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		if(comboBox_1.getSelectedItem()=="Geschlossen")
			try {
				Connection.conHP.updateDataSet("UPDATE `db136045x2588076`.`Ticket` SET `Status`='Geschlossen' WHERE `idTicket`='"+id+"'");
				JOptionPane.showMessageDialog(bGui,"Ticketstatus geändert");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	protected void do_comboBox_actionPerformed(ActionEvent arg0) {
		String idTicket = String.valueOf(comboBox.getSelectedItem());
	try {
		ArrayList besch = Connection.conHP.getSingleDataSetList("SELECT Ticket.Beschreibung FROM db136045x2588076.Ticket WHERE idTicket="+idTicket);
		String list=Arrays.toString(besch.toArray()).replace("[", "").replace("]", "");
		textPane.setText(list);
		ArrayList<String> stat=Connection.conHP.getSingleDataSetList("SELECT Ticket.Status FROM db136045x2588076.Ticket WHERE idTicket="+idTicket);
		String status=Arrays.toString(stat.toArray()).replace("[", "").replace("]", "");
		label.setText("Status: "+status);
		label.setForeground(Color.BLUE);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	}
}
