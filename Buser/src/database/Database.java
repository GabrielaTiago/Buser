package database;

import java.util.ArrayList;

import models.Client;
import models.Company;
import models.Itinerary;
import models.Ticket;
import models.Ticket.SeatType;

public abstract class Database {
	private Client client;
	private Company company;
	private static final ArrayList<Itinerary> itineraries = new ArrayList<Itinerary>();
	private static final ArrayList<Ticket> tickets = new ArrayList<Ticket>();
	
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
	
	public void getCompany(Company company) {
		this.company = company;
	}

	public static ArrayList<Itinerary> getItineraries() {
		return itineraries;
	}

	public static ArrayList<Ticket> getTickets() {
		return tickets;
	}
}
