package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import controllers.TicketController;
import models.Company;
/**
 * Essa classe é a responsável pela tela que nos permite criar uma nova
 * passagem ou de editar uma passagem existente, sendo possível voltar
 * para a tela anterior que lista todas as passagens de um determinado
 * itinerário
 * @author Gabriel Fernando
 *
 */
public class EditTicketScreen implements ActionListener{
	//array given to JlstSeatType
	private static String seatTypes[] = {"Executivo", "Semi-Leito", "Leito"};
	private static JFrame window = new JFrame("Criar/Atualizar Passagens");
	private static JTextField priceField  = new JTextField(10);
	private static JTextField seatNumberField = new JTextField(10);;
	private static JComboBox seatTypeList = new JComboBox<String>(seatTypes);
	private JButton createButton = new JButton();
	private JButton updateButton  = new JButton();
	private static JButton exitButton = new JButton();
	private static Company company;
	
	
	//variables who will store input from JComponents
	private static int operation;
	private int seatTypeIndex;
	private Float price;
	private int seatNumber;
	private int itineraryId;
	/**
	 * O construtor da classe EditTicketScreen adiciona os componentes de 
	 * preço, tipo de poltrona e número de poltrona à um JFrame, onde podem
	 * ser inseridos valores de entrada. O parâmetro "operation" permite 
	 * identificar se é para o botão de criar (int = 0) ou editar (int = 1)
	 * que devem ser adicionados à tela 
	 * @param operation operação selecionada para a passagem
	 * @param id		id do itinerário que possui a passagem
	 * @param company	compania cadastrada
	 */
	EditTicketScreen(int operation, int id, Company company) {
		EditTicketScreen.operation = operation;
		itineraryId = id;
		EditTicketScreen.company = company;
		
	    JPanel container = new JPanel(new BorderLayout());
	    container.setBorder(BorderFactory.createEmptyBorder(45, 45, 45, 45));
	    
	    //atributes panel 
	    JLabel priceLabel = new JLabel("Preço: R$");
	    JLabel seatTypeLabel = new JLabel("Tipo de Poltrona: ");
	    seatTypeLabel.setToolTipText("Há um adicional de R$10, R$15 e R$20 para os respectivos tipos de poltronas");
	    JLabel seatNumberLabel = new JLabel("Nº Poltrona: ");
	    JPanel atributesPanel = new JPanel(new GridLayout(4, 2, 0, 5));
	    atributesPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
	    atributesPanel.add(priceLabel);
	    atributesPanel.add(priceField);
	    atributesPanel.add(seatNumberLabel);
	    atributesPanel.add(seatNumberField);
	    atributesPanel.add(seatTypeLabel);
	    atributesPanel.add(seatTypeList);
	    
	    //JPanel for buttons
	    JPanel buttonPanel = new JPanel();

		exitButton.setBorderPainted(false);
		if (operation == 0) {
			buttonPanel.add(button(createButton, "Criar"));
			createButton.addActionListener(this);
		}
		if (operation == 1) {
			buttonPanel.add(button(updateButton, "Atualizar"));
			updateButton.addActionListener(this);
		}
	    buttonPanel.add(goBack(exitButton, "Voltar"));
	    buttonPanel.setLayout(new GridLayout(3,1,5,10));
	    	
	    container.add(atributesPanel, BorderLayout.CENTER);
	    container.add(buttonPanel, BorderLayout.SOUTH);
	    
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setSize(650, 450);
	    window.setContentPane(container);
	    window.setVisible(true);
	    
	    exitButton.addActionListener(this);
	}
/**Este método é responsável por identificar se o botão das funções "voltar", "Criar"
 * ou "Atualizar" foram acionados, e antes das operações de "Criar" e "Atualizar"
 * serem executadas, chama o controlador de passagens para validar os dados
 * @param ae evento capturado pelo actionListener
 */
	public void actionPerformed(ActionEvent ae) {
		//listens to an event and then determines from which 
		//JComponent it came from and what's it supposed to do
		
		//the try function will catch an eventual excpetion,
		//in this case, the exception encountered was leaving the 
		//price text field empty because it will lead to a NumberFormatException
		//when trying to change into a float variable.
			if (ae.getSource() == createButton) {
				try {
					getWindowValues();
					if (TicketController.checkTicketData(price, seatNumber) == true) {
						TicketController.createTicket(price, seatTypeIndex, seatNumber, itineraryId);
						mensagemSucessoCriar();
					}
					else {
						mensagemErroCadastro(1);
					}
				} catch (NumberFormatException exception) {
					mensagemErroCadastro(0);
				}
			}
			else if (ae.getSource() == updateButton) {
				try {
					getWindowValues();
					if (TicketController.checkTicketData(price, seatNumber) == true) {
						int ticketIndex = TicketController.getUpdatingTicketIndex();
						TicketController.updateTicket(price, seatTypeIndex, seatNumber, ticketIndex, itineraryId);
						mensagemSucessoAtualizar(ticketIndex);
				
					}
					else {
						mensagemErroCadastro(1);
					}
				}
				catch (NumberFormatException exception) {
					mensagemErroCadastro(0);
				}
			}
			if (ae.getSource() == exitButton) {
				EditTicketScreen.window.dispose();
				new TicketsScreen(company, itineraryId);
			}
	}
	/**
	 * Configura um botão de voltar com o texto fornecido e o configura com um 
	 * estilo personalizado, sublinhando o botão quando o cursor do mouse passa por cima 
	 * e voltando ao normal quando o mouse sai.
	 * @param goBackButton o botão de voltar para ser configurado
	 * @param text   o texto a ser exibido no botão
	 * @return o botão configurado
	 */
	private JButton goBack(JButton goBackButton, String text) {
		goBackButton.setText(text);
		goBackButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    goBackButton.setBorderPainted(false);
	    goBackButton.setContentAreaFilled(false);
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
		return goBackButton;
	}
	/**
	 * Configura um botão com o texto fornecido e configurações de estilo personalizadas, que
	 * altera a cor de fundo do botão quando o cursor do mouse passa por cima 
	 * e a restaura quando o mouse sai.
	 * @param button o botão a ser configurado
	 * @param text   o texto a ser exibido no botão
	 * @return o botão configurado
	 */
	private JButton button(JButton button, String text) {
		button.setText(text);
		button.setFocusPainted(false);
		button.setOpaque(true);
		button.setBackground(new Color(241, 16, 117));
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setPreferredSize(new Dimension(button.getPreferredSize().width, 30));
		button.setFont(button.getFont().deriveFont(14f));
		button.setForeground(Color.WHITE);

		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(242, 105, 149));
				button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(241, 16, 117));
			}
		});

		return button;
	}
	/**
	 * Método responsável por pegar os valores de preço, tipo de poltrona e 
	 * numero de poltrona que são fornecidos de entrada na tela de edição
	 */
	private void getWindowValues() {
		//get the input values from jcomponents
		price = Float.parseFloat(priceField.getText());
		seatTypeIndex = seatTypeList.getSelectedIndex();
		seatNumber = Integer.parseInt(seatNumberField.getText());
	}
	/**
	 * Este método é responsável por mostrar uma tela de mensagem de erro,
	 * avisando que os valores fornecidos não são válidos. Podem ter os seguintes
	 * tipos de erro: campo vazio, número negativo, valor nulo e campo com
	 * valor diferente de números
	 * @param error o número de identificação do erro
	 */
	private void mensagemErroCadastro(int error) {
		if (error == 0) {
		JOptionPane.showMessageDialog(null,"ERRO AO SALVAR OS DADOS!\n "
				+ "Pode ter ocorrido um dos dois erros a seguir:  \n"
				+ "1. Preço ou Poltrona não foram preenchidos \n"
				+ "2. Preço não contem apenas numeros", null, 
				JOptionPane.ERROR_MESSAGE);
		}
		if (error == 1) {
			JOptionPane.showMessageDialog(null,"ERRO AO SALVAR OS DADOS!\n "
					+ "Pode ter ocorrido um dos dois erros a seguir:  \n"
					+ "1. Preço ou Poltrona são iguais a zero \n"
					+ "2. Preço ou Poltrona são negativos", null, 
					JOptionPane.ERROR_MESSAGE);
		}
	}
	/**
	 * Este método avisa que o processo de atualizar uma passagem de index i 
	 * , da lista do banco de dados, foi concluido
	 * @param i
	 */
	private void mensagemSucessoAtualizar(int i) {
		JOptionPane.showMessageDialog(null,"Passagem de index " + (i) + " Atualizada Com Sucesso!\n ",
				null, JOptionPane.INFORMATION_MESSAGE);
	}
	/**
	 * Este método mostra uma mensagem que comunica que o processo de criar 
	 * uma passagem foi concluido
	 */
	private void mensagemSucessoCriar() {
		JOptionPane.showMessageDialog(null,"Passagem criada com sucesso!\n ",
				null, JOptionPane.INFORMATION_MESSAGE);
	}
}