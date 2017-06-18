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
import java.util.ArrayList;

import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextPane;

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
	private final JMenu mnDatei = new JMenu("Datei");
	private final JMenu mnHilfe = new JMenu("Hilfe");
	private final JMenuItem mntmber = new JMenuItem("Über");
	private final JMenuItem mntmBeenden = new JMenuItem("Beenden");
	private final JMenu menu = new JMenu("Datenbank");
	private final JMenuItem mntmDBVerbinden = new JMenuItem("verbinden");
	private final JMenuItem mntmDBTrennen = new JMenuItem("trennen");
	private static final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private static final JPanel panel_Status = new JPanel();
	private static final JTabbedPane tabbedPane_NewTicket = new JTabbedPane(JTabbedPane.TOP);
	private static final JPanel panel_direct = new JPanel();
	private static final JLabel lbl_Info = new JLabel();
	private static final JPanel panel_Contact = new JPanel();
	private static JTextField txt_Name = new JTextField();
	private static JTextField txt_phone = new JTextField();
	private static JTextField txt_Mail = new JTextField();
	private static JPanel panel_Issue = new JPanel();
	private static JPanel panel_location = new JPanel();
	private static JTextField txt_Geb = new JTextField();
	private static final JPanel panel_send = new JPanel();
	private static JButton cmd_send = new JButton("absenden");
	private final JLabel lbl_Info2 = new JLabel("<html><Body>Bitte überprüfen Sie Ihre vorherigen Eingaben,<br> bevor Sie auf 'absenden' klicken <br><b>-</b><br>Das erspart ggf. Rückfragen</Body></HTML>");
	private static JComboBox<String> cbB_HW = new JComboBox<String>();
	private static JComboBox<String> cbB_SW = new JComboBox<String>();
	private static JScrollPane scrollPane = new JScrollPane();
	private static JTextPane txtPane_Issue = new JTextPane();
	private static JTextField txt_Raum = new JTextField();
	private static JTextField txt_Anschluss = new JTextField();

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
		setBounds(100, 100, 480, 450);

		setJMenuBar(menuBar);

		menuBar.add(mnDatei);
		
		mnDatei.add(menu);
		mntmDBVerbinden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_mntmDBVerbinden_actionPerformed(arg0);
			}
		});
		
		menu.add(mntmDBVerbinden);
		mntmDBTrennen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mntmDBTrennen_actionPerformed(e);
			}
		});
		
		menu.add(mntmDBTrennen);
		mntmBeenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mntmBeenden_actionPerformed(e);
			}
		});

		mnDatei.add(mntmBeenden);

		menuBar.add(mnHilfe);

		mnHilfe.add(mntmber);
		menuBar.add(lbl_isConnected);
		lbl_isConnected.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_isConnected.setForeground(Color.RED);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		tabbedPane.setBounds(0, 0, 464, 390);
		
		contentPane.add(tabbedPane);
		
		tabbedPane.addTab("Ticket Status", null, panel_Status, null);
		
		tabbedPane.addTab("Neues Ticket anlegen", null, tabbedPane_NewTicket, null);
		
		tabbedPane_NewTicket.addTab("1. Ansprechpartner", null, panel_Contact, "Wer meldet die Störung / Wie erreichen Wir Sie");
		panel_Contact.setLayout(null);
		txt_Name.setBounds(10, 11, 404, 39);
		txt_Name.setColumns(10);
		txt_Name.setBorder(new TitledBorder(null, "Name Meldender / Ansprechpartner", TitledBorder.LEADING,
						TitledBorder.TOP, null, null));
		
		panel_Contact.add(txt_Name);
		txt_phone.setBounds(10, 61, 404, 39);
		txt_phone.setColumns(10);
		txt_phone.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Telefon Ansprechpartner",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		panel_Contact.add(txt_phone);
		txt_Mail.setBounds(10, 111, 404, 39);
		txt_Mail.setColumns(10);
		txt_Mail.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "E-Mail Ansprechpartner",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		panel_Contact.add(txt_Mail);
		tabbedPane_NewTicket.addTab("2. Fehlerbeschreibung", null, panel_Issue, "Beschreiben Sie den Fehler");
		panel_Issue.setLayout(null);
		cbB_HW.setBorder(new TitledBorder(null, "An welchem Ger\u00E4t tritt der Fehler auf:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cbB_HW.setBounds(10, 11, 434, 40);
		
		panel_Issue.add(cbB_HW);
		cbB_SW.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Mit welcher Software der Fehler auf:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		cbB_SW.setBounds(10, 62, 434, 40);
		
		panel_Issue.add(cbB_SW);
		scrollPane.setBorder(new TitledBorder(null, "Beschreiben Sie den Fehler genauer", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(10, 113, 434, 160);
		
		panel_Issue.add(scrollPane);
		
		scrollPane.setViewportView(txtPane_Issue);
		
		tabbedPane_NewTicket.addTab("3. Lokation", null, panel_location, "Wo tritt der Fehler auf / Wo erreichen Wir Sie");
		panel_location.setLayout(null);
		txt_Geb.setBounds(10, 11, 434, 39);
		txt_Geb.setColumns(10);
		txt_Geb.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Adresse / Geb\u00E4ude", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		panel_location.add(txt_Geb);
		txt_Raum.setColumns(10);
		txt_Raum.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Raum", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txt_Raum.setBounds(10, 61, 434, 39);
		
		panel_location.add(txt_Raum);
		txt_Anschluss.setColumns(10);
		txt_Anschluss.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Anschluss (nur wenn bekannt)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txt_Anschluss.setBounds(10, 111, 434, 39);
		
		panel_location.add(txt_Anschluss);
		
		tabbedPane_NewTicket.addTab("4. Senden", null, panel_send, "Bitte überprüfen Sie Ihre vorherigen Eingaben, bevor Sie auf 'absenden' klicken - Das erspart ggf. Rückfragen");
		panel_send.setLayout(null);
		cmd_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_cmd_send_actionPerformed(e);
			}
		});
		cmd_send.setBounds(172, 192, 100, 23);
		
		panel_send.add(cmd_send);
		lbl_Info2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Info2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_Info2.setBounds(10, 71, 404, 57);
		
		panel_send.add(lbl_Info2);
		tabbedPane.addTab("Direktkontakt zum Helpdesk", null, panel_direct, null);
		panel_direct.setLayout(null);
		lbl_Info.setText("<html><body>Sie erreichen uns unter der Telefonnummer:<br>"+
				"<b>0800 - 123456789</b><br>"+
				"Bitte halten Sie die unten aufgeführte Systemkonfiguration bereit.</body></html>");

		lbl_Info.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_Info.setBorder(new TitledBorder(null, "Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lbl_Info.setBounds(10, 11, 409, 88);
		
		panel_direct.add(lbl_Info);
		scrollP_SysInf.setBounds(10, 110, 409, 241);
		panel_direct.add(scrollP_SysInf);

		/*
		 * modTable(); getTable();
		 */
		showSysInf();
		updatelbl(Core.isDbConnected());
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static void showSysInf() {
		Core.getSysInf();

		scrollP_SysInf.setBorder(new TitledBorder(null, "Aktuelle Systemkonfiguration", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		tm_SysInf = new JTable(Core.SysConf[0].length, Core.SysConf.length);
		scrollP_SysInf.setViewportView(tm_SysInf);
		tm_SysInf.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tm_SysInf.setColumnSelectionAllowed(false);
		tm_SysInf.setCellSelectionEnabled(true);
		tm_SysInf.setRowSelectionAllowed(true);
		// https://www.java-forum.org/thema/jtable-spaltennamen-aendern.2266/
		tm_SysInf.setModel(new DefaultTableModel(new Object[Core.SysConf[0].length][Core.SysConf.length],
				new String[] { "Eigenschaft", "Wert" }));
		modTable(Core.SysConf); // Tabelle füllen
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

	private static void updatelbl(boolean con) {
		if (con == true) {
			lbl_isConnected.setForeground(Color.GREEN);
			lbl_isConnected.setText("DB verbunden");
		} else {
			lbl_isConnected.setForeground(Color.RED);
			lbl_isConnected.setText("Nicht verbunden");
		}
	}
	
	protected void do_mntmDBVerbinden_actionPerformed(ActionEvent arg0) {
		updatelbl(Core.dbConnect(true));
		fillcbBoxes(Core.isDbConnected());
		// System.out.println(Core.isDbConnected());
	}

	protected void do_mntmDBTrennen_actionPerformed(ActionEvent e) {
		updatelbl(Core.dbConnect(false));
		fillcbBoxes(Core.isDbConnected());
	}

	protected void do_this_windowClosing(WindowEvent arg0) {
		System.exit(0);
	}

	protected void do_mntmBeenden_actionPerformed(ActionEvent arg0) {
		System.exit(0);
	}

	protected void do_cmd_send_actionPerformed(ActionEvent e) {

	}
	
	private void fillcbBoxes(boolean load){
		if (load){
			ArrayList<String> ArrLHW=Core.arrLDropDown("HW", "HWType");
			ArrayList<String> ArrLSW=Core.arrLDropDown("SW", "SWType");	
			if (ArrLHW!=null && ArrLSW!=null){
				for (String s:ArrLHW){
					cbB_HW.addItem(s);
				}
				for (String s:ArrLSW){
					cbB_SW.addItem(s);
				}
			}else fillcbBoxes(false);
		}
		else{
			cbB_HW.removeAllItems();
			cbB_SW.removeAllItems();
		}
	}
	
	// TODO: Checker, ob die Richtige DB/Tabellen/Spalten vorhanden sind
	
}
