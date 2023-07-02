package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.AuthController;
import database.Database;
import models.Company;

/**
 * Screen used to register the client in the application
 * 
 * @author Gabriela Tiago
 * @since 2023
 * @version 1.4
 * 
 */
public class CompanyAuthScreen implements ActionListener {

	private static JFrame window = new JFrame("Buser");
	private static JLabel title = new JLabel("Boas-vindas à Buser!");
	private static JTextField nameField = new JTextField();
	private static JTextField emailField = new JTextField();
	private static JTextField passwordField = new JPasswordField();
	private static JTextField phoneField = new JTextField();
	private static JTextField addressField = new JTextField();
	private static JTextField cnpjField = new JTextField();
	private static JTextField corporateNameField = new JTextField();
	private static JButton registerButton = new JButton();
	private static JButton loginButton = new JButton();
	private static JButton linkTo = new JButton();

	/**
	 * Adds components to the screen
	 */
	public CompanyAuthScreen() {
		title.setFont(new Font("Serif", Font.BOLD, 36));
		title.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBorder(BorderFactory.createEmptyBorder(55, 55, 55, 55));

		JPanel authContainer = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 25, 8, 25);
		authContainer.setBackground(new Color(250, 250, 250));

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		authContainer.add(title, gbc);

		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		authContainer.add(textField(nameField, "Nome"), gbc);

		gbc.gridy = 2;
		authContainer.add(textField(emailField, "Email"), gbc);

		gbc.gridy = 3;
		authContainer.add(passwordField((JPasswordField) passwordField, "Senha"), gbc);

		gbc.gridy = 4;
		authContainer.add(textField(phoneField, "Telefone"), gbc);

		gbc.gridy = 5;
		authContainer.add(textField(addressField, "Endereço"), gbc);

		gbc.gridy = 6;
		authContainer.add(textField(cnpjField, "CNPJ"), gbc);

		gbc.gridy = 7;
		authContainer.add(textField(corporateNameField, "Razão Social"), gbc);

		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(30, 25, 10, 25);
		authContainer.add(button(registerButton, "Cadastrar Empresa"), gbc);

		gbc.gridy = 9;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10, 25, 10, 25);
		authContainer.add(linkButton(loginButton, "Já possui cadastro como empresa? Clique aqui para entrar"), gbc);

		gbc.gridy = 10;
		authContainer.add(linkButton(linkTo, "Cadastro de Cliente"), gbc);

		container.add(authContainer, BorderLayout.CENTER);

		window.setContentPane(container);
		window.setSize(800, 700);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.requestFocusInWindow();

		registerButton.addActionListener(this);
		loginButton.addActionListener(this);
		linkTo.addActionListener(this);
	}

	/**
	 * Sets up custom styles and events for JTextField component.Adding a
	 * placeholder inside the component and changing the default color of the text
	 * at different times
	 * 
	 * @param textField   Text field to be configured
	 * @param placeholder Text to display as placeholder
	 * 
	 * @return The configured component
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
	 * Sets up custom styles and events for JPasswordField component. Replacing the
	 * initial censor for placeholder text, then adding inside the component.
	 * 
	 * @param passwordField Password field to be configured
	 * @param placeholder   Text to display as placeholder
	 * 
	 * @return The configured component
	 */
	private JPasswordField passwordField(JPasswordField passwordField, String placeholder) {
		passwordField.setOpaque(false);
		passwordField.setPreferredSize(new Dimension(passwordField.getPreferredSize().width, 30));
		passwordField.setForeground(new Color(117, 117, 138));
		passwordField.setText(placeholder);
		passwordField.setEchoChar((char) 0);

		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent event) {
				if (String.valueOf(passwordField.getPassword()).equals(placeholder)) {
					passwordField.setText("");
					passwordField.setEchoChar('\u2022');
					passwordField.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent event) {
				if (String.valueOf(passwordField.getPassword()).isEmpty()) {
					passwordField.setEchoChar((char) 0);
					passwordField.setForeground(new Color(117, 117, 138));
					passwordField.setText(placeholder);
				}
			}
		});

		return passwordField;
	}

	/**
	 * Custom style settings and events for JButton component. Changing the
	 * background and text color, set height and font size. Adding events when the
	 * mouse cursor passes over and restoring when the mouse exits.
	 * 
	 * @param button Button to be configured
	 * @param text   Text to be displayed in the button
	 * 
	 * @return The configured component
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
	 * Custom style settings and events for JButton component. Add underlining the
	 * button when the mouse cursor passes over it, and returning to normal when the
	 * mouse leaves.
	 * 
	 * @param linkButton Button to be configured
	 * @param text       Text to be displayed in the button
	 * 
	 * @return The configured component
	 */
	private JButton linkButton(JButton linkButton, String text) {
		linkButton.setText(text);
		linkButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		linkButton.setBackground(null);
		linkButton.setBorder(null);

		linkButton.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				linkButton.setText("<html><u>" + text + "</u></html>");
			}

			public void mouseExited(MouseEvent e) {
				linkButton.setText(text);
			}
		});

		return linkButton;
	}

	/**
	 *  Main method. Used for code execution
	 * 
	 * @param args 
	 */
	public static void main(String[] args) {
		new CompanyAuthScreen();
		Database.teste();
	}

	/**
	 * Handles the screen action events
	 * 
	 * @param event Action Event
	 */
	public void actionPerformed(ActionEvent event) {
		Object src = event.getSource();

		// Click on the company registration button, takes the data from the text
		// fields, and sends them to the validation.
		if (src == registerButton) {
			String name = nameField.getText();
			String email = emailField.getText();
			String password = passwordField.getText();
			String phone = phoneField.getText();
			String address = addressField.getText();
			String cnpj = cnpjField.getText();
			String corporateName = corporateNameField.getText();

			Company companyData = new Company(name, email, password, phone, address, cnpj, corporateName);
			String errorMessage = AuthController.validatesCompanyData(companyData);

			// If the data is valid, the login screen opens
			if (errorMessage.isEmpty()) {
				JOptionPane.showMessageDialog(window, "Cadastro realizado com sucesso!");
				AuthController.registerCompany(companyData);
				CompanyAuthScreen.window.dispose();
				new LoginClientScreen();
			} else {
				JOptionPane.showMessageDialog(window, "Erro(s) de validação:\n\n" + errorMessage);
			}

			// Click the company login button, opens the company login screen
			if (src == loginButton) {
				CompanyAuthScreen.window.dispose();
				new LoginCompanyScreen();
			}

			// Click on the client registration button, the client registration screen opens
			if (src == linkTo) {
				CompanyAuthScreen.window.dispose();
				new ClientAuthScreen();
			}
		}
	}
}
