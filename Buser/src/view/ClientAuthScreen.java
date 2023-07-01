package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.AuthController;
import models.Client;
/**
 *Essa classe é responsável pela tela usada para cadastrar os clientes no sistema.
 *Inclui campos para inserir nome, email, telefone, endereço, CPF e opção de gratuidade.
 *Também possui botões para cadastrar e um link para a tela de cadastro de empresa.
 *@author Gabriela Tiago
 *
 */
public class ClientAuthScreen implements ActionListener {
	private static JFrame window = new JFrame("Buser");
	private static JLabel title = new JLabel("Boas-vindas à Buser!");
	private static JTextField nameField = new JTextField();
	private static JTextField emailField = new JTextField();
	private static JTextField phoneField = new JTextField();
	private static JTextField addressField = new JTextField();
	private static JTextField cpfField = new JTextField();
	private static JComboBox<String> gratuityOptions = new JComboBox<>();
	private static JButton registerButton = new JButton();
	private static JButton linkTo = new JButton();
	
	/**
    	Adiciona compenentes ao JFrame window para o cadatsro de clientes.
    	@see #button(JButton, String) 
		@see #linkButton(JButton, String)
		@see #textField(JTextField, String)
	 */
	public ClientAuthScreen() {
		title.setFont(new Font("Serif", Font.BOLD, 36));
		title.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel contentPane = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		contentPane.add(title, gbc);

		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		contentPane.add(textField(nameField, "Nome"), gbc);

		gbc.gridy = 2;
		contentPane.add(textField(emailField, "Email"), gbc);

		gbc.gridy = 3;
		contentPane.add(textField(phoneField, "Telefone"), gbc);

		gbc.gridy = 4;
		contentPane.add(textField(addressField, "Endereço"), gbc);

		gbc.gridy = 5;
		contentPane.add(textField(cpfField, "CPF"), gbc);

		gbc.gridy = 6;
		contentPane.add(selectGratuityOption(gratuityOptions, "Tipo de gratuidade"), gbc);

		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(30, 10, 10, 10);
		contentPane.add(button(registerButton, "Cadastrar"), gbc);

		gbc.gridy = 8;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10, 10, 10, 10);
		contentPane.add(linklinkButton(linkTo, "Cadastro de Empresa"), gbc);

		window.setContentPane(contentPane);

		window.setSize(800, 600);
		window.getContentPane().setBackground(new Color(250, 250, 250));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.requestFocusInWindow();

		registerButton.addActionListener(this);
		linkTo.addActionListener(this);
	}
	/**
	 * Configura um botão com o texto fornecido e configurações de estilo personalizadas, que
	 * altera a cor de fundo do botão quando o cursor do mouse passa por cima 
	 * e a restaura quando o mouse sai.
	 * @param button o botão a ser configurado
	 * @param text   o texto a ser exibido no botão
	 * @return o botão configurado
	 */
	private JTextField textField(JTextField textField, String placeholder) {
		textField.setOpaque(false);
		textField.setPreferredSize(new Dimension(textField.getPreferredSize().width, 30));

		textField.setForeground(new Color(117, 117, 138));
		textField.setText(placeholder);

		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent event) {
				if (textField.getText().equals(placeholder)) {
					textField.setText("");
					textField.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent event) {
				if (textField.getText().isEmpty()) {
					textField.setForeground(new Color(117, 117, 138));
					textField.setText(placeholder);
				}
			}
		});

		return textField;
	}
	/**
	 * Configura uma caixa de seleção do tipo JComboBox com opções de gratuidade.
	 *
	 * @param comboBox A caixa de seleção a ser configurada.
	 * @param label    O rótulo a ser exibido como a primeira opção da caixa de seleção.
	 * @return A caixa de seleção configurada.
	 */
	private JComboBox<String> selectGratuityOption(JComboBox<String> comboBox, String label) {
		if (comboBox.getItemCount() == 0) {
			comboBox.addItem(label);
			comboBox.addItem("Sem gratuidade");
			comboBox.addItem("Idade");
			comboBox.addItem("Deficiência física");
		}
		comboBox.setForeground(new Color(117, 117, 138));
		comboBox.setPreferredSize(new Dimension(comboBox.getPreferredSize().width, 30));
		comboBox.setBorder(BorderFactory.createEmptyBorder());
		comboBox.setEditable(false);

		return comboBox;
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
	 * Configura um botão com o texto fornecido e configura com um estilo personalizado,
	 * sublinhando o botão quando o cursor do mouse passa por cima 
	 * e voltando ao normal quando o mouse sai.
	 * @param button o botão a ser configurado
	 * @param text   o texto a ser exibido no botão
	 * @return o botão configurado
	 */
	private JButton linklinkButton(JButton linkButton, String text) {
		linkButton.setText(text);
		linkButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		linkButton.setBackground(null);
		linkButton.setBorder(null);

		linkButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				linkButton.setText("<html><u>" + text + "</u></html>");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				linkButton.setText(text);
			}
		});

		return linkButton;
	}
	/**
	 * Trata os eventos de ação do botão de cadastro e do link para o cadastro de empresa.
	 * Caso, os dados inseridos sejam validados, o cadastro é realizado, caso contrário, uma
	 * mensagem de erro será exibida na tela
	 * @param event o evento de ação
	 */
	public void actionPerformed(ActionEvent event) {
		Object src = event.getSource();

		if (src == registerButton) {
			String name = nameField.getText();
			String email = emailField.getText();
			String phone = phoneField.getText();
			String address = addressField.getText();
			String cpf = cpfField.getText();
			String gratuityType = (String) gratuityOptions.getSelectedItem();

			Client clientData = new Client(name, email, phone, address, cpf, gratuityType);
			String errorMessage = AuthController.validatesClientData(clientData);

			if (errorMessage.isEmpty()) {
				JOptionPane.showMessageDialog(window, "Cadastro realizado com sucesso!");
				AuthController.loginCLient(clientData);
			} else {
				JOptionPane.showMessageDialog(window, "Erro(s) de validação:\n\n" + errorMessage);
			}
		}

		if (src == linkTo) {
			new CompanyAuthScreen();
			ClientAuthScreen.window.dispose();
		}
	}
}
