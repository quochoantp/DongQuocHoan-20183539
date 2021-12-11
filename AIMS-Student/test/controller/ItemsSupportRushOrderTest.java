package controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author Hoan - 20183539
 */
public class ItemsSupportRushOrderTest {

    private PlaceRushOrderController placeRushOrderController;

    @BeforeEach
    void setUp() throws Exception {
        placeRushOrderController = new PlaceRushOrderController();
    }

    @ParameterizedTest
    @CsvSource({
            "59,false",
            "259,false",
            "200,true",
            "134,false"
    })
    void test(int mediaID, boolean expected) {
    	// when
        boolean isValid = placeRushOrderController.isItemsSupportRushOrder(mediaID);
        
        // then
        Assertions.assertEquals(isValid, expected);
    }
}
