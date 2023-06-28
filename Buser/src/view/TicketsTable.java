package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controllers.TicketController;
import database.Database;
import models.*;
import models.Client.GratuityType;

public class TicketsTable implements ActionListener{
	
	//JComponents
	private JFrame window;
	private JTable ticketsTable;
	private JScrollPane tablePane;
	private JLabel indexLabel, clientLabel, nameLabel, phoneLabel,
				   emailLabel, adressLabel, cpfLabel, gratuityLabel, tableLabel;
	private JButton editButton, deleteButton, exitButton;
	private JComboBox ticketIndexList;
	private String tableColumns[] = {"Index", "Preço","Tipo de Poltrona","Número Poltrona","Itinerário"};
	private String tableData[][];
	private String[] itineraries;
	private TicketController controller;
	
	public TicketsTable(TicketController controller) {
		//creates the table with the given string arrays
		this.controller = controller;
		this.setItineraries(controller.itineraryListToString(1));
		
		//JFrame settings
		window = new JFrame("Lista de Passagens");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(850, 600);
		window.setLayout(null);
		
		//JTable
		ticketsTable = new JTable(fillTableData(controller, itineraries), tableColumns); 
		ticketsTable.getColumnModel().getColumn(4).setWidth(400);
		tablePane = new JScrollPane(ticketsTable);
		
		ticketIndexList = new JComboBox<String>(controller.getIndexes());
		
		//JLabel
		indexLabel  = new JLabel("Index:");
		clientLabel = new JLabel("Dados do Cliente");
		tableLabel  = new JLabel("Passagens");
		nameLabel   = new JLabel(	TicketController.getClientInfo(0));
		phoneLabel  = new JLabel(	TicketController.getClientInfo(1));
		emailLabel  = new JLabel(	TicketController.getClientInfo(2));
		adressLabel = new JLabel(	TicketController.getClientInfo(3));
		cpfLabel    = new JLabel(	TicketController.getClientInfo(4));
		gratuityLabel = new JLabel(TicketController.getClientInfo(5));
		
		//JButtons
		editButton = new JButton("Editar");
		deleteButton = new JButton("Deletar");
		exitButton = new JButton("Voltar");
		editButton.addActionListener(this);
		deleteButton.addActionListener(this);
		exitButton.addActionListener(this);
		
		clientLabel.setFont(new Font("Arial", Font.BOLD, 25));
		tableLabel.setFont(new Font("Arial", Font.BOLD, 25));
		
		clientLabel.setBounds(   300, 5, 250, 30);
		nameLabel.setBounds(	 130, 45, 200, 30);
		phoneLabel.setBounds(	 330, 45, 200, 30);
		emailLabel.setBounds(	 530, 45, 200, 30);
		adressLabel.setBounds(	 130, 70, 200, 30);
		cpfLabel.setBounds(	 	 330, 70, 200, 30); 
		gratuityLabel.setBounds( 530, 70, 250, 30);
		tableLabel.setBounds(325, 110, 250, 30);
		tablePane.setBounds(20, 160, 800, 330);
		indexLabel.setBounds(     190,   	          		 160 + 350, 210, 30);
		ticketIndexList.setBounds(190 + 45,         	     160 + 350, 80, 30);
		editButton.setBounds(     190 + 45 + 90,    		 160 + 350, 100, 30);
		deleteButton.setBounds(   190 + 45 + 90 + 110, 		 160 + 350, 100, 30);
		exitButton.setBounds(     190 + 45 + 90 + 110 + 110, 160 + 350, 100, 30);
		
		//nameLabel, phoneLabel, emailLabel, adressLabel, cpfLabel, gratuityLabel
		window.add(clientLabel);
		window.add(nameLabel);
		window.add(phoneLabel);
		window.add(emailLabel);
		window.add(adressLabel);
		window.add(cpfLabel);
		window.add(gratuityLabel);
		window.add(tableLabel);
		window.add(tablePane);
		window.add(ticketIndexList);
		window.add(indexLabel);
		window.add(deleteButton);
		window.add(editButton);
		window.add(exitButton);
		
		window.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("Editar")) {
			int i = ticketIndexList.getSelectedIndex();
			controller.setToUpdateValues(i);
			window.dispose();
			TicketScreen newWindow = new TicketScreen(controller, 1);
		}
		if (ae.getActionCommand().equals("Deletar")) {
			int i = ticketIndexList.getSelectedIndex();
			System.out.println("deleteTicket() view - Ticket a ser deletado: " + i);
			controller.deleteTicket(i);
			mensagemSucessoDeletar(i);
			//window.dispose();
		}
		if (ae.getActionCommand().equals("Voltar")) {
			window.dispose();
			TicketScreen newWindow = new TicketScreen(controller, 0);
		}
	}
	
	public String[][] fillTableData(TicketController controller, String[] itineraries) {
		//returns the tabledata in a string[][]
		int existingTickets = controller.getTicketsQt();
		int scenario;
		int lines = 20;
		tableData = new String[lines][5]; 
		
		if (existingTickets > 0) {
		//there are tickets in the database and we must get their values.
		//Once they are all filled in, we can proceed to fill with empty data up to line value
			scenario = 1;
		}
		else {
		//there are no tickets in the database and we can initialize all lines with empty data
			scenario = 2;
		}
		
		for (int i = 0; i < lines; i++) {
			
			switch(scenario) {
				case (1):

				
					if (i < existingTickets) {
						Ticket t = controller.getTickets().get(i);
						int index = controller.getItineraryIndex(t.getItinerary());
						//saves the ticket i, in database tickets,
						//data in a string array to fill the cell at the table 
						String[] values = {String.valueOf(i),
										   String.valueOf(t.getPrice()),
										   t.getSeatType().toString(),
										   t.getSeatNumber(),
										   itineraries[index]};
						
						for(int j = 0; j < 5; j++) {
							tableData[i][j] = values[j];
						}
					}
//					else {
//						for(int j = 0; j < 5; j++) {
//							tableData[i][j] = "";
//						}
//					}
					break;

				case (2): {
					for(int j = 0; j < 5; j++) {
						tableData[i][j] = "";
					}
				}
			}
		}
		return tableData;
	}
	public void mensagemSucessoDeletar(int i) {
		JOptionPane.showMessageDialog(null,"Passagem de index " + i + "deletada com sucesso!\n ",
				null, JOptionPane.INFORMATION_MESSAGE);
	}
	public String[] getTableColumns() {
		return tableColumns;
	}
	public void setTableColumns(String[] tableColumns) {
		this.tableColumns = tableColumns;
	}
	public String[][] getTableData() {
		return tableData;
	}
	public void setTableData(String[][] tableData) {
		this.tableData = tableData;
	}
	public String[] getItineraries() {
		return itineraries;
	}
	public void setItineraries(String[] itineraries) {
		this.itineraries = itineraries;
	}
}
