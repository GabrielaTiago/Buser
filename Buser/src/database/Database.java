package database;

import java.time.LocalDate;
import java.util.ArrayList;

import models.*;
import models.Client.GratuityType;

/**
 * Database simulation, with mock data
 * 
 * @author Gabriela Tiago
 * @since 2023
 * @version 1.2
 * 
 */
public class Database {
	private static final ArrayList<Itinerary> itineraries = new ArrayList<>();
	private static final ArrayList<Ticket> tickets = new ArrayList<>();
	private static final ArrayList<Client> clients = new ArrayList<>();
	private static final ArrayList<Company> companies = new ArrayList<>();

	// Gets the itineraries data
	public static ArrayList<Itinerary> getItinaraiesData() {
		return itineraries;
	}

	// Gets the tickets data
	public static ArrayList<Ticket> getTicketsData() {
		return tickets;
	}

	// Gets the clients data
	public static ArrayList<Client> getClientData() {
		return clients;
	}

	// Gets the companies data
	public static ArrayList<Company> getCompanyData() {
		return companies;
	}

	public static void teste() {
		LocalDate d1 = LocalDate.now();
		Company company = new Company("Araguarina", "araguarina@hotmail.com", "Teste123$", "6233445566",
				"Avenida dos Bobos, n 0 - Lugar Nenhum - LN", "46922732000130", "Araguarina LTDA");
		companies.add(company);
		Client client = new Client("Gabriela", "gabriela@gmail.com", "Teste123$", "62988776655",
				"Avenida Brasil, n 765 - Algum Lugar AG", "50191137006", GratuityType.noGratuity, tickets);
		clients.add(client);
		Itinerary i1 = new Itinerary("Anápolis", "Brasília", d1, "10:00", "14:00", company);
		Itinerary i2 = new Itinerary("Anápolis", "Goiânia", d1, "08:00", "09:00", company);
		Itinerary i3 = new Itinerary("Anápolis", "Corumbá", d1, "09:00", "10:10", company);
		Itinerary i4 = new Itinerary("Goiania", "Brasília", d1, "10:00", "14:00", company);
		Itinerary i5 = new Itinerary("Caldas Novas", "Goiânia", d1, "08:00", "09:00", company);
		Itinerary i6 = new Itinerary("Goiânia", "Corumbá", d1, "09:00", "10:10", company);
		company.getItineraries().add(i1);
		company.getItineraries().add(i2);
		company.getItineraries().add(i3);
		company.getItineraries().add(i4);
		itineraries.add(i1);
		itineraries.add(i2);
		itineraries.add(i3);
		itineraries.add(i4);
//		itineraries.add(i5);
//		itineraries.add(i6);
	}
}
