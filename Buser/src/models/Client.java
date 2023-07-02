package models;

import java.util.ArrayList;

/**
 * Client class, inheriting characteristics of the parent class User
 * 
 * @author Gabriel Fernandes, Gabriela Tiago
 * @since 2023
 * @version 1.2
 * 
 */
public class Client extends User {
	private String cpf;
	private String gratuity;
	private GratuityType gratuityType;
	private GratuityDocument gratuityDocument;
	private ArrayList<Ticket> clientTickets;

	/**
	 * Enumeration for the typification of gratuity applied
	 */
	public enum GratuityType {
		noGratuity, elderly, phisicallyChallenged
	}

	/**
	 * Enumeration for the typification of the gratuity validation document
	 */
	public enum GratuityDocument {
		birthDate, freePass
	}

	/**
	 * Class constructor for instantiating a new Client
	 * 
	 * @param name          The client's name
	 * @param email         The client's email
	 * @param password      The client's password
	 * @param phone         The client's phone
	 * @param address       The client's address
	 * @param cpf           The client's cpf document
	 * @param gratuityType  The client's gratuity option
	 * @param clientTickets The clients's tickets registered
	 */
	public Client(String name, String email, String password, String phone, String address, String cpf,
			GratuityType gratuityType, ArrayList<Ticket> clientTickets) {
		super(name, email, password, phone, address);

		this.setCpf(cpf);
		this.setGratuityType(gratuityType);
		this.setClientTickets(clientTickets);
	}

	/**
	 * Class constructor for instantiating a new Client to perform the necessary
	 * validations
	 * 
	 * @param name         The client's name
	 * @param email        The client's email
	 * @param password     The client's password
	 * @param phone        The client's phone
	 * @param address      The client's address
	 * @param cpf          The client's cpf document
	 * @param gratuityType The client's gratuity option
	 */
	public Client(String name, String email, String password, String phone, String address, String cpf,
			String gratuityType) {
		super(name, email, password, phone, address);

		setCpf(cpf);
		setGratuity(gratuityType);
		validateGratuity(gratuityType);
	}

	/**
	 * Checks and applies the gratuity to the client
	 * 
	 * @param gratuityType Option sent for validation
	 */
	public void validateGratuity(String gratuityType) {
		// checking gratuity category the client applies to
		if (gratuityType == "Idade") {
			setGratuityType(GratuityType.elderly);
			setGratuityDocument(GratuityDocument.birthDate);
		} else if (gratuityType == "Deficiência física") {
			setGratuityType(GratuityType.phisicallyChallenged);
			setGratuityDocument(GratuityDocument.freePass);
		} else {
			setGratuityType(GratuityType.elderly);
		}
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getGratuity() {
		return gratuity;
	}

	public void setGratuity(String gratuity) {
		this.gratuity = gratuity;
	}

	public GratuityType getGratuityType() {
		return gratuityType;
	}

	public void setGratuityType(GratuityType gratuityType) {
		this.gratuityType = gratuityType;
	}

	public GratuityDocument getGratuityDocument() {
		return gratuityDocument;
	}

	public void setGratuityDocument(GratuityDocument gratuityDocument) {
		this.gratuityDocument = gratuityDocument;
	}

	public ArrayList<Ticket> getClientTickets() {
		return clientTickets;
	}

	public void setClientTickets(ArrayList<Ticket> clientTickets) {
		this.clientTickets = clientTickets;
	}
}
