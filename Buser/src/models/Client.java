package models;

import java.util.ArrayList;

public class Client extends User {
	private String cpf;
	private String gratuity;
	private GratuityType gratuityType;
	private GratuityDocument gratuityDocument;
	private ArrayList<Ticket> tickets;

	public enum GratuityType {
		noGratuity, elderly, phisicallyChallenged
	}

	public enum GratuityDocument {
		birthDate, freePass
	}

	public Client(String name, String phone, String email, String address, String cpf, GratuityType gratuityType,
			ArrayList<Ticket> tickets) {
		super(name, phone, email, address);

		this.setCpf(cpf);
		this.setTickets(tickets);

		// checking gratuity category the client applies to
		// and applying it to all existing tickets if so
		if (gratuityType == GratuityType.elderly) {
			this.setGratuityType(gratuityType);
			this.setGratuityDocument(GratuityDocument.birthDate);
		} else if (gratuityType == GratuityType.phisicallyChallenged) {
			this.setGratuityType(gratuityType);
			this.setGratuityDocument(GratuityDocument.freePass);
		} else {
			this.setGratuityType(gratuityType);
		}

	}

	public Client(String name, String phone, String email, String address, String cpf, String gratuityType) {
		super(name, phone, email, address);

		setCpf(cpf);
		setGratuity(gratuityType);

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

	public ArrayList<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(ArrayList<Ticket> tickets) {
		this.tickets = tickets;
	}
}
