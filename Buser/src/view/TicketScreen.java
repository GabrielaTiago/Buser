package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import controllers.TicketController;
import database.Database;
import models.Client;
import models.Client.GratuityType;

public class TicketScreen implements ActionListener{
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
	int seatTypeIndex;
	Float price;
	String seatNumber;
	
	TicketScreen(TicketController controller, int option) {
		//the option parameter determines if it was the TableTickets class that called the TicketWindow, 
		//if option = 1, the edit button was used and the jcomponents must get the values of the 
		//informed ticket index
		this.controller = controller;
		itinerariesStrings = controller.itineraryListToString(1);
		
		window = new JFrame("Criar/Atualizar Passagens");
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(370, 390);
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
		seatTypeLabel.setBounds(15, 80, 100, 30); 
		seatTypeList.setBounds(130, 80, 210, 30); 
		seatNumberLabel.setBounds(15, 140, 100, 30); 
		seatNumberField.setBounds(130, 140, 210, 30);
		itineraryLabel.setBounds(15, 200, 100, 30); 
		itineraryList.setBounds(130, 200, 210, 30);
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
		
		//the try function will catch an eventual excpetion,
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
		//sets the values for the JComponents 
		String[] values = controller.getToUpdateValues();
		
		priceField.setText(values[0]);
		seatTypeList.setSelectedIndex(Integer.parseInt(values[1]));
		seatNumberField.setText(values[2]);	
		itineraryList.setSelectedIndex(Integer.parseInt(values[3]));
	}
	
	public void getWindowValues() {
		//get the input values from jcomponents
		price = Float.parseFloat(priceField.getText());
		seatTypeIndex = seatTypeList.getSelectedIndex();
		seatNumber = seatNumberField.getText();
		itineraryIndex = itineraryList.getSelectedIndex();
	}
	
	public void createTicket() {
		getWindowValues();
		controller.createTicket(price, seatTypeIndex, seatNumber, itineraryIndex);
		mensagemSucessoCriar();
	}
	
	public void updateTicket() {
		//setWindowValues()
		getWindowValues();
		int ticketIndex = TicketController.getTicketIndex();
		//isso aqui tá dando merda
		controller.updateTicket(price, seatTypeIndex, seatNumber, itineraryIndex, ticketIndex);
		mensagemSucessoAtualizar(ticketIndex);
	}

	public void mensagemErroCadastro() {
		JOptionPane.showMessageDialog(null,"ERRO AO SALVAR OS DADOS!\n "
				+ "Pode ter ocorrido um dos dois erros a seguir:  \n"
				+ "1. Preco ou Poltrona não foram preenchidos \n"
				+ "2. Preco nao contem apenas numeros", null, 
				JOptionPane.ERROR_MESSAGE);
	}
	public void mensagemSucessoAtualizar(int i) {
		JOptionPane.showMessageDialog(null,"Passagem de index " + i + " Atualizada Com Sucesso!\n ",
				null, JOptionPane.INFORMATION_MESSAGE);
	}
	public void mensagemSucessoCriar() {
		JOptionPane.showMessageDialog(null,"Passagem criada com sucesso!\n ",
				null, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void main(String args[]) {
		ClientAuthScreen cas = new ClientAuthScreen(true);
		Database.client = new Client("Gabriel Fernando", "(61) 98663-3660", "Gabriel@e-mail.com",
							  "Rua alguma coisa", "733.188.761.00", GratuityType.elderly); 
		TicketController tc = new TicketController();
		TicketScreen ts = new TicketScreen(tc, 0);
//		TicketsTable tb = new TicketsTable(tc);
		//System.out.println(c.toString());
	}
}
