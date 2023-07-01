package view;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;

import controllers.ItineraryController;
import models.Company;
import models.Itinerary;
/**
 * A classe CompanyItinerariesScreen é a responsável pela tela que lista todos os 
 * itinerários associadas à empresa cadastrada, sendo possível ver os atributos de 
 * cada itinerário, os editar ou excluir e listar passagens associadas a um itinerário
 * em específico 
 * @author Gabriela Tiago
 */
public class CompanyItinerariesScreen implements ActionListener {

	private static JFrame window = new JFrame("Buser");
	private static JLabel title = new JLabel("Meus Itinerários Cadastrados");
	private static JButton goBackButton = new JButton();
	private static ArrayList<Itinerary> companyItineraries;
	private static Company company;
	/**
	 * Construtor da classe que instancia uma tela contendo um container
	 * de containers com as informações sobre os respectivos itinerários de uma empresa, 
	 * também formata os botões de editar e deletar um itinerário e o de 
	 * listar suas passagem
	 * @param company a empresa cadastrada
	 * @see #actionPerformed(ActionEvent)
	 * @see #deleteItinerary(int)
	 * @see #editItinerary(int)
	 * @see #goBack(JButton, String)
	 * @see #itinerary(int, String, String, LocalDate, String, String)
	 * @see #ticketsButton(int)
	 */
	public CompanyItinerariesScreen(Company company) {
		int gbcLocal = 0;
		CompanyItinerariesScreen.company = company;
		companyItineraries = ItineraryController.getCompanyItineraries(company.getName());

		title.setFont(new Font("Serif", Font.BOLD, 36));

		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBorder(BorderFactory.createEmptyBorder(45, 45, 45, 45));

		JPanel listContainer = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 25, 8, 25);
		listContainer.setBackground(new Color(250, 250, 250));

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		listContainer.add(title, gbc);

		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;

		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		if (companyItineraries.isEmpty()) {
			gbcLocal = 1;
			JPanel emptyContainer = new JPanel();
			emptyContainer.setBackground(null);
			emptyContainer.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
			JLabel label = new JLabel("Ainda não possui itinerários cadastrados no momento :/");
			label.setForeground(new Color(117, 117, 138));
			emptyContainer.add(label);
			listContainer.add(emptyContainer, gbc);
		} else {
			for (int i = 0; i < companyItineraries.size(); i++) {
				Itinerary it = companyItineraries.get(i);
				JPanel itineraryContainer = itinerary(it.getId(), it.getOrigin(), it.getDestination(), it.getDate(),
						it.getDepartureDate(), it.getArrivalDate());
				gbcLocal = i + 1;
				gbc.gridy = gbcLocal;
				listContainer.add(itineraryContainer, gbc);
			}
		}

		gbc.gridy = gbcLocal + 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		listContainer.add(goBack(goBackButton, "Voltar"), gbc);

		container.add(listContainer);

		window.setContentPane(container);
		window.setSize(800, 600);
		window.getContentPane().setBackground(new Color(250, 250, 250));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.requestFocusInWindow();

		goBackButton.addActionListener(this);
	}
	/**
	 * Método que cria um container para a origem e destino, um container para 
	 * as datas e horas e outro container para os botões de editar, deletar e lista
	 * passagens de um itinerário
	 * @param id			o id do itinerário
	 * @param origin		a ciadade de origem do itinerário 
	 * @param destination	a cidade de destino do itinerário
	 * @param date			a data de partida do itinerário
	 * @param departureTime a hora de partida do itinerário
	 * @param arrivalTime   a hora de chegada do itinerário
	 * @return JPanel com as informações de um itinerário e botões de edição
	 */
	public JPanel itinerary(int id, String origin, String destination, LocalDate date, String departureTime,
			String arrivalTime) {
		JPanel itineraryContainer = new JPanel(new GridLayout(1, 3, 10, 0));
		itineraryContainer.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		JPanel locationContainer = new JPanel(new GridLayout(2, 1, 5, 5));
		locationContainer.setBackground(null);
		locationContainer.add(new JLabel("Cidade de Origem: " + origin));
		locationContainer.add(new JLabel("Cidade de Destino: " + destination));

		JPanel dateContainer = new JPanel(new GridLayout(3, 1, 5, 5));
		dateContainer.setBackground(null);
		dateContainer.add(new JLabel("Data da viagem: " + date));
		dateContainer.add(new JLabel("Horário de Partida: " + departureTime));
		dateContainer.add(new JLabel("Horário de Chegada: " + arrivalTime));

		JPanel actionContainer = new JPanel(new GridLayout(3, 1, 10, 10));
		actionContainer.setBackground(null);
		actionContainer.setPreferredSize(new Dimension(60, actionContainer.getPreferredSize().height));
		actionContainer.add(editItinerary(id));
		actionContainer.add(deleteItinerary(id));
		actionContainer.add(ticketsButton(id));

		itineraryContainer.add(locationContainer);
		itineraryContainer.add(dateContainer);
		itineraryContainer.add(actionContainer);

		return itineraryContainer;
	}
	/**
	 * Cria e retorna um JButton configurado para a edição de um itinerário.
	 * Também adiciona um action Listener ao botão, que quando acionado,
	 * indica para a tela de edição o index do itinerário e a compania a quem pertence,
	 * e fecha a janela de itinerarios, instanciando a tela de edição de itinerarios EditItineraryScreen
	 * @param id o índice do itinerário a ser editado
	 * @return o JButton configurado para edição
	 */
	public JButton editItinerary(int id) {
		JButton button = new JButton("Editar");
		button.setFocusPainted(false);
		button.setOpaque(true);
		button.setBackground(new Color(10, 71, 157));
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setPreferredSize(new Dimension(button.getPreferredSize().width, 30));
		button.setFont(button.getFont().deriveFont(12f));
		button.setForeground(Color.WHITE);

		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(69, 117, 183));
				button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(10, 71, 157));
			}
		});
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EditItineraryScreen(company, id);
				CompanyItinerariesScreen.window.dispose();
			}
		});

		return button;
	}

	public JButton deleteItinerary(int id) {
		JButton button = new JButton("Excluir");
		button.setFocusPainted(false);
		button.setOpaque(true);
		button.setBackground(new Color(239, 23, 23));
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setPreferredSize(new Dimension(button.getPreferredSize().width, 30));
		button.setFont(button.getFont().deriveFont(12f));
		button.setForeground(Color.WHITE);

		JFrame frame = new JFrame("Deletar Itinerário");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(188, 75, 75));
				button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(239, 23, 23));
			}
		});
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(frame, "Tem certeza que quer excluir este itinerário?",
						"Excluir itinerário", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					ItineraryController.deleteItinerary(id);
					CompanyItinerariesScreen.window.dispose();
					new CompanyItinerariesScreen(company);
				}
			}
		});

		return button;
	}
	/**
	 * Método que cria e configura a função e o formato de um botão que leva
	 * para a tela de listagem de passagens de um itinerário
	 * @param id identificação do itinerário
	 * @return JButton que leva à lista de passagens de um itinerário
	 */
	private JButton ticketsButton(int id) {
		JButton ticketsButton = new JButton("Passagens");
		
		ticketsButton.setText("Passagens");
		ticketsButton.setFocusPainted(false);
		ticketsButton.setOpaque(true);
		ticketsButton.setBackground(new Color(241, 16, 117));
		ticketsButton.setBorder(BorderFactory.createEmptyBorder());
		ticketsButton.setPreferredSize(new Dimension(ticketsButton.getPreferredSize().width, 30));
		ticketsButton.setFont(ticketsButton.getFont().deriveFont(14f));
		ticketsButton.setForeground(Color.WHITE);

		ticketsButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				ticketsButton.setBackground(new Color(242, 105, 149));
				ticketsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				ticketsButton.setBackground(new Color(241, 16, 117));
			}
		});
		ticketsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new TicketsScreen(company, id);
				CompanyItinerariesScreen.window.dispose();
			}
		});
		return ticketsButton;
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

		return goBackButton;
	}
/**
 * Método que identifica se o botão de voltar foi acionado, voltando
 * para a tela da compania cadastrada
 * @param event evento capturado pelo ActionListener 
 */
	public void actionPerformed(ActionEvent event) {
		Object src = event.getSource();

		if (src == goBackButton) {
			CompanyItinerariesScreen.window.dispose();
			new CompanyScreen(company);
		}
	}
}
