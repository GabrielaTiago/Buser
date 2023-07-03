package models;

import java.util.ArrayList;

/**
 * Client class, inheriting characteristics of the parent class User
 * 
 * @author Gabriela Tiago
 * @since 2023
 * @version 1.1
 * 
 */
public class Company extends User {
	private String cnpj;
	private String corporateName;
	private ArrayList<Itinerary> itineraries;

	/**
	 * Class constructor for instantiating a new Company
	 * 
	 * @param name          The company's name
	 * @param email         The client's email
	 * @param password      The client's password
	 * @param phone         The client's phone
	 * @param address       The client's address
	 * @param cnpj          The client's cnpj document
	 * @param corporateName The client's corporate name
	 * @param itineraries   The company's itineraries registered
	 */
	public Company(String name, String email, String password, String phone, String address, String cnpj,
			String corporateName) {

		super(name, email, password, phone, address);
		this.setCNPJ(cnpj);
		this.setCorporateName(corporateName);
		itineraries = new ArrayList<Itinerary>();
		this.setItineraries(itineraries);
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

	public ArrayList<Itinerary> getItineraries() {
		return itineraries;
	}

	public void setItineraries(ArrayList<Itinerary> itineraries) {
		this.itineraries = itineraries;
	}

}
