package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import controllers.*;

/**
 * This class is from the junitTests package and is responsible for the 3 Junit
 * test cases necessary
 * 
 * @author Gabriel Fernando
 *
 */
class CheckTicketData {

	@Test
	/**
	 * This test asserts the combinations that are valid and invalid for a ticket's
	 * price and seatNumber atributes
	 */
	void testCheckTicketData() {

		// only the value -1 is invalid
		float[] priceValues = { -1, 0, 1 };

		// only the value 1 is valid
		int[] seatNumberValues = { -1, 0, 1 };

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i != 0 && j == 2) {
					// the only valid option is if both of them are valid
					assertTrue(TicketController.checkTicketData(priceValues[i], seatNumberValues[j]));
				} else {
					assertFalse(TicketController.checkTicketData(priceValues[i], seatNumberValues[j]));
				}
			}
		}
	}
}

