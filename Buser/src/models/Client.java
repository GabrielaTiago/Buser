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

	public Client(String name, String phone, String email, String address, String cpf, GratuityType gratuityType) {
		super(name, phone, email, address);

		this.setCpf(cpf);
		this.setTickets(tickets);
		this.tickets = new ArrayList<Ticket>();

		// checking gratuity category the client applies to
		// and applying it to all existing tickets if so
		if (gratuityType == GratuityType.elderly) {
			this.setGratuityType(gratuityType);
			this.setGratuityDocument(GratuityDocument.birthDate);
			this.applyGratuity();
		} else if (gratuityType == GratuityType.phisicallyChallenged) {
			this.setGratuityType(gratuityType);
			this.setGratuityDocument(GratuityDocument.freePass);
			this.applyGratuity();
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
			applyGratuity();
		} else if (gratuityType == "Deficiência física") {
			setGratuityType(GratuityType.phisicallyChallenged);
			setGratuityDocument(GratuityDocument.freePass);
			applyGratuity();
		} else {
			setGratuityType(GratuityType.elderly);
		}
	}

	private void applyGratuity() {
		// is called only inside the constructor if the client
		// has the gratuity right
		for (int i = 0; i < this.getTickets().size(); i++) {
			this.getTickets().get(i).setPrice(0f);
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
