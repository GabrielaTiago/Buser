package view;

import java.awt.*;
import javax.swing.*;

/**
 * Represents a custom JPanel that serves as a container for other components.
 * It provides methods for adding components to the container using a
 * GridBagLayout.
 * 
 * @author Gabriela Tiago
 * @since 2023
 * @version 1.1
 * 
 */
public class WrapperContainer extends JPanel {
	/**
	 * A version number for serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new WrapperContainer with a GridBagLayout
	 */
	public WrapperContainer() {
		setLayout(new GridBagLayout());
		setBackground(null);
	}

	/**
	 * 
	 * Adds the specified JPanel to the container using the GridBagLayout
	 *
	 * @param component the JPanel to be added to the container
	 * 
	 */
	public void addComponents(JPanel component) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = getComponentCount();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(8, 0, 8, 0);

		add(component, gbc);
	}
}
