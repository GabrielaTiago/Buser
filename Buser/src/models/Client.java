package application;

import java.util.ArrayList;

public class Client extends User {
	
	private String cpf;
	private gratuity gratuityType;
	private gratuityDocument gratuityDocument; 
	private ArrayList<Ticket> tickets;
	
	public enum gratuity{
		no_gratuity, elderly, phisicallyChallenged
	}
	public enum gratuityDocument {
		birthDate, freePass
	}
	
	public Client(String name, String phone, String email, String address, String cpf,
			ArrayList<Ticket> tickets, gratuity gratuityType) {
		
		super(name, phone, email, address);
		
		this.setCpf(cpf);
		this.setTickets(tickets); 
		
		//checking gratuity category the client applies to
		//and applying it to all existing tickets if so
		if(gratuityType == gratuity.elderly) {
			this.setGratuityType(gratuityType);
			this.setGratuityDocument(gratuityDocument.birthDate);
			this.applyGratuity();
		}
		else if(gratuityType == gratuity.phisicallyChallenged) {
					this.setGratuityType(gratuityType);
					this.setGratuityDocument(gratuityDocument.freePass);
					this.applyGratuity();
				}
		else {
			this.setGratuityType(gratuityType);
		}
			
	}

	public void addTicket(Ticket p) {
		
		if (gratuityType == gratuity.elderly ||
			gratuityType == gratuity.phisicallyChallenged) {
			p.setPrice(0f);
		}
		
		tickets.add(p);
		//System.out.println("A passagem foi adicionada com sucesso!");
	}	 
	
	public void removeTicket(int i) {
		//System.out.println("A passagem" + (i + 1) + "foi removida com sucesso!");
		tickets.remove(i);
	}
	
	public void updateTicket(Ticket p, int i) {
		tickets.set(i, p);
		//System.out.println("A passagem" + (i + 1) + "foi atualizada! com sucesso");
	}
	
	private void applyGratuity () {
		//is called only inside the constructor if the client
		//has the gratuity right
		for (int i = 0; i < this.getTickets().size(); i++) {
			this.getTickets().get(i).setPrice(0f);
		}
	}
	
	public void listTickets() {
		for (int i = 0; i < tickets.size(); i++) {
			//System.out.println("Listando passagens do cliente: " + this.getName() + "\n");
			//System.out.println("Passagem " + (i + 1) + "\n");
			this.tickets.get(i).toString();;
			//System.out.println("------------------------------------");
		}
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public gratuity getGratuityType() {
		return gratuityType;
	}

	public void setGratuityType(gratuity gratuityType) {
		this.gratuityType = gratuityType;
	}
	
	public gratuityDocument getGratuityDocument() {
		return gratuityDocument;
	}

	public void setGratuityDocument(gratuityDocument gratuityDocument) {
		this.gratuityDocument = gratuityDocument;
	}

	public ArrayList<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(ArrayList<Ticket> tickets) {
		this.tickets = tickets;
	}

	public String toString() {
		String saida;
		
		saida = super.toString();
		saida = saida + "Documento (CPF): 	" + this.getCpf();
		saida = saida + "\nNúmero de passagens: 	" + this.getTickets().size();
		return saida;
	}
}