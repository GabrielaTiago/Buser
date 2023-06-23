package controllers;

import java.util.ArrayList;

import database.Database;
import models.Ticket.SeatType;
import models.*;

public class TicketController {
	
	private DatabaseController dataController;
	private int ticketsQt;
	
	public TicketController(DatabaseController d) {
		this.dataController = d;
		ticketsQt = d.getData().getTickets().size();
	}
	
	public void create(Float p, String st, String stN, int itineraryId) {
		Ticket t = new Ticket(p, getSeatType(st), st,
				dataController.getData().getItineraries().get(itineraryId));
		this.dataController.getData().addTicket(t);
	}

	public void update(Float p, String st, String stN, int itineraryId) {
		Ticket t = new Ticket(p, getSeatType(st), st, 
				dataController.getData().getItineraries().get(itineraryId));
		
		this.dataController.getData().updateTicket(t, itineraryId);
	}

	public void delete(int i) {
		this.dataController.getData().removeTicket(i);
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

	public int getTicketsQt() {
		return this.ticketsQt;
	}
	
	public String[] getIDs() {
		String[] s = new String[getTicketsQt()];
		for (int i = 0; i < getTicketsQt(); i++) {
			s[i] =  String.valueOf(this.dataController.getData().getTickets().get(i).getId());
		}
		return s;
	}
}













