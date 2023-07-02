package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import controllers.AuthController;
import controllers.ItineraryController;
import controllers.TicketController;
import models.Company;
import models.Ticket;

/**
 * The TicketsScreen class is responsible for the screen that lists all the
 * tickets associated with an itinerary. It allows viewing the attributes of
 * each ticket and also displays buttons to edit them.
 * 
 * @author Gabriel Fernando
 */
public class TicketsScreen implements ActionListener {

	private static JFrame window = new JFrame("Buser");
	private static JLabel title = new JLabel("Passagens Cadastradas");
	private static JButton goBackButton = new JButton();
	private static JButton ticketEditionButton = new JButton();
	private static ArrayList<Ticket> itineraryTickets = new ArrayList<Ticket>();
	private static WrapperContainer allTicketsContainer;
	private static Company company;
	private static int itineraryId;

	/**
	 * Constructor of the TicketsScreen class that adds a container with the
	 * information about the respective ticket and the corresponding buttons to edit
	 * and delete a ticket.
	 * 
	 * @param company the company logged in
	 * @param id      the id of the itinerary
	 * @see #actionPerformed(ActionEvent)
	 * @see #button(JButton, String)
	 * @see #goBack(JButton, String)
	 * @see #populateTickets(ArrayList)
	 * @see #ticket(int, float, int, String)
	 */
	public TicketsScreen(Company company, int id) {
		TicketsScreen.company = company;

		itineraryTickets = ItineraryController.getItineraryTicketsByID(id);

		itineraryId = id;

		title.setFont(new Font("Serif", Font.BOLD, 36));

		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBorder(BorderFactory.createEmptyBorder(45, 45, 45, 45));

		JPanel listContainer = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 25, 8, 25);
		listContainer.setBackground(new Color(250, 250, 250));

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		listContainer.add(title, gbc);

		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		allTicketsContainer = new WrapperContainer();
		populateTickets(itineraryTickets);
		listContainer.add(allTicketsContainer, gbc);

		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		listContainer.add(button(ticketEditionButton, "Criar Passagem"), gbc);
		gbc.gridy = 3;
		listContainer.add(goBack(goBackButton, "Voltar"), gbc);

		container.add(listContainer);
		JScrollPane listPane = new JScrollPane(container);

		ticketEditionButton.addActionListener(this);
		window.setContentPane(listPane);
		window.setSize(800, 600);
		window.getContentPane().setBackground(new Color(250, 250, 250));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.requestFocusInWindow();

		goBackButton.addActionListener(this);
	}

	/**
	 * Populates the allTicketsContainer with the provided list of tickets. If the
	 * list of tickets is empty, the container will display a page indicating that
	 * there are no registered tickets and displays a button to create tickets.
	 * 
	 * @param tickets the list of tickets to be displayed
	 */
	private void populateTickets(ArrayList<Ticket> tickets) {
		allTicketsContainer.removeAll();
		allTicketsContainer.revalidate();
		allTicketsContainer.repaint();
		if (tickets.isEmpty()) {
			JPanel emptyContainer = new JPanel();
			emptyContainer.setBackground(null);
			emptyContainer.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
			JLabel label = new JLabel("Ainda não possui passagens cadastradas no momento :/");
			label.setForeground(new Color(117, 117, 138));
			emptyContainer.add(label);

			allTicketsContainer.addComponents(emptyContainer);
		} else {
			for (int i = 0; i < tickets.size(); i++) {
				Ticket ticket = tickets.get(i);
				JPanel ticketContainer = ticket(i, ticket.getPrice(), ticket.getSeatNumber(),
						ticket.getSeatTypeString());
				allTicketsContainer.addComponents(ticketContainer);
			}
		}
	}

	/**
	 * Creates and returns a JPanel that contains buttons to edit and delete a
	 * ticket, and also displays the ticket information.
	 * 
	 * @param index      the index of the ticket
	 * @param price      the price of the ticket
	 * @param seatNumber the seat number
	 * @param seatType   the seat type
	 * @return the JPanel representing the ticket
	 */
	public JPanel ticket(int index, float price, int seatNumber, String seatType) {
		JPanel ticketConteiner = new JPanel(new GridLayout(1, 2, 0, 0));
		ticketConteiner.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		JPanel priceConteiner = new JPanel(new GridLayout(3, 1, 0, 0));
		priceConteiner.setBackground(null);
		priceConteiner.add(new JLabel("Preço: R$" + price));
		priceConteiner.add(new JLabel("Tipo de poltrona: " + seatType));
		priceConteiner.add(new JLabel("Numero da poltrona: " + seatNumber));

		JPanel ticektIndexContainer = new JPanel(new GridLayout(3, 1, 0, 5));
		ticektIndexContainer.setBackground(null);
		ticektIndexContainer.add(new JLabel("Passagem " + index));

		JPanel actionContainer = new JPanel(new GridLayout(2, 1, 0, 10));
		actionContainer.setBackground(null);
		actionContainer.setPreferredSize(new Dimension(60, actionContainer.getPreferredSize().height));
		actionContainer.add(editTicket(index));
		actionContainer.add(deleteTicket(index));

		ticketConteiner.add(ticektIndexContainer);
		ticketConteiner.add(priceConteiner);
		ticketConteiner.add(actionContainer);

		return ticketConteiner;
	}

	/**
	 * Configures a button with the provided text and custom styling settings, which
	 * changes the background color of the button when the mouse cursor hovers over
	 * it, and restores the original color when the mouse moves away.
	 * 
	 * @param button the button to be configured
	 * @param text   the text to be displayed on the button
	 * @return the configured button
	 */
	private JButton button(JButton button, String text) {
		button.setText(text);
		button.setFocusPainted(false);
		button.setOpaque(true);
		button.setBackground(new Color(241, 16, 117));
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setFont(button.getFont().deriveFont(18f));
		button.setForeground(Color.WHITE);

		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(242, 105, 149));
				button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(241, 16, 117));
			}
		});

		return button;
	}

	/**
	 * Creates and returns a configured and styled JButton for editing a ticket. The
	 * method also adds an ActionListener to the button, so that when the button is
	 * clicked, the actionPerformed method informs the controller about the index of
	 * the ticket to be updated, closes the ticket window, and opens a new window
	 * for ticket editing.
	 * 
	 * @param index the index of the ticket to be edited
	 * @return the configured JButton for editing
	 */
	public JButton editTicket(int index) {
		JButton button = new JButton("Editar");
		button.setFocusPainted(false);
		button.setOpaque(true);
		button.setBackground(new Color(10, 71, 157));
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setPreferredSize(new Dimension(button.getPreferredSize().width, 30));
		button.setFont(button.getFont().deriveFont(12f));
		button.setForeground(Color.WHITE);

		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(69, 117, 183));
				button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(10, 71, 157));
			}
		});
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TicketsScreen.window.dispose();
				TicketController.setUpdatingTicketIndex(index);
				new EditTicketScreen(1, itineraryId, company);
			}
		});

		return button;
	}

	/**
	 * Creates and returns a configured and styled JButton for deleting a ticket.
	 * The method also adds an ActionListener to the button, so that when the button
	 * is clicked, the actionPerformed method closes the ticket window and opens it
	 * again to update the ticket list.
	 * 
	 * @param index the index of the ticket to be deleted
	 * @return the configured JButton for deletion
	 */
	public JButton deleteTicket(int index) {
		JButton button = new JButton("Excluir");
		button.setFocusPainted(false);
		button.setOpaque(true);
		button.setBackground(new Color(239, 23, 23));
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setPreferredSize(new Dimension(button.getPreferredSize().width, 30));
		button.setFont(button.getFont().deriveFont(12f));
		button.setForeground(Color.WHITE);

		JFrame frame = new JFrame("Deletar Passagem");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(188, 75, 75));
				button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(239, 23, 23));
			}
		});
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(frame, "Tem certeza que quer excluir essa passagem?",
						"Excluir Passagem", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					TicketController.deleteTicket(index, itineraryId);
					TicketsScreen.window.dispose();
					new TicketsScreen(company, itineraryId);
				}
			}
		});

		return button;
	}

	/**
	 * Configures a back button with the provided text and custom styling,
	 * underlining the button when the mouse cursor hovers over it and returning to
	 * normal when the mouse moves away.
	 * 
	 * @param goBackButton the back button to be configured
	 * @param text         the text to be displayed on the button
	 * @return the configured button
	 */
	private JButton goBack(JButton goBackButton, String text) {
		goBackButton.setText(text);
		goBackButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		goBackButton.setBackground(null);
		goBackButton.setBorder(null);

		goBackButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				goBackButton.setText("<html><u>" + text + "</u></html>");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				goBackButton.setText(text);
			}
		});

		return goBackButton;
	}

	/**
	 * This method identifies whether the "back" button or the "Edit" button in the
	 * interface have been clicked. If the "back" button is clicked, the program
	 * returns to the company itineraries screen. If the "Edit" button is clicked,
	 * it opens a screen for the associated tickets of the respective itinerary.
	 * 
	 * @param event the event captured by an ActionListener
	 */
	public void actionPerformed(ActionEvent event) {
		Object src = event.getSource();

		if (src == goBackButton) {
			TicketsScreen.window.dispose();
			new CompanyItinerariesScreen(AuthController.getCompanyLoggedIn());
		}
		if (src == ticketEditionButton) {
			TicketsScreen.window.dispose();
			new EditTicketScreen(0, itineraryId, company);
		}
	}
}