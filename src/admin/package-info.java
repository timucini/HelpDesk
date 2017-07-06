/**
 * @author Matthias Cohn (565998)
 * @version 1.2 (2017-06-26)
 * 
 * 
 * Benutzer Anwendung zum selbstständigen Melden von Störungen,
 * zum Einsehen von Bearbeitungsstati eigener Tickets, 
 * Einsehen von bereits eingepflegten Lösungen zu eigenen Problemen
 * Greift (geplant) auf eigene DB zu (TicketStatus)
 * Greift (reel) auf die DB des Helpdesk , Tabelle: Tickets zu
 * 
 * Erfordert: mysql-connector-java*.jar
 * Erfordert: dbHelper.jar
 * Erfordert: SysInf.jar
 * Erfordert: DBCheck.java
 * 
 * erwartet: ein VBS 'getInstalledSW.vbs" im Anwendungsverzeichnis
 * erwartet: eine 'admin.cfg' im Anwendungsverzeichnis mit entsprechender Datenbank-Konfiguration 
 */
package admin;