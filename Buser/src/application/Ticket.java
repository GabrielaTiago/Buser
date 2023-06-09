package application;

public class Ticket {
	private float price;		
	private SeatType seatType;
	private String seatNumber;
	public Itinerary itinerary;
	
	enum SeatType {
		executivo, semiLeito, leito
	}

	public Ticket(float price, SeatType seatType, String seatNumber, Itinerary itinerary) {
		this.set_Price(price, seatType);
		this.setSeatType(seatType);
		this.setSeatNumber(seatNumber);
		this.setItinerary(itinerary);
	}
	
	public float getPrice() {
		return price;
	}
	
	private void set_Price(float value, SeatType seat) {
		
		if (seat == SeatType.executivo) {
			this.price = value + 10;
		}
		if (seat == SeatType.semiLeito) {
			this.price = value + 15;
		}
		if (seat == SeatType.leito) {
			this.price = value + 20;
		}
	}
	
	public void setPrice(float value) {
		this.price = value;
	}
	public SeatType getSeatType() {
		return seatType;
	}
	
	public void setSeatType(SeatType seatType) {
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
	
	public String toString() {
		String saida;
		saida = "Preço: 		   R$" + this.getPrice();
		saida = "Tipo de poltrona: 	 " + saida + this.getSeatType();
		saida = "Número da poltrona: " + saida + this.getSeatNumber();
		saida = saida + this.getItinerary().toString();
		return saida;
	}
}