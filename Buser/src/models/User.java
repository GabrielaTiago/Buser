package models;
/**
 * Classe abstrata que representa um usuária que tem 2 casos de herança
 * , um para Cliente e outro para Compania, ambos herdam os atributos de 
 * Nome, telefone, email e endereço.
 * @author Gabriel
 *
 */
public abstract class User {
	private String name;
	private String phoneNumber;
	private String email;
	private String password;
	private String address;

	public User(String name, String email, String password, String phone, String address) {
		this.setName(name);
		this.setEmail(email);
		this.setPassword(password);
		this.setPhoneNumber(phone);
		this.setAdress(address);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAdress() {
		return address;
	}

	public void setAdress(String address) {
		this.address = address;
	}
}
