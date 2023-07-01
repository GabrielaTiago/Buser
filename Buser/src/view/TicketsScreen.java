package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import controllers.AuthController;
import controllers.ItineraryController;
import controllers.TicketController;
import models.Company;
import models.Itinerary;
import models.Ticket;

public class TicketsScreen implements ActionListener {

	private static JFrame window = new JFrame("Buser");
	private static JLabel title = new JLabel("Passagens Cadastradas");
	private static JButton goBackButton = new JButton();
	private static JButton ticketEditionButton = new JButton();
	private static ArrayList<Ticket> itineraryTickets = new ArrayList<Ticket>();
	private static AllItinerariesContainer allTicketsContainer;
	private static Company company;
	private static int itineraryId;

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
		allTicketsContainer = new AllItinerariesContainer();
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

			allTicketsContainer.addItinerary(emptyContainer);
		} else {
			for (int i = 0; i < tickets.size(); i++) {
				Ticket ticket = tickets.get(i);
				JPanel ticketContainer = ticket(i, ticket.getPrice(), ticket.getSeatNumber(), ticket.getSeatTypeString());
				allTicketsContainer.addItinerary(ticketContainer);
			}
		}
	}
	
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
			TicketsScreen.window.dispose();
			new CompanyItinerariesScreen(AuthController.getCompanyLoggedIn());
		}
		if (src == ticketEditionButton) {
			TicketsScreen.window.dispose();
			new EditTicketScreen(0, itineraryId, company);
		}
	}
}