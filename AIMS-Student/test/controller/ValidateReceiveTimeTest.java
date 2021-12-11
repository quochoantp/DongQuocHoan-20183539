package controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author Hoan - 20183539
 */
public class ValidateReceiveTimeTest {

    private PlaceRushOrderController placeRushOrderController;

    @BeforeEach
    void setUp() {
        placeRushOrderController = new PlaceRushOrderController();
    }

    @ParameterizedTest
    @CsvSource({
            "12-11-2021 09:00,true",
            "12-11-1024 09:00,false",
            "12-11-4024 09:00,false",
            "02-31-2021 09:00,false",
            "12/11/2021 09:00,false",
            "31/02/2021 09:00,false",
            "12/13/2021 09:00,false",
            "12-11/2021 09:00,false",
            "12-11-2021 30:00,false",
            "53-11-2021 09:00,false"
    })
    void test(String time, boolean expected) {
    	// when
        boolean isValid = placeRushOrderController.validateReceiveTime(time);
        
        // then
        Assertions.assertEquals(isValid, expected);
    }
}
