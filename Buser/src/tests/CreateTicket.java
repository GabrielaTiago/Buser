package tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controllers.AuthController;
import controllers.ItineraryController;
import controllers.TicketController;
import models.Company;
import models.Ticket;
import models.Ticket.SeatType;

public class CreateTicket {
	@Test
	/**
	 * tests if the createTicket method from the controller package adds only the
	 * desired itinerary to the database
	 */
	void testCreateTicket() {

		float price = 10f; // valid price value
		SeatType seatType = SeatType.executivo; // index = 0
		int seatNumber = 2; // valid seatNumber value

		// a itinerary, of id 1, is added to the arrayList in the Database
		Company company = new Company("teste", null, null, null, null, null, null);
		AuthController.registerCompany(company);
		ItineraryController.createItinerary(null, null, null, null, null, company);
		TicketController.createTicket(price, 0, seatNumber, 1, company);
		ArrayList<Ticket> tickets = ItineraryController.getItineraryTicketsByID(1);
		// gets the created ticket from the database
		Ticket newTicket = tickets.get(0);
		// the itinerary that will be used for comparison
		Ticket referenceTicket = new Ticket(price, seatType, seatNumber);

		assertTrue(newTicket.getPrice() == referenceTicket.getPrice());
		assertTrue(newTicket.getSeatTypeString() == referenceTicket.getSeatTypeString());
		assertTrue(newTicket.getSeatNumber() == referenceTicket.getSeatNumber());
		// checks that only 1 ticket was added to database
		assertTrue(tickets.size() == 1);
	}
}
