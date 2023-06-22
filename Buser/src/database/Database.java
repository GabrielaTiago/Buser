package database;

import java.util.ArrayList;

import models.Client;
import models.Company;
import models.Itinerary;
import models.Ticket;

public class Database {
	private Client client;
	private Company company;
	private ArrayList<Itinerary> itineraries;
	private static ArrayList<Ticket> tickets;

	public Database() {
		this.itineraries = new ArrayList<Itinerary>();
		 this.tickets = new ArrayList<Ticket>();
	}
	
	public void addTicket(Ticket t) {
		Database.getTickets().add(t);
	}
	public void removeTicket(int i) {
		Database.getTickets().remove(i);
	}
	public void updateTicket(Ticket t, int i) {
		Database.getTickets().set(i, t);
	}

	
	//getters and setters
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public ArrayList<Itinerary> getItineraries() {
		return itineraries;
	}

	public static ArrayList<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(ArrayList<Ticket> t) {
		this.tickets = t;
	}

	public void setItineraries(ArrayList<Itinerary> t) {
		this.itineraries = t;
	}
	
}
