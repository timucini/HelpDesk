/**
 * 
 */
package user;

import java.awt.EventQueue;
import java.sql.SQLException;

import sysInf.*;
import java.util.ArrayList;

import dbHelper.*;

/**
 * @author Matthias Cohn (565998)
 * @version 2017-06-16
 */
public class Core {
	static String DBConfFile = "user";
	protected static String[][] SysConf = new String[14][2];
	protected static String[][] Tickets = null;

	static SysInf actSysInf = new SysInf();
	static dbHelper.ConfigLoader dbConf = new ConfigLoader();
	static dbHelper.DBCon dbCon = new DBCon();
	static ArrayList<String> AL = new ArrayList<String>();
	static String[] Arr1 = null;
	static String[][] Arr2 = null;

	/**
	 * 
	 */
	public Core() {

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		loadDBs();
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

	public static void loadDBs() {
		// https://www.java-forum.org/thema/aktuellen-pfad-der-anwendung-ermitteln.21044/
		String working_dir = System.getProperty("user.dir");
		working_dir += "\\" + DBConfFile + ".cfg"; // preselect file
		try {
			dbConf.setsFilePath(working_dir);
		} catch (Exception e) {
			// Wenn Datei nicht gefunden... Fileopener?!
			e.printStackTrace();
		}
		// System.out.println(working_dir);
		// dbConnect(true);
	}

	public static boolean dbConnect(boolean bDoConnect) {
		if (bDoConnect == true) {
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
				dbCon.connect();
				System.out.println("Verbunden mit DB");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Datenbankverbindung nicht möglich");
				dbConf.clearConfig(false);
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

	public static boolean isDbConnected() {
		return dbCon.isConnected();
	}

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
		SysConf[7][0] = "Kapazität Systempartition";
		SysConf[7][1] = Double.toString(actSysInf.getSysDriveCap());
		SysConf[8][0] = "Freier Speicherplatz Sys";
		SysConf[8][1] = Double.toString(actSysInf.getSysDriveFree());
		SysConf[9][0] = "Nutzung Systempartition";
		SysConf[9][1] = String.valueOf(actSysInf.getSysDriveUsage()) + "%";
		SysConf[10][0] = "RAM";
		SysConf[10][1] = "RAM kann nicht ausgelesen werden";
		SysConf[11][0] = "IPv4-Addresse";
		SysConf[11][1] = actSysInf.getIPv4Addr();
		SysConf[12][0] = "IPv6-Addresse";
		SysConf[12][1] = actSysInf.getIPv6Addr();
		SysConf[13][0] = "MAC";
		SysConf[13][1] = actSysInf.getMAC();
	}

	public static String[][] getSysInf() {
		loadSysInf();
		return SysConf;
	}

	public static ArrayList<String> arrLDropDown(String Table, String Col) {
		ArrayList<String> ArrL = new ArrayList<String>();
		try {
			ArrL = dbCon.getContentOfColumnList(Table, Col);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return ArrL;
	}

	public static String[][] getTicketsforClient() {
		Tickets = null;
		try {
			Tickets = dbCon.getDataSets("SELECT t.idTicket, t.Created, s.Name, t.Updated " + "FROM Ticket t, Stati s "
					+ "WHERE t.SendingCI='" + actSysInf.getComputername() + "' AND t.Status=s.idStati", false);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return Tickets;
	}
	
	public static String sIssueDescription(int iTicketID){
		String Description="";
		Arr1=null;
		try {
			Arr1=dbCon.getSingleDataSet("SELECT t.Mitarbeiter, h.HWType , s.SWType, t.Beschreibung, t.Gebaeude, t.Raum, t.Anschluss, t.ActSysConfig "+
								"FROM Ticket t, HW h, SW s " +
								"WHERE t.HW=h.idHW AND t.SW=s.idSW AND "+
								"t.idTicket = "+iTicketID+";");
		} catch (Exception e) {
			Arr1=null;
			e.printStackTrace();
			return null;
		}
		if (Arr1!=null){
			Description="";
			if (Arr1.length!=8){
				for (String s:Arr1){
					Description+=s+"\n";
				};
			} else{
			Description+="Gemeldet von: "+Arr1[0]+"\n";
			Description+="Betroffene Hardware: "+Arr1[1]+"\n";
			Description+="Betroffene Software: "+Arr1[2]+"\n";
			Description+="Genaue Beschreibung: "+Arr1[3]+"\n";
			Description+="Adresse / Gebäude: "+Arr1[4]+"\n";
			Description+="Raum: "+Arr1[5]+"\n";
			Description+="Anschluss: "+Arr1[6]+"\n";
			Description+="Aktuelle Konfiguration: "+Arr1[7]+"\n";
			}
		}		
		return Description;
	}
	
	public static String sSolution(int iTicketID){
		Arr1=null;
			try {
				Arr1=dbCon.getSingleDataSet("SELECT s.DoThis FROM Solution s, Ticket t WHERE t.Solution = s.IDSolution");
			} catch (Exception e) {
				Arr1=null;
				e.printStackTrace();
				return null;
			}
		if (Arr1.length==1){
			return Arr1[0];
		}else return null;
	}
	
	public static int sendTicket(ArrayList<String> arrLValues){
		try {
			AL=dbCon.getColumnsList("Ticket");
			AL.remove(0); //id - Autoincrement
			AL.remove(AL.size()-2); //Timestamp update
			AL.remove(AL.size()-1); //TimeStamp created

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
}
