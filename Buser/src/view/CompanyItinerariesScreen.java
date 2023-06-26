package view;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;

import controllers.ItineraryController;
import models.Company;
import models.Itinerary;

public class CompanyItinerariesScreen implements ActionListener {

	private static JFrame window = new JFrame("Buser");
	private static JLabel title = new JLabel("Meus Itinerários Cadastrados");
	private static JButton goBackButton = new JButton();
	private static Company company;

	public CompanyItinerariesScreen(Company company) {
		int gbcLocal = 0;
		CompanyItinerariesScreen.company = company;
		ArrayList<Itinerary> companyItineraries = ItineraryController.getCompanyItineraries(company.getName());

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

		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;

		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		for (int i = 0; i < companyItineraries.size(); i++) {
			if (companyItineraries.isEmpty()) {
				gbcLocal = 1;
				JPanel emptyContainer = new JPanel();
				emptyContainer.setBackground(null);
				emptyContainer.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
				JLabel label = new JLabel("Ainda não possui itinerários cadastrados no momento :/");
				label.setForeground(new Color(117, 117, 138));
				emptyContainer.add(label);

				listContainer.add(emptyContainer, gbc);
				return;
			}
			Itinerary it = companyItineraries.get(i);
			JPanel itineraryContainer = itinerary(it.getId(), it.getOrigin(), it.getDestination(), it.getDate(),
					it.getDepartureDate(), it.getArrivalDate());
			gbcLocal = i + 1;
			gbc.gridy = gbcLocal;
			listContainer.add(itineraryContainer, gbc);
		}

		gbc.gridy = gbcLocal + 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		listContainer.add(goBack(goBackButton, "Voltar"), gbc);

		container.add(listContainer);

		window.add(container);
		window.setSize(800, 600);
		window.getContentPane().setBackground(new Color(250, 250, 250));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.requestFocusInWindow();

		goBackButton.addActionListener(this);
	}

	public JPanel itinerary(int id, String origin, String destination, LocalDate date, String departureTime,
			String arrivalTime) {
		JPanel itineraryContainer = new JPanel(new GridLayout(1, 3, 10, 0));
		itineraryContainer.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		JPanel locationContainer = new JPanel(new GridLayout(2, 1, 0, 5));
		locationContainer.setBackground(null);
		locationContainer.add(new JLabel("Cidade de Origem: " + origin));
		locationContainer.add(new JLabel("Cidade de Destino: " + destination));

		JPanel dateContainer = new JPanel(new GridLayout(3, 1, 0, 5));
		dateContainer.setBackground(null);
		dateContainer.add(new JLabel("Data da viagem: " + date));
		dateContainer.add(new JLabel("Horário de Partida: " + departureTime));
		dateContainer.add(new JLabel("Horário de Chegada: " + arrivalTime));

		JPanel actionContainer = new JPanel(new GridLayout(2, 1, 0, 10));
		actionContainer.setBackground(null);
		actionContainer.setPreferredSize(new Dimension(60, actionContainer.getPreferredSize().height));
		actionContainer.add(editItinerary(id));
		actionContainer.add(deleteItinerary(id));

		itineraryContainer.add(locationContainer);
		itineraryContainer.add(dateContainer);
		itineraryContainer.add(actionContainer);

		return itineraryContainer;
	}

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

		return button;
	}

	public JButton deleteItinerary(int id) {
		JButton button = new JButton("Excluir");
		button.setFocusPainted(false);
		button.setOpaque(true);
		button.setBackground(new Color(239, 23, 23));
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setPreferredSize(new Dimension(button.getPreferredSize().width, 30));
		button.setFont(button.getFont().deriveFont(12f));
		button.setForeground(Color.WHITE);

		JFrame frame = new JFrame("Confirm Delete Itinerary");
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
				}
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

	public void actionPerformed(ActionEvent event) {
		Object src = event.getSource();

		if (src == goBackButton) {
			new CompanyScreen(company);
			CompanyItinerariesScreen.window.dispose();
		}
	}
}
