package controllers;

import java.util.ArrayList;

import database.Database;
import models.Itinerary;
import models.Ticket;

public class DatabaseController {
	private Database data;
	private TicketController ticketController;
	
	public DatabaseController() {
		data = new Database();
		ticketController = new TicketController(this);
	}
	//mover para itinerary controller
	//begin
	public String[] itineraryListToString() {
		int size = this.getData().getItineraries().size();
		String[] itinerariesStrings = new String[size];
		
		for (int i = 0; i < size; i++) {
			String stringValue;
			
			stringValue = this.getData().getItineraries().get(i).getOrigin() + " " +
						  this.getData().getItineraries().get(i).getDepartureDate() + " - " +
						  this.getData().getItineraries().get(i).getDestination() + " " +
						  this.getData().getItineraries().get(i).getArrivalDate();
			
			itinerariesStrings[i] = stringValue;
		}
		
		return itinerariesStrings;
	}
	public void createTicket(Float p, String st, String stN, int itineraryId) {
		Ticket t = new Ticket(p, ticketController.getSeatType(st), st,
				getData().getItineraries().get(itineraryId));
		getData().addTicket(t);
	}

	public void updateTicket(Float p, String st, String stN, int itineraryId) {
		Ticket t = new Ticket(p, ticketController.getSeatType(st), st, 
				getData().getItineraries().get(itineraryId));
		
		this.getData().updateTicket(t, itineraryId);
	}

	public void deleteTicket(int i) {
		this.getData().removeTicket(i);
	}
	
	public ArrayList<Itinerary> getItineraries() {
		return data.getItineraries();
	}
	//end

	public Database getData() {
		return data;
	}

	public void setData(Database data) {
		this.data = data;
	}

	public TicketController getTicketController() {
		return this.ticketController;
	}

	public void setTicketController(TicketController ticketController) {
		this.ticketController = ticketController;
	}
}
