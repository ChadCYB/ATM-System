/*	Class: Bank
 * 	Fuction: ���ѱb���Ʈw�d��(��T�O����)
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
//import com.mysql.jdbc.Connection;

public class Bank {
	private ArrayList<Account> accountArr = new ArrayList<Account>();
	private Properties properties = new Properties();
	private static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
	private String host, username, password;
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
		host = properties.getProperty("host", "jdbc:mysql://localhost/default");	//(�ݭn�����,�w�]��)
		username = properties.getProperty("username");								//(�ݭn�����)
		password = properties.getProperty("password", "");							//(�ݭn�����,�w�]��)
		if(username == null || username.isEmpty()){			//user ���]�w
			System.out.println("user passwd not set");
		}
		if(password.isEmpty()){								//password ���]�w, ���w�]�ȪŦr��
			System.out.println("database passwd not set");
		}
//		dataBaseSearch(host,username,password,"");
	}
	private ResultSet dataBaseSearch(String sql){		//��Ʈw�d�ߥ\��
		Connection dbConn = null;							//��Ʈw�s��
		Statement stmt = null;								//��Ʈw�ާ@
		ResultSet rs = null;
		try {
			Class.forName(DBDRIVER);						//���J�X�ʵ{��
			dbConn = DriverManager.getConnection(host,username,password);		//�s����Ʈw(URL,user,passwd)
			System.out.println(dbConn);						//Check Point
			if(!sql.equals("")){
				stmt = dbConn.createStatement();			//�بҹ���Statement����
				rs = stmt.executeQuery(sql);				//����SQL�ާ@
				return rs;
			}
			stmt.close();								//�ާ@����
			dbConn.close();									//�q�}��Ʈw
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;											//�^�Ǭd�߸��
	}
	private boolean accIsExist(String aID){					//�b���ϧ_�s�b
		try {
			ResultSet rs1 = dataBaseSearch("SELECT AccID FROM tAccount WHERE AccID = "+aID);
			int count = 0;
			while(rs1.next()) count++;
			return (count == 1) ? true:false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
//	private int findAccount(String aID, String aPIN){		//�M��b����m
//		int arrNum = 0;										//�b����}�C��m
//		for(Account i:accountArr){
//			if(i.getAccID().equals(aID) && i.getAccPIN().equals(aPIN))
//				return arrNum;
//			arrNum++;
//		}
//		return -1;
//	}
	public boolean validate(String aID, String aPIN){		//�b������
		try {
				//SELECT * FROM tAccount WHERE AccID = "A10546" AND PIN = "458712";
			ResultSet rs1 = dataBaseSearch(
				"SELECT * FROM tAccount WHERE AccID = \"" +aID+ "\" AND PIN = \""+ aPIN +"\"");
			System.out.println("result set rows: "+rs1.getRow());	//<<<check point
			int count = 0;
			while(rs1.next()) count++;
			return (count == 1) ? true:false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
//	private Account getAccount(String aID, String aPIN){	//���o�b��
//		return accountArr.get(findAccount(aID, aPIN));
//	}
	public String getAccName(String aID, String aPIN){		//���o�b���Τ�W��
		try {
				//SELECT Name FROM tCustomer,tAccount WHERE AccID = "A10546" AND tAccount.CustomerID = tCustomer.CustomerID;
			ResultSet rs1 = dataBaseSearch(
				"SELECT Name FROM tCustomer,tAccount WHERE AccID = \"" +aID+ "\" AND tAccount.CustomerID = tCustomer.CustomerID");
			System.out.println("Get Name CheckPoint");				//<<<check point
			return rs1.getString("Name");		//BUG<<<<!!!!!!!!!
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public void addAccount(Account account){				//�s�W�b��
		accountArr.add(account);
	}
/*
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
*/
}
