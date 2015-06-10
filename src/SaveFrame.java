import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private JLabel moneyLab = new JLabel("金錢");
	private JLabel inMoney = new JLabel("請輸入存入金額:");
	private JTextField jtf1 = new JTextField();
	private JButton Confirm = new JButton("確認");
	private JButton Break = new JButton("退回");
	private int Height = 300, Width = 450;
	private boolean status = false;
	
	public SaveFrame(ATM atm) {
		SaveFrame1(atm);
	}
	public void SaveFrame1(ATM atm) {
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((int)((screenSize.getWidth()-Width)*0.5), (int)((screenSize.getHeight()-Height)*0.5), Width, Height);

		this.setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 5, 5));
		north.setLayout(new GridLayout(1, 0, 5, 5));
		center.setLayout(new GridLayout(1, 0, 0, 0));
		bottom.setLayout(new GridLayout(1, 2, 5, 5));
		
		nowMoney.setFont(new Font("新細明體", Font.BOLD, 22));
		inMoney.setFont(new Font("新細明體", Font.BOLD, 22));
		moneyLab.setFont(new Font("新細明體", Font.BOLD, 22));
		inMoney.setHorizontalAlignment(SwingConstants.CENTER);
		nowMoney.setHorizontalAlignment(SwingConstants.CENTER);
		
		contentPane.add(north);
		north.add(nowMoney);
		north.add(moneyLab);
		contentPane.add(center);
		center.add(inMoney);
		center.add(jtf1);
		contentPane.add(bottom);
		bottom.add(Confirm);
		bottom.add(Break);
		
		Confirm.addActionListener(new ActionListener() {		//確認
			public void actionPerformed(ActionEvent arg0) {
				boolean flag = false;
				try{
					double saveMoney = Double.parseDouble(jtf1.getText());
					if(saveMoney <= 0) throw new ArithmeticException();
					flag = atm.saveMoney(saveMoney);
					if(!flag) throw new Exception();
					closeFrame("交易成功，現金已存入帳戶!");
				}catch(NullPointerException e){
					closeFrame("未輸入金額，交易取消!");
				}catch(ArithmeticException e){
					closeFrame("金額輸入錯誤，交易取消!");
				}catch(Exception e){
					closeFrame("發生錯誤，交易取消!");
				}
			}
		});
		
		Break.addActionListener(new ActionListener() {			//取消
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	public boolean status() {
		return status;
	}
	private void closeFrame(String message){
		JOptionPane.showMessageDialog(null,message);
		int n = JOptionPane.showConfirmDialog(null,
				"您是否繼續交易?","操作問題", JOptionPane.YES_NO_OPTION);
		status = (n != JOptionPane.YES_OPTION) ? true:false;
		dispose();												//關閉本視窗
	}
}
