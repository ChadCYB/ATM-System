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
import com.sun.rowset.CachedRowSetImpl;

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

	private ResultSet dbSearch(String sql){			//資料庫查詢功能
		Connection dbConn = null;							//資料庫連結
		Statement stmt = null;								//資料庫操作
		ResultSet rs1 = null;
		CachedRowSetImpl crs = null;						//ResultSet的Cached
		System.out.println(sql);											//<<<<<checkpoint
		try {
			Class.forName(DBDRIVER);						//載入驅動程式
			dbConn = DriverManager.getConnection(host,username,password);		//連結資料庫(URL,user,passwd)
			System.out.println(dbConn);						//Check Point
			if(!sql.equals("")){
				stmt = dbConn.createStatement();			//建例實體Statement物件
				rs1 = stmt.executeQuery(sql);				//執行SQL操作
				crs = new CachedRowSetImpl();
				crs.populate(rs1);							//複製ResultSet
			}
			rs1.close();
			stmt.close();									//操作關閉
			dbConn.close();									//段開資料庫
			System.out.println("<dbConn.close>");							//<<<<<checkpoint
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("<return rs checkpoint>");						//<<<<checkpoint
		return crs;											//回傳查詢資料
	}
	private boolean dbUpdate(String sql){			//資料庫更新功能
		Connection dbConn = null;							//資料庫連結
		Statement stmt = null;								//資料庫操作
		System.out.println(sql);											//<<<<<checkpoint
		try {
			Class.forName(DBDRIVER);						//載入驅動程式
			dbConn = DriverManager.getConnection(host,username,password);		//連結資料庫(URL,user,passwd)
			System.out.println(dbConn);						//<<<Check Point
			stmt = dbConn.createStatement();				//建例實體Statement物件
			stmt.executeUpdate(sql);						//執行SQL更新操作
			stmt.close();									//操作關閉
			dbConn.close();									//段開資料庫
			System.out.println("<dbConn.close>");							//<<<<<checkpoint
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean accIsExist(String aID){					//帳號使否存在
		try {
			ResultSet rs1 = dbSearch("SELECT AccID FROM tAccount WHERE AccID = "+aID);
			int count = 0;
			while(rs1.next()) count++;
			return (count == 1) ? true:false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean validate(String aID, String aPIN){		//帳號驗證
		ResultSet rs = null;
		String sql = 
			("SELECT * FROM tAccount WHERE AccID = '" +aID+ "' AND PIN = '"+ aPIN +"'");
			//SELECT * FROM tAccount WHERE AccID = "A10546" AND PIN = "458712";
		boolean loginFlag = false;
		try {
			rs = this.dbSearch(sql);
			System.out.println("<SQL>-getRS-");				//<<<<<checkpoint
			int count = 0;
			while(rs.next()) count++;
			loginFlag = (count == 1) ? true:false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loginFlag;									//回傳查詢資料
	}

	public String getAccName(String aID, String aPIN){		//取得帳號用戶名稱
		ResultSet rs = null;
		String name;
		String sql = 
			("SELECT Name FROM tCustomer JOIN tAccount "
			+ "ON tAccount.CustomerID = tCustomer.CustomerID "
			+ "WHERE AccID = \"" +aID+ "\"");
		try {
			rs = this.dbSearch(sql);
			rs.next();
			name = rs.getString("Name");
			System.out.println("<SQL>-getName-");			//<<<<<checkpoint
			return name;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String[] checkMoney(String aID, String aPIN){	//查看餘額
		String[] data = new String[2];
		ResultSet rs = null;
		String sql = 
			("SELECT tBankAccount.BankAccID,Balance FROM tBankAccount JOIN tAccount "
			+ "ON tBankAccount.BankAccID = tAccount.BankAccID "
			+ "WHERE AccID = '" +aID+ "' AND PIN = '"+ aPIN +"'");
		System.out.println(sql);
		try{
			rs = this.dbSearch(sql);
			rs.next();										//rs指標指向第一個(不然預設是指向null)
			data[0] = rs.getString("BankAccID");
			data[1] = rs.getString("Balance");
			rs.close();
			System.out.println("<dbConn.close>");			//<<<<<checkpoint
		}catch (SQLException e) {
			e.printStackTrace();
			data[0] = "Error";
			data[1] = "Error";
		}
		return data;
	}
	public boolean pickUpMoney(String aID, String aPIN, double money){		//領錢
		boolean flag = false;
		String sql = 
			(" UPDATE tBankAccount"
			+" SET Balance = Balance - " + money
			+" WHERE BankAccID = ( SELECT BankAccID FROM tAccount WHERE AccID = '" +aID+ "' AND PIN = '"+ aPIN +"')");
		flag = this.dbUpdate(sql);
		return flag;
	}
	public boolean saveMoney(String aID, String aPIN, double money){		//存錢
		return this.pickUpMoney(aID, aPIN, -money);
	}
	public boolean moneyTrf(String aID, String aPIN, double money, String trfInID){		//匯款
		boolean flag1 = false;
		flag1 = this.pickUpMoney(aID, aPIN, -money);
		if(accIsExist(trfInID) && flag1){
			String sql = 
					("UPDATE tBankAccount"
					+" SET Balance = Balance - " + money
					+" WHERE BankAccID = ( SELECT BankAccID FROM tAccount WHERE AccID = '" +aID+ "')");
			return this.dbUpdate(sql);
		}else if(!flag1){
			System.out.println("<pickUp Failed>");
			return false;
		}else{
			System.out.println("<trfInID NotExist>");
			return false;
		}
	}
/*	public boolean pickUpMoney(String aID, String aPIN, double money){		//領錢(original)
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
			System.out.println("<dbConn.close val>");							//<<<<<checkpoint
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
*/
}
