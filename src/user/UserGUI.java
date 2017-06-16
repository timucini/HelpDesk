package user;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class UserGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTable tm = new JTable(5, 5);
	private static JButton cmd_connect = new JButton("Datenbank verbinden");
	private static JLabel lbl_isConnected = new JLabel("Nicht verbunden");
	private final JButton cmd_system = new JButton("Systeminformationen anzeigen");

	/**
	 * Create the frame.
	 */
	public UserGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		tm.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, }, new String[] { "A", "B", "C", "D", "E" }));
		tm.setColumnSelectionAllowed(true);
		tm.setCellSelectionEnabled(true);
		tm.setBounds(10, 150, 398, 100);

		contentPane.add(tm);
		cmd_connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_cmd_connect_actionPerformed(arg0);
			}
		});
		cmd_connect.setBounds(10, 11, 200, 23);
		
		contentPane.add(cmd_connect);
		lbl_isConnected.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_isConnected.setForeground(Color.RED);
		lbl_isConnected.setBounds(220, 15, 92, 14);
		
		contentPane.add(lbl_isConnected);
		cmd_system.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_cmd_system_actionPerformed(arg0);
			}
		});
		cmd_system.setBounds(10, 45, 200, 23);
		
		contentPane.add(cmd_system);

		modTable();
		getTable();
	}

	public static void modTable() {
		for (int x = 0; x <= tm.getColumnCount() - 1; x++) {
			for (int y = 0; y <= tm.getRowCount() - 1; y++) {
				tm.setValueAt("Col: " + x + "; Row: " + y, y, x);
			}
		}
	}

	public static void getTable() {
		for (int x = 0; x <= tm.getColumnCount() - 1; x++) {
			for (int y = 0; y <= tm.getRowCount() - 1; y++) {
				System.out.print((String) tm.getValueAt(x, y));
			}
			System.out.println("");
		}
	}
	protected void do_cmd_connect_actionPerformed(ActionEvent arg0) {
		updatelbl(Core.dbConnect(true));
	}
	
	private void updatelbl(boolean con){
		if (con==true){
			lbl_isConnected.setForeground(Color.GREEN);
			lbl_isConnected.setText("DB verbunden");
		}else{
		lbl_isConnected.setForeground(Color.RED);
		lbl_isConnected.setText("Nicht verbunden");
		}
	}
	protected void do_cmd_system_actionPerformed(ActionEvent arg0) {
		Core.printSysInf();
	}
}
