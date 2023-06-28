package database;

import java.time.LocalDate;
import java.util.ArrayList;

import models.*;

public class Database {
	private static final ArrayList<Itinerary> itineraries = new ArrayList<>();
	private static final ArrayList<Ticket> tickets = new ArrayList<>();
	private static final ArrayList<Client> logedClient = new ArrayList<>();
	private static final ArrayList<Company> logedCompany = new ArrayList<>();

	public static ArrayList<Itinerary> getItinaraiesData() {
		return itineraries;
	}

	public static ArrayList<Ticket> getTicketsData() {
		return tickets;
	}

	public static ArrayList<Client> getClient() {
		return logedClient;
	}
	
	public static ArrayList<Company> getCompany() {
		return logedCompany;
	}
	
	public static void teste() {
		LocalDate d1 = LocalDate.now();
		Company company = new Company("Nome", "email", "telefone", "endreço", "cnpj", "razao social", itineraries);
		Itinerary i1 = new Itinerary("Anápolis", "Brasília", d1, "10:00", "14:00", company);
		Itinerary i2 = new Itinerary("Anápolis", "Goiânia", d1, "08:00", "09:00", company);
		Itinerary i3 = new Itinerary("Anápolis", "Corumbá", d1, "09:00", "10:10", company);
		Itinerary i4 = new Itinerary("Goiania", "Brasília", d1, "10:00", "14:00", company);
		Itinerary i5 = new Itinerary("Caldas Novas", "Goiânia", d1, "08:00", "09:00", company);
		Itinerary i6 = new Itinerary("Goiânia", "Corumbá", d1, "09:00", "10:10", company);
		itineraries.add(i1);
		itineraries.add(i2);
		itineraries.add(i3);
		itineraries.add(i4);
//		itineraries.add(i5);
//		itineraries.add(i6);
	}
}
