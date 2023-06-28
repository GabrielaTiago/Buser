package database;

import java.util.ArrayList;

import models.Client;
import models.Company;
import models.Itinerary;

public abstract class Database {
	public static Client client;
	public static Company company;
	public static final ArrayList<Itinerary> itineraries = new ArrayList<Itinerary>();
}
