package application;

public class User {
	//atributes
	private String name;
	private String document;
	private String contactNumber;
	private String email;
	private String adress;
	
	//constructors
	public User(String n, String c, String tel, String email,String e) {
		this.name = n;
		this.document = c;
		this.contactNumber = tel;
		this.email = email;
		this.adress = e;
	}
	
	//getters and setters
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDocument() {
		return this.document;
	}
	
	public void setDocument(String c) {
		this.document = c;
	}
	
	public String getContactNumber() {
		return contactNumber;
	}
	
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAdress() {
		return adress;
	}
	
	public void setAdress(String adress) {
		this.adress = adress;
	}	
}
