package atmFuction;
/*	Class: Encryption
 * 	Fuction: �[�K�ѱK�A��
 */

public class Encryption {
	public Encryption(){
		// <<<<<<<<<NOT YET
	}
	public static String TransactSQLInjection(String sql) {  
		return sql.replaceAll(".*([';]+|(--)+).*", " ");  
	}
}
