package atmFrame;
/*	Class: MainFrame1
 * 	Fuction: 主功能視窗
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*; 

import Animation.WelcomeLable;
import atmFuction.ATM;

public class MainFrame1 extends JFrame{
	private WelcomeLable welcome = new WelcomeLable("welcome");
//	private Label welcome=new Label("welcome");
	private JButton Receive=new JButton("領錢");
	private JButton SaveMoney=new JButton("存錢");
	private JButton Transfer=new JButton("匯款");
	private JButton Check=new JButton("查看餘額");
	private JPanel contentPane= new JPanel();
	private JPanel jpwelcome = new JPanel();
	private JPanel gridpanel = new JPanel();
	private int Height = 500, Width = 500;
	private Thread thr;
	
	public MainFrame1(ATM atm){
		initComp(atm);
	}

	private void initComp(final ATM atm){
		setVisible(true);
		this.setTitle("ATM System");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);	//僅關閉此視窗
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((int)((screenSize.getWidth()-Width)*0.5), (int)((screenSize.getHeight()-Height)*0.5), Width, Height);

		this.setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 2, 0));
		welcome.setFont(new Font("Serief",Font.ITALIC+Font.BOLD,40));//字體斜邊+粗體+40字
		gridpanel.setLayout(new GridLayout(0, 2, 2, 5));

		startAnimation();
		welcome.setBackground(new Color(180, 240, 245));
		jpwelcome.setBackground(new Color(180, 240, 245));
		gridpanel.setBackground(Color.orange);
		contentPane.add(jpwelcome);
		jpwelcome.add(welcome);
		contentPane.add(gridpanel);
		gridpanel.add(Receive);
		gridpanel.add(SaveMoney);
		gridpanel.add(Transfer);
		gridpanel.add(Check);
		Receive.setFont(new Font("TimesRoman",Font.PLAIN ,40) );
		SaveMoney.setFont(new Font("TimesRoman",Font.PLAIN ,40) );
		Check.setFont(new Font("TimesRoman",Font.PLAIN ,40) );
		Transfer.setFont(new Font("TimesRoman",Font.PLAIN ,40) );

		Receive.addActionListener(new ActionListener(){		//領錢
			public void actionPerformed(ActionEvent ae){
				final PickUpFrame pkFrame = new PickUpFrame(atm);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							pkFrame.addWindowListener(new WindowListener(){
								public void windowClosed(WindowEvent arg0) {		//視窗關閉後
									if(pkFrame.status()){
										dispose();
									}else{
										setVisible(true);
									}
								}
								public void windowActivated(WindowEvent arg0) { }	//焦點視窗
								public void windowClosing(WindowEvent arg0) {		//關閉視窗
									pkFrame.dispose();									
								}
								public void windowDeactivated(WindowEvent arg0) { }	//視窗失去焦點
								public void windowDeiconified(WindowEvent arg0) { }	//視窗取消最小化
								public void windowIconified(WindowEvent arg0) { }	//視窗最小化
								public void windowOpened(WindowEvent arg0) { }		//開啟視窗
							});
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				setVisible(false);
			}
		});
		SaveMoney.addActionListener(new ActionListener(){	//存錢
			public void actionPerformed(ActionEvent ae){
				SaveFrame saveFrame = new SaveFrame(atm);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							saveFrame.addWindowListener(new WindowListener(){
								public void windowClosed(WindowEvent arg0) {		//視窗關閉後
									if(saveFrame.status()){
										dispose();
									}else{
										setVisible(true);
									}
								}
								public void windowActivated(WindowEvent arg0) { }	//焦點視窗
								public void windowClosing(WindowEvent arg0) {		//關閉視窗
									saveFrame.dispose();									
								}
								public void windowDeactivated(WindowEvent arg0) { }	//視窗失去焦點
								public void windowDeiconified(WindowEvent arg0) { }	//視窗取消最小化
								public void windowIconified(WindowEvent arg0) { }	//視窗最小化
								public void windowOpened(WindowEvent arg0) { }		//開啟視窗
							});
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				setVisible(false);
			}
		});
		Check.addActionListener(new ActionListener(){		//查錢
			public void actionPerformed(ActionEvent ae){
				CheckMoneyFrame ckMoneyFrame = new CheckMoneyFrame(atm);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ckMoneyFrame.addWindowListener(new WindowListener(){
								public void windowClosed(WindowEvent arg0) {		//視窗關閉後
									if(ckMoneyFrame.status()){
										dispose();
									}else{
										setVisible(true);
									}
								}
								public void windowActivated(WindowEvent arg0) { }	//焦點視窗
								public void windowClosing(WindowEvent arg0) {		//關閉視窗
									ckMoneyFrame.dispose();									
								}
								public void windowDeactivated(WindowEvent arg0) { }	//視窗失去焦點
								public void windowDeiconified(WindowEvent arg0) { }	//視窗取消最小化
								public void windowIconified(WindowEvent arg0) { }	//視窗最小化
								public void windowOpened(WindowEvent arg0) { }		//開啟視窗
							});
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				setVisible(false);
			}
		});
		Transfer.addActionListener(new ActionListener(){		//匯款
			public void actionPerformed(ActionEvent ae){
				TransferFrame trsfFrame = new TransferFrame(atm);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							trsfFrame.addWindowListener(new WindowListener(){
								public void windowClosed(WindowEvent arg0) {		//視窗關閉後
									if(trsfFrame.status()){
										dispose();
									}else{
										setVisible(true);
									}
								}
								public void windowActivated(WindowEvent arg0) { }	//焦點視窗
								public void windowClosing(WindowEvent arg0) {		//關閉視窗
									trsfFrame.dispose();									
								}
								public void windowDeactivated(WindowEvent arg0) { }	//視窗失去焦點
								public void windowDeiconified(WindowEvent arg0) { }	//視窗取消最小化
								public void windowIconified(WindowEvent arg0) { }	//視窗最小化
								public void windowOpened(WindowEvent arg0) { }		//開啟視窗
							});
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				setVisible(false);
			}
		});
	}
	private void startAnimation(){
		thr = new Thread(welcome);
		thr.start();
	}
}
