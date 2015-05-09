/*	Class: Bank
 * 	Fuction: 提供帳戶資料庫查詢(資訊保持者)
 */

import java.util.ArrayList;

public class Bank {
	private ArrayList<Account> accountArr = new ArrayList<Account>();
	public Bank(){}
	public int findAccID(String aID){						//尋找帳號位置(ID搜尋)
		int arrNum = 0;										//帳號於陣列位置
		for(Account i:accountArr){
			if(i.getAccID().equals(aID)) return arrNum;
			arrNum++;
		}
		return -1;
	}
	public int findAccPIN(String aPIN){						//尋找帳號位置(PIN搜尋)
		int arrNum = 0;										//帳號於陣列位置
		for(Account i:accountArr){
			if(i.getAccPIN().equals(aPIN)) return arrNum;
			arrNum++;
		}
		return -1;
	}
	public int findAccount(String aID, String aPIN){		//尋找帳號位置
		int arrNum = 0;										//帳號於陣列位置
		for(Account i:accountArr){
			if(i.getAccID().equals(aID) && i.getAccPIN().equals(aPIN))
				return arrNum;
			arrNum++;
		}
		return -1;
	}
	public Account getAccount(String aID, String aPIN){		//取得帳號
		return accountArr.get(findAccount(aID, aPIN));
	}
	public String getAccName(String aID, String aPIN){		//取得帳號用戶名稱
		return accountArr.get(findAccount(aID, aPIN)).getName();
	}
	public void addAccount(Account account){				//新增帳號
		accountArr.add(account);
	}
}
