package HelpDesk;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import dbHelper.*;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;


public class einGui {
	static JFrame eGui=new JFrame();
	private final JLabel lblEingabeTicket = new JLabel("Eingabe Ticket");
	private final JTextField textField = new JTextField();
	private final JTextField textField_1 = new JTextField();
	private final JTextField textField_2 = new JTextField();
	private final JComboBox comboBox = new JComboBox();
	private final JTextArea textArea = new JTextArea();
	private final JComboBox comboBox_1 = new JComboBox();
	
	
	
	public einGui() {
		init();
	}
	private void init() {
		eGui=new JFrame();
		eGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		eGui.setBounds(100, 100, 450, 300);
		eGui.getContentPane().setLayout(null);
		textField.setBorder(new TitledBorder(null, "Ticket-ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textField.setBounds(23, 98, 105, 39);
		textField.setColumns(10);
		
		lblEingabeTicket.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblEingabeTicket.setBounds(23, 27, 127, 28);
		
		eGui.getContentPane().add(lblEingabeTicket);
		
		eGui.getContentPane().add(textField);
		textField_1.setColumns(10);
		textField_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ci-ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textField_1.setBounds(138, 98, 105, 39);
		
		eGui.getContentPane().add(textField_1);
		textField_2.setColumns(10);
		textField_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Mitarbeiter", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textField_2.setBounds(253, 98, 105, 39);
		
		eGui.getContentPane().add(textField_2);
		comboBox.setBorder(new TitledBorder(null, "Bitte ausw\u00E4hlen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		comboBox.setBounds(23, 159, 105, 39);
		

		
		
		eGui.getContentPane().add(comboBox);
		textArea.setBorder(new TitledBorder(null, "Beschreibung", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textArea.setBounds(194, 171, 204, 72);
		comboBox.addItem("Telefon");
		comboBox.addItem("PCs");
		
		
		
		
		eGui.getContentPane().add(textArea);
		comboBox_1.setBorder(new TitledBorder(null, "Bitte ausw\u00E4hlen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		comboBox_1.setBounds(23, 211, 105, 39);
		comboBox_1.addItem("Hardware");
		comboBox_1.addItem("Software");
		
		eGui.getContentPane().add(comboBox_1);
	}
}
