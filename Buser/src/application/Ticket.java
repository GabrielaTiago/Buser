package application;

public class Ticket {
	//atributes
		private float price;			
		private String seatType;	
		private String seatNum;		
		public Itinerary itinerary;
		private Client client;

		//constructors
		public Ticket(String tpPol, String numPol, Client c, Itinerary i) {
			this.seatType = tpPol;
			this.seatNum = numPol;
			this.client = c;
			this.itinerary = i;
		}
		public Ticket(){
			//defaul constructor
		}

		//methods
		
		public void infoAtributos() {
			System.out.println("valor: 		     R$ " + this.price);								
			System.out.println("Tipo de Poltrona:    " + this.seatType);			
			System.out.println("Numero da Poltrona:  " + this.seatNum);										
		}
		

		//getters and setters
		public float getPrice() {
			return price;
		}
		public void setPrice(float v) {
			this.price = v;
		}
		public String getseatType() {
			return seatType;
		}
		public void setseatType(String seatType) {
			this.seatType = seatType;
		}
		public String getseatNum() {
			return seatNum;
		}
		public void setseatNum(String seatNum) {
			this.seatNum = seatNum;
		}
		public Itinerary getitinerary() {
			return itinerary;
		}
		public void setitinerary(Itinerary itinerary) {
			this.itinerary = itinerary;
		}
		public Client getClient() {
			return client;
		}
		public void setClient(Client client) {
			this.client = client;
		}

}
