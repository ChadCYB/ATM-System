import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LogInFrame extends JFrame {
	private JTextField textField;
	private JPasswordField passwordField;

	public LogInFrame(LogIn login) {
		initComp(login);
	}
	private void initComp(final LogIn login) {
		
		JPanel NORTH = new JPanel();
		getContentPane().add(NORTH, BorderLayout.NORTH);
		NORTH.setLayout(new GridLayout(0, 3, 2, 5));
		
		JLabel lblNewLabel = new JLabel("Account");
		NORTH.add(lblNewLabel);
		
		textField = new JTextField();
		NORTH.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Clear");
		NORTH.add(btnNewButton_3);
		
		JLabel lblNewLabel_1 = new JLabel("PIN");
		NORTH.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		NORTH.add(passwordField);
		
		JButton btnNewButton_14 = new JButton("Clear");
		NORTH.add(btnNewButton_14);
		
		JPanel CENTER = new JPanel();
		getContentPane().add(CENTER, BorderLayout.CENTER);
		CENTER.setLayout(new BorderLayout(2, 0));
		
		JPanel EAST = new JPanel();
		CENTER.add(EAST, BorderLayout.EAST);
		EAST.setLayout(new GridLayout(0, 1, 2, 5));
		
		JButton btnNewButton_4 = new JButton("Cancel");
		EAST.add(btnNewButton_4);
		
		JButton btnNewButton_15 = new JButton("Enter");
		EAST.add(btnNewButton_15);
		
		JPanel NUMBER = new JPanel();
		CENTER.add(NUMBER, BorderLayout.CENTER);
		NUMBER.setLayout(new GridLayout(0, 3, 4, 5));
		
		JButton btnNewButton = new JButton("1");
		NUMBER.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnNewButton_1 = new JButton("2");
		NUMBER.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("3");
		NUMBER.add(btnNewButton_2);
		
		JButton btnNewButton_5 = new JButton("4");
		NUMBER.add(btnNewButton_5);
		
		JButton btnNewButton_8 = new JButton("5");
		NUMBER.add(btnNewButton_8);
		
		JButton btnNewButton_6 = new JButton("6");
		NUMBER.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("7");
		NUMBER.add(btnNewButton_7);
		
		JButton btnNewButton_11 = new JButton("8");
		NUMBER.add(btnNewButton_11);
		
		JButton btnNewButton_9 = new JButton("9");
		NUMBER.add(btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton("");
		NUMBER.add(btnNewButton_10);
		
		JButton btnNewButton_12 = new JButton("0");
		NUMBER.add(btnNewButton_12);
		
		JButton btnNewButton_13 = new JButton("");
		NUMBER.add(btnNewButton_13);
	}

}
