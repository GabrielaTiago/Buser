package controllers;

import java.util.ArrayList;

import database.Database;
import models.Ticket.SeatType;
import models.*;

public class TicketController {
	
	private int ticketsQt;
	private String[] toUpdateValues;//this will store the index of the ticket selected
	//to be updated in the tickets table and pass it foward to the ticket window updateTicket()
	private ArrayList<Itinerary> itineraries = new ArrayList<Itinerary>();
	
	public TicketController() {
		//simulating the arraylist stored in the database, which is supposed to be fill with data 
		//by the itinerary crud
		Itinerary i1 = new Itinerary("Brasília", "São Paulo", "24/06/2023", "10:30", "23:40", null);
		Itinerary i2 = new Itinerary("São Paulo", "Brasília", "25/06/2023", "15:30", "20:80", null);
		Itinerary i3 = new Itinerary("Konoha", "Disney", "26/06/2023", "09:30", "17:20", null);
		Itinerary i4 = new Itinerary("Konoha", "Disney", "26/06/2023", "09:30", "17:20", null);
		Itinerary i5 = new Itinerary("Konoha", "Disney", "26/06/2023", "09:30", "17:20", null);
		Itinerary i6 = new Itinerary("Konoha", "Disney", "26/06/2023", "09:30", "17:20", null);
		itineraries.add(i1);
		itineraries.add(i2);
		itineraries.add(i3);
		//aqui que tá o problema, só tem esses itinerarios, preciso usar o Database.getItineraries()
		//mas ele ta vazio. RIP
		
		setTicketsQt();
	}
	
	public void createTicket(Float p, String st, String stN, int itineraryIndex) {
		Ticket t = new Ticket(p, getSeatType(st), stN, itineraries.get(itineraryIndex));
		
		Database.getTickets().add(t);
	}

	public void updateTicket(Float p, String st, String stN, int itineraryIndex, int ticketIndex) {
		Ticket t = new Ticket(p, getSeatType(st), st, null);
		
		Database.getTickets().set(ticketIndex, t);
	}

	public void deleteTicket(int i) {
		Database.getTickets().remove(i);
	}
	
	public String[] itineraryListToString(int option) {
		//returns the string version of all itineraries
		int size = this.itineraries.size();
		String[] itinerariesStrings = new String[size];
		String stringValue;
			for (int i = 0; i < size; i++) {
//				Itinerary itinerary = Database.getItineraries().get(i);
				Itinerary itinerary = itineraries.get(i);
				stringValue = itinerary.getOrigin() + " " +
							  itinerary.getDepartureDate() + " - " +
							  itinerary.getDestination() + " " +
							  itinerary.getArrivalDate();
				itinerariesStrings[i] = stringValue;
			}
		return itinerariesStrings;
	}
	public String itineraryToString(Itinerary itinerary) {
		//returns the string version of a single itinerary
 
		String stringValue =  itinerary.getOrigin() + " " +
							  itinerary.getDepartureDate() + " - " +
							  itinerary.getDestination() + " " +
							  itinerary.getArrivalDate();
		return stringValue;
	}
	
	public static SeatType getSeatType(String s) {
		//converts from string to enum
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
	public int getSeatTypeIndex(SeatType seat) {
		//converts from enum to string and get the correspondent index in the JComboBox
		int index;
		String s = seat.toString();
		
		if (s.equals("executivo")) {
			index = 0;
		}
		if (s.equals("semiLeito")) {
			index = 1;
		}
		if (s.equals("leito")) {
			index = 2;
		}
		return 0;
	}
	public ArrayList<Ticket> getTickets(){
		ArrayList<Ticket> t = Database.getTickets();
		return t;
	}
	public int getTicketsQt() {
		setTicketsQt();
		return this.ticketsQt;
	}
	public void setTicketsQt() {
		ticketsQt = getTickets().size();
	}
	
	public void setToUpdateValues(int i) {
		//receives the index of the ticket that will be updated 
		//and stores it's atributes in a string array
		Ticket ticket = getTickets().get(i);
		Itinerary itinerary = ticket.getItinerary();
		int seatTypeIndex = getSeatTypeIndex(ticket.getSeatType());
		String itineraryIndex = String.valueOf( getItineraryIndex(itinerary));
		
		String[] ticketValues = {String.valueOf(ticket.getPrice()),
								 String.valueOf(seatTypeIndex),
								 ticket.getSeatNumber(),
								 itineraryIndex};
		this.toUpdateValues = ticketValues; 
	}
	
	public String[] getToUpdateValues() {
		return this.toUpdateValues;
	}

	public void setToUpdateValues(String[] toUpdateValues) {
		this.toUpdateValues = toUpdateValues;
	}

	public int getItineraryIndex(Itinerary itinerary) {
		int index = 0;
		int size = getItinerariesQt();
		//substituir com o metodo de getItineraryById()*******************
		//pretending this is the database itineraries that we are using
		for (int i = 0; i < size; i++) {
			Itinerary candidate = this.itineraries.get(i);
			if (itinerary.getId() == candidate.getId()) {
				index = 1;
			}
		}
		return index;
	}
	public int getItinerariesQt() {
		int quantity = this.itineraries.size();
		return quantity;
	}
	
	public String[] getIndexes() {
		String[] v = new String[this.getTicketsQt()];
		for (int i = 0; i < this.getTicketsQt(); i++) {
			v[i] =  String.valueOf(i);
		}
		return v;
	}
}













