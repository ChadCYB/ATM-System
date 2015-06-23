package atmFrame;
/*	Class: CheckMoneyFrame
 * 	Fuction: �d�ݾl�B����
 */

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import atmFuction.ATM;

public class CheckMoneyFrame extends JFrame{

	public CheckMoneyFrame(ATM atm) {
		initComp(atm);
	}
	private JLabel jlb1=new JLabel("BankID:");
	private JLabel jlb2=new JLabel("NAME");
	private JLabel jlb3=new JLabel("BALANCE:");
	private JLabel jlb4=new JLabel("MONEY");
	private JButton jbtnBack=new JButton("�~����");
	private JButton jbtnEXIT=new JButton("�������");
	private Container cp;
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private int Height = 400, Width = 500;
	private boolean status = false;
	
	private void getDetail(ATM atm){
		jlb2.setText(atm.checkMoney()[0]);
		jlb4.setText(atm.checkMoney()[1]);
	}
	private void initComp(ATM atm) {
		setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);	//������������
		this.setTitle("CheckMoney");
		this.setResizable(true);
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((int)((screenSize.getWidth()-Width)*0.5), (int)((screenSize.getHeight()-Height)*0.5), Width, Height);
		cp = this.getContentPane();
		cp.setLayout(new BorderLayout());
		cp.setBackground(Color.orange);
		cp.add(jp1,BorderLayout.CENTER);
		cp.add(jp2,BorderLayout.SOUTH);
		getDetail(atm);
		
		jlb1.setFont(new Font("TimesRoman",Font.PLAIN ,25) );
		jlb2.setFont(new Font("TimesRoman",Font.PLAIN ,33) );
		jlb3.setFont(new Font("TimesRoman",Font.PLAIN ,25) );
		jlb4.setFont(new Font("TimesRoman",Font.PLAIN ,33) );
		jbtnBack.setFont(new Font("�s�ө���", Font.BOLD, 22));
		jbtnEXIT.setFont(new Font("�s�ө���", Font.BOLD, 22));
		jp1.setBackground(new Color(180, 240, 245));
		jp2.setBackground(Color.orange);
		
		jp1.setLayout(new GridLayout(2,2,5,5));
		jp1.add(jlb1);
		jp1.add(jlb2);
		jp1.add(jlb3);
		jp1.add(jlb4);
		
		jp2.setLayout(new GridLayout(1,2,5,5));
		jp2.add(jbtnBack);
		jp2.add(jbtnEXIT);
		
		jbtnBack.addActionListener(new ActionListener(){	//�~����
			public void actionPerformed(ActionEvent ae){
				//<<<<<<<<<<<<<<<<MainFrame setVisible
				dispose();
			}
		});
		jbtnEXIT.addActionListener(new ActionListener(){	//�������
			public void actionPerformed(ActionEvent ae){
				status = true;
				dispose();									//����������
			}
		});
	}
	public boolean status(){								//�O�_�������
		return status;
	}
}
