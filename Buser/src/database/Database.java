package database;

import java.util.ArrayList;
import models.Itinerary;
import models.Ticket;

public class Database {
	public static final ArrayList<Itinerary> itineraries = new ArrayList<>();
	public static final ArrayList<Ticket> tickets = new ArrayList<>();

	public static ArrayList<Itinerary> getItinaraiesData() {
		return itineraries;
	}

	public static ArrayList<Itinerary> getTicketsData() {
		return itineraries;
	}
}
