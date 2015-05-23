/*	Class: Bank
 * 	Fuction: ���ѱb���Ʈw�d��(��T�O����)
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.sql.Connection;
//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;

public class Bank {
	private ArrayList<Account> accountArr = new ArrayList<Account>();
	private Properties properties = new Properties();
	private static final String DBDRIVER = "org.gjt.mm.mysql.Driver";

	public Bank(){
		String configFile = "config.properties";
		try {
			properties.load(new FileInputStream(configFile));	//Ū���]�w��
		} catch (FileNotFoundException ex) {					//�䤣���ɮ�
			ex.printStackTrace();
			return;
		} catch (IOException ex) {
			ex.printStackTrace();
			return;
		} catch (Exception ex) {
			ex.printStackTrace();
			return;
		}
		final String host = properties.getProperty("host", "jdbc:mysql://localhost/default");	//(�ݭn�����,�w�]��)
		final String username = properties.getProperty("username");								//(�ݭn�����)
		final String password = properties.getProperty("password", "");							//(�ݭn�����,�w�]��)
		if(username == null || username.isEmpty()){			//user ���]�w
			System.out.println("user passwd not set");
		}
		if(password.isEmpty()){								//password ���]�w, ���w�]�ȪŦr��
			System.out.println("database passwd not set");
		}
		dataBaseConnect(host,username,password);
	}
	private void dataBaseConnect(String host, String user, String passwd){		//��Ʈw
		Connection dbConn = null;							//��Ʈw�s��
		try {
			Class.forName(DBDRIVER);						//���J�X�ʵ{��
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try{
			dbConn = DriverManager.getConnection(host,user,passwd);		//�s����Ʈw(URL,user,passwd)
//			Statement stm = dbConn.creatStatement();
		}catch (SQLException ex){
			ex.printStackTrace();
		}
		System.out.println(dbConn);
		
		try {
			dbConn.close();									//�q�}��Ʈw
		}catch (SQLException ex){
			ex.printStackTrace();
		}
	}
	private boolean accIsExist(String aID){					//�b���ϧ_�s�b
		for(Account i:accountArr){
			if(i.getAccID().equals(aID))
				return true;
		}
		return false;
	}
	private int findAccount(String aID, String aPIN){		//�M��b����m
		int arrNum = 0;										//�b����}�C��m
		for(Account i:accountArr){
			if(i.getAccID().equals(aID) && i.getAccPIN().equals(aPIN))
				return arrNum;
			arrNum++;
		}
		return -1;
	}
	public boolean validate(String aID, String aPIN){		//�b������
		return findAccount(aID, aPIN)>=0;
	}
	private Account getAccount(String aID, String aPIN){	//���o�b��
		return accountArr.get(findAccount(aID, aPIN));
	}
	public String getAccName(String aID, String aPIN){		//���o�b���Τ�W��
		return (validate(aID, aPIN)) ? accountArr.get(findAccount(aID, aPIN)).getName() : null;
	}
	public void addAccount(Account account){				//�s�W�b��
		accountArr.add(account);
	}
	public boolean pickUpMoney(String aID, String aPIN, double money){		//���
		if(validate(aID, aPIN)){
			getAccount(aID, aPIN).getBankAccount().setDeposit(
					getAccount(aID, aPIN).getBankAccount().getDeposit()-money);
			return true;
		}else{
			return false;
		}
	}
	public boolean saveMoney(String aID, String aPIN, double money){		//�s��
		if(validate(aID, aPIN)){
			getAccount(aID, aPIN).getBankAccount().setDeposit(
					getAccount(aID, aPIN).getBankAccount().getDeposit()+money);
			return true;
		}else{
			return false;
		}
	}
	public boolean moneyTrf(String aID, String aPIN, double money, String trfInID){		//�״�
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
