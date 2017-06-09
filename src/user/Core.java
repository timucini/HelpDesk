/**
 * 
 */
package user;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import dbHelper.*;


/**
 * @author Ich
 *
 */
public class Core {
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
			default:
				throw new Exception ("Datenbankkonfiguration unvollständig.");
			}

		} catch (Exception e) {
			System.out.println("Datenbankverbindung nicht möglich");
			dbConf.clearConfig(false);
			return false;			
		}
		try {
			dbCon.connect();
		} catch (SQLException e) {
			System.out.println("Datenbankverbindung nicht möglich");
			dbConf.clearConfig(false);
			return false;
		}
		
		
		return bDoConnect;
	}
}
