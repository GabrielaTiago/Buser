package controllers;

import java.util.ArrayList;

import database.Database;
import models.Itinerary;

public class DatabaseController {
	Database data = new Database();
	TicketController ticketController = new TicketController(this);
	
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

	public ArrayList<Itinerary> getItineraries() {
		return data.getItineraries();
	}

	public Database getData() {
		return data;
	}

	public void setData(Database data) {
		this.data = data;
	}

	public TicketController getTicketController() {
		return ticketController;
	}

	public void setTicketController(TicketController ticketController) {
		this.ticketController = ticketController;
	}
}
