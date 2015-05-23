/*	Class: Bank
 * 	Fuction: 提供帳戶資料庫查詢(資訊保持者)
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
			properties.load(new FileInputStream(configFile));	//讀取設定檔
		} catch (FileNotFoundException ex) {					//找不到檔案
			ex.printStackTrace();
			return;
		} catch (IOException ex) {
			ex.printStackTrace();
			return;
		} catch (Exception ex) {
			ex.printStackTrace();
			return;
		}
		final String host = properties.getProperty("host", "jdbc:mysql://localhost/default");	//(需要的欄位,預設值)
		final String username = properties.getProperty("username");								//(需要的欄位)
		final String password = properties.getProperty("password", "");							//(需要的欄位,預設值)
		if(username == null || username.isEmpty()){			//user 未設定
			System.out.println("user passwd not set");
		}
		if(password.isEmpty()){								//password 未設定, 給預設值空字串
			System.out.println("database passwd not set");
		}
		dataBaseConnect(host,username,password);
	}
	private void dataBaseConnect(String host, String user, String passwd){		//資料庫
		Connection dbConn = null;							//資料庫連結
		try {
			Class.forName(DBDRIVER);						//載入驅動程式
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try{
			dbConn = DriverManager.getConnection(host,user,passwd);		//連結資料庫(URL,user,passwd)
//			Statement stm = dbConn.creatStatement();
		}catch (SQLException ex){
			ex.printStackTrace();
		}
		System.out.println(dbConn);
		
		try {
			dbConn.close();									//段開資料庫
		}catch (SQLException ex){
			ex.printStackTrace();
		}
	}
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
