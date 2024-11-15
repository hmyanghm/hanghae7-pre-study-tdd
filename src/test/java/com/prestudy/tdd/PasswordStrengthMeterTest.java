package com.prestudy.tdd;

import static org.junit.jupiter.api.Assertions.*;

import com.prestudy.tdd.passwordStrength.PasswordStrength;
import com.prestudy.tdd.passwordStrength.PasswordStrengthMeter;
import org.junit.jupiter.api.Test;

public class PasswordStrengthMeterTest {


    @Test
    void meetsAllCriteria_Then_String() { // 모든 규칙 충족
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("ab12!@AB");
        assertEquals(PasswordStrength.STRONG, result);
        PasswordStrength result2 = meter.meter("abc1!Add");
        assertEquals(PasswordStrength.STRONG, result2);
    }

    @Test
    void meetsOtherCriteria_except_for_Length_Then_Normal() { // 길이 8자 미만, 나머지 조건은 충족
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("ab12!@A");
        assertEquals(PasswordStrength.NORMAL, result);
    }

    @Test
    void meetsOtherCriteria_except_for_number_Then_Normal() { //숫자 미포함, 나머지 조건은 충족
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("abcdefg!@AA");
        assertEquals(PasswordStrength.NORMAL, result);
    }
}
