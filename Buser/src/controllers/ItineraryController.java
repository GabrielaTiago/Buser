package controllers;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import database.Database;
import models.Company;
import models.Itinerary;

public class ItineraryController {

	public static String validatesItinararyData(String origin, String destination, LocalDate date, String departureTime,
			String arrivalTime) {
		String errorMessage = "";
		LocalTime departureTimeChecker = LocalTime.parse("00:00");
		LocalTime arrivalTimeChecker = LocalTime.parse("00:01");

		if (origin == "Cidade de Origem") {
			errorMessage += "O campo 'Cidade de Origem' está vazio\n";
		} else if (!isValidCity(origin)) {
			errorMessage += "Não é uma cidade de origem válida\n";
		}

		if (destination == "Cidade de Destino") {
			errorMessage += "O campo 'Cidade de Destino' está vazio\n";
		} else if (!isValidCity(destination)) {
			errorMessage += "Não é uma cidade de destino válida\n";
		}

		if (origin.equals(destination)) {
			errorMessage += "A cidade de origem não pode ser igual a ciade de destino\n";
		}

		if (date == null) {
			errorMessage += "É necessário escolher uma data\n";
		} else {
			LocalDate today = LocalDate.now();
			long daysBetween = ChronoUnit.DAYS.between(today, date);

			if (daysBetween < 0) {
				errorMessage += "Data escolhida é anterior ao dia de hoje\n";
			}
		}

		switch (departureTime) {
		case "Horário de Partida":
			errorMessage += "O campo 'Horário de Partida' está vazio\n";
			break;
		default:
			if (!isValidTimeFormat(departureTime)) {
				errorMessage += "Horário de partida inválido. Digite no formato 'HH:mm'\n";
			} else {
				departureTimeChecker = LocalTime.parse(departureTime);
			}
			break;
		}

		switch (arrivalTime) {
		case "Horário de Chegada":
			errorMessage += "O campo 'Horário de Chegada' está vazio\n";
			break;
		default:
			if (!isValidTimeFormat(arrivalTime)) {
				errorMessage += "Horário de chegada inválido. Digite no formato 'HH:mm'\n";
			} else {
				arrivalTimeChecker = LocalTime.parse(arrivalTime);
			}
			break;
		}

		if (arrivalTimeChecker.isBefore(departureTimeChecker)) {
			errorMessage += "O horário de chegada é anterior ao horário de partida\n";
		} else if (departureTimeChecker.equals(arrivalTimeChecker)) {
			errorMessage += "O horário de chegada é igual ao horário de partida\n";
		}

		return errorMessage;
	}

	private static boolean isValidCity(String city) {
		return city.matches("[a-zA-ZÀ-ÿ0-9\\s,-]+");
	}

	private static boolean isValidTimeFormat(String time) {
		return time.matches("^([01]\\d|2[0-3]):([0-5]\\d)$");
	}

	public static void createItinerary(String origin, String destination, LocalDate date, String departureTime,
			String arrivalTime, Company company) {

		Itinerary itinerary = new Itinerary(origin, destination, date, departureTime, arrivalTime, company);

		Database.getItinaraiesData().add(itinerary);
	}

	public static ArrayList<Itinerary> getCompanyItineraries(String companyName) {
		ArrayList<Itinerary> companyItineraries = new ArrayList<>();

		for (int i = 0; i < Database.getItinaraiesData().size(); i++) {
			if (Database.getItinaraiesData().get(i).getCompany().getName().equals(companyName)) {
				companyItineraries.add(Database.getItinaraiesData().get(i));
			}
		}
		return companyItineraries;
	}
}
