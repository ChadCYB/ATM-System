import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;


public class CheckMoneyFrame extends JFrame {

	private JPanel contentPane;
	
	public CheckMoneyFrame(ATM atm) {
		initComp(atm);
	}
	private JLabel jlb1=new JLabel("ID:");
	private JLabel jlb2=new JLabel("NAME");
	private JLabel jlb3=new JLabel("BALANCE:");
	private JLabel jlb4=new JLabel("MONEY");
	private JButton jbtnBack=new JButton("繼續交易");
	private JButton jbtnEXIT=new JButton("結束交易");
	private Container cp;
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	
	private void initComp(ATM atm) {
		setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);	//指關閉此視窗
		this.setTitle("CheckMoney");
		this.setResizable(true);
		setBounds(300, 300, 450, 300);
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
		
		jbtnBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
//				<<<<<<<<<<<<<<<<MainFrame setVisible
				dispose();
			}
		});
		jbtnEXIT.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				dispose();
			}
		});
	}

}
