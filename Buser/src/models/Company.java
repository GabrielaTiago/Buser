package application;

import java.util.ArrayList;

public class Company extends User{
	private String cnpj;
	private ArrayList<Itinerary> itineraries;

	public Company(String name, String phone, String email, String address, String cnpj,
			ArrayList<Itinerary> itineraries) {
		super(name, phone, email, address);
		this.cnpj = cnpj;
		this.itineraries = itineraries;
	}

	public String getCNPJ() {
		return cnpj;
	}
	
	public void setCNPJ(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public String toString() {
		String saida = super.toString();
		saida = saida + "\nDocumento (CNPJ): 	  " + this.getCNPJ();
		saida = saida + "\nNúmero de itinerários: " + this.itineraries.size();
		
		return saida;
	}

}
