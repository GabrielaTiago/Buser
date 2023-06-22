package controllers;

import java.util.ArrayList;

import database.Database;
import models.Ticket.SeatType;
import models.*;

public class TicketController {
	
	private DatabaseController dataController;
	private ArrayList<Itinerary> itineraries;
	private int ticketsQt;
	
	public TicketController(DatabaseController d) {
		this.setDataController(d);
		this.setItineraries(d.getItineraries());
	}
	
	public void create(Float p, String st, String stN, int itineraryId) {
		Ticket t = new Ticket(p, getSeatType(st), st, itineraries.get(itineraryId));
		this.getDataController().data.addTicket(t);
	}

	public void update(Float p, String st, String stN, int itineraryId) {
		Ticket t = new Ticket(p, getSeatType(st), st, itineraries.get(itineraryId));
		
		this.getDataController().data.updateTicket(t, itineraryId);
	}

	public void delete(int i) {
		this.getDataController().data.removeTicket(i);
	}
	
	public static SeatType getSeatType(String s) {
		
		SeatType seatType = null;
		
		if (s.equals("Executivo")) {
			seatType = SeatType.executivo;
		}
		if (s.equals("Semi-Leito")) {
			seatType = SeatType.semiLeito;		
				}
		if (s.equals("Leito")) {
			seatType = SeatType.leito;
		}
		return seatType;
	}
	
	public ArrayList<Itinerary> getItineraries() {
		return itineraries;
	}

	public void setItineraries(ArrayList<Itinerary> itineraries) {
		this.itineraries = itineraries;
	}

	public static void getItinerary(String s) {
		
	}

	public DatabaseController getDataController() {
		return this.dataController;
	}

	public void setDataController(DatabaseController dc) {
		this.dataController = dc;
	}

	public int getTicketsQt() {
		return this.getDataController().getData().getTickets().size();
	}
}