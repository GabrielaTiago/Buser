package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import controllers.AuthController;
import controllers.TicketController;
import database.Database;
import models.Client;
import models.Client.GratuityType;

public class TicketEdition implements ActionListener{
	//array given to JlstSeatType
	private String seatTypes[] = {"Executivo", "Semi-Leito", "Leito"};

	//JTextField
	private JFrame window;
	private JTextField priceField, seatNumberField;
	private JComboBox seatTypeList;
	private JButton createButton, updateButton, goBackButton;
	
	//variables who will store input from JComponents
	int seatTypeIndex;
	Float price;
	String seatNumber;
	
	TicketEdition(int option) {
		//the option parameter determines if it was the TicketsScreen class that called the TicketEditon, 
		//if option = 1, the edit button was used and the jcomponents must get the values of the 
		//informed ticket index
		
	    window = new JFrame("Criar/Atualizar Passagens");
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setSize(370, 390);
	    
	    JPanel container = new JPanel(new BorderLayout());
	    container.setBorder(BorderFactory.createEmptyBorder(45, 45, 45, 45));
	    
	    //atributes panel 
	    priceField = new JTextField(10);
	    seatNumberField = new JTextField(10);
	    seatTypeList = new JComboBox<String>(seatTypes);
	    JLabel priceLabel = new JLabel("Preço: R$");
	    JLabel seatTypeLabel = new JLabel("Tipo de Poltrona:");
	    JLabel seatNumberLabel = new JLabel("Poltrona:");
	    JPanel atributesPanel = new JPanel(new GridLayout(4, 2, 0, 5));
	    atributesPanel.add(priceLabel);
	    atributesPanel.add(priceField);
	    atributesPanel.add(seatNumberLabel);
	    atributesPanel.add(seatNumberField);
	    atributesPanel.add(seatTypeLabel);
	    atributesPanel.add(seatTypeList);
	    
	    //JPanel for buttons
	    createButton = new JButton("Criar");
	    updateButton = new JButton("Atualizar");
	    goBackButton = new JButton("Voltar");
	    JPanel buttonPanel = new JPanel();
	    String text = "voltar";
		goBackButton.setText(text);
		goBackButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		goBackButton.setBackground(null);
		goBackButton.setBorder(null);

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
		
	    buttonPanel.add(createButton);
	    buttonPanel.add(updateButton);
	    buttonPanel.add(goBackButton);
	    createButton.addActionListener(this);
	    updateButton.addActionListener(this);
	    goBackButton.addActionListener(this);
	    buttonPanel.setLayout(new GridLayout(3,1,5,10));
	    
	    //Adding the panels to the container
	    container.add(atributesPanel, BorderLayout.CENTER);
	    container.add(buttonPanel, BorderLayout.SOUTH);
	    
	    window.setContentPane(container);
	    
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
				getWindowValues();
				TicketController.createTicket(price, seatTypeIndex, seatNumber);
				mensagemSucessoCriar();
			}
			else if (ae.getSource() == updateButton) {
				getWindowValues();
				int ticketIndex = TicketController.getUpdatingTicketIndex();
				TicketController.updateTicket(price, seatTypeIndex, seatNumber, ticketIndex);
				mensagemSucessoAtualizar(ticketIndex);
			}
			if (ae.getSource() == goBackButton) {
				window.dispose();
				new CompanyScreen(AuthController.getCompanyLoggedIn());
			}
		} catch (NumberFormatException exception) {
			mensagemErroCadastro();
		}
	}
	
	public void setWindowValues() {
		//sets the values for the JComponents 
		String[] values = TicketController.getToUpdateValues();
		
		priceField.setText(values[0]);
		seatTypeList.setSelectedIndex(Integer.parseInt(values[1]));
		seatNumberField.setText(values[2]);	
	}
	
	public void getWindowValues() {
		//get the input values from jcomponents
		price = Float.parseFloat(priceField.getText());
		seatTypeIndex = seatTypeList.getSelectedIndex();
		seatNumber = seatNumberField.getText();
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
}