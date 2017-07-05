
package user;

import java.awt.EventQueue;
import java.sql.SQLException;
import sysInf.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import dbIntegrity.DBCheck;
import dbHelper.*;

/**
 * @author Matthias Cohn (565998)
 * @version 1.2 (2017-06-26)
 */
public class Core {
	static String sDBConfFile = "admin";
	final static String sExpectedSchema="Helpdesk"; // Ticket / Helpdesk / KEDB
 	protected static String[][] SysConf = new String[14][2];
	protected static String[][] Tickets = null;

	static SysInf actSysInf = new SysInf();
	static dbHelper.ConfigLoader dbConfStatus, dbConfHD;
	static dbHelper.DBCon dbStatus, dbHD;
	static boolean bDBisInteger = false;
	static ArrayList<String> AL = new ArrayList<String>();
	static String[] Arr1 = null;
	static String[][] Arr2 = null;


	public Core() {

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		doDBAutoConnect(sDBConfFile);
		loadSysInf();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserGUI frame = new UserGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Autom. laden einer Configuration und erstellen der DB-Verbindung
	 * 
	 * @param DBConfFile:
	 *            String - Name / Pfad zur Configurationsdatei
	 */
	private static void doDBAutoConnect(String DBConfFile) {
		dbConfStatus = loadDBsConf(DBConfFile);
		dbStatus = dbConSetup(dbConfStatus);
	}

	/**
	 * Laden einer Datenbankconfiguration anhand einer Configurationsfile
	 * 
	 * @param DBConfFile:
	 *            Name / Pfad zur Configurationsdatei
	 * @return Datenbankconfigruation
	 */
	public static ConfigLoader loadDBsConf(String DBConfFile) {
		// https://www.java-forum.org/thema/aktuellen-pfad-der-anwendung-ermitteln.21044/
		dbHelper.ConfigLoader dbConf = new ConfigLoader();
		String working_dir = "";
		if (DBConfFile != "") {
			working_dir = System.getProperty("user.dir");
			working_dir += "\\" + DBConfFile + ".cfg"; // preselect file
		}
		try {
			dbConf.setsFilePath(working_dir, "Wählen Sie die Konfiguration für "+sExpectedSchema );
		} catch (Exception e) {
			dbConf.clearConfig(false);
			try {
				dbConf.setsFilePath("");
			} catch (Exception e1) {
				System.out.println("Es konnte keine Config-File eingerichtet werden.");
				e1.printStackTrace();
			}
			System.out.println("Es wurde kein passendes Config-File gefunden ... neues wählen");
			e.printStackTrace();
		}
		return dbConf;
		// System.out.println(working_dir);
		// dbConnect(true);
	}

	/**
	 * Eintichten einer DB-Verbindung anhand einer DB-Configuration
	 * 
	 * @param dbConf:
	 *            Datenbankconfiguration
	 * @return Datenbankverbindung
	 */
	public static DBCon dbConSetup(ConfigLoader dbConf) {
		DBCon dbCon = new DBCon();
		try {
			dbCon.setConnection(dbConf);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Konfiguration der Datenbankverbindung nicht möglich");
			dbConf.clearConfig(false);
			return null;
		}
		return dbCon;
	}

	/**
	 * Überprüft ob das erwartete DB-Schema mit der Datenbank hinter der DB-Konfiguration
	 * übereinstimmt
	 * @return
	 */
	private static boolean bDBOK() {
		// integritätscheck:
		DBCheck dbcCheck=new DBCheck(dbStatus, sExpectedSchema);
		int iOK = dbcCheck.iDBIsInteger();
		System.out.println("Datenbankintegrität: (-1: Fehler; 0:nicht Integer; 1:Integer): " + iOK);
		if (iOK != 1) {
			//dbStatus = null;
			JOptionPane.showMessageDialog(null,
					"Die mit der Konfiguration verbundene Datenbank entspricht nicht der erwarteten Struktur / Inhalt. \n"
							+ "Bitte wählen Sie die richtige Konfiguarions-Datei und verbinden Sie sich erneut.");
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Connect / Disconect zur DB und prüft ob verbindung steht
	 * 
	 * @param bDoConnect
	 *            boolean - connect (true), disconnect (false)
	 * @param dbCon:
	 *            Datenbankverbindung
	 * @return boolean: status der DB-Verbindung
	 */
	public static boolean dbConnect(boolean bDoConnect, DBCon dbCon) {
		if (bDBOK()) {
			if (bDoConnect == true) {
				try {
					dbCon.connect();
					System.out.println("Verbunden mit DB");
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Datenbankverbindung nicht möglich");
					return false;
				}
			} else {
				try {
					dbCon.disconnect();
					System.out.println("Datenbankverbindung erfolgreich getrennt");
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Trennen der Datenbankverbindung nicht möglich");
					return true;
				}
			}
			return dbCon.isConnected();
		} else {
			dbConfStatus.clearConfig(false);
			doDBAutoConnect("");
			return false;
		}
	}

	/**
	 * Prüft ob DB-Verbindung besteht
	 * 
	 * @return true / false
	 */
	public static boolean isDbConnected() {
		if (dbStatus == null) {
			return false;
		}
		return dbStatus.isConnected();
	}

	/**
	 * Erstellen eines Arrays mit Systeminformationen
	 */
	private static void loadSysInf() {
		SysConf[0][0] = "Computername";
		SysConf[0][1] = actSysInf.getComputername();
		SysConf[1][0] = "Anmeldserver";
		SysConf[1][1] = actSysInf.getLogonServer();
		SysConf[2][0] = "Benutzername";
		SysConf[2][1] = actSysInf.getUserName();
		SysConf[3][0] = "Betriebssystem";
		SysConf[3][1] = actSysInf.getOS();
		SysConf[4][0] = "CPU";
		SysConf[4][1] = actSysInf.getProcessor();
		SysConf[5][0] = "Anz. d. CPUs";
		SysConf[5][1] = String.valueOf(actSysInf.getNumberOfCPU());
		SysConf[6][0] = "Systemlaufwerk";
		SysConf[6][1] = actSysInf.getSysDrive();
		SysConf[7][0] = "Kapazität Systempartition in MB";
		SysConf[7][1] = Double.toString(actSysInf.getSysDriveCap());
		SysConf[8][0] = "Freier Speicherplatz Sys in MB";
		SysConf[8][1] = Double.toString(actSysInf.getSysDriveFree());
		SysConf[9][0] = "Nutzung Systempartition";
		SysConf[9][1] = String.valueOf(actSysInf.getSysDriveUsage()) + "%";
		SysConf[10][0] = "RAM in MB";
		SysConf[10][1] = "RAM kann nicht ausgelesen werden";
		SysConf[11][0] = "IPv4-Addresse";
		SysConf[11][1] = actSysInf.getIPv4Addr();
		SysConf[12][0] = "IPv6-Addresse";
		SysConf[12][1] = actSysInf.getIPv6Addr();
		SysConf[13][0] = "MAC";
		SysConf[13][1] = actSysInf.getMAC();
	}

	/**
	 * lädt die Systemkonfiguration und gibt diese als Array zurück
	 * 
	 * @return String[][] - Syteminformationen
	 */
	public static String[][] getSysInf() {
		loadSysInf();
		return SysConf;
	}

	public static String getInstalledWinSW() {
		String sSW = "";
		if (actSysInf.getOS().contains("Windows")) {
			for (String s : actSysInf.arrWinSoftwareList()) {
				sSW += s + "\n";
			}
			;

		} else {
			sSW = "Nur bei Windows-Systemen automatisch";
		}
		return sSW;
	}

	public static ArrayList<String> arrLDropDown(String Table, String Col) {
		ArrayList<String> ArrL = new ArrayList<String>();
		try {
			// Da Felder indexiert (Unique) werden dies Alphabetisch zurück
			// gegeben
			ArrL = dbStatus.getContentOfColumnList(Table, Col);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return ArrL;
	}

	public static String[][] getTicketsforClient() {
		Tickets = null;
		try {
			Tickets = dbStatus
					.getDataSets(
							"SELECT t.idTicket, t.Created, s.Name, t.Updated " + "FROM Ticket t, Stati s "
									+ "WHERE t.SendingCI='" + actSysInf.getComputername() + "' AND t.Status=s.idStati",
							false);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return Tickets;
	}

	public static String sIssueDescription(int iTicketID) {
		String Description = "";
		Arr1 = null;
		try {
			Arr1 = dbStatus.getSingleDataSet(
					"SELECT t.Mitarbeiter, h.HWType , s.SWType, t.Beschreibung, t.Gebaeude, t.Raum, t.Anschluss, t.ActSysConfig, t.ActSoftConfig "
							+ "FROM Ticket t, HW h, SW s " + "WHERE t.HW=h.idHW AND t.SW=s.idSW AND " + "t.idTicket = "
							+ iTicketID + ";");
		} catch (Exception e) {
			Arr1 = null;
			e.printStackTrace();
			return null;
		}
		if (Arr1 != null) {
			Description = "";
			if (Arr1.length != 9) {
				for (String s : Arr1) {
					Description += s + "\n";
				}
				;
			} else {
				Description += "Gemeldet von: " + Arr1[0] + "\n";
				Description += "Betroffene Hardware: " + Arr1[1] + "\n";
				Description += "Betroffene Software: " + Arr1[2] + "\n";
				Description += "Genaue Beschreibung: " + Arr1[3] + "\n";
				Description += "Adresse / Gebäude: " + Arr1[4] + "\n";
				Description += "Raum: " + Arr1[5] + "\n";
				Description += "Anschluss: " + Arr1[6] + "\n";
				Description += "Aktuelle Konfiguration: " + "\n" + Arr1[7] + "\n";
				Description += "Aktuelle Software-Konfiguration: " + "\n" + Arr1[8];
			}
		}
		return Description;
	}

	public static String sSolution(int iTicketID) {
		Arr1 = null;
		try {
			Arr1 = dbStatus.getSingleDataSet("SELECT t.Solution FROM Ticket t WHERE t.idTicket = " + iTicketID + ";");
		} catch (Exception e) {
			Arr1 = null;
			e.printStackTrace();
			return null;
		}
		if (Arr1.length == 1) {
			return Arr1[0];
		} else
			return null;
	}

	public static int sendTicket(ArrayList<String> arrLValues) {
		ArrayList<String> arrLCols = new ArrayList<String>();
		int iDone = 0;
		AL = null;
		try {
			arrLCols = dbStatus.getColumnsList("Ticket");
			arrLCols.remove(0); // id - Autoincrement
			arrLCols.remove(arrLCols.size() - 5); // Solution
			arrLCols.remove(arrLCols.size() - 4); // CI-id
			arrLCols.remove(arrLCols.size() - 3); // Bearbeiter
			arrLCols.remove(arrLCols.size() - 2); // Timestamp update
			arrLCols.remove(arrLCols.size() - 1); // TimeStamp created

			arrLValues.set(4, sForeignKey(dbStatus, arrLValues.get(4), "HWType", "idHW", "HW"));
			arrLValues.set(5, sForeignKey(dbStatus, arrLValues.get(5), "SWType", "idSW", "SW"));
			arrLValues.add(sForeignKey(dbStatus, "Neu", "Name", "idStati", "Stati")); // added
																						// Status
																						// Neu

			iDone = dbStatus.insertDataSet("Ticket", arrLCols.toArray(new String[arrLCols.size()]),
					arrLValues.toArray(new String[arrLValues.size()]));
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return iDone;
	}

	/**
	 * @param db
	 *            Datenbankobjekt in dem gesucht werden soll
	 * @param Crit
	 *            Suchkriterium
	 * @param PrimKey
	 *            in der ForeignTable (Spaltenname)
	 * @param ForeignTable
	 *            Name der ForeingTable
	 * @param CritCol
	 *            Name der Spalte in der das Kriterium gesucht wird
	 * @return int : ForeignKey
	 */
	private static String sForeignKey(DBCon db, String Crit, String CritCol, String PrimKey, String ForeignTable) {
		AL = null;
		try {
			AL = db.getSingleDataSetList(
					"SELECT " + PrimKey + " FROM " + ForeignTable + " WHERE " + CritCol + "='" + Crit + "'");
		} catch (Exception e) {
			System.out.println("Fehler bei SQL: \n" + "SELECT " + PrimKey + " FROM " + ForeignTable + " WHERE "
					+ CritCol + "='" + Crit + "'");
			AL = null;
			e.printStackTrace();
		}
		if (AL != null) {
			return (AL.get(0));
		} else {
			return "1";
		}
	}

}
