package user;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class UserGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private static JTable tm_SysInf;
	private static JLabel lbl_isConnected = new JLabel("Nicht verbunden");
	private static final JScrollPane scrollP_SysInf = new JScrollPane();
	private final JMenuBar menuBar = new JMenuBar();
	private static final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private static final JLayeredPane layeredPane = new JLayeredPane();
	private static final JLayeredPane layeredPane_1 = new JLayeredPane();
	private final JMenu mnDatei = new JMenu("Datei");
	private final JMenu mnHilfe = new JMenu("Hilfe");
	private final JMenuItem mntmber = new JMenuItem("Über");
	private final JMenuItem mntmDatenbankVerbinden = new JMenuItem("Datenbank verbinden");
	private final JMenuItem mntmBeenden = new JMenuItem("Beenden");

	/**
	 * Create the frame.
	 */
	public UserGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				do_this_windowClosing(arg0);
			}
		});
		setTitle("HelpDesk - User-Interface");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		
		setJMenuBar(menuBar);
		
		menuBar.add(mnDatei);
		mntmDatenbankVerbinden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_mntmDatenbankVerbinden_actionPerformed(arg0);
			}
		});
		
		mnDatei.add(mntmDatenbankVerbinden);
		
		mnDatei.add(mntmBeenden);
		
		menuBar.add(mnHilfe);
		
		mnHilfe.add(mntmber);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lbl_isConnected.setBounds(220, 15, 92, 14);
		lbl_isConnected.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_isConnected.setForeground(Color.RED);
		
		contentPane.add(lbl_isConnected);

		/*
		modTable();
		getTable();
		*/
		showSysInf();
	}
	
	private static void showSysInf(){
		Core.getSysInf();
	scrollP_SysInf.setBorder(new TitledBorder(null, "Aktuelle Systemkonfiguration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	scrollP_SysInf.setBounds(10, 327, 414, 102);
	
	contentPane.add(scrollP_SysInf);
	tm_SysInf=new JTable(Core.SysConf[0].length,Core.SysConf.length);
	scrollP_SysInf.setViewportView(tm_SysInf);
	tm_SysInf.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	
	tm_SysInf.setColumnSelectionAllowed(false);
	tm_SysInf.setCellSelectionEnabled(true);
	tm_SysInf.setRowSelectionAllowed(true);
	//https://www.java-forum.org/thema/jtable-spaltennamen-aendern.2266/
	tm_SysInf.setModel(new DefaultTableModel(new Object[Core.SysConf[0].length][Core.SysConf.length],
			new String[] {"Eigenschaft", "Wert"}));
	tabbedPane.setBounds(10, 45, 414, 271);
	
	contentPane.add(tabbedPane);
	
	tabbedPane.addTab("Ticket Status", null, layeredPane, null);
	
	tabbedPane.addTab("Neues Ticket anlegen", null, layeredPane_1, null);
	updatelbl(Core.isDbConnected());
	modTable(Core.SysConf); //Tabelle füllen
	}

	public static void modTable(String[][] sArr) {
		for (int x = 0; x <= sArr.length - 1; x++) {
			for (int y = 0; y <= sArr[x].length - 1; y++) {
				tm_SysInf.setValueAt(sArr[x][y], y, x);
			}
		}
	}

	public static void getTable() {
		for (int x = 0; x <= tm_SysInf.getColumnCount() - 1; x++) {
			for (int y = 0; y <= tm_SysInf.getRowCount() - 1; y++) {
				System.out.print((String) tm_SysInf.getValueAt(x, y));
			}
			System.out.println("");
		}
	}
	
	private static void updatelbl(boolean con){
		if (con==true){
			lbl_isConnected.setForeground(Color.GREEN);
			lbl_isConnected.setText("DB verbunden");
		}else{
		lbl_isConnected.setForeground(Color.RED);
		lbl_isConnected.setText("Nicht verbunden");	
		}
	}
	
	protected void do_this_windowClosing(WindowEvent arg0) {
		System.exit(0);
	}
	protected void do_mntmDatenbankVerbinden_actionPerformed(ActionEvent arg0) {
		updatelbl(Core.dbConnect(true));
		System.out.println(Core.isDbConnected());
	}
}
