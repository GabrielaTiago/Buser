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
	private static JTextField phoneField = new JTextField();
	private static JTextField addressField = new JTextField();
	private static JTextField cnpjField = new JTextField();
	private static JTextField corporateNameField = new JTextField();
	private static JButton registerButton = new JButton();
	private static JButton linkTo = new JButton();

	public CompanyAuthScreen() {
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
		contentPane.add(textField(cnpjField, "CNPJ"), gbc);

		gbc.gridy = 6;
		contentPane.add(textField(corporateNameField, "Razão Social"), gbc);

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
		contentPane.add(linkButton(linkTo, "Cadastro de Cliente"), gbc);

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
			String phone = phoneField.getText();
			String address = addressField.getText();
			String cnpj = cnpjField.getText();
			String corporateName = corporateNameField.getText();

			Company companyData = new Company(name, email, phone, address, cnpj, corporateName);
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
