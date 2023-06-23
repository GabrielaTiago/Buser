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
	int itineraryValue;
	String seatTypeValue;
	Float price;
	String seatNumber;
	int id;
	
	TicketWindow(DatabaseController controller) {
		//table.gets
		this.controller = controller;
		itinerariesStrings = controller.itineraryListToString();
		
		
		JFrame window = new JFrame("Passagens");
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(1200, 420);
		//jfrm.setLayout( new GridLayout(6,3));
		window.setLayout(null);
		
		//JTextFields
		priceField = new JTextField(10);
		seatNumberField = new JTextField(10);
		
		//JComboBoxes
		itineraryList = new JComboBox<String>(itinerariesStrings);
		seatTypeList = new JComboBox<String>(seatTypes);
		idList = new JComboBox<String>(controller.getTicketController().getIDs());
		
		//JLabels
		priceLabel = new JLabel("Preço: R$");
		seatTypeLabel = new JLabel("Tipo de Poltrona:");
		seatNumberLabel = new JLabel("Poltrona:");
		itineraryLabel = new JLabel("Itinerário:");
		idLabel = new JLabel("ID:");
		chosenBnLabel = new JLabel();
		
		//JButtons
		createButton = new JButton("Criar");
		updateButton = new JButton("Atualizar");
		editButton = new JButton("Editar");
		deleteButton = new JButton("Deletar");
		
		//JTable
		//ticketsTable = new JTable(tableData, tableColumns);
		TicketsTable t = new TicketsTable(controller);
		ticketsTable = t.getTable();
		tablePane = new JScrollPane(ticketsTable);
		
		//Button ActionListners
		createButton.addActionListener(this);
		updateButton.addActionListener(this);
		editButton.addActionListener(this);
		deleteButton.addActionListener(this);
		
		//Setting components Bounds
		int primeiraMargem = 15;
		int margemBtn = 75; 
		int height = 20;
		int btnHeight1 = height + 230;
		int componentWidth = 210;
		int margens[] = {primeiraMargem,
						 primeiraMargem + 115,//segunda margem
						 primeiraMargem + (primeiraMargem + 115) + (170 + 50)};//terceira margem
		//Bloco 1
		priceLabel.setBounds(margens[0], height, 100, 30);
		priceField.setBounds(margens[1], height, componentWidth, 30);
		itineraryLabel.setBounds(margens[0], height + 60, 100, 30);
		itineraryList.setBounds( margens[1], height + 60, componentWidth, 30);
		seatTypeLabel.setBounds(margens[0],  height + 120, 100, 30);
		seatTypeList.setBounds( margens[1],  height + 120, componentWidth, 30);
		seatNumberLabel.setBounds(margens[0],   height + 180, 100, 30);
		seatNumberField.setBounds(margens[1],   height + 180, componentWidth, 30);
		createButton.setBounds(margemBtn, 		 btnHeight1, 	 100, 30);
		updateButton.setBounds(margemBtn + 110,  btnHeight1, 	 100, 30);

		//Bloco 2
		tablePane.setBounds(margens[2], 30, 800, 330);
		
		//Bloco 3
		int btnHeight2 = btnHeight1 + 20;
		int width = 80;
		idLabel.setBounds(		margens[0], 	  btnHeight2 + 40, componentWidth, 30);
		idList.setBounds(		margens[0] + 25,  btnHeight2 + 40, 80, 30);
		editButton.setBounds(   margens[0] + 33 + 5 + width,  btnHeight2 + 40, 100, 30);
		deleteButton.setBounds( margens[0] + 40 + width + 110,     	  btnHeight2 + 40, 100, 30);		
		chosenBnLabel.setBounds(margens[0] + 140, btnHeight2 + 70, 100, 30);
		
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
		
		//the try will catche an eventual excpetion,
		//in this case, the exception encountered was leaving the 
		//price text field empty because it will lead to a NumberFormatException
		//when trying to change into a float variable.
		try {
			//remember to create the thing for the id
			id = idList.getSelectedIndex();
			itineraryValue = itineraryList.getSelectedIndex();
			seatTypeValue = (String) seatTypeList.getSelectedItem();
			price = Float.parseFloat(priceField.getText());
			seatNumber = seatNumberField.getText();
			
			if (ae.getActionCommand().equals("Criar")) {
				chosenBnLabel.setText("Criar");
				int option = 1;
				String[] values = {String.valueOf(controller.getTicketController().getTicketsQt() + 1)
								   , price.toString(), seatTypeValue, seatNumber, 
								   (String) itineraryList.getSelectedItem()};
				addToTable(values, option, controller.getTicketController());
				controller.createTicket(Float.parseFloat(priceField.getText()),
								  seatTypeValue,seatNumber, itineraryList.getSelectedIndex());
			}
			if (ae.getActionCommand().equals("Atualizar")) {
				chosenBnLabel.setText("Atualizar");
				//controller.CrudTicket_Controller.update();
				//takes an existing instance of a ticket and replace its 
				//atributes with the new ones from the jcomponents
			}
			if (ae.getActionCommand().equals("Editar")) {
				chosenBnLabel.setText("Editar");
				priceField.setText(Float.toString(0f));
				//Ticket_Controller.edit(client, Float.parseFloat(jtfPrice.getText());
				//the edit method must return an instance of Ticket
				
			}
			if (ae.getActionCommand().equals("Delet")) {
				chosenBnLabel.setText("Deletar");
				//controller.CrudTicket_Controller.delete(getValues());
				//como saber qual passagem eu tenho que deletar?
			}
		} catch (NumberFormatException exception) {
			mensagemErroCadastro();
		}
	}	
	
	public static void main(String args[]) {
		
		DatabaseController dc = new DatabaseController();
		Itinerary i1 = new Itinerary("Anápolis", "Brasília", "01/05/2023",
				 "10:00", "14:00", null);
		Itinerary i2 = new Itinerary("Belo Horizonte", "Brasília", "01/05/2023",
				 "10:00", "14:00", null);
		Itinerary i3 = new Itinerary("Brasília", "Belo Horizonte", "01/05/2023",
				 "10:00", "14:00", null);
		Itinerary i4 = new Itinerary("Brasília", "Anápolis", "01/05/2023",
				 "10:00", "14:00", null);
		Ticket t1 = new Ticket(12f, SeatType.executivo, "", i1);
		Ticket t2 = new Ticket(12f, SeatType.executivo, "", i2);
		Ticket t3 = new Ticket(12f, SeatType.executivo, "", i3);
		Ticket t4 = new Ticket(12f, SeatType.executivo, "", i4);
		
		ArrayList<Itinerary> i = new ArrayList<Itinerary>();
		ArrayList<Ticket> t = new ArrayList<Ticket>();
		
		i.add(i1);
		i.add(i2);
		i.add(i3);
		i.add(i4);
		t.add(t1);
		t.add(t2);
		t.add(t3);
		t.add(t4);
		
		dc.getData().setItineraries(i); 
		dc.getData().setTickets(t);
		
		TicketWindow a = new TicketWindow(dc);
		//System.out.println("IDs: " + t1.getId() + t2.getId() + t3.getId() + t4.getId());
//		System.out.println("testando id com o databaseController: " + dc.getData().getTickets().get(1).getId());;
//		System.out.println("Há essa quantidade de passagens na base de dados: " +
//							dc.getTicketController().getTicketsQt());
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
			//it is an addition to the table, so a new line must be used 
			int line = control.getTicketsQt();
			for(int i = 0; i < 5; i++) {
				this.ticketsTable.setValueAt(s[i], line, i);
			}
		}
		if (source == 2) {
			//it is the update of an existing line
		}
	}
}
