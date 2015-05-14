
public abstract class Customer {
	protected String name, id, address, tel;		//姓名,身分證字號,地址,電話
	
	public void setName(String sName){name = sName;}
	public void setID(String sID){id = sID;}
	public void setAddress(String sAdd){address = sAdd;}
	public void setTel(String sTel){tel = sTel;}
	
	public String getName(){return name;}
	public String getID(){return id;}
	public String getAddress(){return address;}
	public String getTel(){return tel;}
}
