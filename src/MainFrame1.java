
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

import javax.swing.*; 
import javax.swing.UIManager.LookAndFeelInfo;
public class MainFrame1 extends JFrame{
	private Label welcome=new Label("welcome");
	private Button Receive=new Button("���");
	private Button SaveMoney=new Button("�s��");
	private Button Check=new Button("�d��");
	private Button Break=new Button("�h�X");
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
		this.setLayout(null);
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((int)((screenSize.getWidth()-Width)*0.5), (int)((screenSize.getHeight()-Height)*0.5), Width, Height);
		welcome.setBounds(170, 50, 200, 200);
		welcome.setFont(new Font("Serief",Font.ITALIC+Font.BOLD,30));	//�r�����+����+30�r
		
		this.add(welcome);
		this.add(Receive);
		this.add(SaveMoney);
		this.add(Check);
		this.add(Break);
		
		Receive.setBounds(0,300,240,70);
		SaveMoney.setBounds(245,300,240,70);
		Check.setBounds(0,380,240,70);
		Break.setBounds(245,380,240,70);
		
		Receive.addActionListener(new ActionListener(){		//���
			public void actionPerformed(ActionEvent ae){
				
			}
		});
		SaveMoney.addActionListener(new ActionListener(){	//�s��
			public void actionPerformed(ActionEvent ae){
				
			}
		});
		Check.addActionListener(new ActionListener(){		//�d��
			public void actionPerformed(ActionEvent ae){
				final CheckMoneyFrame ckmoneyFrame = new CheckMoneyFrame(atm);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ckmoneyFrame.addWindowListener(new WindowListener(){
								public void windowClosed(WindowEvent arg0) {		//����������
									if(ckmoneyFrame.status()){
										dispose();
									}else{
										setVisible(true);
									}
								}
								public void windowActivated(WindowEvent arg0) {		//�J�I����
									
								}
								public void windowClosing(WindowEvent arg0) {		//��������
									ckmoneyFrame.dispose();									
								}
								public void windowDeactivated(WindowEvent arg0) {	//�������h�J�I
									
								}
								public void windowDeiconified(WindowEvent arg0) {	//���������̤p��
									
								}
								public void windowIconified(WindowEvent arg0) {		//�����̤p��
									
								}
								public void windowOpened(WindowEvent arg0) {		//�}�ҵ���
									
								}
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
