import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LogInFrame extends JFrame {
	public LogInFrame(LogIn login){
		initComp(login);
	}
	private JButton jbtnClear=new JButton("Clear");
	private JButton jbtnLogin=new JButton("LogIn");
	private JButton jbtnExit=new JButton("Exit");
	private JLabel jlb1=new JLabel("AccountID:");
	private JLabel jlb2=new JLabel("AccountPIN:");
	private JTextField jtfAcc=new JTextField("b00001");
	private JPasswordField jpfPass=new JPasswordField("458712");
	private Container cp;
	private boolean tt = false;
	
	public boolean status(){
		return tt;
	}
	
	private void initComp(final LogIn login){
		setVisible(true);
		this.setTitle("LogIn");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		setBounds(300,300,350,200);
		cp = this.getContentPane();
		cp.setLayout(null);
		cp.setBackground(Color.orange);
		
		cp.add(jbtnClear);
		cp.add(jbtnLogin);
		cp.add(jbtnExit);
		cp.add(jlb1);
		cp.add(jlb2);
		cp.add(jtfAcc);
		cp.add(jpfPass);
		
		jbtnClear.setBounds(100, 90, 65, 30);		
		jbtnLogin.setBounds(240, 23, 65, 97);
		jbtnExit.setBounds(170, 90, 65, 30);
		jlb1.setBounds(30, 20, 70, 30);
		jlb2.setBounds(30, 50, 70, 30);
		jtfAcc.setBounds(100, 23, 135, 25);
		jpfPass.setBounds(100, 53, 135, 25);
		
		jbtnExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				System.exit(0);
			}
		});
		
		jbtnClear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				jtfAcc.setText(""); jpfPass.setText("");
			}
		});
		
		jbtnLogin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String sAcc = jtfAcc.getText();
				login.setAcc(sAcc);										//帳戶ID
				login.setPassWD(new String(jpfPass.getPassword()));		//帳戶PIN
				if(login.findAccount()){								//是否登入成功
					JOptionPane.showMessageDialog(null,"Welcome "+login.getUserName()+" !");
					setVisible(false);
					tt = true;
				}else{
					JOptionPane.showMessageDialog(null,"Ops! "+sAcc+" please try again!");
				}
//				System.out.println("ACC: "+account+", PASS: "+passwd+");
			}
		});
	}
}