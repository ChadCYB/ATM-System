/*	Class: CheckMoneyFrame
 * 	Fuction: �d�ݾl�B����
 */

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class CheckMoneyFrame extends JFrame {

	public CheckMoneyFrame(ATM atm) {
		initComp(atm);
	}
	private JLabel jlb1=new JLabel("ID:");
	private JLabel jlb2=new JLabel("NAME");
	private JLabel jlb3=new JLabel("BALANCE:");
	private JLabel jlb4=new JLabel("MONEY");
	private JButton jbtnBack=new JButton("�~����");
	private JButton jbtnEXIT=new JButton("�������");
	private Container cp;
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private int Height = 400, Width = 500;
	private boolean tt = false;
	
	public boolean status(){								//�O�_�������
		return tt;
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
//				<<<<<<<<<<<<<<<<MainFrame setVisible
				dispose();
			}
		});
		jbtnEXIT.addActionListener(new ActionListener(){	//�������
			public void actionPerformed(ActionEvent ae){
				JOptionPane.showMessageDialog(null,"�P�¨ϥΡA������r��  !");
				tt = true;
				dispose();									//����������
			}
		});
	}

}
