/*	Class: Bank
 * 	Fuction: 提供帳戶資料庫查詢(資訊保持者)
 */

import java.util.ArrayList;

public class Bank {
	private ArrayList<Account> accountArr = new ArrayList<Account>();
	public Bank(){}
	private boolean accIsExist(String aID){					//帳號使否存在
		for(Account i:accountArr){
			if(i.getAccID().equals(aID))
				return true;
		}
		return false;
	}
	private int findAccount(String aID, String aPIN){		//尋找帳號位置
		int arrNum = 0;										//帳號於陣列位置
		for(Account i:accountArr){
			if(i.getAccID().equals(aID) && i.getAccPIN().equals(aPIN))
				return arrNum;
			arrNum++;
		}
		return -1;
	}
	public boolean validate(String aID, String aPIN){		//帳號驗證
		return findAccount(aID, aPIN)>=0;
	}
	private Account getAccount(String aID, String aPIN){	//取得帳號
		return accountArr.get(findAccount(aID, aPIN));
	}
	public String getAccName(String aID, String aPIN){		//取得帳號用戶名稱
		return (validate(aID, aPIN)) ? accountArr.get(findAccount(aID, aPIN)).getName() : null;
	}
	public void addAccount(Account account){				//新增帳號
		accountArr.add(account);
	}
	public boolean pickUpMoney(String aID, String aPIN, double money){		//領錢
		if(validate(aID, aPIN)){
			getAccount(aID, aPIN).getBankAccount().setDeposit(
					getAccount(aID, aPIN).getBankAccount().getDeposit()-money);
			return true;
		}else{
			return false;
		}
	}
	public boolean saveMoney(String aID, String aPIN, double money){		//存錢
		if(validate(aID, aPIN)){
			getAccount(aID, aPIN).getBankAccount().setDeposit(
					getAccount(aID, aPIN).getBankAccount().getDeposit()+money);
			return true;
		}else{
			return false;
		}
	}
	public boolean moneyTrf(String aID, String aPIN, double money, String trfInID){		//匯款
		int arrNum = 0;
		if(validate(aID, aPIN) && accIsExist(trfInID)){
			getAccount(aID, aPIN).getBankAccount().setDeposit(
					getAccount(aID, aPIN).getBankAccount().getDeposit()-money);
			for(Account i:accountArr){
				if(i.getAccID().equals(aID)) break;
				arrNum++;
			}
			accountArr.get(arrNum).getBankAccount().setDeposit(
					accountArr.get(arrNum).getBankAccount().getDeposit()+money);
			return true;
		}else{
			return false;
		}
	}
}
