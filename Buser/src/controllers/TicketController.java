package controllers;

import java.util.ArrayList;

import database.Database;
import models.Ticket.SeatType;
import models.*;

public class TicketController {
	
	private int ticketsQt;
	private String[] toUpdateValues;//this will store the index of the ticket selected
	//to be updated in the tickets table and pass it foward to the ticket window updateTicket()
	private static int ticketIndex;
	private ArrayList<Itinerary> itineraries = new ArrayList<Itinerary>();
	
	public TicketController() {
		//simulating the arraylist stored in the database, which is supposed to be fill with data 
		//by the itinerary crud
		Itinerary i1 = new Itinerary("Brasília", "São Paulo", "24/06/2023",   "01:30", "18:00", null);
		Itinerary i2 = new Itinerary("São Paulo", "Brasília", "25/06/2023",   "02:35", "19:10", null);
		Itinerary i3 = new Itinerary("Konoha", "Disney", "26/06/2023", 		  "03:40", "20:20", null);
		Itinerary i4 = new Itinerary("Tatooine", "Disney", "26/06/2023", 	  "04:45", "21:30", null);
		Itinerary i5 = new Itinerary("Demon Continent", "Moon", "26/06/2023", "05:50", "22:40", null);
		Itinerary i6 = new Itinerary("Piaui", "Tokyo", "26/06/2023", 		  "06:55", "23:50", null);
		itineraries.add(i1);
		itineraries.add(i2);
		itineraries.add(i3);
		itineraries.add(i4);
		itineraries.add(i5);
		itineraries.add(i6);
		
		Ticket t1 = new Ticket(10, SeatType.executivo, "A1", i1);
		Ticket t2 = new Ticket(15, SeatType.semiLeito, "A2", i2);
		Ticket t3 = new Ticket(20, SeatType.leito, "A3", i3);
		Ticket t4 = new Ticket(25, SeatType.executivo, "A4", i4);
		Ticket t5 = new Ticket(30, SeatType.semiLeito, "A5", i5);
		Ticket t6 = new Ticket(45, SeatType.leito, "A6", i6);
		Database.getTickets().add(t1);
		Database.getTickets().add(t2);
		Database.getTickets().add(t3);
		Database.getTickets().add(t4);
		Database.getTickets().add(t5);
		Database.getTickets().add(t6);
		//aqui que tá o problema, só tem esses itinerarios, preciso usar o Database.getItineraries()
		//mas ele ta vazio. RIP
		
		setTicketsQt();
	}
	
	public void createTicket(Float price, int seatTypeIndex, String seatNumber, int itineraryIndex) {
		//Based on the values received from the TicketWindow claas, creates
		//a new ticket and adds it to the Database ArrayList<Tickets>
		Itinerary i = itineraries.get(itineraryIndex);
		SeatType s = getSeatType(seatTypeIndex);
		Ticket t = new Ticket(price, s, seatNumber, i);
		Database.getTickets().add(t);
	}

	public void updateTicket(Float price, int seatTypeIndex, String seatNumber, int itineraryIndex, int ticketIndex) { 
		//replace the correspondent ticket atributes in the database with the 
		//values received from the TicketWindow class JComponents
		SeatType s = getSeatType(seatTypeIndex);
		Itinerary i = itineraries.get(itineraryIndex);

		Database.getTickets().get(ticketIndex).setPrice(price);
		Database.getTickets().get(ticketIndex).setSeatType(s);
		Database.getTickets().get(ticketIndex).setSeatNumber(seatNumber);
		Database.getTickets().get(ticketIndex).setItinerary(i);
	}

	public void deleteTicket(int i) {
		//deletes the ticket i in arraylist of the database
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
	
	public int getSeatTypeIndex(SeatType seat) {
		//converts from enum to the index of the seatType in the JComboBox and
		//gets the correspondent index in the JComboBox is used only when fetching
		//a ticket information to set the values in the creation window
		int index = 3;
		//se retornar o 3, iremos saber por index out of bounds
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
		return index;
	}
	public String[] getToUpdateValues() {
		return this.toUpdateValues;
	}
	public void setToUpdateValues(int i) {
		//receives the index of the ticket that will be updated 
		//and stores it's atributes in a string array
		setTicketIndex(i);
		
		Ticket ticket = getTickets().get(i);
		//ticket atributes
		float price = ticket.getPrice();
		int seatTypeIndex = getSeatTypeIndex(ticket.getSeatType());
		String seatNumber = ticket.getSeatNumber();
		Itinerary itinerary = ticket.getItinerary();
		
		int itineraryIndex = (getItineraryIndex(itinerary));
		
		String[] ticketValues = {String.valueOf(ticket.getPrice()),
								 String.valueOf(seatTypeIndex),
								 seatNumber,
								 String.valueOf(itineraryIndex)};
		
		this.toUpdateValues = ticketValues; 
	}
	

	public int getItineraryIndex(Itinerary itinerary) {
		//finds out where in the arraylist the ticket itinerary is 
		int index = 0;
		int size = getItinerariesQt();
		int id1 = itinerary.getId();
		//substituir com o metodo de getItineraryById()*******************
		//pretending this is the database itineraries that we are using
		for (int i = 0; i < size; i++) {
			int id2 = itineraries.get(i).getId();
			
			if (id1 == id2) {
				index = i;
				return index;
			}
		}
		return 30;
		//this way we know if it reaches this return, since it will lead to a out of bounds
	}
	
	public ArrayList<Ticket> getTickets(){
		ArrayList<Ticket> t = Database.getTickets();
		return t;
	}
	public String[] getIndexes() {
		String[] v = new String[this.getTicketsQt()];
		for (int i = 0; i < this.getTicketsQt(); i++) {
			v[i] =  String.valueOf(i);
		}
		return v;
	}
	public int getItinerariesQt() {
		int quantity = this.itineraries.size();
		return quantity;
	}
	public int getTicketsQt() {
		setTicketsQt();
		return this.ticketsQt;
	}
	public void setTicketsQt() {
		ticketsQt = getTickets().size();
	}

	public static int getTicketIndex() {
		return ticketIndex;
	}

	public static void setTicketIndex(int Index) {
		ticketIndex = Index;
	}
	
}













