
public class ATM {
	private Bank theBank;
	private String aID, aPIN;
	public ATM(Bank bank){
		theBank = bank;
	}
	public boolean pickUpMoney(double money){	//���
		return theBank.pickUpMoney(aID, aPIN, money);
	}
	public boolean saveMoney(double money){		//�s��
		return theBank.saveMoney(aID, aPIN, money);
	}
//	public boolean fdMoney(){					//�w�s
//		return false;	//<<<<NOT YET
//	}
//	public boolean moneyTransfer(double money, String TrfInID){		//�״�
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
