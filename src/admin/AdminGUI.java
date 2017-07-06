package admin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

/**
 * @author Matthias Cohn (565998)
 * @version 1.2 (2017-06-26)
 * 
 * Grafische Oberfläche zur Anwendung
 */
public class AdminGUI extends JFrame {

	/**
	 * Alles autom. generierter Code - i.d.R. nur Designvorschriften
	 * Es werden keine weiteren Kommentare dazu verfasst
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
	private final JButton cmd_send = new JButton("PC registieren");
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private final JScrollPane scrollP_SW = new JScrollPane();
	private static JTextPane txtP_SW = new JTextPane();

	/**
	 * Create the frame.
	 */
	public AdminGUI() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				do_this_windowClosing(arg0);
			}
		});
		setTitle("HelpDesk - Admin-Interface");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 385);

		setJMenuBar(menuBar);
		mnDatei.setMnemonic('D');

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
		mntmBeenden.setMnemonic('B');
		mntmBeenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mntmBeenden_actionPerformed(e);
			}
		});

		mnDatei.add(mntmBeenden);
		mnHilfe.setMnemonic('h');

		menuBar.add(mnHilfe);
		mntmber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_mntmber_actionPerformed(arg0);
			}
		});
		mntmber.setMnemonic('b');

		mnHilfe.add(mntmber);
		menuBar.add(lbl_isConnected);
		lbl_isConnected.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_isConnected.setForeground(Color.RED);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		tabbedPane.setBounds(0, 0, 474, 300);

		contentPane.add(tabbedPane);
		tm_SysInf = new JTable(ACore.SysConf[0].length, ACore.SysConf.length);
		tabbedPane.addTab("System", null, scrollP_SysInf, null);
		scrollP_SysInf.setViewportView(tm_SysInf);
		tm_SysInf.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tm_SysInf.setColumnSelectionAllowed(false);
		tm_SysInf.setCellSelectionEnabled(true);
		tm_SysInf.setRowSelectionAllowed(true);

		// https://www.java-forum.org/thema/jtable-spaltennamen-aendern.2266/
		tm_SysInf.setModel(new DefaultTableModel(new Object[ACore.SysConf.length][ACore.SysConf[0].length],
				new String[] { "Eigenschaft", "Wert" }));

		tabbedPane.addTab("Software", null, scrollP_SW, null);
		txtP_SW.setText("Nur auf Windows möglich");

		scrollP_SW.setViewportView(txtP_SW);
		cmd_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_cmd_send_actionPerformed(arg0);
			}
		});
		cmd_send.setMnemonic('P');
		cmd_send.setEnabled(false);
		cmd_send.setBounds(330, 305, 140, 23);

		contentPane.add(cmd_send);

		
		/**
		 * Ab hier eigenständiger Code - es wird Dokumentiert
		 */
		showSysInf();
		updatelbl(ACore.isDbConnected());

		
	}

	/**
	 * Startmethode
	 * Fragt alle wichtigen Systeminnformationen bei der ACore-Klasse an und
	 * leitet diese an entsprechende "Befüll"-Methoden weiter
	 */
	private static void showSysInf() {
		ACore.getSysInf();

		scrollP_SysInf.setBorder(new TitledBorder(null, "Aktuelle Systemkonfiguration", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		modTable(ACore.SysConf); // Tabelle füllen
		fillSW(ACore.getInstalledWinSW());
	}


	
	/**
	 * Das Software-Informations Pane wird befüllt,
	 * 
	 * @param sSW
	 */
	private static void fillSW(String sSW) {
		txtP_SW.setText(sSW);
	}

	/**
	 * Die HW-Tabelle wird befüllt
	 * @param sArr
	 */
	public static void modTable(String[][] sArr) {
		for (int x = 0; x <= sArr.length - 1; x++) {
			for (int y = 0; y <= sArr[x].length - 1; y++) {
				tm_SysInf.setValueAt(sArr[x][y], x, y);
			}
		}
	}

	
	@SuppressWarnings("unused")
	/**
	 * Erstellt ArrayList mit allen angezeigten Elementen 
	 * und wandelt diese ggf. in String um (Prevention)
	 * @return ArrayList (String) Aktuelle Systemkonfiguration
	 */
	public static ArrayList<String> getTable() {
		String sValue = "";
		tm_SysInf.selectAll();
		ArrayList<String> arrL = new ArrayList<String>();
		for (int x = 1; x <= tm_SysInf.getColumnCount() - 1; x++) {
			for (int y = 0; y <= tm_SysInf.getRowCount() - 1; y++) {
				sValue = tm_SysInf.getValueAt(y, x).toString();
				switch (y) { // Fehlerbehandlung zahlenwerte in den Spalten
				case 5:
					try {
						int i = Integer.parseInt(sValue);
						arrL.add(sValue);
					} catch (Exception e) {
						arrL.add("0");
					}
					break;
				case 7:
					try {
						double d = Double.parseDouble(sValue);
						arrL.add(sValue);
					} catch (Exception e) {
						arrL.add("0");
					}
					break;
				case 8:
					try {
						double d = Double.parseDouble(sValue);
						arrL.add(sValue);
					} catch (Exception e) {
						arrL.add("0");
					}
					break;
				case 9:
					try {
						float f = Float.parseFloat(sValue);
						arrL.add(sValue);
					} catch (Exception e) {
						arrL.add("0");
					}
					break;
				case 10:
					try {
						int i = Integer.parseInt(sValue);
						arrL.add(sValue);
					} catch (Exception e) {
						arrL.add("0");
					}
					break;
				default:
					arrL.add(sValue);
				}
			}
		}
		arrL.add(txtP_SW.getText());
		if (arrL.size() == 19) {
			return arrL;
		} else {
			return null;
		}
	}

	
	/**
	 * Fragt den Status der Datenbankverbindung ab und aktualisiert die Statusanzeige auf der GUI
	 * @param con - Datenbankverbindungsobjekt vom Typ DBCon
	 */
	private static void updatelbl(boolean con) {
		if (con == true) {
			lbl_isConnected.setForeground(Color.GREEN);
			lbl_isConnected.setText("DB verbunden");
		} else {
			lbl_isConnected.setForeground(Color.RED);
			lbl_isConnected.setText("Nicht verbunden");
		}
	}

	/**
	 * Befehl für das Verbinden mit der aktuellen DB-Configuration
	 * der Befehl this.repaint(); holt die aktuelle DB-Verbindung
	 * für den Fall, dass sich die Verbindung seit der Initialisierung des Fensters
	 * geändert hat (zB durch ungültige DB-Struktur)
	 * @param arg0
	 */
	protected void do_mntmDBVerbinden_actionPerformed(ActionEvent arg0) {
		this.repaint();
		if (ACore.dbConnect(true, ACore.dbStatus)) {
			updatelbl(ACore.isDbConnected());
			cmd_send.setEnabled(ACore.isDbConnected());
		}
		// System.out.println(ACore.isDbConnected());
	}

	// ====================================================================
	/**
	 * Befehl zum trennen der Datenbankverbindung
	 * @param e
	 */
	protected void do_mntmDBTrennen_actionPerformed(ActionEvent e) {
		if (ACore.dbConnect(false, ACore.dbStatus)) {
			updatelbl(ACore.dbConnect(false, ACore.dbStatus));
			cmd_send.setEnabled(ACore.isDbConnected());
		}
	}

	/**
	 * Aufräumen, wenn Fenster geschlossen wird
	 * @param arg0
	 */
	protected void do_this_windowClosing(WindowEvent arg0) {
		System.exit(0);
	}

	/**
	 * Anwendung beenden
	 * @param arg0
	 */
	protected void do_mntmBeenden_actionPerformed(ActionEvent arg0) {
		System.exit(0);
	}

	/**
	 * Anzeigen der Hilfe \ About - Informationen
	 * @param arg0
	 */
	protected void do_mntmber_actionPerformed(ActionEvent arg0) {
		JOptionPane.showMessageDialog(this, new About(), "About", JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * Schickt das 'Ticket' zum Erstellen / Updaten via Core an die DB
	 * @param arg0
	 */
	protected void do_cmd_send_actionPerformed(ActionEvent arg0) {
		if (getTable() != null) {
			int i = 0;
			i = ACore.sendTicket(getTable());
			if (i > 0) {
				JOptionPane.showMessageDialog(null, "PC wurde erfolgreich registriert");
			} else if (i < 0) {
				JOptionPane.showMessageDialog(null, "PC war bereits in der Datenbank - Eintrag wurde aktualisiert.");
			} else {
				JOptionPane.showMessageDialog(null, "PC konnt nicht registriert werden.");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Systeminformationen konnten nicht verarbeitet werden.");
		}
	}
}
