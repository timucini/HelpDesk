package HelpDesk;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import dbHelper.*;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;

public class beGui {
	static JFrame bGui=new JFrame();
	private final JLabel lblNewLabel = new JLabel("Tickets bearbeiten");
	private final JComboBox comboBox = new JComboBox();
	private final JComboBox comboBox_1 = new JComboBox();
	private final JButton btnAnwenden = new JButton("anwenden");
	private final JButton btnNewButton = new JButton("zur\u00FCck");
	
public beGui() {
	init();
}
private void init() {
	bGui.setBounds(100, 100, 435, 283);
	bGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	bGui.getContentPane().setLayout(null);
	bGui.getContentPane().setLayout(null);
	lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
	lblNewLabel.setBounds(47, 48, 124, 28);
	
	bGui.getContentPane().add(lblNewLabel);
	comboBox.setBorder(new TitledBorder(null, "Tickets ausw\u00E4hlen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	comboBox.setBounds(47, 87, 124, 44);
	
	bGui.getContentPane().add(comboBox);
	comboBox_1.setBorder(new TitledBorder(null, "Status setzen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	comboBox_1.setBounds(220, 87, 124, 44);
	
	bGui.getContentPane().add(comboBox_1);
	btnAnwenden.setBounds(193, 199, 97, 23);
	
	bGui.getContentPane().add(btnAnwenden);
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			do_btnNewButton_actionPerformed(e);
		}
	});
	btnNewButton.setBounds(300, 199, 97, 23);
	
	bGui.getContentPane().add(btnNewButton);
	
}

	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		bGui.setVisible(false);
		conGui.cGui.setVisible(true);
	}
}
