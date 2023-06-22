package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;

import database.Database;
import models.*;

public class TicketsTable{
	
	private Database d;
	private JTable table;
	private String tableColumns[] = {"Preço","Tipo de Poltrona","Número Poltrona","Itinerário"};
	private String tableData[][] = {{""}, {""}, {""}, {""}};
	
	public TicketsTable(ArrayList<Ticket> tickets) {
		
	}
	
	public JTable getTable() {
		return null;
	}
	
}
