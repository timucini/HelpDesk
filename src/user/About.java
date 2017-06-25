package user;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.BevelBorder;

/**
 * @author Wolfgang Lang
 * @version ==> lblVersion
 * @see "Foliensatz zur Vorlesung"
 */
@SuppressWarnings( "serial" )
public class About extends JPanel {

  private JLabel        lblTitle     = new JLabel( "  HelpDesk - User-Interface  "     );
  private JLabel        lblEmpty     = new JLabel( " "                      );
  private JLabel        lblAuthor    = new JLabel( "Author: Matthias Cohn"  );
  private JLabel        lblVersion   = new JLabel( "Rel. 1.1.0, 2017-06-25" );
  private JLabel        lblCompany   = new JLabel( "HWR Berlin"             );
  private GridBagLayout layoutMain   = new GridBagLayout();
  private Border        border       = BorderFactory.createEtchedBorder();
  
  /*--------------------------------------------------------------------------*/
  
  public About() {    
    try { jbInit(); } catch (Exception e) { e.printStackTrace(); }
  }

  /*--------------------------------------------------------------------------*/
  
  /**
   * Panel initialisieren
   */
  private void jbInit() throws Exception {
    
    setLayout( layoutMain );
    setBorder( border );
    
    lblTitle.setFont( new Font( "Tahoma", 1, 20) );
    lblTitle.setForeground( Color.LIGHT_GRAY );
    lblTitle.setBorder(
                  new BevelBorder(BevelBorder.RAISED, null, null, null, null) );    
    
    lblAuthor.setFont( new Font( "Tahoma", 1, 14) );
    lblAuthor.setForeground( new Color(0, 82, 255) );
    
    lblVersion.setFont( new Font( "Tahoma", 1, 14) );
    lblVersion.setForeground( new Color(0, 82, 255) );
    
    lblCompany.setFont( new Font( "Tahoma", 1, 14) );
    lblCompany.setForeground( new Color(0, 82, 255) );
    
    this.add( lblTitle, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                                    GridBagConstraints.WEST,
                                    GridBagConstraints.NONE,
                                    new Insets(5, 15, 0, 15), 0, 0));
    this.add( lblEmpty, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                                    GridBagConstraints.WEST,
                                    GridBagConstraints.NONE,
                                    new Insets(5, 15, 0, 15), 0, 0));
    this.add( lblAuthor, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                                    GridBagConstraints.WEST,
                                    GridBagConstraints.NONE,
                                    new Insets(0, 15, 0, 15), 0, 0));
    this.add( lblVersion, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                                    GridBagConstraints.WEST,
                                    GridBagConstraints.NONE,
                                    new Insets(0, 15, 0, 15), 0, 0));
    this.add( lblCompany, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                                    GridBagConstraints.WEST,
                                    GridBagConstraints.NONE,
                                    new Insets(0, 15, 5, 15), 0, 0));    
  }
}
