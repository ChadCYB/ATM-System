
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*; 
import javax.swing.border.EmptyBorder;
public class MainFrame1 {
	static JFrame jfrm=new JFrame("MainFrame1");
	static JPanel jpwelcome= new JPanel();
	static Label welcome=new Label("welcome");
	static Button Receive=new Button("領錢");
	static Button SaveMoney=new Button("存錢");
	static Button Check=new Button("查錢");
	static Button Break=new Button("退出");
	static JPanel contentPane= new JPanel();
	static JPanel gridpanel = new JPanel();
	
	public MainFrame1(ATM atm1){
		MainFrame2();
	}
	private void MainFrame2(){
		jfrm.setBounds(100,100,450,300);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		jfrm.setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 2, 5));
		welcome.setFont(new Font("Serief",Font.ITALIC+Font.BOLD,30));//字體斜邊+粗體+30字
		gridpanel.setLayout(new GridLayout(0, 2, 2, 5));

		
		Receive.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				
			}
		});
		
		SaveMoney.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				
			}
		});
		Check.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				
			}
		});
		Break.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				
			}
		});
		
		contentPane.add(jpwelcome);
		jpwelcome.add(welcome);
		contentPane.add(gridpanel);
		gridpanel.add(Receive);
		gridpanel.add(SaveMoney);
		gridpanel.add(Check);
		gridpanel.add(Break);
		jfrm.setVisible(true);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //X可以按
	}
}
