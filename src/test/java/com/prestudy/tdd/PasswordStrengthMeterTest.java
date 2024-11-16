package com.prestudy.tdd;

import com.prestudy.tdd.passwordStrength.PasswordStrength;
import com.prestudy.tdd.passwordStrength.PasswordStrengthMeter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthMeterTest {

    private PasswordStrengthMeter meter = new PasswordStrengthMeter();
    private void assertStrength(String password, PasswordStrength expStr) {
        assertEquals(expStr, meter.meter(password));
    }


    @Test
    @DisplayName("모든 규칙 충족")
    void meetsAllCriteria_Then_String() {
        /*PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("ab12!@AB");
        assertEquals(PasswordStrength.STRONG, result);
        PasswordStrength result2 = meter.meter("abc1!Add");
        assertEquals(PasswordStrength.STRONG, result2);*/
        assertStrength("ab12!@AB", PasswordStrength.STRONG);
        assertStrength("abc1!Add", PasswordStrength.STRONG);
    }

    @Test
    @DisplayName("길이 8자 미만, 나머지 조건은 충족")
    void meetsOtherCriteria_except_for_Length_Then_Normal() {
        /*PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("ab12!@A");
        assertEquals(PasswordStrength.NORMAL, result);*/
        assertStrength("ab12!@A", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("숫자 미포함, 나머지 조건은 충족")
    void meetsOtherCriteria_except_for_number_Then_Normal() {
        /*PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("abcdefg!@AA");
        assertEquals(PasswordStrength.NORMAL, result);*/
        assertStrength("abcdefg!@AA", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("입력이 null or empty or black인 경우")
    void nullInput_Then_Invalid() {
        assertStrength(null, PasswordStrength.INVALID);
        assertStrength("", PasswordStrength.INVALID);
        assertStrength(" ", PasswordStrength.INVALID);
    }

    @Test
    @DisplayName("대문자 미포함, 나머지 조건은 충족")
    void meetsOtherCriteria_except_for_Uppercase_Then_Normal() {
        assertStrength("abcd!@#1212", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("길이가 8글자 이상인 조건만 충족")
    void meetsOnlyLenghCriteria_Then_Weak() {
        assertStrength("abcdefghijk", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("숫자 조건만 충족")
    void meetsOnlyNumCriteria_Then_Weak() {
        assertStrength("12345", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("대문자 조건만 충족")
    void meetsOnlyUpperCriteria_Then_Weak() {
        assertStrength("ABCDE", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("아무 조건도 충족하지 않는 경우")
    void meetsNoCriteria_Then_Weak() {
        assertStrength("abc", PasswordStrength.WEAK);
    }
}
