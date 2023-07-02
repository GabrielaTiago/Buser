package junitTests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import controllers.*;
import models.*;
import models.Ticket.SeatType;

/**
 * This class is from the junitTests package and is responsible for the 3 Junit
 * test cases necessary
 * 
 * @author Gabriel Fernando
 *
 */
class JUnitTests {

	@Test
	/**
	 * This test asserts the combinations that are valid and invalid for a ticket's
	 * price and seatNumber atributes
	 */
	void testCheckTicketData() {

		// only the value -1 is invalid
		float[] priceValues = { -1, 0, 1 };

		// only the value 1 is valid
		int[] seatNumberValues = { -1, 0, 1 };

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i != 0 && j == 2) {
					// the only valid option is if both of them are valid
					assertTrue(TicketController.checkTicketData(priceValues[i], seatNumberValues[j]));
				} else {
					assertFalse(TicketController.checkTicketData(priceValues[i], seatNumberValues[j]));
				}
			}
		}
	}

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
		TicketController.createTicket(price, 0, seatNumber, 1);
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

	@Test
	/**
	 * tests if the createItinerary method from the controller packageadds only the
	 * desired itinerary to the database
	 */
	void testCreateItinerary() {
		// initializes the company that will receive the itinerary
		LocalDate date = LocalDate.parse("2023-07-02");
		Company company = new Company("Araguarina", "araguarina@hotmail.com", "Teste123$", "6233445566",
				"Avenida dos Bobos, n 0 - Lugar Nenhum - LN", "46922732000130", "Araguarina LTDA");
		// registers the company in the Database
		AuthController.registerCompany(company);
		// adds a itinerary to the database
		ItineraryController.createItinerary("Anápolis", "Brasília", date, "10:00", "14:00", company);
		// gets the itinerary from the database, using the controller
		Itinerary newItinerary = ItineraryController.getCompanyItineraries(company.getName()).get(0);
		// the itinerary that will be used for comparison
		Itinerary referenceItinerary = new Itinerary("Anápolis", "Brasília", date, "10:00", "14:00", null);
		ArrayList<Itinerary> itineraries = ItineraryController.getAllItinerarys();

		assertTrue(newItinerary.getArrivalDate() == referenceItinerary.getArrivalDate());
		assertTrue(newItinerary.getDepartureDate() == referenceItinerary.getDepartureDate());
		assertTrue(newItinerary.getDate() == referenceItinerary.getDate());
		assertTrue(newItinerary.getOrigin() == referenceItinerary.getOrigin());
		assertTrue(newItinerary.getDestination() == referenceItinerary.getDestination());
		// checks that only 1 itinerary was added to database
		assertTrue(itineraries.size() == 1);
	}
}
