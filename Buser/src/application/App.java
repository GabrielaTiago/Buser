package application;

import java.util.*;

public class App {

	public static void main(String[] args) {
		ArrayList<Itinerary> itinerarys = new ArrayList<>();
		
		Itinerary i1 = createItinerary("Anápolis", "Brasília", "01/05/2023", "10:00", "14:00");
		Itinerary i2 = createItinerary("Anápolis", "Goiânia", "01/05/2023", "08:00", "09:00");
		Itinerary i3 = createItinerary("Anápolis", "Corumbá", "01/05/2023", "09:00", "10:10");
		Itinerary i4 = createItinerary("São Paulo", "Rio de Janeiro", "02/05/2023", "04:00", "08:50");
		
		itinerarys.add(i1);
		itinerarys.add(i2);
		itinerarys.add(i3);
		itinerarys.add(i4);
		
		getAllItinerarys(itinerarys);
		updateItinerary(2, itinerarys, "Anápolis", "Goianápolis", "02/05/2023", "06:00", "06:40");
		deleteItinerary(1, itinerarys);
		
		getItinerariesByDay("02/05/2023", itinerarys);
		getItinerariesByOrigin("São Paulo", itinerarys);
		getItinerariesByDestination("Corumbá", itinerarys);
	}
	
	public static Itinerary createItinerary(String origin, String destination, String day, String departureDate, String arrivalDate) {	
		Itinerary itinerary = new Itinerary(origin, destination, day, departureDate, arrivalDate);
		
		System.out.println("Itinerário criado com sucesso!\n");	
		displayItineraryData(itinerary);
		System.out.println("**\n");
		
		return itinerary;
	}
	
	public static void getAllItinerarys(ArrayList<Itinerary> itinerarys) {
		System.out.println("------------------------------------------");
		System.out.println("Lista de todos os itinerários disponíveis");
		System.out.println("------------------------------------------\n");
		
		for(int i = 0; i < itinerarys.size(); i++) {
			displayItineraryData(itinerarys.get(i));
			System.out.println("*\n");
		}
	}
	
	public static Itinerary getItineraryById(int id, ArrayList<Itinerary> itinerarys) {
		int i = id - 1;
		Itinerary itinerary = itinerarys.get(i);
		
		displayItineraryData(itinerary);
		
		return itinerary;
	}
	
	public static void updateItinerary(int id, ArrayList<Itinerary> itinerarys, String origin, String destination, String day, String departureDate, String arrivalDate) {
		System.out.println("--------------------------------");
		System.out.println("Itinerário que será atualizado:");
		System.out.println("--------------------------------\n");
		Itinerary itinerary = getItineraryById(id, itinerarys);
		
		itinerary.setOrigin(origin);
		itinerary.setDestination(destination);
		itinerary.setDay(day);
		itinerary.setDepartureDate(departureDate);
		itinerary.setArrivalDate(arrivalDate);
		
		System.out.println("-----------------------------------");
		System.out.println("Itinerário atualizado com sucesso!");
		System.out.println("-----------------------------------\n");
		displayItineraryData(itinerary);
		System.out.println("***\n");
	}
	
	public static void deleteItinerary(int id, ArrayList<Itinerary> itinerarys) {
		System.out.println("------------------------------");
		System.out.println("Itinerário que será deletado:");
		System.out.println("------------------------------\n");
		getItineraryById(id, itinerarys);
		
		itinerarys.remove(id-1);
		System.out.println("---------------------------------");
		System.out.println("Itinerário deletado com sucesso!");
		System.out.println("---------------------------------\n");
		System.out.println("****\n");
	}
	
	public static void getItinerariesByDay(String day,  ArrayList<Itinerary> itinerarys) {
		System.out.println("------------------------------------------------------------");
		System.out.println("Lista de todos os itinerários disponíveis no dia "+ day);
		System.out.println("------------------------------------------------------------\n");
		
		for(int i = 0; i < itinerarys.size(); i++) {
			if(itinerarys.get(i).getDay().equals(day)) {
				displayItineraryData(itinerarys.get(i));
				System.out.println("*\n");
			}
		}
	}
	
	public static void getItinerariesByOrigin(String origin,  ArrayList<Itinerary> itinerarys) {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("Lista de todos os itinerários disponíveis saindo de "+ origin);
		System.out.println("-----------------------------------------------------------------\n");
		
		for(int i = 0; i < itinerarys.size(); i++) {
			if(itinerarys.get(i).getOrigin().equals(origin)) {
				displayItineraryData(itinerarys.get(i));
				System.out.println("*\n");
			}
		}
	}
	
	public static void getItinerariesByDestination(String destination,  ArrayList<Itinerary> itinerarys) {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("Lista de todos os itinerários disponíveis indo para "+ destination);
		System.out.println("-----------------------------------------------------------------\n");
		
		for(int i = 0; i < itinerarys.size(); i++) {
			if(itinerarys.get(i).getDestination().equals(destination)) {
				displayItineraryData(itinerarys.get(i));
				System.out.println("*\n");
			}
		}
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
