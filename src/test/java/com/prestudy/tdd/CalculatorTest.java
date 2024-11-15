package com.prestudy.tdd;

import static org.junit.jupiter.api.Assertions.*;

import com.prestudy.tdd.calculator.Calculator;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    void plus() {
        int result = Calculator.plus(1, 2);
        assertEquals(3, result);
        assertEquals(5, Calculator.plus(1, 4));
    }
}
