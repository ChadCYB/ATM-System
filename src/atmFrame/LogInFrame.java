package atmFrame;
/*	Class: LogInFrame
 * 	Fuction: �n�J����
 */
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import atmFuction.ATM;
import atmFuction.LogIn;

public class LogInFrame extends JFrame implements ActionListener {
	public LogInFrame(LogIn login, ATM atm){
		initComp(login,atm);
	}

	private JButton jbtnClear1 = new JButton("Clear");
	private JButton jbtnClear2 = new JButton("Clear");
	private JLabel jlb1 = new JLabel("ID:");
	private JLabel jlb2 = new JLabel("PIN:");
	private JTextField jtfAcc = new JTextField("A10546");
	private JPasswordField jpfPass = new JPasswordField("458712");

	private JButton JButn[] = new JButton[12];
	private JButton jbtnCancel = new JButton("Cancel");
	private JButton jbtnEnter = new JButton("Enter");
	private Container cp;
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp3 = new JPanel();
	private JPanel jp4 = new JPanel();

	private void initComp(final LogIn login, final ATM atm){
		setVisible(true);
		this.setTitle("LogIn");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);	//������ӵ{��
		this.setResizable(true);
		setBounds(300, 300, 500, 400);
		cp = this.getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(jp1, BorderLayout.NORTH);
		cp.add(jp2, BorderLayout.CENTER);

		jp1.setLayout(new GridLayout(0, 3, 2, 5));
		jp1.setBackground(new Color(180, 240, 245));
		jp1.add(jlb1);
		jp1.add(jtfAcc);
		jp1.add(jbtnClear1);
		jp1.add(jlb2);
		jp1.add(jpfPass);
		jp1.add(jbtnClear2);
		
		jpfPass.setEditable(false);
		
		jp2.setLayout(new BorderLayout());
		jp2.add(jp3, BorderLayout.CENTER);
		jp2.add(jp4, BorderLayout.EAST);

		jp3.setLayout(new GridLayout(0, 3, 4, 5));
		jp3.setBackground(Color.orange);

		jp4.setLayout(new GridLayout(0, 1, 2, 5));
		jp4.setBackground(Color.orange);
		jp4.add(jbtnCancel);
		jp4.add(jbtnEnter);
		
		for (int i = 0; i < JButn.length; i++) {
			JButn[i] = new JButton();
			JButn[i].addActionListener(this);
			jp3.add(JButn[i]);
		}
		randJBTN();										//�üƫ��s

		jbtnClear1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				jtfAcc.setText("");
			}
		});
		jbtnClear2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				jpfPass.setText("");
			}
		});
		jbtnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				randJBTN();								//�üƫ��s
				jtfAcc.setText("");
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
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								mFrame1.addWindowListener(new WindowListener(){
									public void windowClosed(WindowEvent arg0) {		//����������
										resetFrame(); //<<<<<<<<<<<<<<<<<<<RESET
										JOptionPane.showMessageDialog(null,"�P�¨ϥΡA������r��  !");
										setVisible(true);
									}
									public void windowActivated(WindowEvent arg0) { }	//�J�I����
									public void windowClosing(WindowEvent arg0) {		//��������
										mFrame1.dispose();
										mFrame1.setVisible(false);
									}
									public void windowDeactivated(WindowEvent arg0) { }	//�������h�J�I
									public void windowDeiconified(WindowEvent arg0) { }	//���������̤p��
									public void windowIconified(WindowEvent arg0) { }	//�����̤p��
									public void windowOpened(WindowEvent arg0) { }		//�}�ҵ���
								});
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					setVisible(false);
				}else{
					JOptionPane.showMessageDialog(null,"Ops! "+sAcc+" please try again!");
					resetFrame();
				}
			}
		});
	}
	private void randJBTN(){							//�üƫ��s
		int[] num = new int[12];
		boolean[] bNum = new boolean[12];

		for (int i = 0; i < num.length; i++) {
			int rand = (int)(Math.random()*num.length);
			if(bNum[rand]){
				i--;
			}else{
				num[i] = rand;
				bNum[rand] = true;
			}
		}
		for (int i = 0; i < num.length; i++) {
			JButn[i].setFont(new Font("TimesRoman",Font.PLAIN ,40) );
			if(num[i]<10){
				JButn[i].setText(num[i] + "");
			}else if(num[i]==10){
				JButn[i].setText("#");
			}else if(num[i]==11){
				JButn[i].setText("*");
			}
		}
	}
	private void resetFrame() {							//���m�e��
		jtfAcc.setText("");
		jpfPass.setText(""); 
		randJBTN();
	}
	public void actionPerformed(ActionEvent e) { 		//�������s���O
		JButton HitBtn = (JButton) e.getSource(); 		//�d�ߨӷ�
		jpfPass.setText(jpfPass.getText() + HitBtn.getText());
		randJBTN();
	}

}