package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;

import controllers.DatabaseController;
import controllers.TicketController;
import database.Database;
import models.*;

public class TicketsTable{
	
	//JComponents
	//private JFrame window;
	private JTable table;
	private String tableColumns[] = {"ID", "Preço","Tipo de Poltrona","Número Poltrona","Itinerário"};
	private String tableData[][];
	private String[] itineraries;
	
	public TicketsTable(DatabaseController controller) {
		//creates the table with the given arrays
		this.setItineraries(controller.itineraryListToString());
		table = new JTable(fillTableData(controller, itineraries), tableColumns);
	}
	
	public String[][] fillTableData(DatabaseController controller, String[] iti) {
		int existingTickets = controller.getTicketController().getTicketsQt();
		int scenario;
		int lines = 30;
		tableData = new String[lines][5]; 
		
		if (existingTickets > 0) {
		//there are tickets in the database instance and we must get their values
		//and once they are all filled in, proceed to fill with empty data up to line value
			scenario = 1;
		}
		else {
		//there are no tickets in the database and we can initialize all lines with empty data
			scenario = 2;
		}
		
		for (int i = 0; i < lines - 1; i++) {
			
			switch(scenario) {
				case (1):

				
					if (i < existingTickets) {
						Ticket t = controller.getData().getTickets().get(i);
						//saves the ticket i, in database tickets,
						//data in a string array to fill the cell at the table 
						String[] values = {String.valueOf(t.getId()),
										   String.valueOf(t.getPrice()),
										   t.getSeatType().toString(),
										   t.getSeatNumber(),
										   iti[i]};
						
						for(int j = 0; j < 5; j++) {
							tableData[i][j] = values[j];
						}
					}
					else {
						for(int j = 0; j < 5; j++) {
							tableData[i][j] = "";
						}
					}
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
	public JTable getTable() {
		return this.table;
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
