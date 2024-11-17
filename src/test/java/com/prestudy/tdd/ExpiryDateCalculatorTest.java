package com.prestudy.tdd;

import com.prestudy.tdd.calculator.ExpiryDateCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {

    @Test
    @DisplayName("만원 납부 시, 한 달 뒤 만료일")
    void pay_10000_won() {

        assertExpiryDate(LocalDate.of(2024, 11, 17)
                , 10_000, LocalDate.of(2024, 12, 17));

        assertExpiryDate(LocalDate.of(2024, 12, 17)
                , 10_000, LocalDate.of(2025, 1, 17));
        /*LocalDate billingDate = LocalDate.of(2024, 11, 17);
        int payAmount = 10_000;

        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate expiryDate = cal.calculateExpiryDate(billingDate, payAmount);

        assertEquals(LocalDate.of(2024, 12, 17), expiryDate);*/

        /*LocalDate billingDate2 = LocalDate.of(2024, 12, 17);
        int payAmount2 = 10_000;

        ExpiryDateCalculator cal2 = new ExpiryDateCalculator();
        LocalDate expiryDate2 = cal2.calculateExpiryDate(billingDate2, payAmount2);

        assertEquals(LocalDate.of(2025, 01, 17), expiryDate2);*/
    }

    @Test
    @DisplayName("납부일과 한달 뒤 일자가 같지 않음")
    void paymentDate_Mismatch() {
        assertExpiryDate(LocalDate.of(2024, 1, 31), 10_000,
                LocalDate.of(2024, 2, 29));

    }

    private void assertExpiryDate(LocalDate billingDate, int payAmount, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator calculator = new ExpiryDateCalculator();
        LocalDate expiryDate = calculator.calculateExpiryDate(billingDate, payAmount);
        assertEquals(expectedExpiryDate, expiryDate);
    }
}
