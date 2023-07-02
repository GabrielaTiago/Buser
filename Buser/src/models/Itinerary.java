package models;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Classe que representa um itinerário de uma empresa. Possui atributos de
 * cidade de origem, cidade de destino, data de partida, hora de partida, hora
 * de chegada e as passagens associadas ao itinerário
 * 
 * @author Gabriela Tiago
 */
public class Itinerary {
	private static int nextId = 1;

	private int id;
	private String origin;
	private String destination;
	private LocalDate date;
	private String departureDate;
	private String arrivalDate;
	private Company company;
	private ArrayList<Ticket> tickets;

	/**
	 * Construtor da classe Itinerary.
	 *
	 * @param origin        a origem do itinerário
	 * @param destination   o destino do itinerário
	 * @param date          a data do itinerário
	 * @param departureDate a data de partida do itinerário
	 * @param arrivalDate   a data de chegada do itinerário
	 * @param company       a empresa do itinerário
	 */
	public Itinerary(String origin, String destination, LocalDate date, String departureDate, String arrivalDate,
			Company company) {
		this.tickets = new ArrayList<Ticket>();
		this.setId();
		this.setOrigin(origin);
		this.setDestination(destination);
		this.setDate(date);
		this.setDepartureDate(departureDate);
		this.setArrivalDate(arrivalDate);
		this.setCompany(company);

	}

	public ArrayList<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(ArrayList<Ticket> tickets) {
		this.tickets = tickets;
	}

	public void setId() {
		this.id = nextId;
		nextId++;
	}

	public int getId() {
		return id;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getOrigin() {
		return origin;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDestination() {
		return destination;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Company getCompany() {
		return company;
	}
}