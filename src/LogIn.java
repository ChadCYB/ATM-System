/*	Class: LogIn
 * 	Fuction: 處理LogInFrame視窗界面和Bank的溝通(介面者)
 */

public class LogIn {
	private String aID, aPIN;
	Bank theBank;
	public LogIn(Bank bank){
		theBank = bank;
	}
	public void setAcc(String acc){
		aID = acc;
	}
	public void setPassWD(String pwd){
		aPIN = pwd;
	}
	public boolean findAccount(){
		return theBank.findAccID(aID)==theBank.findAccPIN(aPIN);
	}
	public String getUserName(){
		return theBank.getAccName(aID, aPIN);
	}
}
