package controllers;

import java.util.ArrayList;

import database.Database;
import models.Ticket.SeatType;
import models.*;

public class TicketController {
	private static int updatingTicketIndex;

	
	public static void createTicket(Float price, int seatTypeIndex, int seatNumber, int id) {
		//Based on the values received from the TicketWindow class, creates
		//a new ticket and adds it to the Database ArrayList<Tickets>
		int index = id - 1;
		Company company = AuthController.getCompanyLoggedIn();
		String name = company.getName();
		SeatType s = getSeatType(seatTypeIndex);
		
		Ticket t = new Ticket(price, s, seatNumber);
		
		ItineraryController.getCompanyItineraries(name).get(index).getTickets().add(t);
	}

	public static void updateTicket(Float price, int seatTypeIndex, int seatNumber, int ticketIndex, int id) { 
		//replace the correspondent ticket atributes in the database with the 
		//values received from the TicketWindow class JComponents
		SeatType s = getSeatType(seatTypeIndex);
		int index = id - 1;
		Company company = AuthController.getCompanyLoggedIn();
		String name = company.getName();
		ItineraryController.getCompanyItineraries(name).get(index).getTickets().get(ticketIndex).setPrice(price);
		ItineraryController.getCompanyItineraries(name).get(index).getTickets().get(ticketIndex).setSeatType(s);
		ItineraryController.getCompanyItineraries(name).get(index).getTickets().get(ticketIndex).setSeatNumber(seatNumber);
	}

	public static void deleteTicket(int ticketIndex, int id) {
		//deletes the ticket i in arraylist of the database
		int index = id - 1;
		Company company = AuthController.getCompanyLoggedIn();
		String name = company.getName();
		ItineraryController.getCompanyItineraries(name).get(index).getTickets().remove(ticketIndex);
	}
	
	public static SeatType getSeatType(int i) {
		//based on the index received, returns the correspondent enum type
		SeatType seatType = null;
		
		if (i == 0) {
			seatType = SeatType.executivo;
		}
		if (i == 1) {
			seatType = SeatType.semiLeito;		
				}
		if (i == 2) {
			seatType = SeatType.leito;
		}
		return seatType;
	}
	public static ArrayList<Ticket> getTickets(){
		ArrayList<Ticket> t = Database.getTicketsData();
		return t;
	}
	
	public static String[] getIndexes() {
		String[] v = new String[getTicketsSize()];
		for (int i = 0; i < getTicketsSize(); i++) {
			v[i] =  String.valueOf(i);
		}
		return v;
	}
	
	public static int getTicketsSize() {
		return getTickets().size();
	}

	public static int getUpdatingTicketIndex() {
		return updatingTicketIndex;
	}

	public static void setUpdatingTicketIndex(int Index) {
		updatingTicketIndex = Index;
	}
}
