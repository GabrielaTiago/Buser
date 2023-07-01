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
import java.util.ArrayList;

import javax.swing.*;

import controllers.AuthController;
import controllers.ItineraryController;
import controllers.TicketController;
import models.Itinerary;

public class EditTicketScreen implements ActionListener{
	//array given to JlstSeatType
	private String seatTypes[] = {"Executivo", "Semi-Leito", "Leito"};

	//JTextField
	private static JFrame window;
	private JTextField priceField;
	private JTextField seatNumberField;
	private JComboBox seatTypeList;
	private JButton createButton = new JButton();
	private JButton updateButton  = new JButton();
	private JButton exitButton = new JButton();
	ArrayList<Itinerary> companyItineraries;
	
	
	//variables who will store input from JComponents
	private int seatTypeIndex;
	private Float price;
	private int seatNumber;
	private int itineraryId;
	
	EditTicketScreen(int id) {
		itineraryId = id;
		
	    JPanel container = new JPanel(new BorderLayout());
	    container.setBorder(BorderFactory.createEmptyBorder(45, 45, 45, 45));
	    
	    //atributes panel 
	    priceField = new JTextField(10);
	    seatNumberField = new JTextField(10);
	    seatTypeList = new JComboBox<String>(seatTypes);
	    companyItineraries = ItineraryController.getCompanyItineraries(AuthController.getCompanyLoggedIn().getName());
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
	    
	    //JPanel for buttons
	    JPanel buttonPanel = new JPanel();

		exitButton.setBorderPainted(false);
	    buttonPanel.add(button(createButton, "Criar"));
	    buttonPanel.add(button(updateButton, "Atualizar"));
	    buttonPanel.add(goBack(exitButton, "Voltar"));
	    createButton.addActionListener(this);
	    updateButton.addActionListener(this);
	    exitButton.addActionListener(this);
	    buttonPanel.setLayout(new GridLayout(3,1,5,10));
	    	
	    container.add(atributesPanel, BorderLayout.CENTER);
	    container.add(buttonPanel, BorderLayout.SOUTH);
	    
	    window = new JFrame("Criar/Atualizar Passagens");
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setSize(650, 450);
	    window.setContentPane(container);
	  
	    window.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		//listens to an event and then determines from which 
		//JComponent it came from and what's it supposed to do
		
		//the try function will catch an eventual excpetion,
		//in this case, the exception encountered was leaving the 
		//price text field empty because it will lead to a NumberFormatException
		//when trying to change into a float variable.
			if (ae.getSource() == createButton) {
				try {
					getWindowValues();
					TicketController.createTicket(price, seatTypeIndex, seatNumber, itineraryId);
					mensagemSucessoCriar();
				} catch (NumberFormatException exception) {
					mensagemErroCadastro();
				}
			}
			else if (ae.getSource() == updateButton) {
				try {
					getWindowValues();
					int ticketIndex = TicketController.getUpdatingTicketIndex();
					TicketController.updateTicket(price, seatTypeIndex, seatNumber, ticketIndex, itineraryId);
					mensagemSucessoAtualizar(ticketIndex);
				} 
				catch (NumberFormatException exception) {
					mensagemErroCadastro();
				}
			}
			if (ae.getSource() == exitButton) {
				EditTicketScreen.window.dispose();
				new TicketsScreen(companyItineraries, itineraryId);
			}
	}
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
	
	private void getWindowValues() {
		//get the input values from jcomponents
		price = Float.parseFloat(priceField.getText());
		seatTypeIndex = seatTypeList.getSelectedIndex();
		seatNumber = Integer.parseInt(seatNumberField.getText());
	}

	private void mensagemErroCadastro() {
		JOptionPane.showMessageDialog(null,"ERRO AO SALVAR OS DADOS!\n "
				+ "Pode ter ocorrido um dos dois erros a seguir:  \n"
				+ "1. Preco ou Poltrona não foram preenchidos \n"
				+ "2. Preco nao contem apenas numeros", null, 
				JOptionPane.ERROR_MESSAGE);
	}
	private void mensagemSucessoAtualizar(int i) {
		JOptionPane.showMessageDialog(null,"Passagem de index " + i + " Atualizada Com Sucesso!\n ",
				null, JOptionPane.INFORMATION_MESSAGE);
	}
	private void mensagemSucessoCriar() {
		JOptionPane.showMessageDialog(null,"Passagem criada com sucesso!\n ",
				null, JOptionPane.INFORMATION_MESSAGE);
	}
}