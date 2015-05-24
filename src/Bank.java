/*	Class: Bank
 * 	Fuction: 提供帳戶資料庫查詢(資訊保持者)
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
		host = properties.getProperty("host", "jdbc:mysql://localhost/default");	//(需要的欄位,預設值)
		username = properties.getProperty("username");								//(需要的欄位)
		password = properties.getProperty("password", "");							//(需要的欄位,預設值)
		if(username == null || username.isEmpty()){			//user 未設定
			System.out.println("user passwd not set");
		}
		if(password.isEmpty()){								//password 未設定, 給預設值空字串
			System.out.println("database passwd not set");
		}
//		dataBaseSearch(host,username,password,"");
	}
	private ResultSet dataBaseSearch(String sql){		//資料庫查詢功能
		Connection dbConn = null;							//資料庫連結
		Statement stmt = null;								//資料庫操作
		ResultSet rs = null;
		try {
			Class.forName(DBDRIVER);						//載入驅動程式
			dbConn = DriverManager.getConnection(host,username,password);		//連結資料庫(URL,user,passwd)
			System.out.println(dbConn);						//Check Point
			if(!sql.equals("")){
				stmt = dbConn.createStatement();			//建例實體Statement物件
				rs = stmt.executeQuery(sql);				//執行SQL操作
				return rs;
			}
			stmt.close();								//操作關閉
			dbConn.close();									//段開資料庫
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;											//回傳查詢資料
	}
	private boolean accIsExist(String aID){					//帳號使否存在
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
//	private int findAccount(String aID, String aPIN){		//尋找帳號位置
//		int arrNum = 0;										//帳號於陣列位置
//		for(Account i:accountArr){
//			if(i.getAccID().equals(aID) && i.getAccPIN().equals(aPIN))
//				return arrNum;
//			arrNum++;
//		}
//		return -1;
//	}
	public boolean validate(String aID, String aPIN){		//帳號驗證
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
//	private Account getAccount(String aID, String aPIN){	//取得帳號
//		return accountArr.get(findAccount(aID, aPIN));
//	}
	public String getAccName(String aID, String aPIN){		//取得帳號用戶名稱
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
	public void addAccount(Account account){				//新增帳號
		accountArr.add(account);
	}
/*
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
*/
}
