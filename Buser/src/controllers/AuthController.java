package controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import database.Database;

import models.*;
import models.Client.GratuityType;

/**
 * Authentication controller, manages the user access and validations
 * 
 * @author Gabriela Tiago
 * @since 2023
 * @version 1.0
 * 
 */
public class AuthController {
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

	/**
	 * 
	 * Performs validation of user data
	 * 
	 * @param name     The name sent
	 * @param email    The email sent
	 * @param password The password sent
	 * @param phone    The phone number sent
	 * @param address  The address sent
	 * 
	 * @return Returns the error message
	 * 
	 */
	public static String validatesUserData(String name, String email, String password, String phone, String address) {
		String errorMessage = "";

		if (!isValidName(name)) {
			errorMessage += "Nome inválido. Use apenas letras\n";
		}

		if (!isValidEmail(email)) {
			errorMessage += "Email inválido\n";
		}

		if (!isValidPassword(password)) {
			errorMessage += "Senha inválida. É necessário: letras maiúsculas, letras minúsculas, caracteres especiais, ter entre 8 e 20 caracteres\n";
		}

		if (!isValidPhone(phone)) {
			errorMessage += "Telefone inválido. Apenas números com 10 ou 11 dígitos\n";
		}

		if (!isValidAddress(address)) {
			errorMessage += "Endereço inválido\n";
		}

		return errorMessage;
	}

	/**
	 * 
	 * Performs the validation of the Client's data in the registry
	 * 
	 * @param client Instance of Client
	 * 
	 * @return Returns the error message
	 * 
	 */
	public static String validatesClientData(Client client) {
		String errorMessage = "";
		String userErrorMessage = validatesUserData(client.getName(), client.getEmail(), client.getPassword(),
				client.getPhoneNumber(), client.getAdress());

		if (!isValidCPF(client.getCpf())) {
			errorMessage += "CPF inválido, digite apenas números\n";
		}

		if (!isValidGratuityOption(client.getGratuity())) {
			errorMessage += "Opção de gratuidade não selecionada";
		}

		return userErrorMessage += errorMessage;
	}

	/**
	 * 
	 * Performs the validation of the Company's data in the registry
	 * 
	 * @param company Instance of Company
	 * 
	 * @return Returns the error message
	 * 
	 */
	public static String validatesCompanyData(Company company) {
		String errorMessage = "";
		String userErrorMessage = validatesUserData(company.getName(), company.getEmail(), company.getPassword(),
				company.getPhoneNumber(), company.getAdress());

		if (!isValidCNPJ(company.getCNPJ())) {
			errorMessage += "CNPJ inválido, digite apenas números\n";
		}

		if (!isValidCorporateName(company.getCorporateName())) {
			errorMessage += "Razão social inválida";
		}

		return userErrorMessage += errorMessage;
	}

	/**
	 * 
	 * Performs email and password verification at client login
	 * 
	 * @param email    The client's email
	 * @param password The client's password
	 * 
	 * @return Returns the error message
	 * 
	 */
	public static String validatesClient(String email, String password) {
		String errorMessage = "";
		Client client = getClientLoggedIn();

		if (!client.getEmail().equals(email) || !client.getPassword().equals(password)) {
			errorMessage += "Email e/ou senha errados";
		}

		return errorMessage;
	}

	/**
	 * 
	 * Performs email and password verification at company login
	 * 
	 * @param email    The company's email
	 * @param password The company's password
	 * 
	 * @return Returns the error message
	 * 
	 */
	public static String validatesCompany(String email, String password) {
		String errorMessage = "";
		Company company = getCompanyLoggedIn();

		if (!company.getEmail().equals(email) || !company.getPassword().equals(password)) {
			errorMessage += "Email e/ou senha errados";
		}

		return errorMessage;
	}

	/**
	 * 
	 * Performs the client registration
	 * 
	 * @param client Instance of Client
	 * 
	 */
	public static void registerClient(Client client) {
		String name = client.getName();
		String email = client.getEmail();
		String password = client.getPassword();
		String phone = client.getPhoneNumber();
		String address = client.getAdress();
		String cpf = client.getCpf();
		GratuityType gratuityType = client.getGratuityType();

		Client c = new Client(name, email, password, phone, address, cpf, gratuityType, Database.getTicketsData());

		Database.getClientData().add(c);
	}

	/**
	 * 
	 * Performs the company registration
	 * 
	 * @param company Instance of Company
	 * 
	 */
	public static void registerCompany(Company company) {
		Database.getCompanyData().add(company);
	}

	/**
	 * 
	 * Searches which client is logged into the application
	 * 
	 * @return Returns the Client logged in
	 * 
	 */
	public static Client getClientLoggedIn() {
		return Database.getClientData().get(0);
	}

	/**
	 * 
	 * Searches which company is logged into the application
	 * 
	 * @return Returns the Company logged in
	 * 
	 */
	public static Company getCompanyLoggedIn() {
		return Database.getCompanyData().get(0);
	}

	/**
	 * 
	 * Checks if a client is logged into the application
	 * 
	 * @return Returns true if it is a client, false otherwise
	 * 
	 */
	public static boolean checkClientLogin() {
		if (Database.getClientData().isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * Checks if a company is logged into the application
	 * 
	 * @return Returns true if it is a company, false otherwise
	 * 
	 */
	public static boolean checkCompanyLogin() {
		if (Database.getCompanyData().isEmpty()) {
			return false;
		}
		return true;
	}

	// Individual validations for the registration

	/**
	 * 
	 * Checks that the name sent is within the given pattern. It must not contain
	 * any numbers or special characters.
	 * 
	 * @param name The data that will be validated
	 * 
	 * @return Returns false if it is out of the pattern and true if it is in
	 * 
	 */
	public static boolean isValidName(String name) {
		return name.matches("[a-zA-ZÀ-ÿ\\s]+");
	}

	/**
	 * 
	 * Checks that the email sent is within the given pattern. Can contain lowercase
	 * and/or uppercase letters, numbers. Must contain one '@' character, one '.'
	 * character followed by at least two letters.
	 * 
	 * @param email The data that will be validated
	 * 
	 * @return Returns false if it is out of the pattern and true if it is in
	 * 
	 */
	public static boolean isValidEmail(String email) {
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * 
	 * Checks that the password sent is within the given pattern. Must contain
	 * uppercase and lowercase letters, a number and a special character. It must
	 * have a minimum of 8 and a maximum of 20 characters.
	 * 
	 * @param password The data that will be validated
	 * 
	 * @return Returns false if it is out of the pattern and true if it is in
	 * 
	 */
	public static boolean isValidPassword(String password) {
		String passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,20}$";
		return password.matches(passwordRegex);
	}

	/**
	 * 
	 * Checks that the phone number sent is within the given pattern. Must contain
	 * only numbers. It must have a minimum of 10 and a maximum of 11 characters.
	 * 
	 * @param phone The data that will be validated
	 * 
	 * @return Returns false if it is out of the pattern and true if it is in
	 * 
	 */
	public static boolean isValidPhone(String phone) {
		return phone.matches("\\d{10,11}");
	}

	/**
	 * 
	 * Checks that the address sent is within the given pattern. May contain letters
	 * and numbers, no special characters, except commas and dashes.
	 * 
	 * @param address The data that will be validated
	 * 
	 * @return Returns false if it is out of the pattern and true if it is in
	 * 
	 */
	public static boolean isValidAddress(String address) {
		return address.matches("[a-zA-ZÀ-ÿ0-9\\s,-]+");
	}

	/**
	 * 
	 * Checks that the cpf sent is within the given pattern. It must contain only
	 * numbers and be 11 characters in length.
	 * 
	 * @param cpf The data that will be validated
	 * 
	 * @return Returns false if it is out of the pattern and true if it is in
	 * 
	 */
	public static boolean isValidCPF(String cpf) {
		return cpf.matches("\\d{1,11}");
	}

	/**
	 * 
	 * Checks that the gratuity option has been selected.
	 * 
	 * @param gratuityOption The data that will be validated
	 * 
	 * @return Returns false if it is out of the pattern and true if it is in
	 * 
	 */
	public static boolean isValidGratuityOption(String gratuityOption) {
		if (gratuityOption == "Tipo de gratuidade") {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * Checks that the cpnj sent is within the given pattern. It must contain only
	 * numbers and be 14 characters in length.
	 * 
	 * @param cnpj The data that will be validated
	 * 
	 * @return Returns false if it is out of the pattern and true if it is in
	 * 
	 */
	public static boolean isValidCNPJ(String cnpj) {
		return cnpj.matches("\\d{1,14}");
	}

	/**
	 * 
	 * Checks that the corporete name sent is within the given pattern. It must not
	 * contain any numbers or special characters.
	 * 
	 * @param corporateName The data that will be validated
	 * 
	 * @return Returns false if it is out of the pattern and true if it is in
	 * 
	 */
	public static boolean isValidCorporateName(String corporateName) {
		return corporateName.matches("[a-zA-ZÀ-ÿ0-9\\s,-]+");
	}
}
