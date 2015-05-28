
public class ATM {
	private Bank theBank;
//	private Account currentAcc;
	private String aID, aPIN;
	public ATM(Bank bank){
		theBank = bank;
	}
	public boolean pickUpMoney(double money){	//領錢
		return theBank.pickUpMoney(aID, aPIN, money);
	}
	public boolean saveMoney(double money){		//存錢
		return theBank.saveMoney(aID, aPIN, money);
	}
//	public boolean fdMoney(){					//定存
//		return false;	//<<<<NOT YET
//	}
//	public boolean moneyTransfer(double money, String TrfInID){		//匯款
//		return theBank.moneyTrf(aID, aPIN, money, TrfInID);
//	}
	public void setAccUser(String sAcc, String sPass) {
		aID = sAcc;
		aPIN = sPass;
	}
	public String[] checkMoney(){
		return theBank.checkMoney(aID, aPIN);
	}
}
