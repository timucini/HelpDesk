package HelpDesk;

import java.sql.SQLException;
import dbHelper.*;



public class Connection {
	static DBCon conHP=new DBCon();
	static DBCon conTS=new DBCon();
	static DBCon conKE=new DBCon();
	
	

	

public Connection() {
	connect();
}
private void connect() {
	//Verbindung zur Helpdesk Datendank
			conHP.setConnection("mysql.webhosting47.1blu.de:3306", "s136045_2588076", "HelpDesk1!=0");
		try {
			conHP.connect();
			System.out.println("Verbindungen steht zur HP-DB");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Fehler bei Verbindung zur HP-DB");
		}
		//Verbindung zur Ticketstatus DB
			conTS.setConnection("mysql.webhosting47.1blu.de;3306", "s136045_2588057", "TicketStatus1!=0");
		try {
			conTS.connect();
			System.out.println("Verbindungen steht zur TS-DB");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Fehler bei Verbindung zur TS-DB");
		}
		//Verbindung zur KEDB
			conKE.setConnection("mysql.webhosting47.1blu.de;3306", "s136045_2588052", "KEDB1!=0");
			try {
				conKE.connect();
				System.out.println("Verbindungen steht zur KEDB");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("Fehler bei Verbindung zur KEDB");
			}
	
}
public boolean checkCon() {
	if(conHP.isConnected()&&conTS.isConnected()&&conHP.isConnected())
		return true;
	else
	return false;
}
}
