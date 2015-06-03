import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PickUpFrame extends JFrame {
	private JPanel contentPane = new JPanel();
	private JLabel Receive = new JLabel("領錢");
	private JPanel gridpanel = new JPanel();
	private JButton btn1 = new JButton("$1000");
	private JButton btn2 = new JButton("$2000");
	private JButton btn3 = new JButton("$3000");
	private JButton btn5 = new JButton("$5000");
	private JButton btn10 = new JButton("$10,000");
	private JButton btntext = new JButton("自行輸入(只提供千元)");
	private int Height = 450, Width = 500;

	public PickUpFrame(ATM atm) {
		initComp(atm);
	}

	private void initComp(ATM atm) {
		setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);	//僅關閉此視窗
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((int)((screenSize.getWidth()-Width)*0.5), (int)((screenSize.getHeight()-Height)*0.5), Width, Height);

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 2, 5));
		gridpanel.setLayout(new GridLayout(0, 2, 4, 5));
		
		Receive.setFont(new Font("新細明體", Font.BOLD, 40));
		Receive.setHorizontalAlignment(SwingConstants.CENTER);
		
		contentPane.add(Receive);
		contentPane.add(gridpanel);
		gridpanel.add(btn1);
		gridpanel.add(btn5);
		gridpanel.add(btn2);
		gridpanel.add(btn10);
		gridpanel.add(btn3);
		gridpanel.add(btntext);
		
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atm.pickUpMoney(1000);
			}
		});
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atm.pickUpMoney(2000);
			}
		});
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atm.pickUpMoney(3000);
			}
		});
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atm.pickUpMoney(5000);
			}
		});
		btn10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atm.pickUpMoney(10000);
			}
		});
		btntext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
	}

	public boolean status() {
		// TODO Auto-generated method stub
		return false;
	}
}
