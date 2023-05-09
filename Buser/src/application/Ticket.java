package application;

public class Ticket {
	private float price;			
	private String seatType;	
	private String seatNumber;		
	public Itinerary itinerary;

	public Ticket(float price, String seatType, String seatNumber, Itinerary itinerary) {
		this.setPrice(price);
		this.setSeatType(seatType);
		this.setSeatNumber(seatNumber);
		this.setItinerary(itinerary);
	}

	public String toString() {
		String saida;
		saida = "Preço: " + this.getPrice();
		saida = "Tipo de poltrona: " + saida + this.getSeatType();
		saida = "Número da poltrona: " + saida + this.getSeatNumber();
		saida = saida + this.getItinerary().toString();
		return saida;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float value) {
		this.price = value;
	}
	
	public String getSeatType() {
		return seatType;
	}
	
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	
	public String getSeatNumber() {
		return seatNumber;
	}
	
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	
	public Itinerary getItinerary() {
		return itinerary;
	}
	
	public void setItinerary(Itinerary itinerary) {
		this.itinerary = itinerary;
	}
}
