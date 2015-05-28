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
import java.util.Properties;

public class Bank {
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
/*	private ResultSet dataBaseSearch(String sql){		//資料庫查詢功能
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
				rs.previous();
//				return rs;
			}
			stmt.close();									//操作關閉
			dbConn.close();									//段開資料庫
			System.out.println("dbConn.close checkpoint");					//<<<<<checkpoint
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("return rs checkpoint");							//<<<<<checkpoint
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
*/

	public boolean validate(String aID, String aPIN){		//帳號驗證
		Connection dbConn = null;							//資料庫連結
		Statement stmt = null;								//資料庫操作
		ResultSet rs = null;
		String sql = 
			("SELECT * FROM tAccount WHERE AccID = '" +aID+ "' AND PIN = '"+ aPIN +"'");
			//SELECT * FROM tAccount WHERE AccID = "A10546" AND PIN = "458712";
		boolean loginFlag = false;
		System.out.println(sql);
		try {
			Class.forName(DBDRIVER);						//載入驅動程式
			dbConn = DriverManager.getConnection(host,username,password);		//連結資料庫(URL,user,passwd)
			System.out.println(dbConn);						//<<<Check Point
			stmt = dbConn.createStatement();				//建例實體Statement物件
			rs = stmt.executeQuery(sql);					//執行SQL操作
			rs.previous();
			int count = 0;
			while(rs.next()) count++;
			loginFlag = (count == 1) ? true:false;
			rs.close();
			stmt.close();									//操作關閉
			dbConn.close();									//段開資料庫
			System.out.println("dbConn.close val checkpoint");					//<<<<<checkpoint
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loginFlag;									//回傳查詢資料
	}

	public String getAccName(String aID, String aPIN){		//取得帳號用戶名稱
		Connection dbConn = null;							//資料庫連結
		Statement stmt = null;								//資料庫操作
		ResultSet rs = null;
		String name;
		String sql = 
			("SELECT Name FROM tCustomer JOIN tAccount "
			+ "ON tAccount.CustomerID = tCustomer.CustomerID "
			+ "WHERE AccID = \"" +aID+ "\"");
		try {
			Class.forName(DBDRIVER);						//載入驅動程式
			dbConn = DriverManager.getConnection(host,username,password);		//連結資料庫(URL,user,passwd)
			System.out.println(dbConn);						//<<<Check Point
			stmt = dbConn.createStatement();				//建例實體Statement物件
			rs = stmt.executeQuery(sql);					//執行SQL操作
			rs.next();										//rs指標指向第一個(不然預設是指向null)
			name = rs.getString("Name");
			stmt.close();									//操作關閉
			dbConn.close();									//段開資料庫
			System.out.println("dbConn.close val checkpoint");					//<<<<<checkpoint
			return name;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String[] checkMoney(String aID, String aPIN){					//查看餘額
		String[] data = new String[2];
		Connection dbConn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = 
			("SELECT tBankAccount.BankAccID,Balance FROM tBankAccount JOIN tAccount "
			+ "ON tBankAccount.BankAccID = tAccount.BankAccID "
			+ "WHERE AccID = '" +aID+ "' AND PIN = '"+ aPIN +"'");
		System.out.println(sql);
		try{
			Class.forName(DBDRIVER);
			dbConn = DriverManager.getConnection(host,username,password);
			System.out.println(dbConn);						//<<<Check Point
			stmt = dbConn.createStatement();				//建例實體Statement物件
			rs = stmt.executeQuery(sql);					//執行SQL操作
			rs.next();										//rs指標指向第一個(不然預設是指向null)
			data[0] = rs.getString("BankAccID");
			data[1] = rs.getString("Balance");
			rs.close();
			stmt.close();									//操作關閉
			dbConn.close();									//段開資料庫
			System.out.println("dbConn.close val checkpoint");					//<<<<<checkpoint
		}catch (ClassNotFoundException e){
			e.printStackTrace();
			data[0] = "Error";
			data[1] = "Error";
		}catch (SQLException e) {
			e.printStackTrace();
			data[0] = "Error";
			data[1] = "Error";
		}
		return data;
	}
	public boolean pickUpMoney(String aID, String aPIN, double money){		//領錢
		Connection dbConn = null;							//資料庫連結
		Statement stmt = null;								//資料庫操作
		String sql = 
			("UPDATE tBankAccount"
			+" SET Balance = Balance - " + money
			+" WHERE BankAccID = ( SELECT * FROM tAccount WHERE AccID = \"" +aID+ "\" AND PIN = \""+ aPIN +"\"");
		try {
			Class.forName(DBDRIVER);						//載入驅動程式
			dbConn = DriverManager.getConnection(host,username,password);		//連結資料庫(URL,user,passwd)
			System.out.println(dbConn);						//<<<Check Point
			stmt = dbConn.createStatement();				//建例實體Statement物件
			stmt.executeUpdate(sql);						//執行SQL更新操作
			stmt.close();									//操作關閉
			System.out.println("stmt.close val checkpoint");					//<<<<<checkpoint
			dbConn.close();									//段開資料庫
			System.out.println("dbConn.close val checkpoint");					//<<<<<checkpoint
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean saveMoney(String aID, String aPIN, double money){		//存錢
		return this.pickUpMoney(aID, aPIN, -money);
	}
/*
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
