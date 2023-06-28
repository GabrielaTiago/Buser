package models;

import java.util.ArrayList;

import models.Client.GratuityType;

public class Client extends User {
	private String cpf;
	String gratuity;
	private GratuityType gratuityType;
	private GratuityDocument gratuityDocument;
	private ArrayList<Ticket> tickets;

	public enum GratuityType {
		noGratuity, elderly, phisicallyChallenged
	}

	public enum GratuityDocument {
		birthDate, freePass
	}

	public Client(String name, String phone, String email, String address, String cpf,
			GratuityType gratuityType) {
		//example of method override
		super(name, phone, email, address);

		this.setCpf(cpf);
		this.setTickets(tickets);
		this.tickets = new ArrayList<Ticket>();

		// checking gratuity category the client applies to
		if (gratuityType == GratuityType.elderly) {
			this.setGratuityType(gratuityType);
			this.setGratuityDocument(GratuityDocument.birthDate);
		} 
		else if (gratuityType == GratuityType.phisicallyChallenged) {
			this.setGratuityType(gratuityType);
			this.setGratuityDocument(GratuityDocument.freePass);
		} 
		else {
			this.setGratuityType(gratuityType);
		}
	}
	
	public String getGratuity() {
		return gratuity;
	}


	public void setGratuity(String gratuity) {
		this.gratuity = gratuity;
	}


	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public GratuityType getGratuityType() {
		return this.gratuityType;
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
