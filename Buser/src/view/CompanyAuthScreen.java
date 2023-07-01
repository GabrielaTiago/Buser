package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.AuthController;
import database.Database;
import models.Company;

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
		authContainer.add(button(registerButton, "Cadastrar"), gbc);

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
		linkTo.addActionListener(this);
	}

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

	private JButton linkButton(JButton linkButton, String text) {
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

	public static void main(String[] args) {
		new CompanyAuthScreen();
		Database.teste();
	}

	public void actionPerformed(ActionEvent event) {
		Object src = event.getSource();

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

			if (errorMessage.isEmpty()) {
				JOptionPane.showMessageDialog(window, "Cadastro realizado com sucesso!");
				AuthController.loginCompany(companyData);
				new CompanyScreen(companyData);
				CompanyAuthScreen.window.dispose();
			} else {
				JOptionPane.showMessageDialog(window, "Erro(s) de validação:\n\n" + errorMessage);
			}
		}

		if (src == linkTo) {
			new ClientAuthScreen();
			CompanyAuthScreen.window.dispose();
		}
	}
}
