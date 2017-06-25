/**
 * 
 */
package dbIntegrity;

import java.util.ArrayList;

import dbHelper.*;

/**
 * @author Ich
 *
 */
public class DBCheck {
	private static int iDBType;
	private static DBCon dbCon;
	//private static ConfigLoader dbConf;
	private static ArrayList<String> arrlTables, arrLCols;
	
	/**
	 * 
	 */
	public DBCheck() {
		iDBType=0;
		dbCon=new DBCon();
		//dbConf= new ConfigLoader();		
	}
	
	/**
	 * 
	 * @param dbConf
	 * @param con
	 * @param sDBName
	 * @return int: -1 (Datenbankauswahl fehlgeschlagen),
	 */
	public static int bDBIsInteger(ConfigLoader dbConf, DBCon con, String sDBName){
		iDBType=iSetDBType(sDBName);
		if (iDBType==-1){
			return iDBType; 
		}		
		dbCon=con;
		
	}
	
	
	private static int iSetDBType(String sDBName){
		if (sDBName.contains("Ticket")){
			return 1;
		}else if (sDBName.contains("Help")){
			return 2;
		}else if (sDBName.contains("kedb")){
			return 3;
		}else {
			return -1;
		}
	}
	
	private static void arrlFillTables(){
		
	}
	
	private static void arrlFillCols(){
		
	}
	
	

}
