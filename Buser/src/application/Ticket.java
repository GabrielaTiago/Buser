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
		this.setitinerary(itinerary);
	}

	public void infoAtributos() {
		System.out.println("valor: 		     R$ " + this.price);								
		System.out.println("Tipo de Poltrona:    " + this.seatType);			
		System.out.println("Numero da Poltrona:  " + this.seatNumber);										
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
	
	public Itinerary getitinerary() {
		return itinerary;
	}
	
	public void setitinerary(Itinerary itinerary) {
		this.itinerary = itinerary;
	}
}
