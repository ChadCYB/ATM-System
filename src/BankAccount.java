
public class BankAccount{
	private String bankID;				//��s��
	private double Balance,Deposit;		//�b��l�B,�b��w�s
	
	public BankAccount(){}
//	public BankAccount(String bankid,double balance){
//		bankID = bankid;
//		Balance = balance;
//	}
	public BankAccount(String bankid,double balance,double deposit){
		bankID = bankid;
		Balance = balance;
		Deposit = deposit;
	}
	
	public void setbankID(String bankid){bankID = bankid;}
	public void setBalance(double balance){Balance = balance;}
	public void setDeposit(double df){Deposit = df;}
	public String getbankID(){return bankID;}
	public double getBalance(){return Balance;}
	public double getDeposit(){return Deposit;}
	
//	public void show() {}
}
