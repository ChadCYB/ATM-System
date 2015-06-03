/*	Class: MainFrame1
 * 	Fuction: 主功能視窗
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*; 
import javax.swing.UIManager.LookAndFeelInfo;

public class MainFrame1 extends JFrame{
	private Label welcome=new Label("welcome");
	private Button Receive=new Button("領錢");
	private Button SaveMoney=new Button("存錢");
	private Button Check=new Button("查錢");
	private Button Break=new Button("退出");
//	private Container cp = new Container();
	private JPanel contentPane= new JPanel();
	private JPanel jpwelcome = new JPanel();
	private JPanel gridpanel = new JPanel();
	private int Height = 500, Width = 500;
	
	public MainFrame1(ATM atm){
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		initComp(atm);
	}

	private void initComp(final ATM atm){
		setVisible(true);
		this.setTitle("ATM System");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);	//僅關閉此視窗
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((int)((screenSize.getWidth()-Width)*0.5), (int)((screenSize.getHeight()-Height)*0.5), Width, Height);

		this.setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 2, 5));
		welcome.setFont(new Font("Serief",Font.ITALIC+Font.BOLD,30));//字體斜邊+粗體+30字
		gridpanel.setLayout(new GridLayout(0, 2, 2, 5));

		contentPane.add(jpwelcome);
		jpwelcome.add(welcome);
		contentPane.add(gridpanel);
		gridpanel.add(Receive);
		gridpanel.add(SaveMoney);
		gridpanel.add(Check);
		gridpanel.add(Break);
		
		Receive.setBounds(0,300,240,70);
		SaveMoney.setBounds(245,300,240,70);
		Check.setBounds(0,380,240,70);
		Break.setBounds(245,380,240,70);
		
		Receive.addActionListener(new ActionListener(){		//領錢
			public void actionPerformed(ActionEvent ae){
				final PickUpFrame pkFrame = new PickUpFrame(atm);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							pkFrame.addWindowListener(new WindowListener(){
								public void windowClosed(WindowEvent arg0) {		//視窗關閉後
									if(pkFrame.status()){
										JOptionPane.showMessageDialog(null,"感謝使用，祝交易愉快  !");
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
				
			}
		});
		Check.addActionListener(new ActionListener(){		//查錢
			public void actionPerformed(ActionEvent ae){
				final CheckMoneyFrame ckMoneyFrame = new CheckMoneyFrame(atm);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ckMoneyFrame.addWindowListener(new WindowListener(){
								public void windowClosed(WindowEvent arg0) {		//視窗關閉後
									if(ckMoneyFrame.status()){
										JOptionPane.showMessageDialog(null,"感謝使用，祝交易愉快  !");
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
		Break.addActionListener(new ActionListener(){		//退出
			public void actionPerformed(ActionEvent ae){
				JOptionPane.showMessageDialog(null,"感謝使用，祝交易愉快  !");
				dispose();									//關閉本視窗
			}
		});
	}
}
