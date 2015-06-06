import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PickUpFrame extends JFrame {
	private JPanel contentPane = new JPanel();
	private JLabel Receive = new JLabel("���");
	private JPanel gridpanel = new JPanel();
	private JButton btn1 = new JButton("$1000");
	private JButton btn2 = new JButton("$2000");
	private JButton btn3 = new JButton("$3000");
	private JButton btn5 = new JButton("$5000");
	private JButton btn10 = new JButton("$10,000");
	private JButton btntext = new JButton("�ۦ��J(�u���Ѥd��)");
	private int Height = 450, Width = 500;
	private boolean status = false;
	
	public PickUpFrame(ATM atm) {
		initComp(atm);
	}

	private void initComp(ATM atm) {
		setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);	//������������
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((int)((screenSize.getWidth()-Width)*0.5), (int)((screenSize.getHeight()-Height)*0.5), Width, Height);

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 2, 5));
		gridpanel.setLayout(new GridLayout(0, 2, 5, 5));
		gridpanel.setBackground(Color.orange);
		
		Receive.setFont(new Font("�s�ө���", Font.BOLD, 40));
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
				atmPickUp(1000,atm);
			}
		});
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atmPickUp(2000,atm);
			}
		});
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atmPickUp(3000,atm);
			}
		});
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atmPickUp(5000,atm);
			}
		});
		btn10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atmPickUp(10000,atm);
			}
		});
		btntext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str= JOptionPane.showInputDialog("�п�J�{��(�H�d�����)");
				try{
					if(str.isEmpty()) throw new NumberFormatException();	//�S����J�F��
					double money = Double.parseDouble(str);     			//�ഫ�����
					if(!atm.pickUpMoney(money*1000)) throw new Exception();	//�l�B����
					closeFrame("������\�A�д����{��  !");
				}catch (NumberFormatException ex){
					closeFrame("�o�Ϳ��~�A����w���� !");
				}catch (Exception ex){
					closeFrame("������ѡA�z���b��l�B����  !");
				}
			}
		});
		
	}
	private void atmPickUp(double money, ATM atm){
		if(atm.pickUpMoney(money)){
			closeFrame("������\�A�д����{��  !");
		}else{
			closeFrame("������ѡA�z���b��l�B����  !");
		}
	}
	private void closeFrame(String message){
		JOptionPane.showMessageDialog(null,message);
		int n = JOptionPane.showConfirmDialog(null,
				"�z�O�_�~����?","�ާ@���D", JOptionPane.YES_NO_OPTION);
		status = (n != JOptionPane.YES_OPTION) ? true:false;
		dispose();									//����������
	}
	public boolean status() {
		return status;
	}
}
