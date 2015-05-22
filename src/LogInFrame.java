import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class LogInFrame extends JFrame {
	public LogInFrame(LogIn login, ATM atm){
		initComp(login,atm);
	}
	
	private JButton jbtnClear1=new JButton("Clear");
	private JButton jbtnClear2=new JButton("Clear");
	private JLabel jlb1=new JLabel("ID:");
	private JLabel jlb2=new JLabel("PIN:");
	private JTextField jtfAcc=new JTextField("b00001");
	private JPasswordField jpfPass=new JPasswordField("458712");
	private JButton jbtnNumber1=new JButton("1");
	private JButton jbtnNumber2=new JButton("2");
	private JButton jbtnNumber3=new JButton("3");
	private JButton jbtnNumber4=new JButton("4");
	private JButton jbtnNumber5=new JButton("5");
	private JButton jbtnNumber6=new JButton("6");
	private JButton jbtnNumber7=new JButton("7");
	private JButton jbtnNumber8=new JButton("8");
	private JButton jbtnNumber9=new JButton("9");
	private JButton jbtnNumbera=new JButton("");
	private JButton jbtnNumber0=new JButton("0");
	private JButton jbtnNumberb=new JButton("");
	private JButton jbtnCancel=new JButton("Cancel");
	private JButton jbtnEnter=new JButton("Enter");
	private Container cp;
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp3 = new JPanel();
	private JPanel jp4 = new JPanel();
//	private boolean tt = false;
	
//	public boolean status(){
//		return tt;
//	}
	
	private void initComp(final LogIn login, final ATM atm){
		setVisible(true);
		this.setTitle("LogIn");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(true);
		setBounds(300,300,500,400);
		cp = this.getContentPane();
		cp.setLayout(new BorderLayout());		
		cp.add(jp1,BorderLayout.NORTH);
		cp.add(jp2,BorderLayout.CENTER);
		
		jp1.setLayout(new GridLayout(0,3,2,5));
		jp1.setBackground(new Color(180,240,245));
		jp1.add(jlb1);
		jp1.add(jtfAcc);
		jp1.add(jbtnClear1);
		jp1.add(jlb2);
		jp1.add(jpfPass);
		jp1.add(jbtnClear2);
		
		jp2.setLayout(new BorderLayout());
		jp2.add(jp3,BorderLayout.CENTER);
		jp2.add(jp4,BorderLayout.EAST);
		
		jp3.setLayout(new GridLayout(0, 3, 4, 5));
		jp3.setBackground(Color.orange);
		jp3.add(jbtnNumber1);
		jp3.add(jbtnNumber2);
		jp3.add(jbtnNumber3);
		jp3.add(jbtnNumber4);
		jp3.add(jbtnNumber5);
		jp3.add(jbtnNumber6);
		jp3.add(jbtnNumber7);
		jp3.add(jbtnNumber8);
		jp3.add(jbtnNumber9);
		jp3.add(jbtnNumbera);
		jp3.add(jbtnNumber0);
		jp3.add(jbtnNumberb);
		
		jp4.setLayout(new GridLayout(0, 1, 2, 5));
		jp4.setBackground(Color.orange);
		jp4.add(jbtnCancel);
		jp4.add(jbtnEnter);  
		
		jbtnClear1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				jtfAcc.setText("");
			}
		});
		
		jbtnClear2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				jpfPass.setText("");
			}
		});
		
		jbtnEnter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String sAcc = jtfAcc.getText();
				login.setAcc(sAcc);										
				login.setPassWD(new String(jpfPass.getPassword()));		
				if(login.findAccount()){								
					JOptionPane.showMessageDialog(null,"Welcome "+login.getUserName()+" !");
					atm.setAccUser(sAcc, new String(jpfPass.getPassword()));
					MainFrame1 mFrame1 = new MainFrame1(atm);
//					tt = true;
					dispose();											//Ãö³¬µøµ¡
				}else{
					JOptionPane.showMessageDialog(null,"Ops! "+sAcc+" please try again!");
				}
			}
		});
				
	}

}
