package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import models.Company;

/**
 * Classe reponsável pela tela que permite a empresa cadastrada criar um
 * itinerário, fazer uma busca por itinerário e ver os itinerários cadastrados
 * 
 * @author Gabriela Tiago
 */

public class CompanyScreen implements ActionListener {

	private static JFrame window = new JFrame("Buser");
	private static JLabel title = new JLabel();
	private static JButton homePageButton = new JButton();
	private static JButton itinerariesButton = new JButton();
	private static JButton createItineraryButton = new JButton();
	private static Company company;

	/**
	 * O construtor da classe é o responsável por estilizar e organizar em
	 * containers os botões que levam a outras telas, os adicionando ao JFrame
	 * window
	 * 
	 * @param company compania cadastrada
	 * @see #actionPerformed(ActionEvent)
	 * @see #button(JButton, String)
	 */
	public CompanyScreen(Company company) {
		CompanyScreen.company = company;
		title.setFont(new Font("Serif", Font.BOLD, 36));
		title.setText("Painel de controle " + company.getName());

		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBorder(BorderFactory.createEmptyBorder(80, 80, 80, 80));

		JPanel dashboardContainer = new JPanel(new BorderLayout());
		dashboardContainer.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		dashboardContainer.setBackground(new Color(250, 250, 250));

		JPanel buttonsContainer = new JPanel(new GridLayout(1, 3, 30, 0));
		buttonsContainer.setBackground(new Color(250, 250, 250));
		buttonsContainer.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

		buttonsContainer.add(button(homePageButton, "Página Inicial"));
		buttonsContainer.add(button(itinerariesButton, "Meus Itinerários"));
		buttonsContainer.add(button(createItineraryButton, "Criar Itinerário"));

		dashboardContainer.add(title, BorderLayout.NORTH);
		dashboardContainer.add(buttonsContainer, BorderLayout.CENTER);

		container.add(dashboardContainer, BorderLayout.CENTER);

		window.setContentPane(container);
		window.setSize(1250, 700);
		window.getContentPane().setBackground(new Color(250, 250, 250));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		homePageButton.addActionListener(this);
		itinerariesButton.addActionListener(this);
		createItineraryButton.addActionListener(this);
	}

	/**
	 * Configura um botão com o texto fornecido e configurações de estilo
	 * personalizadas, que altera a cor de fundo do botão quando o cursor do mouse
	 * passa por cima e a restaura quando o mouse sai.
	 * 
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
		button.setFont(button.getFont().deriveFont(18f));
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
	 * Método que identifica se o botão de "Página Inicial", "Meus Itinerários" ou o
	 * de "Criar Itinerário" foi acionado, redirecionando para as respectivas telas
	 * 
	 * @param event evento capturado pelo ActionListener
	 */
	public void actionPerformed(ActionEvent event) {
		Object src = event.getSource();

		if (src == homePageButton) {
			CompanyScreen.window.dispose();
			new HomeScreen();
		}

		if (src == itinerariesButton) {
			CompanyScreen.window.dispose();
			new CompanyItinerariesScreen(company);
		}

		if (src == createItineraryButton) {
			CompanyScreen.window.dispose();
			new ItineraryScreen(company);
		}

	}
}
