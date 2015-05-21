import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;


public class CheckMoneyFrame extends JFrame {

	private JPanel contentPane;
	
	public CheckMoneyFrame() {
		initComp();
	}
	private JLabel jlb1=new JLabel("ID:");
	private JLabel jlb2=new JLabel("NAME");
	private JLabel jlb3=new JLabel("BALANCE:");
	private JLabel jlb4=new JLabel("MONEY");
	private JButton jbtnBack=new JButton("Back");
	private JButton jbtnEXIT=new JButton("EXIT");
	private Container cp;
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private boolean tt = false;
	
	public boolean status(){
		return tt;
	}
	
	private void initComp() {
		
		setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("LogIn");
		this.setResizable(true);
		setBounds(100, 100, 450, 300);
		cp = this.getContentPane();
		cp.setLayout(new BorderLayout());
		cp.setBackground(Color.orange);
		
		cp.add(jp1,BorderLayout.CENTER);
		cp.add(jp2,BorderLayout.SOUTH);
		
		jp1.setLayout(new GridLayout(2,2,0,5));
		
		jp1.add(jlb1);
		jp1.add(jlb2);
		jp1.add(jlb3);
		jp1.add(jlb4);
		
		jp2.setLayout(new GridLayout(1,2,0,5));
		
		jp2.add(jbtnBack);
		jp2.add(jbtnEXIT);
		
		
	}

}
