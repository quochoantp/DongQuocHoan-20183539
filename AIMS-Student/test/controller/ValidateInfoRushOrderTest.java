package controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.impl.RushOrderValidator;

/**
 * @author Hoan - 20183539
 */
public class ValidateInfoRushOrderTest {

    private PlaceRushOrderController placeRushOrderController;

    @BeforeEach
    void setUp() {
    	placeRushOrderController = new PlaceRushOrderController(new RushOrderValidator());
    }

    @ParameterizedTest
    @CsvSource({
            "Hay giao hang vao nhung thu 2 4 6,true",
            "@@giao hang,false",
            "Co the giao hang vao moi thoi diem trong tuan,true",
            "- ngay thu 2!,false",
            "***,false"
    })
    void test(String info, boolean expected) {
        boolean isValid = placeRushOrderController.validateRushOrderInfo(info);
        Assertions.assertEquals(isValid, expected);
    }
}
