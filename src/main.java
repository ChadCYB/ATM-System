/*	Class: CheckMoneyFrame
 * 	Fuction: 主程式(程式進度點)
 */

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class main {
	public static void main(String[] args) {
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
		Bank aBank = new Bank();
		LogIn login = new LogIn(aBank);
		ATM atm1 = new ATM(aBank);
		aBank.addAccount(new Account("兆博","A10546","b00001","458712",
				new BankAccount("123456",15000,5000)));
		aBank.addAccount(new Account("小九","A12608","b00002","453354",
				new BankAccount("231897",13534,1200)));
		LogInFrame loginFrame = new LogInFrame(login, atm1);
	}
}
