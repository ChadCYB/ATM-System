package atmFuction;
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
	}

	private ResultSet dbSearch(String sql){			//��Ʈw�d�ߥ\��
		Connection dbConn = null;							//��Ʈw�s��
		Statement stmt = null;								//��Ʈw�ާ@
		ResultSet rs1 = null;
		CachedRowSetImpl crs = null;						//ResultSet��Cached
		System.out.println(sql);											//<<<<<checkpoint
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
		System.out.println(sql);											//<<<<<checkpoint
		try {
			Class.forName(DBDRIVER);						//���J�X�ʵ{��
			dbConn = DriverManager.getConnection(host,username,password);		//�s����Ʈw(URL,user,passwd)
			System.out.println(dbConn);						//<<<Check Point
			stmt = dbConn.createStatement();				//�بҹ���Statement����
			stmt.executeUpdate(sql);						//����SQL��s�ާ@
			stmt.close();									//�ާ@����
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
			ResultSet rs1 = dbSearch("SELECT AccID FROM tAccount WHERE BankAccID = "+aID);
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
		double bankBalance = Double.parseDouble(checkMoney(aID,aPIN)[1]);
		if(bankBalance >= money){											//�P�_�O�_�����
			String sql = 
				("UPDATE tBankAccount"
				+" SET Balance = Balance - " + money
				+" WHERE BankAccID = ( SELECT BankAccID FROM tAccount WHERE AccID = '" +aID+ "' AND PIN = '"+ aPIN +"')");
			flag = this.dbUpdate(sql);
		}
		return flag;
	}
	public boolean saveMoney(String aID, String aPIN, double money){		//�s��
		return this.pickUpMoney(aID, aPIN, -money);
	}
	public boolean moneyTrf(String aID, String aPIN, double money, String trfInID){		//�״�
		boolean flag1 = false;
		flag1 = this.pickUpMoney(aID, aPIN, money);			//��X�n�״ڪ��B
		if(accIsExist(trfInID) && flag1){
			String sql = 
					("UPDATE tBankAccount"
					+" SET Balance = Balance + " + money
					+" WHERE BankAccID = '" +trfInID+ "'");
			flag1 = this.dbUpdate(sql);
		}else if(!flag1){									//������~
			System.out.println("<pickUp Failed>");
		}else{												//�״ڿ��~
			System.out.println("<trfInID NotExist>");
			saveMoney(aID,aPIN,money); 						//�״ڥ��ѡA����s�^��
			flag1 = false;
		}
		return flag1;
	}
	
//	private void atmLogger(){
//		
//	}
}
