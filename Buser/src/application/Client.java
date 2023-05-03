package application;

import java.util.ArrayList;

public class Client extends User{
	private String cpf;
	private ArrayList<Ticket> tickets;
	
	public Client(String name, String phone, String email, String address, String cpf, ArrayList<Ticket> tickets) {
		super(name, phone, email, address);
		this.setCPF(cpf);
		this.tickets = tickets;
	}

	public void addTicket(Ticket p) {
		tickets.add(p);
		System.out.println("A passagem foi adicionada com sucesso!");
	}	 
	
	public void removeTicket(int i) {
		System.out.println("A passagem" + (i + 1) + "foi removida com sucesso!");
		tickets.remove(i);
	}
	
	public void updateTicket(Ticket p, int i) {
		tickets.set(i, p);
		System.out.println("A passagem" + (i + 1) + "foi atualizada! com sucesso");
	}
	
	public void listTickets() {
		for (int i = 0; i < tickets.size(); i++) {
			System.out.println("Listando passagens do cliente: " + this.getName() + "\n");
			System.out.println("Passagem " + (i + 1) + "\n");
			this.tickets.get(i).infoAtributos();;
			System.out.println("------------------------------------");
		}
	}
	
	public String getCPF() {
		return cpf;
	}
	
	public void setCPF(String cpf) {
		this.cpf = cpf;
	}
}
