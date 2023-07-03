package tests;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controllers.AuthController;
import controllers.ItineraryController;
import models.Company;
import models.Itinerary;

public class CreateItinerary {
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
		ArrayList<Itinerary> itineraries = ItineraryController.getCompanyItineraries(company.getName());

		assertTrue(newItinerary.getArrivalDate() == referenceItinerary.getArrivalDate());
		assertTrue(newItinerary.getDepartureDate() == referenceItinerary.getDepartureDate());
		assertTrue(newItinerary.getDate() == referenceItinerary.getDate());
		assertTrue(newItinerary.getOrigin() == referenceItinerary.getOrigin());
		assertTrue(newItinerary.getDestination() == referenceItinerary.getDestination());
		// checks that only 1 itinerary was added to database
		assertTrue(itineraries.size() == 1);
	}
}
