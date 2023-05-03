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
	
	public void infoAtributes() {
		System.out.println("Empresa: " + this.getName());
		System.out.println("CNPJ: " + this.getCNPJ());
		System.out.println("Número de itinerários: " + this.itineraries.size());
	}

}
