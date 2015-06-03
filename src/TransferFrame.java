import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class TransferFrame extends JFrame implements ActionListener{
		private JPanel contentPane = new JPanel();
		private JLabel Transfer_Account=new JLabel("Transfer Account");	//輸入轉帳帳號文字(顯示)
		private JTextField txtTransfer_Account =new JTextField();		//輸入轉帳帳號(輸入區)
		private JLabel Money=new JLabel("Transfer Amount");				//輸入金額號文字(顯示)
		private JTextField txtMoney =new JTextField();					//輸入金額 (輸入區)
		private JButton Confirm=new JButton("Confirm");					//確認按鈕
		private JButton Cancel=new JButton("Cancel");					//取消按鈕
	
	public TransferFrame(){
		initComp();
	}
	
	private void initComp(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 250);
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3,2,0,50));
		
		contentPane.add(Transfer_Account);
		contentPane.add(txtTransfer_Account);
		contentPane.add(Money);
		contentPane.add(txtMoney);
		contentPane.add(Confirm);
		contentPane.add(Cancel);
		
		Confirm.addActionListener(this);						//放入請聽
		Cancel.addActionListener(this);							//放入請聽
		
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e){					//請聽器
		JButton HitBtn=(JButton)e.getSource();					//看是誰被啟動的
		JOptionPane.showMessageDialog(null, HitBtn.getText());	//測試
	}
}
