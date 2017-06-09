package HelpDesk;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class statusGui {
	static JFrame sGui=new JFrame();
	private final JTextArea txtpn = new JTextArea();
	private final JLabel lblNewLabel = new JLabel("Ticketstatus");
	private final JButton btnZurck = new JButton("zur\u00FCck");
	private final JTable table_1 = new JTable();
	
public statusGui(){
	init();
}
public void init() {
	sGui=new JFrame();
	sGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	sGui.setBounds(100, 100, 450, 450);
	sGui.getContentPane().setLayout(null);
	txtpn.setBounds(23, 44, 167, 315);
	
	
	
	sGui.getContentPane().add(txtpn);
	lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
	lblNewLabel.setBounds(89, 11, 101, 22);
	
	sGui.getContentPane().add(lblNewLabel);
	btnZurck.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			do_btnZurck_actionPerformed(arg0);
		}
	});
	btnZurck.setBounds(335, 377, 89, 23);
	
	sGui.getContentPane().add(btnZurck);
	table_1.setBounds(261, 32, 135, 197);
	
	sGui.getContentPane().add(table_1);
	

	
	String table="`db136045x2588076`.`Ticket`";
	String columnid="`idTicket`";
	String columnStatus="`Status`";
	
	try {
		String[] id = Connection.conHP.getContentOfColumn(table, columnid);
		String[] status = Connection.conHP.getContentOfColumn(table, columnStatus);
		for (int i=0;i<id.length;i++) {
			
			txtpn.append("Ticket-ID:"+id[i].substring(0)+"        Status:"+status[i].substring(0)+"\n");
	        
			}
		} 
		catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String table1="`db136045x2588076`.`Ticket`";
	String column="`idTicket`";
	
	
	
	String[] ids = null;
	try {
		ids = Connection.conHP.getContentOfColumn(table1, column);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String[] stat=null;
	
	try {
		stat=Connection.conHP.getContentOfColumn(table1, columnStatus);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	DefaultTableModel dtm=new DefaultTableModel(0,0);
	String header[]=new String[]{"Ticket-ID","Status"};
	dtm.setColumnIdentifiers(header);
	table_1.setModel(dtm);
	for (int i = 0; i < ids.length; i++) {
        dtm.addRow(new Object[] { ids[i], stat[i]}
        );}
        
	
	
	
	
	
	
	
	}
	protected void do_btnZurck_actionPerformed(ActionEvent arg0) {
		sGui.setVisible(false);
		conGui.cGui.setVisible(true);
	}
}

