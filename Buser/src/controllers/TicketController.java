package controllers;

import models.Ticket.SeatType;
import models.*;

/**
 * This class is responsible for receiving and storing the information to be
 * updated. The information is received from the respective ticket screens in
 * the view package and stored in the attributes of the Database class.
 * 
 * @author Gabriel Fernando
 */
public class TicketController {
	private static int updatingTicketIndex;

	/**
	 * Method responsible for creating a new ticket based on the values received
	 * from the ticket creation screen.
	 * 
	 * @param price         the price of the ticket to be created
	 * @param seatTypeIndex the index of the seat type to be returned
	 * @param seatNumber    the seat number
	 * @param id            the id of the itinerary to which the ticket should be
	 *                      added ser adicionada
	 */
	public static void createTicket(Float price, int seatTypeIndex, int seatNumber, int id) {
		// Based on the values received from the TicketWindow class, creates
		// a new ticket and adds it to the Database ArrayList<Tickets>
		int index = id - 1;
		Company company = AuthController.getCompanyLoggedIn();
		String name = company.getName();
		SeatType s = getSeatType(seatTypeIndex);

		Ticket t = new Ticket(price, s, seatNumber);

		ItineraryController.getCompanyItineraries(name).get(index).getTickets().add(t);
	}

	/**
	 * Method responsible for updating the attributes of a ticket.
	 * 
	 * @param price         the new price of the ticket
	 * @param seatTypeIndex the index of the new seat type of the ticket
	 * @param seatNumber    the new seat number of the ticket
	 * @param ticketIndex   the index of the ticket to be updated
	 * @param id            the id of the itinerary that contains the ticket
	 */
	public static void updateTicket(Float price, int seatTypeIndex, int seatNumber, int ticketIndex, int id) {
		// replace the correspondent ticket atributes in the database with the
		// values received from the TicketWindow class JComponents
		SeatType s = getSeatType(seatTypeIndex);
		int index = id - 1;
		Company company = AuthController.getCompanyLoggedIn();
		String name = company.getName();
		ItineraryController.getCompanyItineraries(name).get(index).getTickets().get(ticketIndex).setPrice(price);
		ItineraryController.getCompanyItineraries(name).get(index).getTickets().get(ticketIndex).setSeatType(s);
		ItineraryController.getCompanyItineraries(name).get(index).getTickets().get(ticketIndex)
				.setSeatNumber(seatNumber);
	}

	/**
	 * Method that removes a ticket from the list of tickets of an itinerary, based
	 * on the provided ticket index and the itinerary that contains the ticket.
	 * 
	 * @param ticketIndex the index of the ticket to be deleted
	 * @param id          the itinerary that contains the ticket
	 */
	public static void deleteTicket(int ticketIndex, int id) {
		// deletes the ticket i in arraylist of the database
		int index = id - 1;
		Company company = AuthController.getCompanyLoggedIn();
		String name = company.getName();
		ItineraryController.getCompanyItineraries(name).get(index).getTickets().remove(ticketIndex);
	}

	/**
	 * Method that validates if the price and seat number are greater than zero.
	 * 
	 * @param price      the price to be validated
	 * @param seatNumber the seat number to be validated
	 * @return a boolean indicating whether the data is valid or not
	 */
	public static boolean checkTicketData(float price, int seatNumber) {
		if ((price >= 0f) && (seatNumber > 0)) {
			return true;
		}
		return false;
	}

	/**
	 * Method that receives an integer indicating the seat type to be returned.
	 * 
	 * @param i the index of the seat type
	 * @return the corresponding seat type
	 */
	public static SeatType getSeatType(int i) {
		// based on the index received, returns the correspondent enum type
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

	/**
	 * Method that returns the stored index of the ticket to be updated.
	 * 
	 * @return the index of the ticket to be updated
	 */
	public static int getUpdatingTicketIndex() {
		return updatingTicketIndex;
	}

	/**
	 * Method responsible for storing the index of the ticket to be stored, and also
	 * informing the edit screen.
	 * 
	 * @param index the index of the ticket to be stored
	 */
	public static void setUpdatingTicketIndex(int index) {
		updatingTicketIndex = index;
	}
}
