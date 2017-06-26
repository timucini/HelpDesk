/**
 * 
 */
package dbIntegrity;

import java.sql.SQLException;
import java.util.ArrayList;

import dbHelper.*;

/**
 * @author Matthias Cohn (565998)
 * @version 1.0 (2017-06-26)
 */
public class DBCheck {
	private static int iDBType; // (1=Ticket), 2=Helpdesk, 3= KEDB
	private static DBCon dbCon;
	private static String sDB;
	// private static ConfigLoader dbConf;

	private static ArrayList<String> arrlTables = new ArrayList<String>();
	private static ArrayList<String> arrlCols = new ArrayList<String>();

	/**
	 * 
	 */
	public DBCheck(DBCon dbConnection, String sDataBase) {
		iDBType = 0;
		dbCon = dbConnection;
		sDB=sDataBase;
		
		// dbConf= new ConfigLoader();
	}

	/**
	 * 
	 * @param dbConf
	 * @param con
	 * @param sDBName
	 * @return int: -1 (Datenbankauswahl fehlgeschlagen),
	 */
	public int iDBIsInteger(){
		iDBType=iSetDBType(sDB);
		if (iDBType==-1){
			return iDBType; 
		}	
		
		arrlFillTables(); //soll wird geladen
		
			try {
				dbCon.connect();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}	
		
		boolean bOK= bCheck();
		try {
			dbCon.disconnect();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (bOK) {			
			return 1;
		}
		else {
			return 0;
		}
	}

	private static boolean bCheck() {
		boolean bOK = true;
		ArrayList<String> arrlTempTable = new ArrayList<String>();
		try {
			arrlTempTable = dbCon.getTablesList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		ArrayList<String> arrlTempCols = new ArrayList<String>();
		for (String sTab : arrlTables) {

			if (arrlTempTable.contains(sTab) != true) { // sind soll tabellen
														// vorhanden
				return false;
			} else {
				arrlTempCols.clear();
				arrlFillCols(sTab); // Soll wird geladen
				try {
					arrlTempCols = dbCon.getColumnsList(sTab);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				for (String sCol : arrlTempCols) {
					if (arrlCols.contains(sCol) != true) { // sind soll Spalten
															// vorhanden
						return false;
					}
				}
			}
		}
		return bOK;
	}

	private static int iSetDBType(String sDBName) {
		if (sDBName.contains("Ticket")) {
			return 1;
		} else if (sDBName.contains("Help")) {
			return 2;
		} else if (sDBName.contains("kedb")) {
			return 3;
		} else {
			return -1;
		}
	}

	private static void arrlFillTables() {
		arrlTables.clear();
		switch (iDBType) {
		case 1: // Ticket: Tritt nach Reduzierung der DB's nicht auf
			
			arrlTables.add("SW");
			arrlTables.add("HW");
			arrlTables.add("Ticket");
			arrlTables.add("Stati");
			arrlTables.add("Solution");
			break;
		case 2: // HelpDesk
			arrlTables.add("Bearbeiter");
			arrlTables.add("CIs");
			arrlTables.add("SW");
			arrlTables.add("HW");
			arrlTables.add("Ticket");
			arrlTables.add("Stati");
			break;
		case 3: // KEDB
			arrlTables.add("KEDB");
			break;
		default:
			arrlTables = null;
		}
	}

	private static void arrlFillCols(String sTable) {
		arrlCols.clear();
		switch (sTable) {
		case "Ticket":
			arrlCols.add("idTicket");
			arrlCols.add("SendingCI");
			arrlCols.add("Mitarbeiter");
			arrlCols.add("Telefon");
			arrlCols.add("EMail");
			arrlCols.add("HW");
			arrlCols.add("SW");
			arrlCols.add("Beschreibung");
			arrlCols.add("Gebaeude");
			arrlCols.add("Raum");
			arrlCols.add("Anschluss");
			arrlCols.add("ActSysConfig");
			arrlCols.add("ActSoftConfig");
			arrlCols.add("Status");
			arrlCols.add("Solution");
			arrlCols.add("Created");
			arrlCols.add("Updated");
			if (iDBType == 2) {
				arrlCols.add("CIs_id");
				arrlCols.add("Bearbeiter_idMitarbeiter");
			}
			break;
		case "CIs":
			arrlCols.add("id");
			arrlCols.add("COMPUTERNAME");
			arrlCols.add("LOGONSERVER");
			arrlCols.add("USERNAME");
			arrlCols.add("OS");
			arrlCols.add("Processor");
			arrlCols.add("NrOfCPUs");
			arrlCols.add("HomeDrive");
			arrlCols.add("HDCap");
			arrlCols.add("HDFree");
			arrlCols.add("HDUsage");
			arrlCols.add("RAM");
			arrlCols.add("IPv4");
			arrlCols.add("IPv6");
			arrlCols.add("MAC");
			arrlCols.add("Tel");
			arrlCols.add("Gebaeude");
			arrlCols.add("Raum");
			arrlCols.add("Anschluss");
			arrlCols.add("Software");
			break;
		case "Bearbeiter":
			arrlCols.add("idMitarbeiter");
			arrlCols.add("Vorname");
			arrlCols.add("Nachname");
			arrlCols.add("E-Mail");
			arrlCols.add("Telefonnummer");
			arrlCols.add("CIS");
			break;
		case "Stati":
			arrlCols.add("idStati");
			arrlCols.add("Name");
			arrlCols.add("Beschreibung");
			break;
		case "HW":
			arrlCols.add("idHW");
			arrlCols.add("HWType");
			arrlCols.add("Beschreibung");
			break;
		case "SW":
			arrlCols.add("idSW");
			arrlCols.add("SWType");
			arrlCols.add("Beschreibung");
			break;
		case "KEDB":
			arrlCols.add("Fehler");
			arrlCols.add("Hardware");
			arrlCols.add("Softwware");
			arrlCols.add("Betroffen");
			arrlCols.add("Loesungsvorschlag");
		case "Solution":
			arrlCols.add("idSolution");
			arrlCols.add("DoThis");
			break;
		default:
			arrlCols = null;
		}
	}

}
