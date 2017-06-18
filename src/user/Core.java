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
	final static String DBConfFile="user";
	static SysInf actSysInf = new SysInf();
	static dbHelper.ConfigLoader dbConf = new ConfigLoader();
	static dbHelper.DBCon dbCon = new DBCon();
	static ArrayList<String> AL = new ArrayList<String>();
	static String[] Arr1 = null;
	static String[][] Arr2 = null;
	protected static String[][] SysConf = new String[2][14];

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
		working_dir += "\\"+DBConfFile +".cfg"; // preselect file
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
		SysConf[1][0] = actSysInf.getComputername();
		SysConf[0][1] = "Anmeldserver";
		SysConf[1][1] = actSysInf.getLogonServer();
		SysConf[0][2] = "Benutzername";
		SysConf[1][2] = actSysInf.getUserName();
		SysConf[0][3] = "Betriebssystem";
		SysConf[1][3] = actSysInf.getOS();
		SysConf[0][4] = "CPU";
		SysConf[1][4] = actSysInf.getProcessor();
		SysConf[0][5] = "Anz. d. CPUs";
		SysConf[1][5] = String.valueOf(actSysInf.getNumberOfCPU());
		SysConf[0][6] = "Systemlaufwerk";
		SysConf[1][6] = actSysInf.getSysDrive();
		SysConf[0][7] = "Kapazität Systempartition";
		SysConf[1][7] = Double.toString(actSysInf.getSysDriveCap());
		SysConf[0][8] = "Freier Speicherplatz Sys";
		SysConf[1][8] = Double.toString(actSysInf.getSysDriveFree());
		SysConf[0][9] = "Nutzung Systempartition";
		SysConf[1][9] = String.valueOf(actSysInf.getSysDriveUsage()) + "%";
		SysConf[0][10] = "RAM";
		SysConf[1][10] = "RAM kann nicht ausgelesen werden";
		SysConf[0][11] = "IPv4-Addresse";
		SysConf[1][11] = actSysInf.getIPv4Addr();
		SysConf[0][12] = "IPv6-Addresse";
		SysConf[1][12] = actSysInf.getIPv6Addr();
		SysConf[0][13] = "MAC";
		SysConf[1][13] = actSysInf.getMAC();
	}

	public static String[][] getSysInf() {
		loadSysInf();
		return SysConf;
	}
	
	public static ArrayList<String> arrLDropDown(String Table, String Col){
		ArrayList<String> ArrL=new ArrayList<String>();
		try {
			ArrL=dbCon.getContentOfColumnList(Table, Col);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
		return ArrL;
	}

}
