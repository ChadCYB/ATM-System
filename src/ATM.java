
public class ATM {
	private Bank theBank;
	private Account currentAcc;
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
	public boolean fdMoney(){					//�w�s
		return false;
	}
	public boolean moneyTransfer(double money, String TrfInID){		//�״�
		return theBank.moneyTrf(aID, aPIN, money, TrfInID);
	}
	public void setAccUser(String sAcc, String sPass) {
		aID = sAcc;
		aPIN = sPass;
	}
}
