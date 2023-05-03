package application;

import java.util.*;

public class App {

	public static void main(String[] args) {
		ArrayList<Itinerary> itineraries = new ArrayList<>();
		ArrayList<Ticket> tickets = new ArrayList<>();
		
		Client client = new Client("Joaquim", "(62) 98877-6655", "joaquim@hotmail.com", "Avenida Brasil, 1257, Anápolis - GO", "060.103.940-82", tickets);
		Company company = new Company("XYZ", "(62) 92233-4455", "company@xyz.com", "Avenida Universitária, 3080, Anápolis - GO", "50.140.496/0001-77", itineraries);
		
		Itinerary i1 = createItinerary("Anápolis", "Brasília", "01/05/2023", "10:00", "14:00", company);
		Itinerary i2 = createItinerary("Anápolis", "Goiânia", "01/05/2023", "08:00", "09:00", company);
		Itinerary i3 = createItinerary("Anápolis", "Corumbá", "01/05/2023", "09:00", "10:10", company);
		Itinerary i4 = createItinerary("São Paulo", "Rio de Janeiro", "02/05/2023", "04:00", "08:50", company);
		
		itineraries.add(i1);
		itineraries.add(i2);
		itineraries.add(i3);
		itineraries.add(i4);
		
		getAllItinerarys(itineraries);
		updateItinerary(2, itineraries, "Anápolis", "Goianápolis", "02/05/2023", "06:00", "06:40");
		deleteItinerary(1, itineraries);
		
		getItinerariesByDay("02/05/2023", itineraries);
		getItinerariesByOrigin("São Paulo", itineraries);
		getItinerariesByDestination("Corumbá", itineraries);
		
		Ticket t1 = new Ticket(50, "convencional", "6J",i1);
		Ticket t2 = new Ticket(225, "leito", "10C", i4);
		
		client.addTicket(t1);
		client.addTicket(t2);
		
		client.listTickets();
		
		client.updateTicket(t2, 0);
		
		client.removeTicket(0);
	}
	
	public static Itinerary createItinerary(String origin, String destination, String day, String departureDate, String arrivalDate, Company company) {	
		Itinerary itinerary = new Itinerary(origin, destination, day, departureDate, arrivalDate, company);
		
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
	
	public static void getItinerariesByDay(String day,  ArrayList<Itinerary> itineraries) {
		System.out.println("------------------------------------------------------------");
		System.out.println("Lista de todos os itinerários disponíveis no dia "+ day);
		System.out.println("------------------------------------------------------------\n");
		
		for(int i = 0; i < itineraries.size(); i++) {
			if(itineraries.get(i).getDay().equals(day)) {
				displayItineraryData(itineraries.get(i));
				System.out.println("*\n");
			}
		}
	}
	
	public static void getItinerariesByOrigin(String origin,  ArrayList<Itinerary> itineraries) {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("Lista de todos os itinerários disponíveis saindo de "+ origin);
		System.out.println("-----------------------------------------------------------------\n");
		
		for(int i = 0; i < itineraries.size(); i++) {
			if(itineraries.get(i).getOrigin().equals(origin)) {
				displayItineraryData(itineraries.get(i));
				System.out.println("*\n");
			}
		}
	}
	
	public static void getItinerariesByDestination(String destination,  ArrayList<Itinerary> itineraries) {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("Lista de todos os itinerários disponíveis indo para "+ destination);
		System.out.println("-----------------------------------------------------------------\n");
		
		for(int i = 0; i < itineraries.size(); i++) {
			if(itineraries.get(i).getDestination().equals(destination)) {
				displayItineraryData(itineraries.get(i));
				System.out.println("*\n");
			}
		}
	}
	
 	public static void displayItineraryData(Itinerary itinerary) {
		System.out.println("Id: " + itinerary.getId());
		System.out.println("Empresa: " + itinerary.getCompany().getName());
		System.out.println("Cidade de origem: " + itinerary.getOrigin());
		System.out.println("Cidade de destino: " + itinerary.getDestination());
		System.out.println("Dia da viagem: " +  itinerary.getDay());
		System.out.println("Horário de partida: " + itinerary.getDepartureDate());
		System.out.println("Horário de chegada: " + itinerary.getArrivalDate() + "\n");
		
	}
	
}
