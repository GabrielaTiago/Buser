package models;

/**
 * Class that represents a ticket, which has price, seat type, and seat number
 * as attributes.
 * 
 * @author Gabriel Fernando
 *
 */
public class Ticket {
	private float price;
	private SeatType seatType;
	private int seatNumber;

	/**
	 * Enum that represents the possible types of seat to choose
	 */
	public enum SeatType {
		executivo, semiLeito, leito
	}

	/**
	 * Ticket constructor.
	 * 
	 * @param price      the price of the ticket
	 * @param seatType   the type of the seat
	 * @param seatNumber the number of the seat
	 */
	public Ticket(float price, SeatType seatType, int seatNumber) {
		this.set_Price(price, seatType);
		this.setSeatType(seatType);
		this.setSeatNumber(seatNumber);
	}

	public float getPrice() {
		return price;
	}

	/**
	 * Metodo responsavel por alterar o valor inicial da passagem conforme o tipo de
	 * assento escolhido
	 * 
	 * @param value
	 * @param seat
	 */
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

	public String getSeatTypeString() {
		String s = null;
		String seatType = this.getSeatType().toString();

		if (seatType.equals("executivo")) {
			s = "Executivo";
		}
		if (seatType.equals("semiLeito")) {
			s = "Semi-Leito";
		}
		if (seatType.equals("leito")) {
			s = "Leito";
		}
		return s;
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