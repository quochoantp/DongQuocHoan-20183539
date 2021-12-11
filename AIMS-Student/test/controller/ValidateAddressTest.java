package controller;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author Hoan - 20183539
 */
class ValidateAddressTest {

	private PlaceOrderController placeOrderController;
	
	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController();
	}

	@ParameterizedTest
	@CsvSource({
		", false",
		"DaiCoViet!!@@, false",
		"So 1 Dai Co Viet, false",
		"So1DaiCoViet, true"
	})
	void test(String address, boolean expected) {
		// when
		boolean isValided = placeOrderController.validateAddress(address);
		
		// then
		assertEquals(expected, isValided);
	}

}
