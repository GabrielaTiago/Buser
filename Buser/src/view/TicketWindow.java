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
import models.Client.GratuityType;
import models.Ticket.*;
import database.*;
import controllers.DatabaseController;
public class TicketWindow implements ActionListener{
	//array given to JlstSeatType
	private String seatTypes[] = {"Executivo", "Semi-Leito", "Leito"};
	//array given to JlstItinerary
	private String itinerariesStrings[];
	private String tableColumns[] = {"Preço","Tipo de Poltrona","Número Poltrona","Itinerário"};
	private String tableData[][] = {{"a", "a", "a", "a"}, {"a", "a", "a", "a"},
							{"a", "a", "a", "a"}, {""}};
	//n precia desse atributo, o que posso fazer é receber um controlador de tickets
	//e um de dados, pelo controlador de dados eu posso pegar a lista de itinerários
	//e usar o meu método
	
	//JTextField
	JTextField priceField, seatNumberField;
	JComboBox itineraryList, seatTypeList;
	JLabel jlabPrice, jlabSeatType, jlabSeatNumber, jlabItinerary, jlabChosenBn;
	JButton createButton, updateButton, editButton,deleteButton; 
	DatabaseController controller;
	JTable ticketsTable;
	JScrollPane tablePane;
	
	//variables who will receive input values
	int itinerary;
	String seatType;
	String price;
	String seatNumber;
	
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
		
		//JLabels
		jlabPrice = new JLabel("Preço:");
		jlabSeatType = new JLabel("Tipo de Poltrona:");
		jlabSeatNumber = new JLabel("Poltrona:");
		jlabItinerary = new JLabel("Itinerário:");
		jlabChosenBn = new JLabel();
		
		//JButtons
		createButton = new JButton("Add");
		updateButton = new JButton("Update");
		editButton = new JButton("Edit");
		deleteButton = new JButton("Delete");
		
		//JTable
		ticketsTable = new JTable(tableData, tableColumns);
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
		int margemBtn = 65; 
		int height = 50;
		int btnHeight = height + 230 + 50;
		int componentWidth = 200;

		jlabPrice.setBounds(primeiraMargem, height, 100, 30);
		priceField.setBounds(segundaMargem, height, componentWidth, 30);
		jlabItinerary.setBounds(primeiraMargem, height + 60, 100, 30);
		itineraryList.setBounds(segundaMargem,  height + 60, componentWidth, 30);
		jlabSeatType.setBounds(primeiraMargem, height + 120, 100, 30);
		seatTypeList.setBounds(segundaMargem,  height + 120, componentWidth, 30);
		jlabSeatNumber.setBounds(primeiraMargem,   height + 180, 100, 30);
		seatNumberField.setBounds(segundaMargem,   height + 180, componentWidth, 30);
		createButton.setBounds(margemBtn, 		 btnHeight, 	 100, 30);
		updateButton.setBounds(margemBtn + 110,  btnHeight, 	 100, 30);
		editButton.setBounds(  margemBtn + 110,  btnHeight + 40, 100, 30);
		deleteButton.setBounds(margemBtn,     	 btnHeight + 40, 100, 30);
		tablePane.setBounds(terceiraMargem, 15, 800, 400);
		jlabChosenBn.setBounds(primeiraMargem + 140, btnHeight + 80, 300, 30);
		
		//Adding components to the jframe
		window.add(jlabPrice);
		window.add(priceField);
		window.add(jlabSeatNumber);
		window.add(seatNumberField);
		window.add(jlabSeatType);
		window.add(seatTypeList);
		window.add(jlabItinerary);
		window.add(itineraryList);
		window.add(createButton);
		window.add(updateButton);
		window.add(editButton);
		window.add(deleteButton);
		window.add(jlabChosenBn);
		window.add(tablePane);
		
		window.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		//the try function runs a block of code and catches an excpetion
		//in this case, the exception encountered was leaving the 
		//price text field empty because it will lead to a NumberFormatException
		//when trying to change into a float variable
		//listens to an event and then determines from which 
		//JComponent it came from and what's it supposed to do
		try {
			
			itinerary = itineraryList.getSelectedIndex();
			seatType = (String) seatTypeList.getSelectedItem();
			price = priceField.getText();
			seatNumber = seatNumberField.getText();
			
			if (ae.getActionCommand().equals("Add")) {
				jlabChosenBn.setText("Criar");
				String[] s = {price.toString(), seatType, seatNumber, (String) itineraryList.getSelectedItem()};
				addToTable(s, 1, controller.getTicketController());
				//controller.create(Float.parseFloat(priceField.getText()),
				//				  seatType, seatNumber, itineraryList.getSelectedIndex());
			}
			if (ae.getActionCommand().equals("Update")) {
				jlabChosenBn.setText("Atualizar");
				//controller.CrudTicket_Controller.update();
				//como saber qual passagem eu tenho que Atualizar?
				//Must take an existing instance of a ticket and replace its 
				//atributes with the new ones from the jcomponents
			}
			if (ae.getActionCommand().equals("Edit")) {
				jlabChosenBn.setText("Editar");
				priceField.setText(Float.toString(0f));
				//Ticket_Controller.edit(client, Float.parseFloat(jtfPrice.getText());
				//como saber qual passagem eu tenho que editar?
				//the edit method must return an instance of Ticket
				
			}
			if (ae.getActionCommand().equals("Delete")) {
				jlabChosenBn.setText("Deletar");
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
		ArrayList<Itinerary> i = new ArrayList<Itinerary>();
		i.add(i1);
		d.setItineraries(i); 
		
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
