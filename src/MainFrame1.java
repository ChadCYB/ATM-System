
import java.awt.*;
import javax.swing.*; 

public class MainFrame1 {
	private JFrame jfrm=new JFrame("MainFrame1");
	private Label welcome=new Label("welcome");
	private Button Receive=new Button("���");
	private Button SaveMoney=new Button("�s��");
	private Button Check=new Button("�d��");
	private Button Break=new Button("�h�X");
	
	public MainFrame1(ATM atm){
		jfrm.setLayout(null);
		jfrm.setSize(500,500);
		jfrm.setLocation(300,100);
		welcome.setLocation(170,50);
		welcome.setSize(200,200);
		welcome.setFont(new Font("Serief",Font.ITALIC+Font.BOLD,30));//�r�����+����+30�r
		
		Receive.setBounds(0,300,240,70);
		SaveMoney.setBounds(245,300,240,70);
		Check.setBounds(0,380,240,70);
		Break.setBounds(245,380,240,70);
		
		jfrm.add(welcome);
		jfrm.add(Receive);
		jfrm.add(SaveMoney);
		jfrm.add(Check);
		jfrm.add(Break);
		jfrm.setVisible(true);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //X�i�H��
	}
}
