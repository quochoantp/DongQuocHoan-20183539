package controller;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.impl.ShippingFeeCalculatorImpl;

/**
 * @author Hoan - 20183539
 */
class ValidateNameTest {

	private PlaceOrderController placeOrderController;
	
	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController(new ShippingFeeCalculatorImpl());
	}

	@ParameterizedTest
	@CsvSource({
		"HoanDq, true",
		"hoan..@@!!, false",
		"Dong Quoc Hoan, false",
		"hoan123 , false",
		", false",
		
	})
	void test(String name, boolean expected) {
		// when
		boolean isValided = placeOrderController.validateName(name);
		
		// then
		assertEquals(expected, isValided);
	}

}
