
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*; 
import javax.swing.UIManager.LookAndFeelInfo;
public class MainFrame1 extends JFrame{
	private Label welcome=new Label("welcome");
	private Button Receive=new Button("���");
	private Button SaveMoney=new Button("�s��");
	private Button Check=new Button("�d��");
	private Button Break=new Button("�h�X");
//	private boolean tt = true;
	
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
	
//	public boolean status(){
//		return tt;
//	}

	
	private void initComp(ATM atm){
		setVisible(true);
		this.setTitle("ATM System");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 			//X�i�H��
		
		this.setLayout(null);
		this.setBounds(300, 300, 500, 500);
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
				CheckMoneyFrame ckmoneyFrame = new CheckMoneyFrame(atm);
				setVisible(false);
			}
		});
		Break.addActionListener(new ActionListener(){		//�h�X
			public void actionPerformed(ActionEvent ae){
				JOptionPane.showMessageDialog(null,"�P�¨ϥΡA������r��  !");
				System.exit(0);
			}
		});
	}
}
