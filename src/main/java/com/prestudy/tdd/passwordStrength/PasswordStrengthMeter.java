package com.prestudy.tdd.passwordStrength;

public class PasswordStrengthMeter {

    public PasswordStrength meter(String s) {
        if (s.length() < 8 || !s.matches(".*\\d.*")) {
            return PasswordStrength.NORMAL;
        }
        return PasswordStrength.STRONG;
    }
}
