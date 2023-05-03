package application;

import java.util.ArrayList;

public class Company extends User{
	//atributes
	private ArrayList<Ticket> Tickets;

	//constructors
	public Company(String n, String cnpj, String tel, String email, String e) {
		super(n, cnpj, tel, email, e);
		this.Tickets = new ArrayList<Ticket>();
	}
	
	//methods
	public void addTicket(Ticket p) {
		Tickets.add(p);
	}	
	
	public void removeTicket(int i) {
		Tickets.remove(i);
	}
	
	public void updateTicket(Ticket p, int i) {
		Tickets.set(i, p);
	}
	
	public void listTickets() {
		for (int i = 0; i < Tickets.size(); i++) {
			System.out.println("Listando passagens da empresa: " + this.getName() + "\n");
			System.out.println("Passagem " + (i + 1) + "\n");
			Tickets.get(i).infoAtributos();
			System.out.println("------------------------------------");
		}
	}
	
	public void infoAtributes() {
		System.out.println("Nome:      " + this.getName() );
		System.out.println("CNPJ:      " + this.getDocument() );
		System.out.println("Número de passagens: " + this.Tickets.size() );
	}
	//getters and setters

}
