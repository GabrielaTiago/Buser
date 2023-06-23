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
		setTicketsQt();
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
		setTicketsQt();
		return this.ticketsQt;
	}
	public void setTicketsQt() {
		ticketsQt = this.dataController.getData().getTickets().size();
	}
	
	public String[] getIDs() {
		String[] v = new String[this.getTicketsQt()];
		for (int i = 0; i < this.getTicketsQt(); i++) {
			v[i] =  String.valueOf(this.dataController.getData().getTickets().get(i).getId());
		}
		return v;
	}
}













