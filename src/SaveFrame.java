import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SaveFrame extends JFrame {
	private JPanel contentPane=new JPanel();
	private JPanel north = new JPanel();
	private JPanel center = new JPanel();
	private JPanel bottom = new JPanel();
	private JLabel nowMoney = new JLabel("目前金額:");
	private JLabel money = new JLabel("金錢");
	private JLabel inMoney = new JLabel("請輸入金額:");
	private JTextField jtf1 = new JTextField();
	private JButton Confirm = new JButton("確認");
	private JButton Break = new JButton("退回");
	
	public SaveFrame(ATM atm) {
		SaveFrame1(atm);
	}
	public void SaveFrame1(ATM atm) {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 5, 5));
		north.setLayout(new GridLayout(1, 0, 5, 5));
		center.setLayout(new GridLayout(1, 0, 0, 0));
		
		nowMoney.setFont(new Font("新細明體", Font.BOLD, 22));
		nowMoney.setHorizontalAlignment(SwingConstants.CENTER);

		inMoney.setFont(new Font("新細明體", Font.BOLD, 22));
		inMoney.setHorizontalAlignment(SwingConstants.CENTER);
		
		bottom.setLayout(new GridLayout(1, 2, 5, 5));
		
		contentPane.add(north);
		north.add(nowMoney);
		north.add(money);
		contentPane.add(center);
		center.add(inMoney);
		center.add(jtf1);
		contentPane.add(bottom);
		bottom.add(Confirm);
		bottom.add(Break);
		
		Confirm.addActionListener(new ActionListener() {		//確認
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		Break.addActionListener(new ActionListener() {			//取消
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
}
