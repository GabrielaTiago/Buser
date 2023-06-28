package controllers;

import java.util.regex.Pattern;

import database.Database;
import models.*;
import models.Client.GratuityType;

public class AuthController {
	public static GratuityType getGratuityType(String gratuity) {
		GratuityType type = null;
		
		if (gratuity == "Idoso"){
			type = GratuityType.elderly; 
		}
		else if (gratuity == "Deficiência física"){
			type = GratuityType.phisicallyChallenged;
		}
		else {
			type = GratuityType.noGratuity;
		}
		return type;
	}
	
	public static void registerClient(Client clientData) {
		Database.client = clientData;
	}
	
	public static String validatesUserData(String name, String email, String phone, String address) {
		String errorMessage = "";

		if (!isValidName(name)) {
			errorMessage += "Nome inválido. Use apenas letras\n";
		}

		if (!isValidEmail(email)) {
			errorMessage += "Email inválido\n";
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
		String userErrorMessage = validatesUserData(company.getName(), company.getEmail(), company.getPhoneNumber(),
				company.getAdress());

		if (!isValidCNPJ(company.getCNPJ())) {
			errorMessage += "CNPJ inválido, digite apenas númeroso\n";
		}

		if (!isValidCorporateName(company.getCorporateName())) {
			errorMessage += "Razão social inválida";
		}

		return userErrorMessage += errorMessage;
	}

	public static String validatesClientData(Client client, String gratuity) {
		String errorMessage = "";
		String userErrorMessage = validatesUserData(client.getName(), client.getEmail(), client.getPhoneNumber(),
				client.getAdress());

		if (!isValidCPF(client.getCpf())) {
			errorMessage += "CPF inválido, digite apenas números\n";
		}

		if (!isValidGratuityOption(gratuity)) {
			errorMessage += "Opção de gratuidade não selecionada";
		}

		return userErrorMessage += errorMessage;
	}

	public static boolean isValidName(String name) {
		return name.matches("[a-zA-ZÀ-ÿ\\s]+");
	}

	public static boolean isValidEmail(String email) {
		String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		return Pattern.matches(emailPattern, email);
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
}