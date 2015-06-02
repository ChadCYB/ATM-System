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
import java.util.Properties;
import com.sun.rowset.CachedRowSetImpl;

public class Bank {
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

	private ResultSet dbSearch(String sql){			//��Ʈw�d�ߥ\��
		Connection dbConn = null;							//��Ʈw�s��
		Statement stmt = null;								//��Ʈw�ާ@
		ResultSet rs1 = null;
		CachedRowSetImpl crs = null;						//ResultSet��Cached
		try {
			Class.forName(DBDRIVER);						//���J�X�ʵ{��
			dbConn = DriverManager.getConnection(host,username,password);		//�s����Ʈw(URL,user,passwd)
			System.out.println(dbConn);						//Check Point
			if(!sql.equals("")){
				stmt = dbConn.createStatement();			//�بҹ���Statement����
				rs1 = stmt.executeQuery(sql);				//����SQL�ާ@
				crs = new CachedRowSetImpl();
				crs.populate(rs1);							//�ƻsResultSet
			}
			rs1.close();
			stmt.close();									//�ާ@����
			dbConn.close();									//�q�}��Ʈw
			System.out.println("<dbConn.close>");							//<<<<<checkpoint
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("<return rs checkpoint>");						//<<<<checkpoint
		return crs;											//�^�Ǭd�߸��
	}
	private boolean dbUpdate(String sql){			//��Ʈw��s�\��
		Connection dbConn = null;							//��Ʈw�s��
		Statement stmt = null;								//��Ʈw�ާ@
		try {
			Class.forName(DBDRIVER);						//���J�X�ʵ{��
			dbConn = DriverManager.getConnection(host,username,password);		//�s����Ʈw(URL,user,passwd)
			System.out.println(dbConn);						//<<<Check Point
			stmt = dbConn.createStatement();				//�بҹ���Statement����
			stmt.executeUpdate(sql);						//����SQL��s�ާ@
			stmt.close();									//�ާ@����
			System.out.println("stmt.close val checkpoint");				//<<<<checkpoint
			dbConn.close();									//�q�}��Ʈw
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
	
	private boolean accIsExist(String aID){					//�b���ϧ_�s�b
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

	public boolean validate(String aID, String aPIN){		//�b������
		ResultSet rs = null;
		String sql = 
			("SELECT * FROM tAccount WHERE AccID = '" +aID+ "' AND PIN = '"+ aPIN +"'");
			//SELECT * FROM tAccount WHERE AccID = "A10546" AND PIN = "458712";
		boolean loginFlag = false;
		System.out.println(sql);
		try {
			rs = this.dbSearch(sql);
			System.out.println("<SQL>-getRS-");				//<<<<<checkpoint
			int count = 0;
			while(rs.next()) count++;
			loginFlag = (count == 1) ? true:false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loginFlag;									//�^�Ǭd�߸��
	}

	public String getAccName(String aID, String aPIN){		//���o�b���Τ�W��
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

	public String[] checkMoney(String aID, String aPIN){	//�d�ݾl�B
		String[] data = new String[2];
		ResultSet rs = null;
		String sql = 
			("SELECT tBankAccount.BankAccID,Balance FROM tBankAccount JOIN tAccount "
			+ "ON tBankAccount.BankAccID = tAccount.BankAccID "
			+ "WHERE AccID = '" +aID+ "' AND PIN = '"+ aPIN +"'");
		System.out.println(sql);
		try{
			rs = this.dbSearch(sql);
			rs.next();										//rs���Ы��V�Ĥ@��(���M�w�]�O���Vnull)
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
	public boolean pickUpMoney(String aID, String aPIN, double money){		//���
		boolean flag = false;
		String sql = 
			("UPDATE tBankAccount"
			+" SET Balance = Balance - " + money
			+" WHERE BankAccID = ( SELECT * FROM tAccount WHERE AccID = \"" +aID+ "\" AND PIN = \""+ aPIN +"\"");
		flag = this.dbUpdate(sql);
		return flag;
	}
	public boolean saveMoney(String aID, String aPIN, double money){		//�s��
		return this.pickUpMoney(aID, aPIN, -money);
	}
/*	public boolean pickUpMoney(String aID, String aPIN, double money){		//���(original)
		Connection dbConn = null;							//��Ʈw�s��
		Statement stmt = null;								//��Ʈw�ާ@
		String sql = 
			("UPDATE tBankAccount"
			+" SET Balance = Balance - " + money
			+" WHERE BankAccID = ( SELECT * FROM tAccount WHERE AccID = \"" +aID+ "\" AND PIN = \""+ aPIN +"\"");
		try {
			Class.forName(DBDRIVER);						//���J�X�ʵ{��
			dbConn = DriverManager.getConnection(host,username,password);		//�s����Ʈw(URL,user,passwd)
			System.out.println(dbConn);						//<<<Check Point
			stmt = dbConn.createStatement();				//�بҹ���Statement����
			stmt.executeUpdate(sql);						//����SQL��s�ާ@
			stmt.close();									//�ާ@����
			System.out.println("stmt.close val checkpoint");					//<<<<<checkpoint
			dbConn.close();									//�q�}��Ʈw
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

/*
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
