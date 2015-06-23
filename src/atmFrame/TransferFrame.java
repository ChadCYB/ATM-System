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
		private JLabel Transfer_Account=new JLabel("�״ڱb��");			//��J��b�b����r(���)
		private JTextField txtTransfer_Account =new JTextField();		//��J��b�b��(��J��)
		private JLabel Money=new JLabel("�״ڪ��B");						//��J���B��r(���)
		private JTextField txtMoney =new JTextField();					//��J���B (��J��)
		private JButton Confirm=new JButton("�T�{�״�");					//�T�{���s
		private JButton Cancel=new JButton("�������");					//�������s
		private int Height = 300, Width = 500;
		private boolean status = false;
		private ATM atm;
	public TransferFrame(ATM atm1){
		atm = atm1;
		initComp();
	}
	
	private void initComp(){
		setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);			//������������
		this.setTitle("CheckMoney");
		this.setResizable(true);
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((int)((screenSize.getWidth()-Width)*0.5), (int)((screenSize.getHeight()-Height)*0.5), Width, Height);
		
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3,2,0,50));
		contentPane.setBackground(new Color(180, 240, 245));
		
		Transfer_Account.setFont(new Font("�s�ө���", Font.BOLD, 22));
		txtTransfer_Account.setFont(new Font("�s�ө���", Font.BOLD, 22));
		Money.setFont(new Font("�s�ө���", Font.BOLD, 22));
		txtMoney.setFont(new Font("�s�ө���", Font.BOLD, 22));
		Confirm.setFont(new Font("�s�ө���", Font.BOLD, 22));
		Cancel.setFont(new Font("�s�ө���", Font.BOLD, 22));
		
		contentPane.add(Transfer_Account);
		contentPane.add(txtTransfer_Account);
		contentPane.add(Money);
		contentPane.add(txtMoney);
		contentPane.add(Confirm);
		contentPane.add(Cancel);
		
		Confirm.addActionListener(this);							//��J��ť
		Cancel.addActionListener(this);								//��J��ť
		
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e){						//��ť��
		JButton HitBtn=(JButton)e.getSource();						//�ݬO�ֳQ�Ұʪ�
		switch(HitBtn.getText()){
			case "�T�{�״�":
				String trfID = txtTransfer_Account.getText();
				String trfBalance = txtMoney.getText();
				try{
					if(trfID.isEmpty() || trfBalance.isEmpty()) throw new Exception();	//�S����J�F��
					double money = Double.parseDouble(trfBalance);				//�ഫ�����
					if(money <=0) throw new Exception();
					if(!atm.moneyTransfer(money, trfID)) throw new Exception();	//�l�B����
					closeFrame("�״ڦ��\  !");
				}catch (Exception ex){
					closeFrame("�o�Ϳ��~�A����w���� !");
				}
				break;
			case "�������":
				dispose();										//����������
				break;
		}
	}
	private void closeFrame(String message){
		JOptionPane.showMessageDialog(null,message);
		int n = JOptionPane.showConfirmDialog(null,
				"�z�O�_�~����?","�ާ@���D", JOptionPane.YES_NO_OPTION);
		status = (n != JOptionPane.YES_OPTION) ? true:false;
		dispose();												//����������
	}
	public boolean status() {
		return status;
	}
}
