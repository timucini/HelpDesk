package HelpDesk;

import java.io.File;
/*import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Connection {
	Connection conHP=new Connection();
	
	public void connect() {
	
	String usernameHP= "s136045_2588076";
	String passwordHP= "HelpDesk1!=0";
	String urlHP="mysql.webhosting47.1blu.de:3306";
	
	
	conHP.connect() ;	

	
	
	
	
	}
}*/
import java.sql.SQLException;
import java.util.ArrayList;

import dbHelper.*;

/**
 * @author Timur Burkholz (562205)
 * @version 2017-06-16
 * Anlehnung an Core.java
 */

public class Connection {
	//Erstellt 3 Configloader für die 3 DBs und 3DBConnections für die jeweiligen DBS
	static dbHelper.ConfigLoader dbConfHp, dbConfKE,dbConfTs;
	static dbHelper.DBCon conHP, conKE, conTs;
	static ArrayList<String> AL = new ArrayList<String>();

	/**
	 * 
	 */
	public Connection() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		dbConfHp=loadDBsConf("admin");
		conHP=dbConSetup(dbConfHp);
		System.out.println("Verbunden mit Helpdesk: "+dbConnect(true, conHP));

		dbConfKE=loadDBsConf("KEDB");
		conKE=dbConSetup(dbConfHp);
		System.out.println("Verbunden mit KEDB: "+dbConnect(true, conKE));
		
		
		dbConfTs=loadDBsConf("user");
		conTs=dbConSetup(dbConfTs);
		System.out.println("Verbunden mit Status: "+dbConnect(true, conTs));
		
	}

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
		
	}

	public static DBCon dbConSetup(ConfigLoader dbConf) {
		DBCon dbCon = new DBCon();
		AL=null;
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
	
	private static void printTables(DBCon dbCon){
		ArrayList<String> ArrLTables=new ArrayList<String>();
		try {
			ArrLTables=dbCon.getTablesList();
		} catch (Exception e) {
			ArrLTables=null;
			e.printStackTrace();
		}
		if(ArrLTables!=null){
		for (String s:ArrLTables){
			System.out.println(" - Tabelle "+s+ " : ");
			printColumns(dbCon, s);
		}
		}else{
			System.out.println("Keine Tabellen in der Datenbank");
		}
	}
	
	private static void printColumns(DBCon dbCon, String sTable){
		ArrayList<String> ArrLCols=new ArrayList<String>();
		try {
			ArrLCols=dbCon.getColumnsList(sTable);
		} catch (Exception e) {
			ArrLCols=null;
			e.printStackTrace();
		}
		for (String c:ArrLCols){
			System.out.println(" - - "+c);
		}
	}
}

