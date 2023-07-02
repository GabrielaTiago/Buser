package models;

/**
 * Abstract class, represents an user
 * 
 * @author Gabriel Fernandes, Gabriela Tiago
 * @since 2023
 * @version 1.1
 */
public abstract class User {
	private String name;
	private String email;
	private String password;
	private String phoneNumber;
	private String address;

	/**
	 * Class constructor
	 * 
	 * @param name     the user's name
	 * @param email    the user's email
	 * @param password the user's password
	 * @param phone    the user's phone
	 * @param address  the user's address
	 */
	public User(String name, String email, String password, String phone, String address) {
		this.setName(name);
		this.setEmail(email);
		this.setPassword(password);
		this.setPhoneNumber(phone);
		this.setAdress(address);
	}

	/**
	 * @return Get the user's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name Set the user's name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Get the user's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email Set the user's email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return Returns the user's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password Set the user's password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return Get the user's phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber Set the user's phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return Get the user's address
	 */
	public String getAdress() {
		return address;
	}

	/**
	 * @param address Set the user's address
	 */
	public void setAdress(String address) {
		this.address = address;
	}
}
