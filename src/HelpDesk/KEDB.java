package HelpDesk;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class KEDB {
	static JFrame kGui=new JFrame();
	private final JComboBox comboBox = new JComboBox();
	private final JComboBox comboBox_1 = new JComboBox();
	private final JComboBox comboBox_2 = new JComboBox();
	private final JTable table_1 = new JTable();
	private final JTable table_2 = new JTable();
	
	
public KEDB() {
	init();
	}
		
	private void init() {
		 kGui.setBounds(100, 100, 574, 482);
			kGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			kGui.getContentPane().setLayout(null);
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_comboBox_actionPerformed(e);
				}
			});
			comboBox.setBounds(47, 39, 85, 44);
			
			kGui.getContentPane().add(comboBox);
			comboBox_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					do_comboBox_1_actionPerformed(arg0);
				}
			});
			comboBox_1.setBounds(168, 39, 86, 44);
			comboBox.addItem("Hardware");
			comboBox.addItem("Software");
			
			kGui.getContentPane().add(comboBox_1);
			
			
			comboBox_2.setBorder(new TitledBorder(null, "CI-ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			comboBox_2.setBounds(47, 285, 85, 44);
			
			
			
			kGui.getContentPane().add(comboBox_2);
			table_1.setBounds(47, 105, 442, 139);
			
			kGui.getContentPane().add(table_1);
			table_2.setBounds(168, 285, 145, 147);
			
			kGui.getContentPane().add(table_2);
			String table="`db136045x2588076`.`CIs`";
			String column="`id`";
		/*	try {
				String[] s = Connection.conHP.getContentOfColumn(table, column);
				comboBox_2.setModel(new javax.swing.DefaultComboBoxModel(s));
				
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		*/	
}
	protected void do_comboBox_1_actionPerformed(ActionEvent arg0) {
	}
	protected void do_comboBox_actionPerformed(ActionEvent e) {
		comboBox_1.removeAllItems();
		if(comboBox.getSelectedItem()=="Hardware"){
			comboBox_1.addItem("Festplatte");
			comboBox_1.addItem("Grafikkarte");
			}
			if(comboBox.getSelectedItem()=="Software") {
				comboBox_1.addItem("Windows");
			}
		
	}
}
