import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MainFrame1 extends JFrame {
	public MainFrame1(Bank theBank){
		initComp(theBank);
	}
	private Container cp;
	
	private void initComp(Bank theBank){
		setVisible(true);
		this.setTitle("ATM");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		setBounds(300,300,350,200);
		cp = this.getContentPane();
		cp.setLayout(null);
		cp.setBackground(Color.orange);
	}
}