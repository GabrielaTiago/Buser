package controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import database.Database;

import models.*;

public class AuthController {
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

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

	public static String validatesClient(String email, String password) {
		String errorMessage = "";
		Client client = getClientLoggedIn();

		if (!client.getEmail().equals(email) || !client.getPassword().equals(password)) {
			errorMessage += "Email e/ou senha errados";
		}

		return errorMessage;
	}

	public static String validatesCompany(String email, String password) {
		String errorMessage = "";
		Company company = getCompanyLoggedIn();

		if (!company.getEmail().equals(email) || !company.getPassword().equals(password)) {
			errorMessage += "Email e/ou senha errados";
		}

		return errorMessage;
	}

	public static boolean isValidName(String name) {
		return name.matches("[a-zA-ZÀ-ÿ\\s]+");
	}

	public static boolean isValidEmail(String email) {
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static boolean isValidPassword(String password) {
		String passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,20}$";
		return password.matches(passwordRegex);
	}

	public static boolean isValidPhone(String phone) {
		return phone.matches("\\d{10,11}");
	}

	private static boolean isValidAddress(String address) {
		return address.matches("[a-zA-ZÀ-ÿ0-9\\s,-]+");
	}

	private static boolean isValidCPF(String cpf) {
		return cpf.matches("\\d{1,11}");
	}

	private static boolean isValidGratuityOption(String gratuityOption) {
		if (gratuityOption == "Tipo de gratuidade") {
			return false;
		}
		return true;
	}

	public static boolean isValidCNPJ(String cnpj) {
		return cnpj.matches("\\d{1,14}");
	}

	public static boolean isValidCorporateName(String corporateName) {
		return corporateName.matches("[a-zA-ZÀ-ÿ0-9\\s,-]+");
	}

	public static void registerClient(Client client) {
		Database.getClientData().add(client);
	}

	public static void registerCompany(Company company) {
		String name = company.getName();
		String email = company.getEmail();
		String password = company.getPassword();
		String phone = company.getPhoneNumber();
		String address = company.getAdress();
		String cnpj = company.getCNPJ();
		String corporateName = company.getCorporateName();

		Company c = new Company(name, email, password, phone, address, cnpj, corporateName,
				Database.getItinaraiesData());
		Database.getCompanyData().add(c);
	}

	public static Client getClientLoggedIn() {
		return Database.getClientData().get(0);
	}

	public static Company getCompanyLoggedIn() {
		return Database.getCompanyData().get(0);
	}

	public static boolean checkClientLogin() {
		if (Database.getClientData().isEmpty()) {
			return false;
		}
		return true;
	}

	public static boolean checkCompanyLogin() {
		if (Database.getCompanyData().isEmpty()) {
			return false;
		}
		return true;
	}
}
