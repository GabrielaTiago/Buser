package models;

import java.util.ArrayList;

public class Company extends User {	
	private String cnpj;
	private String corporateName;
	private ArrayList<Itinerary> itineraries;

	public Company(String name, String phone, String email, String address, String cnpj, String corporateName,
			ArrayList<Itinerary> itineraries) {
		super(name, phone, email, address);
		this.setCNPJ(cnpj);
		this.setCorporateName(corporateName);
		this.itineraries = itineraries;
	}

	public Company(String name, String phone, String email, String address, String cnpj, String corporateName) {
		super(name, phone, email, address);
		this.setCNPJ(cnpj);
		this.setCorporateName(corporateName);
	}

	public String getCNPJ() {
		return cnpj;
	}

	public void setCNPJ(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCorporateName() {
		return corporateName;
	}

	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}
}
