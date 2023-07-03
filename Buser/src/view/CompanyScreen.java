package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import models.Company;

/**
 * Company dashboard screen
 * 
 * @author Gabriela Tiago
 * @since 2023
 * @version 1.0
 */

public class CompanyScreen implements ActionListener {

	private static JFrame window = new JFrame("Buser");
	private static JLabel title = new JLabel();
	private static JButton homePageButton = new JButton();
	private static JButton itinerariesButton = new JButton();
	private static JButton createItineraryButton = new JButton();
	private static Company company;

	/**
	 * Adds components to the screen
	 * 
	 * @param company Logged in company
	 */
	public CompanyScreen(Company company) {
		CompanyScreen.company = company;
		title.setFont(new Font("Serif", Font.BOLD, 36));
		title.setText("Painel de controle " + company.getName());

		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBorder(BorderFactory.createEmptyBorder(80, 80, 80, 80));

		JPanel dashboardContainer = new JPanel(new BorderLayout());
		dashboardContainer.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		dashboardContainer.setBackground(new Color(250, 250, 250));

		JPanel buttonsContainer = new JPanel(new GridLayout(1, 3, 30, 0));
		buttonsContainer.setBackground(new Color(250, 250, 250));
		buttonsContainer.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

		buttonsContainer.add(button(homePageButton, "Página Inicial"));
		buttonsContainer.add(button(itinerariesButton, "Meus Itinerários"));
		buttonsContainer.add(button(createItineraryButton, "Criar Itinerário"));

		dashboardContainer.add(title, BorderLayout.NORTH);
		dashboardContainer.add(buttonsContainer, BorderLayout.CENTER);

		container.add(dashboardContainer, BorderLayout.CENTER);

		window.setContentPane(container);
		window.setSize(1250, 700);
		window.getContentPane().setBackground(new Color(250, 250, 250));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		homePageButton.addActionListener(this);
		itinerariesButton.addActionListener(this);
		createItineraryButton.addActionListener(this);
	}

	/**
	 * Custom style settings and events for JButton component. Changing the
	 * background and text color, set height and font size. Adding events when the
	 * mouse cursor passes over and restoring when the mouse exits.
	 * 
	 * @param button Button to be configured
	 * @param text   Text to be displayed in the button
	 * 
	 * @return The configured component
	 */
	public JButton button(JButton button, String text) {
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
	 * Handles the screen action events
	 * 
	 * @param event Action Event
	 */
	public void actionPerformed(ActionEvent event) {
		Object src = event.getSource();

		// Clicking goes to the main screen of all itinerary listings
		if (src == homePageButton) {
			CompanyScreen.window.dispose();
			new HomeScreen();
		}

		// Clicking goes to the company's own itinerary listings screen
		if (src == itinerariesButton) {
			CompanyScreen.window.dispose();
			new CompanyItinerariesScreen(company);
		}

		// Clicking goes to the screen to create a new itinerary
		if (src == createItineraryButton) {
			CompanyScreen.window.dispose();
			new ItineraryScreen(company);
		}

	}
}
