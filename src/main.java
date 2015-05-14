
public class main {

	public static void main(String[] args) {
		Bank aBank = new Bank();
		LogIn login = new LogIn(aBank);
		aBank.addAccount(new Account("¥ü³Õ","A10546","b00001","458712",
				new BankAccount("123456",15000,5000)));
		aBank.addAccount(new Account("¤p¤E","A12608","b00002","453354",
				new BankAccount("231897",13534,1200)));
		LogInFrame loginFrame = new LogInFrame(login);
		loginFrame.setVisible(true);
//		do{
//			System.out.print("");
//		}while(!loginFrame.status());
//		System.out.println("login success");
//		MainFrame1 mFrame1 = new MainFrame1(aBank);
		
	}

}
