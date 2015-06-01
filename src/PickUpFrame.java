
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PickUpFrame {
	static JFrame jfrm=new JFrame();
	static JPanel contentPane=new JPanel();
	static JLabel Receive=new JLabel("領錢");
	static JPanel gridpanel=new JPanel();
	static JButton btn1=new JButton("$1000");
	static JButton btn2=new JButton("$2000");
	static JButton btn3=new JButton("$3000");
	static JButton btn5=new JButton("$5000");
	static JButton btn10=new JButton("$10,000");
	static JButton btntext=new JButton("自行輸入(只提供千元)");
	public PickUpFrame(ATM atm1){
		PickUpFrame2();
	}
	public void PickUpFrame2(){
		jfrm.setBounds(100,100,450,300);
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		jfrm.setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 2, 5));
		
		Receive.setFont(new Font("新細明體", Font.BOLD, 40));
		Receive.setHorizontalAlignment(SwingConstants.CENTER);
		gridpanel.setLayout(new GridLayout(0, 2, 4, 5));
		
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btn10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btntext.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(Receive);
		contentPane.add(gridpanel);
		gridpanel.add(btn1);
		gridpanel.add(btn5);
		gridpanel.add(btn2);
		gridpanel.add(btn10);
		gridpanel.add(btn3);
		gridpanel.add(btntext);
		jfrm.setVisible(true);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

