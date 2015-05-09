/*	Class: Bank
 * 	Fuction: ���ѱb���Ʈw�d��(��T�O����)
 */

import java.util.ArrayList;

public class Bank {
	private ArrayList<Account> accountArr = new ArrayList<Account>();
	public Bank(){}
	public int findAccID(String aID){						//�M��b����m(ID�j�M)
		int arrNum = 0;										//�b����}�C��m
		for(Account i:accountArr){
			if(i.getAccID().equals(aID)) return arrNum;
			arrNum++;
		}
		return -1;
	}
	public int findAccPIN(String aPIN){						//�M��b����m(PIN�j�M)
		int arrNum = 0;										//�b����}�C��m
		for(Account i:accountArr){
			if(i.getAccPIN().equals(aPIN)) return arrNum;
			arrNum++;
		}
		return -1;
	}
	public int findAccount(String aID, String aPIN){		//�M��b����m
		int arrNum = 0;										//�b����}�C��m
		for(Account i:accountArr){
			if(i.getAccID().equals(aID) && i.getAccPIN().equals(aPIN))
				return arrNum;
			arrNum++;
		}
		return -1;
	}
	public Account getAccount(String aID, String aPIN){		//���o�b��
		return accountArr.get(findAccount(aID, aPIN));
	}
	public String getAccName(String aID, String aPIN){		//���o�b���Τ�W��
		return accountArr.get(findAccount(aID, aPIN)).getName();
	}
	public void addAccount(Account account){				//�s�W�b��
		accountArr.add(account);
	}
}
