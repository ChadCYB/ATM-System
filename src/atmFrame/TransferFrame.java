package atmFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import atmFuction.ATM;

class TransferFrame extends JFrame implements ActionListener {
		private JPanel contentPane = new JPanel();
		private JLabel Transfer_Account=new JLabel("匯款帳號");			//輸入轉帳帳號文字(顯示)
		private JTextField txtTransfer_Account =new JTextField();		//輸入轉帳帳號(輸入區)
		private JLabel Money=new JLabel("匯款金額");						//輸入金額文字(顯示)
		private JTextField txtMoney =new JTextField();					//輸入金額 (輸入區)
		private JButton Confirm=new JButton("確認匯款");					//確認按鈕
		private JButton Cancel=new JButton("取消交易");					//取消按鈕
		private int Height = 300, Width = 500;
		private boolean status = false;
		private ATM atm;
	public TransferFrame(ATM atm1){
		atm = atm1;
		initComp();
	}
	
	private void initComp(){
		setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);			//僅關閉此視窗
		this.setTitle("CheckMoney");
		this.setResizable(true);
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((int)((screenSize.getWidth()-Width)*0.5), (int)((screenSize.getHeight()-Height)*0.5), Width, Height);
		
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3,2,0,50));
		contentPane.setBackground(new Color(180, 240, 245));
		
		Transfer_Account.setFont(new Font("新細明體", Font.BOLD, 22));
		txtTransfer_Account.setFont(new Font("新細明體", Font.BOLD, 22));
		Money.setFont(new Font("新細明體", Font.BOLD, 22));
		txtMoney.setFont(new Font("新細明體", Font.BOLD, 22));
		Confirm.setFont(new Font("新細明體", Font.BOLD, 22));
		Cancel.setFont(new Font("新細明體", Font.BOLD, 22));
		
		contentPane.add(Transfer_Account);
		contentPane.add(txtTransfer_Account);
		contentPane.add(Money);
		contentPane.add(txtMoney);
		contentPane.add(Confirm);
		contentPane.add(Cancel);
		
		Confirm.addActionListener(this);							//放入請聽
		Cancel.addActionListener(this);								//放入請聽
		
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e){						//請聽器
		JButton HitBtn=(JButton)e.getSource();						//看是誰被啟動的
		switch(HitBtn.getText()){
			case "確認匯款":
				String trfID = txtTransfer_Account.getText();
				String trfBalance = txtMoney.getText();
				try{
					if(trfID.isEmpty() || trfBalance.isEmpty()) throw new Exception();	//沒有輸入東西
					double money = Double.parseDouble(trfBalance);				//轉換成整數
					if(money <=0) throw new Exception();
					if(!atm.moneyTransfer(money, trfID)) throw new Exception();	//餘額不足
					closeFrame("匯款成功  !");
				}catch (Exception ex){
					closeFrame("發生錯誤，交易已取消 !");
				}
				break;
			case "取消交易":
				dispose();										//關閉本視窗
				break;
		}
	}
	private void closeFrame(String message){
		JOptionPane.showMessageDialog(null,message);
		int n = JOptionPane.showConfirmDialog(null,
				"您是否繼續交易?","操作問題", JOptionPane.YES_NO_OPTION);
		status = (n != JOptionPane.YES_OPTION) ? true:false;
		dispose();												//關閉本視窗
	}
	public boolean status() {
		return status;
	}
}
