package application;

import java.util.*;

public class Itinerary {
	private String origin;
	private String destination;
	private Date day;
	private String departureDate;
	private String arrivalDate;
	
	Itinerary(String origin, String destination, Date day, String departureDate, String arrivalDate) {
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
	
	public void setDay(Date day) {
		this.day = day;
	} 
	
	public Date getDay() {
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
