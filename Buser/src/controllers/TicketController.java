package controllers;

import models.Ticket.SeatType;
import models.*;

/**
 * Classe responsável por receber as informações a serem atualizadas e
 * armazenadas , informações que são recebidas das respectivas telas de passagem
 * no pacote view, nos atributos da classe Database
 * 
 * @author Gabriel Fernando
 */
public class TicketController {
	private static int updatingTicketIndex;

	/**
	 * Método responsável por criar uma nova passagem com base nos valores recebidos
	 * pela tela de criar uma passagem
	 * 
	 * @param price         preço da passagem a ser criada
	 * @param seatTypeIndex index do tipo de poltrona a ser retornada
	 * @param seatNumber    número da poltrona
	 * @param id            id do itinerário a quem a passagem deve ser adicionada
	 */
	public static void createTicket(Float price, int seatTypeIndex, int seatNumber, int id) {
		// Based on the values received from the TicketWindow class, creates
		// a new ticket and adds it to the Database ArrayList<Tickets>
		int index = id - 1;
		Company company = AuthController.getCompanyLoggedIn();
		String name = company.getName();
		SeatType s = getSeatType(seatTypeIndex);

		Ticket t = new Ticket(price, s, seatNumber);

		ItineraryController.getCompanyItineraries(name).get(index).getTickets().add(t);
	}

	/**
	 * Método responsável por atualizar os atributos de uma passagem
	 * 
	 * @param price         novo preço da passagem
	 * @param seatTypeIndex index do novo tipo de poltrona da passagem
	 * @param seatNumber    novo número da poltrona da passagem
	 * @param ticketIndex   posição da passagem a ser atualizada
	 * @param id            id do itinerário que possui a passagem
	 */
	public static void updateTicket(Float price, int seatTypeIndex, int seatNumber, int ticketIndex, int id) {
		// replace the correspondent ticket atributes in the database with the
		// values received from the TicketWindow class JComponents
		SeatType s = getSeatType(seatTypeIndex);
		int index = id - 1;
		Company company = AuthController.getCompanyLoggedIn();
		String name = company.getName();
		ItineraryController.getCompanyItineraries(name).get(index).getTickets().get(ticketIndex).setPrice(price);
		ItineraryController.getCompanyItineraries(name).get(index).getTickets().get(ticketIndex).setSeatType(s);
		ItineraryController.getCompanyItineraries(name).get(index).getTickets().get(ticketIndex)
				.setSeatNumber(seatNumber);
	}

	/**
	 * Método que remove uma passagem da lista de passagens de um ticket com base no
	 * valor fornecido da posição da passagem e no itinerário que possui a passagem
	 * 
	 * @param ticketIndex posição da passagem a ser deletada
	 * @param id          itinerário que possui a passagem
	 */
	public static void deleteTicket(int ticketIndex, int id) {
		// deletes the ticket i in arraylist of the database
		int index = id - 1;
		Company company = AuthController.getCompanyLoggedIn();
		String name = company.getName();
		ItineraryController.getCompanyItineraries(name).get(index).getTickets().remove(ticketIndex);
	}

	/**
	 * Método que valida se o preço e o número de uma poltrona são maiores que zero
	 * 
	 * @param price      o preço a ser validado
	 * @param seatNumber o numero da poltrana a ser validado
	 * @return um boolean indicando se os dados são válidos ou não
	 */
	public static boolean checkTicketData(float price, int seatNumber) {
		if ((price >= 0f) && (seatNumber > 0)) {
			return true;
		}
		return false;
	}

	/**
	 * Método que recebe um inteiro que indica qual o tipo de poltrona deve ser
	 * retornao
	 * 
	 * @param i
	 * @return seatType tipo de poltrona correspondente
	 */
	public static SeatType getSeatType(int i) {
		// based on the index received, returns the correspondent enum type
		SeatType seatType = null;

		if (i == 0) {
			seatType = SeatType.executivo;
		}
		if (i == 1) {
			seatType = SeatType.semiLeito;
		}
		if (i == 2) {
			seatType = SeatType.leito;
		}
		return seatType;
	}

	/**
	 * Método que retorna o index armazenado da passagem a ser atualizada
	 * 
	 * @return updatingTicketIndex index da passagem a ser atualizada
	 */
	public static int getUpdatingTicketIndex() {
		return updatingTicketIndex;
	}

	/**
	 * Método responsável por armazenar a posição da passagem a ser armazenada, que
	 * também informa a tela de edição
	 * 
	 * @param Index
	 */
	public static void setUpdatingTicketIndex(int Index) {
		updatingTicketIndex = Index;
	}
}
