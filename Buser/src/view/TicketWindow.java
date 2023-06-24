package view;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controllers.TicketController;
import models.Client;
import models.Company;
import models.Itinerary;
import models.Ticket;
import models.Client.GratuityType;
import models.Ticket.*;
import database.*;

public class TicketWindow implements ActionListener{
	//array given to JlstSeatType
	private String seatTypes[] = {"Executivo", "Semi-Leito", "Leito"};
	//array given to JlstItinerary
	private String itinerariesStrings[];

	//JTextField
	JFrame window;
	JTextField priceField, seatNumberField;
	JComboBox itineraryList, seatTypeList, idList;
	JLabel priceLabel, seatTypeLabel, seatNumberLabel, itineraryLabel, chosenBnLabel, idLabel;
	JButton createButton, updateButton, ticketsButton; 
	TicketController controller;
	
	//variables who will store input from JComponents
	int itineraryIndex;
	String seatTypeValue;
	Float price;
	String seatNumber;
	int ticketIndex;
	
	TicketWindow(TicketController controller, int option) {
		//table.gets
		this.controller = controller;
		itinerariesStrings = controller.itineraryListToString(1);
		
		window = new JFrame("Criar/Atualizar Passagens");
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(370, 390);
		//jfrm.setLayout( new GridLayout(6,3));
		window.setLayout(null);
		
		//JTextFields
		priceField = new JTextField(10);
		seatNumberField = new JTextField(10);
		
		//JComboBoxes
		itineraryList = new JComboBox<String>(itinerariesStrings);
		seatTypeList = new JComboBox<String>(seatTypes);
		
		//JLabels
		priceLabel = new JLabel("Preço: R$");
		seatTypeLabel = new JLabel("Tipo de Poltrona:");
		seatNumberLabel = new JLabel("Poltrona:");
		itineraryLabel = new JLabel("Itinerário:");
		chosenBnLabel = new JLabel();
		
		//JButtons
		createButton = new JButton("Criar");
		updateButton = new JButton("Atualizar");
		ticketsButton = new JButton("Listar Passagens");
		
		//Button ActionListners
		createButton.addActionListener(this);
		updateButton.addActionListener(this);
		ticketsButton.addActionListener(this);
		
		//Setting components Bounds
		priceLabel.setBounds(15, 20, 100, 30);
		priceField.setBounds(130, 20, 210, 30);
		itineraryLabel.setBounds(15, 80, 100, 30);
		itineraryList.setBounds(130, 80, 210, 30);
		seatTypeLabel.setBounds(15, 140, 100, 30);
		seatTypeList.setBounds(130, 140, 210, 30);
		seatNumberLabel.setBounds(15, 200, 100, 30);
		seatNumberField.setBounds(130, 200, 210, 30);
		createButton.setBounds(75, 250, 100, 30);
		updateButton.setBounds(185, 250, 100, 30);
		ticketsButton.setBounds(100, 290, 160, 30);
		chosenBnLabel.setBounds(155, 320, 100, 30);
		
		//Adding components to the jframe
		window.add(priceLabel);
		window.add(priceField);
		window.add(seatNumberLabel);
		window.add(seatNumberField);
		window.add(seatTypeLabel);
		window.add(seatTypeList);
		window.add(itineraryLabel);
		window.add(itineraryList);
		window.add(createButton);
		window.add(updateButton);
		window.add(ticketsButton);
		window.add(chosenBnLabel);
		
		if (option == 1) {
			setWindowValues();
		}
		window.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		//listens to an event and then determines from which 
		//JComponent it came from and what's it supposed to do
		
		//the try will catche an eventual excpetion,
		//in this case, the exception encountered was leaving the 
		//price text field empty because it will lead to a NumberFormatException
		//when trying to change into a float variable.
		try {
			if (ae.getSource() == createButton) {
				chosenBnLabel.setText("Criar");
				createTicket();
			}
			else if (ae.getSource() == updateButton) {
				chosenBnLabel.setText("Atualizar");
				updateTicket();
			}
			else if (ae.getSource() == ticketsButton) {
				chosenBnLabel.setText("Listar Passagens");
				TicketsTable t = new TicketsTable(controller);
				window.dispose();
			}
		} catch (NumberFormatException exception) {
			mensagemErroCadastro();
		}
	}
	
	public void setWindowValues() {
		//ainda preciso terminar
		String[] values = controller.getToUpdateValues();
		priceField.setText(values[0]);
		seatTypeList.setSelectedIndex(Integer.parseInt(values[1]));
		seatNumberField.setText(values[2]);	
		itineraryList.setSelectedIndex(Integer.parseInt(values[3]));
	}
	
	public void getWindowValues() {
		//get the input values from jcomponents
		price = Float.parseFloat(priceField.getText());
		seatTypeValue = (String) seatTypeList.getSelectedItem();
		seatNumber = seatNumberField.getText();
		itineraryIndex = itineraryList.getSelectedIndex();
	}
	
	public void createTicket() {
		getWindowValues();
		controller.createTicket(price, seatTypeValue, seatNumber, itineraryIndex);
		mensagemSucessoCriar();
	}
	
	public void updateTicket() {
		//setWindowValues()
		getWindowValues();
		int ticketIndex = Integer.parseInt(controller.getToUpdateValues()[1]);
		controller.updateTicket(price, seatTypeValue, seatNumber, itineraryIndex, ticketIndex);
		mensagemSucessoAtualizar();
	}

	public void mensagemErroCadastro() {
		JOptionPane.showMessageDialog(null,"ERRO AO SALVAR OS DADOS!\n "
				+ "Pode ter ocorrido um dos dois erros a seguir:  \n"
				+ "1. Preco ou Poltrona não foram preenchidos \n"
				+ "2. Preco nao contem apenas numeros", null, 
				JOptionPane.ERROR_MESSAGE);
	}
	public void mensagemSucessoAtualizar() {
		JOptionPane.showMessageDialog(null,"Passagem Atualizada Com Sucesso!\n ",
				null, JOptionPane.INFORMATION_MESSAGE);
	}
	public void mensagemSucessoCriar() {
		JOptionPane.showMessageDialog(null,"Passagem Criada Com Sucesso!\n ",
				null, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void main(String args[]) {
		
		TicketController tc = new TicketController();
		
		TicketWindow a = new TicketWindow(tc, 0);
		//TicketsTable b = new TicketsTable(tc);
	}
}
