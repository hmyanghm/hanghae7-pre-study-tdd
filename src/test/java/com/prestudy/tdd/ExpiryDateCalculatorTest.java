package com.prestudy.tdd;

import com.prestudy.tdd.calculator.ExpiryDateCalculator;
import com.prestudy.tdd.calculator.PayData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {

    @Test
    @DisplayName("만원 납부 시, 한 달 뒤 만료일")
    void pay_10000_won() {

        assertExpiryDate(PayData.builder()
                                .billingDate(LocalDate.of(2024,11,17))
                                .payAmount(10_000)
                                .build(),
                        LocalDate.of(2024, 12, 17));

        assertExpiryDate(PayData.builder()
                                .billingDate(LocalDate.of(2024,12,17))
                                .payAmount(10_000)
                                .build(),
                        LocalDate.of(2025, 1, 17));
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
        assertExpiryDate(PayData.builder()
                                .billingDate(LocalDate.of(2024,1,31))
                                .payAmount(10_000)
                                .build(),
                        LocalDate.of(2024, 2, 29));

    }

    @Test
    @DisplayName("납부일과 만료일자가 다를 때, 만원 납부 시 납부일 기준으로 다음 만료일 정함")
    void paymentDate_Mismatch_restart() {
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2024,1,31))
                .billingDate(LocalDate.of(2024,2,29))
                .payAmount(10_000)
                .build();

        assertExpiryDate(payData, LocalDate.of(2024,3,31));

        PayData payData2 = payData.builder()
                .firstBillingDate(LocalDate.of(2024,1,31))
                .billingDate(LocalDate.of(2024,2,29))
                .payAmount(10_000)
                .build();

        assertExpiryDate(payData2, LocalDate.of(2024,3,31));

        PayData payData3 = payData.builder()
                .firstBillingDate(LocalDate.of(2024,5,31))
                .billingDate(LocalDate.of(2024,6,30))
                .payAmount(10_000)
                .build();

        assertExpiryDate(payData3, LocalDate.of(2024,7,31));

        assertExpiryDate(PayData.builder()
                .firstBillingDate(LocalDate.of(2024,1,31))
                .billingDate(LocalDate.of(2024,2,29))
                .payAmount(40_000).build(), LocalDate.of(2024,6,30));
        assertExpiryDate(PayData.builder()
                .firstBillingDate(LocalDate.of(2024,3,31))
                .billingDate(LocalDate.of(2024,4,30))
                .payAmount(30_000).build(), LocalDate.of(2024,7,31));
    }

    @Test
    @DisplayName("2만원 이상 납부 시, 납부일 비례해서 만료일 계산")
    public void pay_20000_won() {
        assertExpiryDate(PayData.builder()
                .firstBillingDate(LocalDate.of(2024,1,1))
                .billingDate(LocalDate.of(2024,3,1))
                .payAmount(20_000).build(), LocalDate.of(2024,5,1));
    }

    @Test
    @DisplayName("십만원을 납부하면 1년 제공")
    public void pay_100000_1_year() {
        assertExpiryDate(PayData.builder()
                .billingDate(LocalDate.of(2024,1,26))
                .payAmount(220_000)
                .build(), LocalDate.of(2026, 3, 26));

    }


    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator calculator = new ExpiryDateCalculator();
        LocalDate expiryDate = calculator.calculateExpiryDate(payData);
        assertEquals(expectedExpiryDate, expiryDate);
    }
}
