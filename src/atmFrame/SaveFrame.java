package atmFrame;

import java.awt.Font;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import atmFuction.ATM;


public class SaveFrame extends JFrame {
	private JPanel contentPane=new JPanel();
	private JPanel north = new JPanel();
	private JPanel center = new JPanel();
	private JPanel bottom = new JPanel();
	private JLabel nowMoney = new JLabel("�ثe���B:");
	private JLabel moneyLab = new JLabel("����");
	private JLabel inMoney = new JLabel("�п�J�s�J���B:");
	private JTextField jtf1 = new JTextField();
	private JButton Confirm = new JButton("�T�{");
	private JButton Break = new JButton("�h�^");
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
		
		nowMoney.setFont(new Font("�s�ө���", Font.BOLD, 22));
		inMoney.setFont(new Font("�s�ө���", Font.BOLD, 22));
		moneyLab.setFont(new Font("�s�ө���", Font.BOLD, 22));
		inMoney.setHorizontalAlignment(SwingConstants.CENTER);
		nowMoney.setHorizontalAlignment(SwingConstants.CENTER);
		setLabelMoney(atm);
		
		contentPane.add(north);
		north.add(nowMoney);
		north.add(moneyLab);
		contentPane.add(center);
		center.add(inMoney);
		center.add(jtf1);
		contentPane.add(bottom);
		bottom.add(Confirm);
		bottom.add(Break);
				
		Confirm.addActionListener(new ActionListener() {		//�T�{
			public void actionPerformed(ActionEvent arg0) {
				boolean flag = false;
				try{
					double saveMoney = Double.parseDouble(jtf1.getText());
					if(saveMoney <= 0) throw new ArithmeticException();
					flag = atm.saveMoney(saveMoney);
					if(!flag) throw new Exception();
					setLabelMoney(atm);
					closeFrame("������\�A�{���w�s�J�b��!");
				}catch(NullPointerException e){
					closeFrame("����J���B�A�������!");
				}catch(ArithmeticException e){
					closeFrame("���B��J���~�A�������!");
				}catch(Exception e){
					closeFrame("�o�Ϳ��~�A�������!");
				}
			}
		});
		
		Break.addActionListener(new ActionListener() {			//����
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	private void setLabelMoney(ATM atm){
		moneyLab.setText(atm.checkMoney()[1]);
	}
	public boolean status() {
		return status;
	}
	private void closeFrame(String message){
		JOptionPane.showMessageDialog(null,message);
		int n = JOptionPane.showConfirmDialog(null,
				"�z�O�_�~����?","�ާ@���D", JOptionPane.YES_NO_OPTION);
		status = (n != JOptionPane.YES_OPTION) ? true:false;
		dispose();												//����������
	}
}
