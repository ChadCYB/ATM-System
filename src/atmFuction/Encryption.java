package atmFuction;
/*	Class: Encryption
 * 	Fuction: 加密解密服務
 */

public class Encryption {
	public Encryption(){ }
	public static String TransactSQLInjection(String sql) {  
		return sql.replaceAll(".*([';]+|(--)+).*", " ");  
	}
}
