package view;

import java.awt.*;
import javax.swing.*;

public class WrapperContainer extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrapperContainer() {
		setLayout(new GridBagLayout());
		setBackground(null);
	}

	public void addComponents(JPanel itinerary) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = getComponentCount();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(8, 0, 8, 0);

		add(itinerary, gbc);
	}
}
