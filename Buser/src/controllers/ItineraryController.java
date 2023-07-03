package controllers;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import database.Database;
import models.Company;
import models.Itinerary;
import models.Ticket;

/**
 * Authentication controller, manages the user access and validations
 * 
 * @author Gabriela Tiago
 * @since 2023
 * @version 1.1
 * 
 */
public class ItineraryController {

	/**
	 * 
	 * Performs validation of itinerary data
	 * 
	 * @param origin        The origin city sent
	 * @param destination   The destination city sent
	 * @param date          The date sent
	 * @param departureTime The departure time sent
	 * @param arrivalTime   The arrival time sent
	 * 
	 * @return Returns the error message
	 * 
	 */
	public static String validatesItinararyData(String origin, String destination, LocalDate date, String departureTime,
			String arrivalTime) {
		String errorMessage = "";
		LocalTime departureTimeChecker = LocalTime.parse("00:00");
		LocalTime arrivalTimeChecker = LocalTime.parse("00:01");

		if (origin == "Cidade de Origem") {
			errorMessage += "O campo 'Cidade de Origem' está vazio\n";
		} else if (!isValidCity(origin)) {
			errorMessage += "Não é uma cidade de origem válida\n";
		}

		if (destination == "Cidade de Destino") {
			errorMessage += "O campo 'Cidade de Destino' está vazio\n";
		} else if (!isValidCity(destination)) {
			errorMessage += "Não é uma cidade de destino válida\n";
		}

		if (origin.equals(destination)) {
			errorMessage += "A cidade de origem não pode ser igual a ciade de destino\n";
		}

		if (date == null) {
			errorMessage += "É necessário escolher uma data\n";
		} else {
			LocalDate today = LocalDate.now();
			long daysBetween = ChronoUnit.DAYS.between(today, date);

			if (daysBetween < 0) {
				errorMessage += "Data escolhida é anterior ao dia de hoje\n";
			}
		}

		switch (departureTime) {
		case "Horário de Partida":
			errorMessage += "O campo 'Horário de Partida' está vazio\n";
			break;
		default:
			if (!isValidTimeFormat(departureTime)) {
				errorMessage += "Horário de partida inválido. Digite no formato 'HH:mm'\n";
			} else {
				departureTimeChecker = LocalTime.parse(departureTime);
			}
			break;
		}

		switch (arrivalTime) {
		case "Horário de Chegada":
			errorMessage += "O campo 'Horário de Chegada' está vazio\n";
			break;
		default:
			if (!isValidTimeFormat(arrivalTime)) {
				errorMessage += "Horário de chegada inválido. Digite no formato 'HH:mm'\n";
			} else {
				arrivalTimeChecker = LocalTime.parse(arrivalTime);
			}
			break;
		}

		if (arrivalTimeChecker.isBefore(departureTimeChecker)) {
			errorMessage += "O horário de chegada é anterior ao horário de partida\n";
		} else if (departureTimeChecker.equals(arrivalTimeChecker)) {
			errorMessage += "O horário de chegada é igual ao horário de partida\n";
		}

		return errorMessage;
	}

	/**
	 * 
	 * Creates a new Itinerary
	 * 
	 * @param origin        The origin sent
	 * @param destination   The email sent
	 * @param date          The date sent
	 * @param departureTime The departure Time sent
	 * @param arrivalTime   The arrivalTime sent
	 * @param company       In which company the itinerary will be created
	 * 
	 */
	public static void createItinerary(String origin, String destination, LocalDate date, String departureTime,
			String arrivalTime, Company company) {

		Itinerary itinerary = new Itinerary(origin, destination, date, departureTime, arrivalTime, company);
		company.getItineraries().add(itinerary);
		Database.getItinaraiesData().add(itinerary);
	}

	/**
	 * 
	 * Deletes an Itinerary
	 * 
	 * @param id      Which the itinerary is being deleted
	 * @param company The company that has a itinerary removed
	 * 
	 */
	public static void deleteItinerary(int id, Company company) {
		Database.getItinaraiesData().remove(id - 1);
		company.getItineraries().remove(id - 1);
	}

	/**
	 * 
	 * Edits an Itinerary
	 * 
	 * @param id            Which the itinerary is being edited
	 * @param origin        The origin sent
	 * @param destination   The email sent
	 * @param date          The date sent
	 * @param departureTime The departure time sent
	 * @param arrivalTime   The arrival time sent
	 * @param company       The company that receives the itinerary update
	 */
	public static void updateItinerary(int id, String origin, String destination, LocalDate date, String departureTime,
			String arrivalTime, Company company) {
		int i = id - 1;
		Itinerary itinerary = company.getItineraries().get(i);

		itinerary.setOrigin(origin);
		itinerary.setDestination(destination);
		itinerary.setDate(date);
		itinerary.setDepartureDate(departureTime);
		itinerary.setArrivalDate(arrivalTime);
	}

	/**
	 * 
	 * Searches all itineraries registered
	 * 
	 * @return Returns the list with all the itineraries registered
	 * 
	 */
	public static ArrayList<Itinerary> getAllItinerarys() {
		ArrayList<Itinerary> itineraries = Database.getItinaraiesData();

		return itineraries;
	}

	/**
	 * 
	 * Searches all routes with the given city of origin
	 * 
	 * @param origin Filter by city of origin
	 * 
	 * @return Returns the list with all the itineraries leaving from that city
	 * 
	 */
	public static ArrayList<Itinerary> getItinerariesByOrigin(String origin) {
		ArrayList<Itinerary> itineraries = Database.getItinaraiesData();
		ArrayList<Itinerary> filteredItineraries = new ArrayList<>();

		for (int i = 0; i < itineraries.size(); i++) {
			if (itineraries.get(i).getOrigin().equals(origin)) {
				filteredItineraries.add(itineraries.get(i));
			}
		}

		return filteredItineraries;
	}

	/**
	 * 
	 * Searches all routes with the given city of destination
	 * 
	 * @param destination Filter by city of destination
	 * 
	 * @return Returns the list with all the itineraries going to that city
	 * 
	 */
	public static ArrayList<Itinerary> getItinerariesByDestination(String destination) {
		ArrayList<Itinerary> itineraries = Database.getItinaraiesData();
		ArrayList<Itinerary> filteredItineraries = new ArrayList<>();

		for (int i = 0; i < itineraries.size(); i++) {
			if (itineraries.get(i).getDestination().equals(destination)) {
				filteredItineraries.add(itineraries.get(i));
			}
		}

		return filteredItineraries;
	}

	/**
	 * 
	 * Searches all routes with the given the date
	 * 
	 * @param date Filter by date
	 * 
	 * @return Returns the list with all the itineraries for that date
	 * 
	 */
	public static ArrayList<Itinerary> getItinerariesByDate(LocalDate date) {
		ArrayList<Itinerary> itineraries = Database.getItinaraiesData();
		ArrayList<Itinerary> filteredItineraries = new ArrayList<>();

		for (int i = 0; i < itineraries.size(); i++) {
			if (itineraries.get(i).getDate().equals(date)) {
				filteredItineraries.add(itineraries.get(i));
			}
		}

		return filteredItineraries;
	}

	/**
	 * 
	 * Searches for the tickets in the itinerary
	 * 
	 * @param id Filter by id
	 * 
	 * @return Returns the list with tickets in that itinerary
	 * 
	 */
	public static ArrayList<Ticket> getItineraryTicketsByID(int id) {
		ArrayList<Itinerary> companyItineraries = getAllItinerarys();
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();

		for (int i = 0; i < companyItineraries.size(); i++) {
			if (companyItineraries.get(i).getId() == id) {
				return companyItineraries.get(i).getTickets();
			}
		}
		return tickets;
	}

	// Individual validations for the registration

	/**
	 * 
	 * Checks that the city sent is within the given pattern. It must not contain
	 * any numbers or special characters.
	 * 
	 * @param city The data that will be validated
	 * 
	 * @return Returns false if it is out of the pattern and true if it is in
	 * 
	 */
	public static boolean isValidCity(String city) {
		return city.matches("[a-zA-ZÀ-ÿ0-9\\s,-]+");
	}

	/**
	 * 
	 * Checks that the time sent is within the given pattern. It must not contain
	 * any letters or special characters, except the character : in the center
	 * position.
	 * 
	 * @param time The data that will be validated
	 * 
	 * @return Returns false if it is out of the pattern and true if it is in
	 * 
	 */
	public static boolean isValidTimeFormat(String time) {
		return time.matches("^([01]\\d|2[0-3]):([0-5]\\d)$");
	}

}
