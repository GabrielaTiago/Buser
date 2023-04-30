package application;

public class Itinerary {
	private String origin;
	private String destination;
	private String day;
	private String departureDate;
	private String arrivalDate;
	
	public Itinerary(String origin, String destination, String day, String departureDate, String arrivalDate) {
		this.setOrigin(origin);
		this.setDestination(destination);
		this.setDay(day);
		this.setDepartureDate(departureDate);
		this.setArrivalDate(arrivalDate);
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
}
