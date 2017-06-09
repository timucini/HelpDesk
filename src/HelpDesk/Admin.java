package HelpDesk;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import dbHelper.*;

public class Admin extends JFrame {
	static DBCon dbHD = new DBCon();
	static DBCon dbStatus = new DBCon();
	static ArrayList<String> ArrLDBConf = new ArrayList<String>();
	static ConfigLoader dbConf = new ConfigLoader();
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		setdbConneting();
		printTablesHD();
		printTablesStatus();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Admin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	private static void setdbConneting() {
		try {
			dbConf.clearConfig(false);
			ArrLDBConf = dbConf.getsArrLConfigItems();
			if (ArrLDBConf.size() == 3) {
				dbHD.setConnection(ArrLDBConf.get(0), ArrLDBConf.get(1), ArrLDBConf.get(2));
			}
			if (ArrLDBConf.size() == 4) {
				dbHD.setConnection(ArrLDBConf.get(0), ArrLDBConf.get(1), ArrLDBConf.get(2), ArrLDBConf.get(3));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Zurücksetzten Verbindungsinformationen
		ArrLDBConf.clear();
		dbConf.clearConfig(false);
		
		try {
			
			ArrLDBConf = dbConf.getsArrLConfigItems();
			if (ArrLDBConf.size() == 3) {
				dbStatus.setConnection(ArrLDBConf.get(0), ArrLDBConf.get(1), ArrLDBConf.get(2));
			}
			if (ArrLDBConf.size() == 4) {
				dbStatus.setConnection(ArrLDBConf.get(0), ArrLDBConf.get(1), ArrLDBConf.get(2), ArrLDBConf.get(3));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void doConnectHD() {
		try {
			dbHD.connect();
			System.out.println("Verbunden mit HD");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void doConnectStatus(){
		try {
			dbStatus.connect();
			System.out.println("Verbunden mit Status");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private static void printTablesHD() {
		doConnectHD();
		String content[] = null;
		try {
			content = dbHD.getTables();
			for (int i = 0; i <= content.length - 1; i++) {
				System.out.println(content[i]);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void printTablesStatus() {
		doConnectStatus();
		String content[] = null;
		try {
			content = dbStatus.getTables();
			for (int i = 0; i <= content.length - 1; i++) {
				System.out.println(content[i]);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
