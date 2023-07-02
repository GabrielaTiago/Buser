package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import controllers.TicketController;
import models.Company;

/**
 * This class is responsible for the screen that allows us to create a new
 * ticket or edit an existing ticket. It also provides the option to go back to
 * the previous screen that lists all the tickets of a specific itinerary.
 * 
 * @author Gabriel Fernando
 *
 */
public class EditTicketScreen implements ActionListener {
	// array given to JlstSeatType
	private static String seatTypes[] = { "Executivo", "Semi-Leito", "Leito" };
	private static JFrame window = new JFrame("Criar/Atualizar Passagens");
	private static JTextField priceField = new JTextField(10);
	private static JTextField seatNumberField = new JTextField(10);;
	private static JComboBox seatTypeList = new JComboBox<String>(seatTypes);
	private JButton createButton = new JButton();
	private JButton updateButton = new JButton();
	private static JButton exitButton = new JButton();
	private static Company company;

	// variables who will store input from JComponents
	private int seatTypeIndex;
	private Float price;
	private int seatNumber;
	private int itineraryId;

	/**
	 * The constructor of the EditTicketScreen class adds the components for price,
	 * seat type, and seat number to a JFrame, where input values can be entered.
	 * The "operation" parameter allows identifying whether the create button (int =
	 * 0) or the edit button (int = 1) should be added to the screen.
	 * 
	 * @param operation the selected operation for the ticket
	 * @param id        the id of the itinerary that contains the ticket
	 * @param company   the registered company
	 */
	EditTicketScreen(int operation, int id, Company company) {
		itineraryId = id;
		EditTicketScreen.company = company;

		JPanel container = new JPanel(new BorderLayout());
		container.setBorder(BorderFactory.createEmptyBorder(45, 45, 45, 45));

		// atributes panel
		JLabel priceLabel = new JLabel("Preço: R$");
		JLabel seatTypeLabel = new JLabel("Tipo de Poltrona: ");
		seatTypeLabel.setToolTipText("Há um adicional de R$10, R$15 e R$20 para os respectivos tipos de poltronas");
		JLabel seatNumberLabel = new JLabel("Nº Poltrona: ");
		JPanel atributesPanel = new JPanel(new GridLayout(4, 2, 0, 5));
		atributesPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		atributesPanel.add(priceLabel);
		atributesPanel.add(priceField);
		atributesPanel.add(seatNumberLabel);
		atributesPanel.add(seatNumberField);
		atributesPanel.add(seatTypeLabel);
		atributesPanel.add(seatTypeList);

		// JPanel for buttons
		JPanel buttonPanel = new JPanel();

		exitButton.setBorderPainted(false);
		if (operation == 0) {
			buttonPanel.add(button(createButton, "Criar"));
			createButton.addActionListener(this);
		}
		if (operation == 1) {
			buttonPanel.add(button(updateButton, "Atualizar"));
			updateButton.addActionListener(this);
		}
		buttonPanel.add(goBack(exitButton, "Voltar"));
		buttonPanel.setLayout(new GridLayout(3, 1, 5, 10));

		container.add(atributesPanel, BorderLayout.CENTER);
		container.add(buttonPanel, BorderLayout.SOUTH);

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(650, 450);
		window.setContentPane(container);
		window.setVisible(true);

		exitButton.addActionListener(this);
	}

	/**
	 * This method is responsible for identifying whether the "back", "Create", or
	 * "Update" buttons have been clicked. Before the "Create" and "Update"
	 * operations are performed, it calls the ticket controller to validate the
	 * data.
	 * 
	 * @param ae the event captured by the ActionListener
	 */
	public void actionPerformed(ActionEvent ae) {
		// listens to an event and then determines from which
		// JComponent it came from and what's it supposed to do

		// the try function will catch an eventual excpetion,
		// in this case, the exception encountered was leaving the
		// price text field empty because it will lead to a NumberFormatException
		// when trying to change into a float variable.
		if (ae.getSource() == createButton) {
			try {
				getWindowValues();
				if (TicketController.checkTicketData(price, seatNumber) == true) {
					TicketController.createTicket(price, seatTypeIndex, seatNumber, itineraryId);
					mensagemSucessoCriar();
				} else {
					mensagemErroCadastro(1);
				}
			} catch (NumberFormatException exception) {
				mensagemErroCadastro(0);
			}
		} else if (ae.getSource() == updateButton) {
			try {
				getWindowValues();
				if (TicketController.checkTicketData(price, seatNumber) == true) {
					int ticketIndex = TicketController.getUpdatingTicketIndex();
					TicketController.updateTicket(price, seatTypeIndex, seatNumber, ticketIndex, itineraryId);
					mensagemSucessoAtualizar(ticketIndex);

				} else {
					mensagemErroCadastro(1);
				}
			} catch (NumberFormatException exception) {
				mensagemErroCadastro(0);
			}
		}
		if (ae.getSource() == exitButton) {
			EditTicketScreen.window.dispose();
			new TicketsScreen(company, itineraryId);
		}
	}

	/**
	 * Configures a goBackButton button with the provided text and custom styling,
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
		goBackButton.setBorderPainted(false);
		goBackButton.setContentAreaFilled(false);
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
		button.setPreferredSize(new Dimension(button.getPreferredSize().width, 30));
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

	/**
	 * Method responsible for retrieving the values of price, seat type, and seat
	 * number that are provided as input on the edit screen
	 */
	private void getWindowValues() {
		// get the input values from jcomponents
		price = Float.parseFloat(priceField.getText());
		seatTypeIndex = seatTypeList.getSelectedIndex();
		seatNumber = Integer.parseInt(seatNumberField.getText());
	}

	/**
	 * This method is responsible for displaying an error message screen, indicating
	 * that the provided values are not valid. The possible error types can include:
	 * empty field, negative number, null value, and field with a non-numeric value.
	 * 
	 * @param error the error identification number
	 */
	private void mensagemErroCadastro(int error) {
		if (error == 0) {
			JOptionPane.showMessageDialog(null,
					"ERRO AO SALVAR OS DADOS!\n " + "Pode ter ocorrido um dos dois erros a seguir:  \n"
							+ "1. Preço ou Poltrona não foram preenchidos \n" + "2. Preço não contem apenas numeros",
					null, JOptionPane.ERROR_MESSAGE);
		}
		if (error == 1) {
			JOptionPane.showMessageDialog(null,
					"ERRO AO SALVAR OS DADOS!\n " + "Pode ter ocorrido um dos dois erros a seguir:  \n"
							+ "1. Preço ou Poltrona são iguais a zero \n" + "2. Preço ou Poltrona são negativos",
					null, JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * This method notifies that the process of updating a ticket at index i in the
	 * database's ticket list has been completed.
	 * 
	 * @param i the index of the updated ticket
	 */
	private void mensagemSucessoAtualizar(int i) {
		JOptionPane.showMessageDialog(null, "Passagem de index " + (i) + " Atualizada Com Sucesso!\n ", null,
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * This method displays a message that communicates the completion of the ticket
	 * creation process
	 */
	private void mensagemSucessoCriar() {
		JOptionPane.showMessageDialog(null, "Passagem criada com sucesso!\n ", null, JOptionPane.INFORMATION_MESSAGE);
	}
}