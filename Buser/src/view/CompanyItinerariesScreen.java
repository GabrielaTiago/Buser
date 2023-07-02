package view;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;

import controllers.ItineraryController;
import models.Company;
import models.Itinerary;

/**
 * Screen that shows the list of itineraries associated with the logged in
 * company
 * 
 * @author Gabriela Tiago, Gabriel Fernando
 * @since 2023
 * @version 1.0
 */
public class CompanyItinerariesScreen implements ActionListener {

	private static JFrame window = new JFrame("Buser");
	private static JLabel title = new JLabel("Meus Itinerários Cadastrados");
	private static JButton goBackButton = new JButton();
	private static WrapperContainer allItinerariesContainer;
	private static ArrayList<Itinerary> companyItineraries;
	private static Company company;

	/**
	 * Adds components to the screen
	 * 
	 * @param company Logged in company
	 */
	public CompanyItinerariesScreen(Company company) {
		CompanyItinerariesScreen.company = company;
		companyItineraries = ItineraryController.getCompanyItineraries(company.getName());

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
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 1.0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		allItinerariesContainer = new WrapperContainer();
		populateItineraries(companyItineraries);
		listContainer.add(allItinerariesContainer, gbc);

		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		listContainer.add(goBack(goBackButton, "Voltar"), gbc);

		container.add(listContainer);

		window.setContentPane(container);
		window.setSize(800, 700);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.requestFocusInWindow();

		goBackButton.addActionListener(this);
	}

	/**
	 * Method that adds the data from the itinerary list to the screen
	 * 
	 * @param itineraries An array of routes
	 */
	private void populateItineraries(ArrayList<Itinerary> itineraries) {
		allItinerariesContainer.removeAll();
		allItinerariesContainer.revalidate();
		allItinerariesContainer.repaint();
		if (itineraries.isEmpty()) {
			JPanel emptyContainer = new JPanel();
			emptyContainer.setBackground(null);
			emptyContainer.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
			JLabel label = new JLabel("Ainda não possui itinerários cadastrados no momento :/");
			label.setForeground(new Color(117, 117, 138));
			emptyContainer.add(label);

			allItinerariesContainer.addComponents(emptyContainer);
		} else {
			for (int i = 0; i < itineraries.size(); i++) {
				Itinerary it = itineraries.get(i);
				JPanel itineraryContainer = itinerary(it.getId(), it.getOrigin(), it.getDestination(), it.getDate(),
						it.getDepartureDate(), it.getArrivalDate());
				allItinerariesContainer.addComponents(itineraryContainer);
			}
		}
	}

	/**
	 * Method that creates a itinerary container. It shows origin and destination
	 * city, date, departure and arrival time information. It has buttons for
	 * editing or deleting this component.
	 * 
	 * @param id            Itinerary id
	 * @param origin        Itinerary city of origin
	 * @param destination   Itinerary city of destination
	 * @param date          Itinerary date
	 * @param departureTime Itinerary departure time
	 * @param arrivalTime   Itinerary arrival time
	 * 
	 * @return JPanel component with the information for a route, along with the
	 *         edit and delete buttons
	 */
	public JPanel itinerary(int id, String origin, String destination, LocalDate date, String departureTime,
			String arrivalTime) {
		JPanel itineraryContainer = new JPanel(new GridLayout(1, 3, 10, 0));
		itineraryContainer.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		JPanel locationContainer = new JPanel(new GridLayout(2, 1, 5, 5));
		locationContainer.setBackground(null);
		locationContainer.add(new JLabel("Cidade de Origem: " + origin));
		locationContainer.add(new JLabel("Cidade de Destino: " + destination));

		JPanel dateContainer = new JPanel(new GridLayout(3, 1, 5, 5));
		dateContainer.setBackground(null);
		dateContainer.add(new JLabel("Data da viagem: " + date));
		dateContainer.add(new JLabel("Horário de Partida: " + departureTime));
		dateContainer.add(new JLabel("Horário de Chegada: " + arrivalTime));

		JPanel actionContainer = new JPanel(new GridLayout(3, 1, 8, 8));
		actionContainer.setBackground(null);
		actionContainer.add(editItinerary(id));
		actionContainer.add(deleteItinerary(id));
		actionContainer.add(ticketsButton(id));

		itineraryContainer.add(locationContainer);
		itineraryContainer.add(dateContainer);
		itineraryContainer.add(actionContainer);

		return itineraryContainer;
	}

	/**
	 * Creates and returns a JButton configured for editing an itinerary. Clicking
	 * on it takes you to the edit screen
	 * 
	 * @param id Index of the itinerary to be edited
	 * 
	 * @return Button configured for editing
	 */
	public JButton editItinerary(int id) {
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
				new EditItineraryScreen(company, id);
				CompanyItinerariesScreen.window.dispose();
			}
		});

		return button;
	}

	/**
	 * Creates and returns a JButton configured for deletion of an itinerary.
	 * Clicking deletes the referring itinerary
	 * 
	 * @param id Index of the itinerary to be deleted
	 * 
	 * @return Button configured for editing
	 */
	public JButton deleteItinerary(int id) {
		JButton button = new JButton("Excluir");
		button.setFocusPainted(false);
		button.setOpaque(true);
		button.setBackground(new Color(239, 23, 23));
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setPreferredSize(new Dimension(button.getPreferredSize().width, 30));
		button.setFont(button.getFont().deriveFont(12f));
		button.setForeground(Color.WHITE);

		JFrame frame = new JFrame("Deletar Itinerário");
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
				int result = JOptionPane.showConfirmDialog(frame, "Tem certeza que quer excluir este itinerário?",
						"Excluir itinerário", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					ItineraryController.deleteItinerary(id);
					CompanyItinerariesScreen.window.dispose();
					new CompanyItinerariesScreen(company);
				}
			}
		});

		return button;
	}

	/**
	 * Method that creates a button that takes to the itinerary tickets list screen
	 * 
	 * @param id Index of the itinerary
	 * 
	 * @return Button to go to the ticket list screen
	 */
	private JButton ticketsButton(int id) {
		JButton ticketsButton = new JButton("Passagens");
		ticketsButton.setFocusPainted(false);
		ticketsButton.setOpaque(true);
		ticketsButton.setBackground(new Color(241, 16, 117));
		ticketsButton.setBorder(BorderFactory.createEmptyBorder());
		ticketsButton.setPreferredSize(new Dimension(ticketsButton.getPreferredSize().width, 30));
		ticketsButton.setFont(ticketsButton.getFont().deriveFont(14f));
		ticketsButton.setForeground(Color.WHITE);

		ticketsButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				ticketsButton.setBackground(new Color(242, 105, 149));
				ticketsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				ticketsButton.setBackground(new Color(241, 16, 117));
			}
		});
		ticketsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CompanyItinerariesScreen.window.dispose();
				new TicketsScreen(company, id);
			}
		});
		return ticketsButton;
	}

	/**
	 * Custom style settings and events for JButton component. Add underlining the
	 * button when the mouse cursor passes over it, and returning to normal when the
	 * mouse leaves.
	 * 
	 * @param linkButton Button to be configured
	 * @param text       Text to be displayed in the button
	 * 
	 * @return The configured component
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
	 * Handles the screen action events
	 * 
	 * @param event Action Event
	 */
	public void actionPerformed(ActionEvent event) {
		Object src = event.getSource();

		// Returns to the company screen
		if (src == goBackButton) {
			CompanyItinerariesScreen.window.dispose();
			new CompanyScreen(company);
		}
	}
}
