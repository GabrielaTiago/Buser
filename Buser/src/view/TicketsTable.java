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
	
	private Database d;
	private JTable table;
	private String tableColumns[] = {"ID", "Preço","Tipo de Poltrona","Número Poltrona","Itinerário"};
	private String tableData[][];
	
	public TicketsTable(DatabaseController controller, String[] iti) {
		int existingTickets = controller.getTicketController().getTicketsQt();
		int scenario;
		tableData = new String[15][5]; 
		
		if (existingTickets > 0) {
		//there are tickets in the database instance and we must get their values
			scenario = 1;
		}
		else {
		//there are no tickets in the database and we can initialize with empty data
			scenario = 2;
		}
		
		for (int i = 0; i < 7; i++) {
			
			switch(scenario) {
				case (1):
					Ticket t = controller.getData().getTickets().get(i);
					//saves the ticket i, in database tickets,
					//data in a string array to fill the cell at the table 
					String[] values = {String.valueOf(t.getId()),
									   String.valueOf(t.getPrice()),
									   t.getSeatType().toString(),
									   t.getSeatNumber(),
									   iti[1]};
				
					if (i < existingTickets) {
						for(int j = 0; j < 4; j++) {
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
		//creates the table with the given arrays
		table = new JTable(tableData, tableColumns);
	}
	
	public JTable getTable() {
		return this.table;
	}
}
