/*	Class: LogIn
 * 	Fuction: �B�zLogInFrame�����ɭ��MBank�����q(������)
 */

public class LogIn {
	private String aID, aPIN;
	private Bank theBank;
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
		return theBank.validate(aID, aPIN);
	}
	public String getUserName(){
		return theBank.getAccName(aID, aPIN);
	}
}
