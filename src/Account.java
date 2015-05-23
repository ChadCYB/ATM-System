
public class Account extends Customer{
	private BankAccount bankAcc = new BankAccount();	//ª»¶Ê§·¿Y
	private String AccID,AccPIN;						//±b§·ID,±b§·PIN

	public Account(){}
	public Account(String sName, String sID, String sAccID, String sAccPIN, BankAccount bankA){
		name = sName;
		id = sID;
		AccID= sAccID;
		AccPIN = sAccPIN;
	}
	
	public void setAccID(String sAccID){AccID = sAccID;}
	public void setAccPIN(String sAccPIN){AccPIN = sAccPIN;}
	public String getAccID(){return AccID;}
	public String getAccPIN(){return AccPIN;}
	
	public void setBankAccount(String bankid,double balance,double deposit){
		bankAcc = new BankAccount(bankid, balance, deposit);
	}
	public void setBankAccount(BankAccount bankacc){
		bankAcc = bankacc;
	}
	public BankAccount getBankAccount(){
		return bankAcc;
	}
}
