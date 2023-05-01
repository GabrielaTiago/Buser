package application;

import java.util.*;

public class App {

	public static void main(String[] args) {
		ArrayList<Itinerary> itinerarys = new ArrayList<>();
		
		Itinerary i1 = createItinerary("Anápolis", "Brasília", "01/05/2023", "10:00", "14:00");
		Itinerary i2 = createItinerary("Anápolis", "Goiânia", "01/05/2023", "08:00", "09:00");
		Itinerary i3 = createItinerary("Anápolis", "Corumbá", "01/05/2023", "09:00", "10:10");
		
		itinerarys.add(i1);
		itinerarys.add(i2);
		itinerarys.add(i3);
	}
	
	public static Itinerary createItinerary(String origin, String destination, String day, String departureDate, String arrivalDate) {	
		Itinerary itinerary = new Itinerary(origin, destination, day, departureDate, arrivalDate);
		
		System.out.println("Itinerário criado com sucesso!\n");	
		displayItineraryData(itinerary);
		
		return itinerary;
	}
	
	public static void displayItineraryData(Itinerary itinerary) {
		System.out.println("Id: " + itinerary.getId());
		System.out.println("Cidade de origem: " + itinerary.getOrigin());
		System.out.println("Cidade de destino: " + itinerary.getDestination());
		System.out.println("Dia da viagem: " +  itinerary.getDay());
		System.out.println("Horário de partida: " + itinerary.getDepartureDate());
		System.out.println("Horário de chegada: " + itinerary.getArrivalDate() + "\n");
	}
	
}
