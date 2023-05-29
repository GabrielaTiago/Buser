package application;

public class User {
	private String name;
	private String phoneNumber;
	private String email;
	private String address;
	
	public User(String name, String phone, String email, String address) {
		this.setName(name);
		this.setPhoneNumber(phone);
		this.setEmail(email);
		this.setAdress(address);
	}
	
	public String toString() {
		String saida;
		
		saida = "Endereço: " + this.getAdress() 	 + "\n";
		saida = "email:    " + this.getEmail() 		 + "\n" + saida;
		saida = "Telefone: " + this.getPhoneNumber() + "\n" + saida;
		saida = "Nome: 	   " + this.getName() 		 + "\n" + saida;

		return saida;
	}

	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAdress() {
		return address;
	}
	
	public void setAdress(String address) {
		this.address = address;
	}	
}
