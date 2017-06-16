/**
 * 
 */
package user;

import java.awt.EventQueue;
import sysInf.*;

import java.util.ArrayList;

import dbHelper.*;


/**
 * @author Ich
 *
 */
public class Core {
	static SysInf actSysInf=new SysInf();
	static dbHelper.ConfigLoader dbConf=new ConfigLoader();
	static dbHelper.DBCon dbCon = new DBCon();
	static ArrayList<String> AL=new ArrayList<String>();
	static String[] Arr1=null;
	static String[][] Arr2=null;
	/**
	 * 
	 */
	public Core() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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

	public static boolean dbConnect(boolean bDoConnect){
		try {
			AL=dbConf.getsArrLConfigItems();
			switch (AL.size()){
			case 3:
				dbCon.setConnection(AL.get(0),AL.get(1), AL.get(2));				
			break;
			case 4:
				dbCon.setConnection(AL.get(0),AL.get(1), AL.get(2), AL.get(3));
			break;
			default:
				throw new Exception ("Datenbankkonfiguration unvollständig.");
			}
			dbCon.connect();
			System.out.println("Verbunden mit DB");
		} catch (Exception e) {
			System.out.println("Datenbankverbindung nicht möglich");
			dbConf.clearConfig(false);
			return false;			
		}
				
		return dbCon.isConnected();
	}
	public static void printSysInf(){
		System.out.println(actSysInf.getComputername());
		System.out.println(actSysInf.getLogonServer());
		System.out.println(actSysInf.getOS());
		System.out.println(actSysInf.getProcessor());
		System.out.println(actSysInf.getUserName());
		System.out.println(actSysInf.getSysDrive());
		System.out.println(actSysInf.getSysDriveUsage().toString()+"%");
		System.out.println(actSysInf.getIPv4Addr());
		System.out.println(actSysInf.getIPv6Addr());
		System.out.println(actSysInf.getMAC());
	}
}
