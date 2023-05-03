package application;

public class Itinerary {
	private static int nextId = 1;
	
	private int id;
	private String origin;
	private String destination;
	private String day;
	private String departureDate;
	private String arrivalDate;
	private Company company;
	
	public Itinerary(String origin, String destination, String day, String departureDate, String arrivalDate, Company company) {
		this.setId();
		this.setOrigin(origin);
		this.setDestination(destination);
		this.setDay(day);
		this.setDepartureDate(departureDate);
		this.setArrivalDate(arrivalDate);
		this.setCompany(company);
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
	
	public void setDay(String day) {
		this.day = day;
	} 
	
	public String getDay() {
		return day;
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
