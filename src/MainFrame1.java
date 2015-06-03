/*	Class: MainFrame1
 * 	Fuction: �D�\�����
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
	private Button Receive=new Button("���");
	private Button SaveMoney=new Button("�s��");
	private Button Check=new Button("�d��");
	private Button Break=new Button("�h�X");
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
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);	//������������
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((int)((screenSize.getWidth()-Width)*0.5), (int)((screenSize.getHeight()-Height)*0.5), Width, Height);

		this.setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 2, 5));
		welcome.setFont(new Font("Serief",Font.ITALIC+Font.BOLD,30));//�r�����+����+30�r
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
		
		Receive.addActionListener(new ActionListener(){		//���
			public void actionPerformed(ActionEvent ae){
				final PickUpFrame pkFrame = new PickUpFrame(atm);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							pkFrame.addWindowListener(new WindowListener(){
								public void windowClosed(WindowEvent arg0) {		//����������
									if(pkFrame.status()){
										JOptionPane.showMessageDialog(null,"�P�¨ϥΡA������r��  !");
										dispose();
									}else{
										setVisible(true);
									}
								}
								public void windowActivated(WindowEvent arg0) { }	//�J�I����
								public void windowClosing(WindowEvent arg0) {		//��������
									pkFrame.dispose();									
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
			}
		});
		SaveMoney.addActionListener(new ActionListener(){	//�s��
			public void actionPerformed(ActionEvent ae){
				
			}
		});
		Check.addActionListener(new ActionListener(){		//�d��
			public void actionPerformed(ActionEvent ae){
				final CheckMoneyFrame ckMoneyFrame = new CheckMoneyFrame(atm);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ckMoneyFrame.addWindowListener(new WindowListener(){
								public void windowClosed(WindowEvent arg0) {		//����������
									if(ckMoneyFrame.status()){
										JOptionPane.showMessageDialog(null,"�P�¨ϥΡA������r��  !");
										dispose();
									}else{
										setVisible(true);
									}
								}
								public void windowActivated(WindowEvent arg0) { }	//�J�I����
								public void windowClosing(WindowEvent arg0) {		//��������
									ckMoneyFrame.dispose();									
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
			}
		});
		Break.addActionListener(new ActionListener(){		//�h�X
			public void actionPerformed(ActionEvent ae){
				JOptionPane.showMessageDialog(null,"�P�¨ϥΡA������r��  !");
				dispose();									//����������
			}
		});
	}
}
