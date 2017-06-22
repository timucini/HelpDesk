/**
 * 
 */
package admin;

import java.awt.EventQueue;
import java.sql.SQLException;

import sysInf.*;
import java.util.ArrayList;

import dbHelper.*;

/**
 * @author Matthias Cohn (565998)
 * @version 2017-06-16
 */
public class ACore {
	static String sDBConfFile = "admin";
	protected static String[][] SysConf = new String[18][2];
	protected static String[][] Tickets = null;

	static SysInf actSysInf = new SysInf();
	static dbHelper.ConfigLoader dbConfStatus, dbConfHD;
	static dbHelper.DBCon dbStatus, dbHD;
	static ArrayList<String> AL = new ArrayList<String>();
	static String[] Arr1 = null;
	static String[][] Arr2 = null;

	/**
	 * 
	 */
	public ACore() {

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
					AdminGUI frame = new AdminGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Autom. laden einer Configuration und erstellen der DB-Verbindung
	 * @param DBConfFile: String - Name / Pfad zur Configurationsdatei
	 */
	private static void doDBAutoConnect(String DBConfFile) {
		dbConfStatus = loadDBsConf(DBConfFile);
		dbStatus =dbConSetup(dbConfStatus);

	}

	/**
	 * Laden einer Datenbankconfiguration anhand einer Configurationsfile
	 * @param DBConfFile: Name / Pfad zur Configurationsdatei
	 * @return Datenbankconfigruation
	 */
	public static ConfigLoader loadDBsConf(String DBConfFile) {
		// https://www.java-forum.org/thema/aktuellen-pfad-der-anwendung-ermitteln.21044/
		dbHelper.ConfigLoader dbConf = new ConfigLoader();
		String working_dir = System.getProperty("user.dir");
		working_dir += "\\" + DBConfFile + ".cfg"; // preselect file
		try {
			dbConf.setsFilePath(working_dir);
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
	 * @param dbConf: Datenbankconfiguration
	 * @return Datenbankverbindung
	 */
	public static DBCon dbConSetup(ConfigLoader dbConf) {
		DBCon dbCon = new DBCon();
		AL = null;
		try {
			AL = dbConf.getsArrLConfigItems();
			switch (AL.size()) {
			case 3:
				dbCon.setConnection(AL.get(0), AL.get(1), AL.get(2));
				break;
			case 4:
				dbCon.setConnection(AL.get(0), AL.get(1), AL.get(2), AL.get(3));
				break;
			default:
				throw new Exception("Datenbankkonfiguration unvollständig.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Konfiguration der Datenbankverbindung nicht möglich");
			dbConf.clearConfig(false);
			return null;
		}
		return dbCon;
	}

	/**
	 * Connect / Disconect zur DB und prüft ob verbindung steht
	 * @param bDoConnect boolean - connect (true), disconnect (false)
	 * @param dbCon: Datenbankverbindung
	 * @return boolean: status der DB-Verbindung
	 */
	public static boolean dbConnect(boolean bDoConnect, DBCon dbCon) {
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
	}

	/**
	 * Prüft ob DB-Verbindung besteht
	 * @return true / false
	 */
	public static boolean isDbConnected() {
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
		SysConf[8][0] = "Freier Speicherplatz Sys  in MB";
		SysConf[8][1] = Double.toString(actSysInf.getSysDriveFree());
		SysConf[9][0] = "Nutzung Systempartition in %";
		SysConf[9][1] = String.valueOf(actSysInf.getSysDriveUsage());
		SysConf[10][0] = "RAM";
		SysConf[10][1] = "Manuell eintragen";
		SysConf[11][0] = "IPv4-Addresse";
		SysConf[11][1] = actSysInf.getIPv4Addr();
		SysConf[12][0] = "IPv6-Addresse";
		SysConf[12][1] = actSysInf.getIPv6Addr();
		SysConf[13][0] = "MAC";
		SysConf[13][1] = actSysInf.getMAC();
		SysConf[14][0] = "Telefon";
		SysConf[14][1] = "Manuell eintragen";
		SysConf[15][0] = "Gebaeude";
		SysConf[15][1] = "Manuell eintragen";
		SysConf[16][0] = "Raum";
		SysConf[16][1] = "Manuell eintragen";
		SysConf[17][0] = "Anschluss";
		SysConf[17][1] = "Manuell eintragen";
	}

	/**
	 * lädt die Systemkonfiguration und gibt diese als Array zurück
	 * @return String[][] - Syteminformationen
	 */
	public static String[][] getSysInf() {
		loadSysInf();
		return SysConf;
	}

	
	/**
	 * Erstellen / Updaten einen CI Eintrags in der DB
	 * @param arrLValues: ArrayList<String> - Liste mit neuen Werten
	 * @return int: Anzahl der veränderten Datensätze; Negativ: Update von Datensäten
	 */
	public static int sendTicket(ArrayList<String> arrLValues) {
		ArrayList<String> arrLCols = new ArrayList<String>();
		String sTable = "CIs";
		int iDone = 0;
		AL = null;

		if (bDoesExsist(arrLValues.get(0)) == false) {
			try {
				arrLCols = dbStatus.getColumnsList(sTable);
				arrLCols.remove(0); // id - Autoincrement

				/*
				 * arrLCols.remove(arrLCols.size() - 3); // Solution
				 * arrLCols.remove(arrLCols.size() - 2); // Timestamp update
				 * arrLCols.remove(arrLCols.size() - 1); // TimeStamp created
				 * 
				 * arrLValues.set(4, sForeignKey(dbStatus, arrLValues.get(4),
				 * "HWType","idHW", "HW")); arrLValues.set(5,
				 * sForeignKey(dbStatus, arrLValues.get(5), "SWType","idSW",
				 * "SW")); arrLValues.add(sForeignKey(dbStatus, "Neu",
				 * "Name","idStati", "Stati")); //added Status Neu
				 */
				iDone = dbStatus.insertDataSet(sTable, arrLCols.toArray(new String[arrLCols.size()]),
						arrLValues.toArray(new String[arrLValues.size()]));
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		} else {
			try {
				arrLCols = dbStatus.getColumnsList(sTable);
				arrLCols.remove(0); // id - Autoincrement

				String[][] sCriteria = { { "COMPUTERNAME", arrLValues.get(0) } };
				iDone = dbStatus.updateDataSet(sTable, buildArr(arrLCols, arrLValues), sCriteria);
				iDone*=-1;
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		}

		return iDone;
	}

	/**
	 * Prüft ob ein CI bereits in der DB vorhanden ist
	 * @param sVal: String - Name der CI
	 * @return true / false;
	 */
	private static boolean bDoesExsist(String sVal) {
		ArrayList<String> arrL = null;
		try {
			arrL = dbStatus.getContentOfColumnList("CIs", "COMPUTERNAME");
		} catch (Exception e) {
			arrL = null;
			e.printStackTrace();
		}
		if (arrL == null) {
			return false;
		} else {
			if (arrL.contains(sVal)==true){
			return true;
			} else{
				return false;
			}
		}
	}

	/**
	 * Erstellt ein 2-Dimensionales Array aus den Listen der Spalten und Werte
	 * @param sCols: ArrayList<String> - Namen der Spalten
	 * @param sVals: ArrayList<String> - Werte in den Spalten
	 * @return String[][] - [Spalte][Wert]
	 */
	private static String[][]buildArr(ArrayList<String> sCols, ArrayList<String> sVals){
		String[][] sColContent = new  String[sCols.size()][2];
			for (int cols=0; cols<=sCols.size()-1;cols++){
				sColContent[cols][0]=sCols.get(cols);
				sColContent[cols][1]=sVals.get(cols);
			}			
		return sColContent;
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
	@SuppressWarnings("unused")
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
