package view;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.*;

import controllers.AuthController;
import controllers.ItineraryController;
import models.Client;
import models.Company;
import models.Itinerary;

public class HomeScreen implements ActionListener {

	private static JFrame window = new JFrame("Buser");
	private static JTextField originSearchField = new JTextField();
	private static JTextField destinationSearchField = new JTextField();
	private static JTextField dateSearchField = new JTextField();
	private static JButton searchButton = new JButton();
	private static JButton dashboardButton = new JButton();
	private static Boolean companyIsLoggedIn;
	private static WrapperContainer allItinerariesContainer;
	private static Company company;
	private static Client client;

	public HomeScreen() {
		ArrayList<Itinerary> itineraries = ItineraryController.getAllItinerarys();
		companyIsLoggedIn = AuthController.checkCompanyLogin();

		JPanel container = new JPanel(new BorderLayout());
		container.setBorder(BorderFactory.createEmptyBorder(15, 50, 15, 50));
		container.setBackground(new Color(255, 255, 255));

		JPanel listContainer = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 0, 8, 0);
		listContainer.setBackground(new Color(250, 250, 250));

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JPanel searchContainer = new JPanel(new GridLayout(1, 4, 10, 0));
		searchContainer.setBackground(new Color(250, 250, 250));

		searchContainer.add(textField(originSearchField, "Digite a cidade de origem"));
		searchContainer.add(textField(destinationSearchField, "Digite a cidade de destino"));
		searchContainer.add(textField(dateSearchField, "Digite a data"));
		searchContainer.add(button(searchButton, "Buscar"));

		listContainer.add(searchContainer, gbc);
		allItinerariesContainer = new WrapperContainer();
		populateItineraries(itineraries);
		gbc.gridy = 1;
		listContainer.add(allItinerariesContainer, gbc);

		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(30, 10, 10, 10);
		listContainer.add(goBack(dashboardButton, "Voltar ao meu dashboard"), gbc);

		container.add(listContainer);

		window.setContentPane(container);
		window.setSize(800, 600);
		window.getContentPane().setBackground(new Color(250, 250, 250));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.requestFocusInWindow();

		searchButton.addActionListener(this);
		dashboardButton.addActionListener(this);
	}

	private JTextField textField(JTextField textField, String placeholder) {
		textField.setOpaque(false);
		textField.setPreferredSize(new Dimension(textField.getPreferredSize().width, 40));
		textField.setForeground(new Color(117, 117, 138));
		textField.setText(placeholder);

		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent event) {
				if (textField.getText().equals(placeholder)) {
					textField.setText("");
					textField.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent event) {
				if (textField.getText().isEmpty()) {
					textField.setForeground(new Color(117, 117, 138));
					textField.setText(placeholder);
				}
			}
		});

		return textField;
	}

	private JButton button(JButton button, String text) {
		button.setText(text);
		button.setFocusPainted(false);
		button.setOpaque(true);
		button.setBackground(new Color(241, 16, 117));
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setPreferredSize(new Dimension(50, 30));
		button.setFont(button.getFont().deriveFont(14f));
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
				JPanel itineraryContainer = itinerary(it.getId(), it.getCompany().getName(), it.getOrigin(),
						it.getDestination(), it.getDate(), it.getDepartureDate(), it.getArrivalDate());
				allItinerariesContainer.addComponents(itineraryContainer);
			}
		}
	}

	private JPanel itinerary(int id, String company, String origin, String destination, LocalDate date,
			String departureTime, String arrivalTime) {
		JPanel itineraryContainer = new JPanel(new GridLayout(1, 3, 10, 0));
		itineraryContainer.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		JPanel locationContainer = new JPanel(new GridLayout(3, 1, 0, 5));
		locationContainer.setBackground(null);
		locationContainer.add(new JLabel("Empresa: " + company));
		locationContainer.add(new JLabel("Cidade de Origem: " + origin));
		locationContainer.add(new JLabel("Cidade de Destino: " + destination));

		JPanel dateContainer = new JPanel(new GridLayout(3, 1, 0, 5));
		dateContainer.setBackground(null);
		dateContainer.add(new JLabel("Data da viagem: " + date));
		dateContainer.add(new JLabel("Horário de Partida: " + departureTime));
		dateContainer.add(new JLabel("Horário de Chegada: " + arrivalTime));

		JPanel actionContainer = new JPanel(new GridLayout(1, 1, 0, 0));
		actionContainer.setBackground(null);
		actionContainer.setPreferredSize(new Dimension(60, actionContainer.getPreferredSize().height));
		actionContainer.add(selectItinerary(id));

		itineraryContainer.add(locationContainer);
		itineraryContainer.add(dateContainer);
		itineraryContainer.add(actionContainer);

		return itineraryContainer;
	}

	public JButton selectItinerary(int id) {
		JButton btn = new JButton();
		JButton sla = button(btn, "Selecionar");

		sla.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// [TO DO] -> go to ticket screen
				JOptionPane.showMessageDialog(window, "Clicou! id:" + id);
			}
		});

		return sla;
	}

	public void actionPerformed(ActionEvent event) {
		Object src = event.getSource();

		if (src == searchButton) {
			String origin = originSearchField.getText();
			String destination = destinationSearchField.getText();
			String date = dateSearchField.getText();

			if (origin != null && !origin.isEmpty() && !origin.equals("Digite a cidade de origem")) {
				ArrayList<Itinerary> itineraries = ItineraryController.getItinerariesByOrigin(origin);
				populateItineraries(itineraries);
			} else if (destination != null && !destination.isEmpty()
					&& !destination.equals("Digite a cidade de destino")) {
				ArrayList<Itinerary> itineraries = ItineraryController.getItinerariesByDestination(destination);
				populateItineraries(itineraries);
			} else if (date != null && !date.isEmpty() && !date.equals("Digite a data")) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate searchDate = LocalDate.parse(date, formatter);
				ArrayList<Itinerary> itineraries = ItineraryController.getItinerariesByDate(searchDate);
				populateItineraries(itineraries);
			}
		}

		if (src == dashboardButton) {
			if (companyIsLoggedIn) {
				company = AuthController.getCompanyLoggedIn();
				new CompanyScreen(company);
				HomeScreen.window.dispose();
			} else {
				// [TO DO] -> client dashboardPage
			}
		}
	}
}
