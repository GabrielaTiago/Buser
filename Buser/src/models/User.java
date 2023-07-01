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
	private String address;
/**
 * Construtor da classe user
 * @param name
 * @param phone
 * @param email
 * @param address
 */
	public User(String name, String phone, String email, String address) {
		this.setName(name);
		this.setPhoneNumber(phone);
		this.setEmail(email);
		this.setAdress(address);
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
