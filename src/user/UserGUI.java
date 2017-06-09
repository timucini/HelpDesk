package user;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserGUI extends JFrame {

	private JPanel contentPane;
	private static JTable tm = new JTable(5, 5);
	private final JButton cmd_connect = new JButton("Datenbank verbinden");

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
		cmd_connect.setBounds(10, 11, 135, 23);
		
		contentPane.add(cmd_connect);

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
		Core.dbConnect(true);
	}
}
