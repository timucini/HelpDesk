package HelpDesk;

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



public class Connection {
	static dbHelper.ConfigLoader dbConf=new ConfigLoader();
	static dbHelper.DBCon conHP = new DBCon();
	static ArrayList<String> AL=new ArrayList<String>();
	static String[] Arr1=null;
	static String[][] Arr2=null;
public Connection() {
	try {
		AL=dbConf.getsArrLConfigItems();
	} catch (Exception e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	switch (AL.size()){
	case 3:
		conHP.setConnection(AL.get(0),AL.get(1), AL.get(2));				
	break;
	case 4:
		conHP.setConnection(AL.get(0),AL.get(1), AL.get(2), AL.get(3));
	break;
	default:
		try {
			throw new Exception ("Datenbankkonfiguration unvollständig.");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	try {
		conHP.connect();
		System.out.println("Verbunden mit DB");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

}
}

