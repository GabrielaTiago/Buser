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
import controllers.DatabaseController;
public class TicketWindow implements ActionListener{
	//array given to JlstSeatType
	private String seatTypes[] = {"Executivo", "Semi-Leito", "Leito"};
	//array given to JlstItinerary
	private String itinerariesStrings[];

	//JTextField
	JTextField priceField, seatNumberField;
	JComboBox itineraryList, seatTypeList, idList;
	JLabel priceLabel, seatTypeLabel, seatNumberLabel, itineraryLabel, chosenBnLabel, idLabel;
	JButton createButton, updateButton, editButton,deleteButton; 
	DatabaseController controller;
	JTable ticketsTable;
	//JTable ticketsTable;
	JScrollPane tablePane;
	
	//variables who will store input from JComponents
	int itinerary;
	String seatType;
	String price;
	String seatNumber;
	int id;
	
	TicketWindow(DatabaseController controller) {
		//table.gets
		this.controller = controller;
		itinerariesStrings = controller.itineraryListToString();
		
		
		JFrame window = new JFrame("Passagens");
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(1200, 500);
		//jfrm.setLayout( new GridLayout(6,3));
		window.setLayout(null);
		
		//JTextFields
		priceField = new JTextField(10);
		seatNumberField = new JTextField(10);
		
		//JComboBoxes
		itineraryList = new JComboBox<String>(itinerariesStrings);
		seatTypeList = new JComboBox<String>(seatTypes);
		idList = new JComboBox<>(controller.getTicketController().getIDs());
		
		//JLabels
		priceLabel = new JLabel("Preço: R$");
		seatTypeLabel = new JLabel("Tipo de Poltrona:");
		seatNumberLabel = new JLabel("Poltrona:");
		itineraryLabel = new JLabel("Itinerário:");
		idLabel = new JLabel("ID");
		chosenBnLabel = new JLabel();
		
		//JButtons
		createButton = new JButton("Add");
		updateButton = new JButton("Update");
		editButton = new JButton("Edit");
		deleteButton = new JButton("Delete");
		
		//JTable
		//ticketsTable = new JTable(tableData, tableColumns);
		TicketsTable t = new TicketsTable(controller, itinerariesStrings);
		ticketsTable = t.getTable();
		tablePane = new JScrollPane(ticketsTable);
		
		//Button ActionListners
		createButton.addActionListener(this);
		updateButton.addActionListener(this);
		editButton.addActionListener(this);
		deleteButton.addActionListener(this);
		
		//Setting components Bounds
		int primeiraMargem = 15;
		int segundaMargem = 130;
		int terceiraMargem = primeiraMargem + segundaMargem + 170 + 50;
		int margemBtn = 75; 
		int height = 20;
		int btnHeight1 = height + 230;
		int btnHeight2 = btnHeight1 + 110;
		int componentWidth = 200;

		priceLabel.setBounds(primeiraMargem, height, 100, 30);
		priceField.setBounds(segundaMargem, height, componentWidth, 30);
		
		itineraryLabel.setBounds(primeiraMargem, height + 60, 100, 30);
		itineraryList.setBounds(segundaMargem,  height + 60, componentWidth, 30);
		
		seatTypeLabel.setBounds(primeiraMargem, height + 120, 100, 30);
		seatTypeList.setBounds(segundaMargem,  height + 120, componentWidth, 30);
		
		seatNumberLabel.setBounds(primeiraMargem,   height + 180, 100, 30);
		seatNumberField.setBounds(segundaMargem,   height + 180, componentWidth, 30);
		
		createButton.setBounds(margemBtn, 		 btnHeight1, 	 100, 30);
		updateButton.setBounds(margemBtn + 110,  btnHeight1, 	 100, 30);
		editButton.setBounds(  margemBtn + 110,  btnHeight2 + 40, 100, 30);
		deleteButton.setBounds(margemBtn,     	 btnHeight2 + 40, 100, 30);
		
		tablePane.setBounds(terceiraMargem, 60, 800, 200);
		
		chosenBnLabel.setBounds(primeiraMargem + 140, btnHeight2 + 70, 100, 30);
		
		idLabel.setBounds(margemBtn + 40, btnHeight1 + 100, componentWidth, 30);
		idList.setBounds(margemBtn + 70, btnHeight1 + 100, 80, 30);
		
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
		window.add(editButton);
		window.add(idList);
		window.add(idLabel);
		window.add(deleteButton);
		window.add(chosenBnLabel);
		window.add(tablePane);
		
		window.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		//listens to an event and then determines from which 
		//JComponent it came from and what's it supposed to do
		
		//the try function runs a block of code and catches an excpetion
		//in this case, the exception encountered was leaving the 
		//price text field empty because it will lead to a NumberFormatException
		//when trying to change into a float variable.
		try {
			//remember to create the thing for the id
			itinerary = itineraryList.getSelectedIndex();
			seatType = (String) seatTypeList.getSelectedItem();
			price = priceField.getText();
			seatNumber = seatNumberField.getText();
			
			if (ae.getActionCommand().equals("Add")) {
				chosenBnLabel.setText("Criar");
				String[] s = {null, price.toString(), seatType, seatNumber, 
				(String) itineraryList.getSelectedItem()};
				addToTable(s, 1, controller.getTicketController());
				//controller.create(Float.parseFloat(priceField.getText()),
				//				  seatType, seatNumber, itineraryList.getSelectedIndex());
			}
			if (ae.getActionCommand().equals("Update")) {
				chosenBnLabel.setText("Atualizar");
				//controller.CrudTicket_Controller.update();
				//como saber qual passagem eu tenho que Atualizar?
				//Must take an existing instance of a ticket and replace its 
				//atributes with the new ones from the jcomponents
			}
			if (ae.getActionCommand().equals("Edit")) {
				chosenBnLabel.setText("Editar");
				priceField.setText(Float.toString(0f));
				//Ticket_Controller.edit(client, Float.parseFloat(jtfPrice.getText());
				//como saber qual passagem eu tenho que editar?
				//the edit method must return an instance of Ticket
				
			}
			if (ae.getActionCommand().equals("Delete")) {
				chosenBnLabel.setText("Deletar");
				//controller.CrudTicket_Controller.delete(getValues());
				//como saber qual passagem eu tenho que deletar?
			}
		} catch (NumberFormatException exception) {
			mensagemErroCadastro();
		}
	}	
	
	public static void main(String args[]) {
		Database d = new Database();
		DatabaseController dc = new DatabaseController();
		Itinerary i1 = new Itinerary("Anápolis", "Brasília", "01/05/2023",
				 "10:00", "14:00", null);
		Ticket t1 = new Ticket(12f, SeatType.executivo, "", i1);
		
		ArrayList<Itinerary> i = new ArrayList<Itinerary>();
		ArrayList<Ticket> t = new ArrayList<Ticket>();
		i.add(i1);
		d.setItineraries(i); 
		d.setTickets(t);
		TicketController tcktC = new TicketController(dc);
		
		TicketWindow a = new TicketWindow(dc);
	}
	
	public void mensagemErroCadastro() {
		JOptionPane.showMessageDialog(null,"ERRO AO SALVAR OS DADOS!\n "
				+ "Pode ter ocorrido um dos dois erros a seguir:  \n"
				+ "1. Preco ou Poltrona não foram preenchidos \n"
				+ "2. Preco nao contem apenas numeros", null, 
				JOptionPane.ERROR_MESSAGE);
	}
	public void addToTable(String[] s, int source, TicketController control) {
		if (source == 1) {
			//it is and addition to the table, so a new line must be used 
			int line = control.getTicketsQt() + 1;
			for(int i = 0; i < 4; i++) {
				this.ticketsTable.setValueAt(s[i], line, i);
			}
		}
		if (source == 2) {
			//it is the update of an existing line
		}
	}
}
