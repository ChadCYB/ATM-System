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
		private JLabel Transfer_Account=new JLabel("Transfer Account");	//��J��b�b����r(���)
		private JTextField txtTransfer_Account =new JTextField();		//��J��b�b��(��J��)
		private JLabel Money=new JLabel("Transfer Amount");				//��J���B����r(���)
		private JTextField txtMoney =new JTextField();					//��J���B (��J��)
		private JButton Confirm=new JButton("Confirm");					//�T�{���s
		private JButton Cancel=new JButton("Cancel");					//�������s
	
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
		
		Confirm.addActionListener(this);						//��J��ť
		Cancel.addActionListener(this);							//��J��ť
		
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e){					//��ť��
		JButton HitBtn=(JButton)e.getSource();					//�ݬO�ֳQ�Ұʪ�
		JOptionPane.showMessageDialog(null, HitBtn.getText());	//����
	}
}
