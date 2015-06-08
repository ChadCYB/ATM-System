import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class PickUpFrame extends JFrame {
	private JPanel contentPane = new JPanel();
	private JLabel Receive = new JLabel("領錢");
	private JPanel gridpanel = new JPanel();
	private JButton btn1 = new JButton("$1000");
	private JButton btn2 = new JButton("$2000");
	private JButton btn3 = new JButton("$3000");
	private JButton btn5 = new JButton("$5000");
	private JButton btn10 = new JButton("$10,000");
	private JButton btntext = new JButton("自行輸入(只提供千元)");
	private int Height = 450, Width = 500;
	private boolean status = false;
	
	public PickUpFrame(ATM atm) {
		initComp(atm);
	}

	private void initComp(ATM atm) {
		setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);	//僅關閉此視窗
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((int)((screenSize.getWidth()-Width)*0.5), (int)((screenSize.getHeight()-Height)*0.5), Width, Height);

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 2, 5));
		gridpanel.setLayout(new GridLayout(0, 2, 5, 5));
		contentPane.setBackground(new Color(180, 240, 245));
		gridpanel.setBackground(Color.orange);
		
		Receive.setFont(new Font("新細明體", Font.BOLD, 40));
		Receive.setHorizontalAlignment(SwingConstants.CENTER);
		
		contentPane.add(Receive);
		contentPane.add(gridpanel);
		btn1.setFont(new Font("Serief",Font.PLAIN,30));
		btn2.setFont(new Font("Serief",Font.PLAIN,30));
		btn3.setFont(new Font("Serief",Font.PLAIN,30));
		btn5.setFont(new Font("Serief",Font.PLAIN,30));
		btn10.setFont(new Font("Serief",Font.PLAIN,30));
		btntext.setFont(new Font("Serief",Font.PLAIN,20));
		gridpanel.add(btn1);
		gridpanel.add(btn5);
		gridpanel.add(btn2);
		gridpanel.add(btn10);
		gridpanel.add(btn3);
		gridpanel.add(btntext);
		
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atmPickUp(1000,atm);
			}
		});
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atmPickUp(2000,atm);
			}
		});
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atmPickUp(3000,atm);
			}
		});
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atmPickUp(5000,atm);
			}
		});
		btn10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atmPickUp(10000,atm);
			}
		});
		btntext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str= JOptionPane.showInputDialog("請輸入現金(以千為單位)");
				try{
					double money = Double.parseDouble(str);     				//轉換成整數
					if(money<=0) throw new NumberFormatException();				//沒有輸入東西
					if(!atm.pickUpMoney(money*1000)) throw new Exception();		//餘額不足
					closeFrame("交易成功，請提取現金  !");
				}catch (NullPointerException ex){
					closeFrame("未輸入資料，交易已取消 !");
				}catch (NumberFormatException ex){
					closeFrame("發生錯誤，交易已取消 !");
				}catch (Exception ex){
					System.out.println(ex);
					closeFrame("交易失敗，您的帳戶餘額不足  !");
				}
			}
		});
	}
	private void atmPickUp(double money, ATM atm){
		if(atm.pickUpMoney(money)){
			closeFrame("交易成功，請提取現金  !");
		}else{
			closeFrame("交易失敗，您的帳戶餘額不足  !");
		}
	}
	private void closeFrame(String message){		//關閉視窗
		JOptionPane.showMessageDialog(null,message);
		int n = JOptionPane.showConfirmDialog(null,
				"您是否繼續交易?","操作問題", JOptionPane.YES_NO_OPTION);
		status = (n != JOptionPane.YES_OPTION) ? true:false;
		dispose();
	}
	public boolean status() {
		return status;
	}
}
