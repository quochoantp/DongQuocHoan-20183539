package controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author Hoan - 20183539
 */
public class ValidateInsRushOrderTest {

    private PlaceRushOrderController placeRushOrderController;

    @BeforeEach
    void setUp() {
        placeRushOrderController = new PlaceRushOrderController();
    }

    @ParameterizedTest
    @CsvSource({
            "Dung giao hang trong thu 6 vi toi di cong tac,true",
            "@dd12!3,false",
            "Tim nha so 8 ngach 8 nhe,true",
            "@123@,false",
            "!!!,false"
    })
    void test(String instruction, boolean expected) {
        boolean isValid = placeRushOrderController.validateRushOrderInstruction(instruction);
        Assertions.assertEquals(isValid, expected);
    }
}
