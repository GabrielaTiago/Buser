package models;

public class Ticket {
	private float price;		
	private SeatType seatType;
	private int seatNumber;
	
	public enum SeatType {
		executivo, semiLeito, leito
	}

	public Ticket(float price, SeatType seatType, int seatNumber) {
		this.set_Price(price, seatType);
		this.setSeatType(seatType);
		this.setSeatNumber(seatNumber);
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
	
	public int getSeatNumber() {
		return seatNumber;
	}
	
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
}