package controllers;

import java.util.ArrayList;

import database.Database;
import models.Ticket.SeatType;
import models.*;
import models.Client.GratuityType;

public class TicketController {
	private static String[] toUpdateValues;//this will store the index of the ticket selected
	//to be updated in the tickets table and pass it foward to the ticket window updateTicket()
	private static int updatingTicketIndex;

	
	public static void createTicket(Float price, int seatTypeIndex, String seatNumber) {
		//Based on the values received from the TicketWindow claas, creates
		//a new ticket and adds it to the Database ArrayList<Tickets>
		SeatType s = getSeatType(seatTypeIndex);
		
		Ticket t = new Ticket(price, s, seatNumber);
		Database.getTicketsData().add(t);
	}

	public static void updateTicket(Float price, int seatTypeIndex, String seatNumber, int ticketIndex) { 
		//replace the correspondent ticket atributes in the database with the 
		//values received from the TicketWindow class JComponents
		SeatType s = getSeatType(seatTypeIndex);

		Database.getTicketsData().get(ticketIndex).setPrice(price);
		Database.getTicketsData().get(ticketIndex).setSeatType(s);
		Database.getTicketsData().get(ticketIndex).setSeatNumber(seatNumber);
	}

	public static void deleteTicket(int i) {
		//deletes the ticket i in arraylist of the database
		Database.getTicketsData().remove(i);
	}
	
	public static String[][] fillTableData() {
		//returns the tabledata in a string[][]
		int size = getTicketsSize();
		int lines = 20;
		String[][] tableData = new String[lines][4]; 
		
		for (int i = 0; i < lines; i++) {

				if (i < size) {
					Ticket t = getTickets().get(i);
					//saves the ticket i, in database tickets,
					//data in a string array to fill the cell at the table 
					String[] values = {String.valueOf(i),
									   String.valueOf(t.getPrice()),
									   t.getSeatType().toString(),
									   t.getSeatNumber()};
					
					for(int j = 0; j < 4; j++) {
						tableData[i][j] = values[j];
					}
				}
				else {
				for(int j = 0; j < 4; j++) {
					tableData[i][j] = "";
				}
			}
		}
		return tableData;
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
	
	public static  int getSeatTypeIndex(SeatType seat) {
		//converts from enum to the index of the seatType in the JComboBox and
		//gets the correspondent index in the JComboBox is used only when fetching
		//a ticket information to set the values in the creation window
		//if it returns 3 we will know by a index out of bounds exception
		int index = 3;
		
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
	
	public static String[] getToUpdateValues() {
		return toUpdateValues;
	}
	
	public static void setToUpdateValues(int i) {
		//receives the index of the ticket that will be updated 
		//and stores it's atributes in a string array
		setUpdatingTicketIndex(i);
		
		Ticket ticket = getTickets().get(i);
		//ticket atributes
		float price = ticket.getPrice();
		int seatTypeIndex = getSeatTypeIndex(ticket.getSeatType());
		String seatNumber = ticket.getSeatNumber();
		
		String[] ticketValues = {String.valueOf(price),
								 String.valueOf(seatTypeIndex),
								 seatNumber};
		
		toUpdateValues = ticketValues; 
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
