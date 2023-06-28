package view;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.swing.*;
import javax.swing.table.*;
import java.util.*;

import controllers.ItineraryController;
import models.Company;

public class ItineraryScreen implements ActionListener {
	private static JFrame window = new JFrame("Buser");
	private static JLabel title = new JLabel();
	private static JTextField originField = new JTextField();
	private static JTextField destinationField = new JTextField();
	private static JLabel dateLabel = new JLabel("Escolha o dia:");
	private static DefaultTableModel model;
	private static Calendar calendar = new GregorianCalendar();
	private static JLabel labelCalendar = new JLabel();
	private static JButton prevMonth = new JButton("<");
	private static JButton nextMonth = new JButton(">");
	private static LocalDate selectedDate;
	private static JTextField departureTimeField = new JTextField();
	private static JTextField arrivalTimeField = new JTextField();
	private static JButton registerButton = new JButton();
	private static JButton goBackButton = new JButton();
	private static Company company;

	public ItineraryScreen(Company company) {
		ItineraryScreen.company = company;
		title.setFont(new Font("Serif", Font.BOLD, 36));
		title.setText("Novo Itinerário");
		title.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBorder(BorderFactory.createEmptyBorder(55, 55, 55, 55));

		JPanel formContainer = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 25, 8, 25);
		formContainer.setBackground(new Color(250, 250, 250));

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		formContainer.add(title, gbc);

		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		formContainer.add(textField(originField, "Cidade de Origem"), gbc);

		gbc.gridy = 2;
		formContainer.add(textField(destinationField, "Cidade de Destino"), gbc);

		JPanel dateContainer = new JPanel(new GridLayout(1, 2, 0, 0));
		dateContainer.setPreferredSize(new Dimension(dateContainer.getPreferredSize().width, 180));
		dateContainer.setBackground(null);

		JPanel calendarContainer = new JPanel();
		calendarContainer.setLayout(new BorderLayout());
		calendarContainer.setBackground(new Color(250, 250, 250));

		JPanel labelRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labelRow.add(dateLabel);
		labelRow.setPreferredSize(new Dimension(labelRow.getPreferredSize().width, 30));

		JPanel calendarBox = new JPanel();

		JPanel calendarMonthInfo = new JPanel(new GridLayout(1, 3, 30, 0));
		calendarMonthInfo.add(prevMonth);
		calendarMonthInfo.add(labelCalendar);
		calendarMonthInfo.add(nextMonth);

		String[] columns = { "D", "S", "T", "Q", "Q", "S", "S" };
		model = new DefaultTableModel(null, columns);
		JTable table = new JTable(model) {
			@Override
			public TableCellRenderer getCellRenderer(int row, int column) {
				DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				return renderer;
			}
		};
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				int selectedRow = table.getSelectedRow();
				int selectedColumn = table.getSelectedColumn();
				Object selectedValue = table.getValueAt(selectedRow, selectedColumn);
				if (selectedValue != null) {
					int day = (int) selectedValue;
					calendar.set(Calendar.DAY_OF_MONTH, day);
					Date date = calendar.getTime();
					selectedDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				}
			}
		});
		JScrollPane days = new JScrollPane(table);

		calendarBox.add(calendarMonthInfo);
		calendarBox.add(days);

		calendarContainer.add(labelRow, BorderLayout.NORTH);
		calendarContainer.add(calendarBox, BorderLayout.CENTER);

		JPanel timerContainer = new JPanel();
		timerContainer.setLayout(new BorderLayout());
		timerContainer.setBorder(BorderFactory.createEmptyBorder(0, 50, 70, 0));

		JPanel scheduleContainer = new JPanel(new GridLayout(2, 1, 0, 15));
		scheduleContainer.add(textField(departureTimeField, "Horário de Partida"));
		scheduleContainer.add(textField(arrivalTimeField, "Horário de Chegada"));

		timerContainer.add(scheduleContainer);

		dateContainer.add(calendarContainer);
		dateContainer.add(timerContainer);

		gbc.gridy = 3;
		formContainer.add(dateContainer, gbc);

		gbc.gridy = 4;
		formContainer.add(button(registerButton, "Criar Itinerário"), gbc);

		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		formContainer.add(goBack(goBackButton, "Voltar"), gbc);

		container.add(formContainer);

		this.updateMonth();
		window.setContentPane(container);
		window.setSize(800, 600);
		window.getContentPane().setBackground(new Color(250, 250, 250));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.requestFocusInWindow();

		prevMonth.addActionListener(this);
		nextMonth.addActionListener(this);
		registerButton.addActionListener(this);
		goBackButton.addActionListener(this);
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

	private void updateMonth() {
		calendar.set(Calendar.DAY_OF_MONTH, 1);

		String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
		int year = calendar.get(Calendar.YEAR);
		labelCalendar.setText(month + " " + year);

		int startDay = calendar.get(Calendar.DAY_OF_WEEK);
		int numberOfDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int weeks = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);

		model.setRowCount(0);
		model.setRowCount(weeks);

		int i = startDay - 1;
		for (int day = 1; day <= numberOfDays; day++) {
			model.setValueAt(day, i / 7, i % 7);
			i = i + 1;
		}
	}

	public void actionPerformed(ActionEvent event) {
		Object src = event.getSource();

		if (src == prevMonth) {
			calendar.add(Calendar.MONTH, -1);
			updateMonth();
		}

		if (src == nextMonth) {
			calendar.add(Calendar.MONTH, +1);
			updateMonth();
		}

		if (src == registerButton) {
			String origin = originField.getText();
			String destination = destinationField.getText();
			String departureTime = departureTimeField.getText();
			String arrivalTime = arrivalTimeField.getText();

			String errorMessage = ItineraryController.validatesItinararyData(origin, destination, selectedDate,
					departureTime, arrivalTime);

			if (errorMessage.isEmpty()) {
				ItineraryController.createItinerary(origin, destination, selectedDate, departureTime, arrivalTime,
						company);
				JOptionPane.showMessageDialog(window, "Iinerário cadastrado com sucesso!");
				ItineraryScreen.window.dispose();
				new CompanyItinerariesScreen(company);
			} else {
				JOptionPane.showMessageDialog(window, "Erro(s) de validação:\n\n" + errorMessage);
			}
		}

		if (src == goBackButton) {
			ItineraryScreen.window.dispose();
			new CompanyScreen(company);
		}
	}
}
