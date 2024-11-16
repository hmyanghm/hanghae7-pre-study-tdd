package com.prestudy.tdd.passwordStrength;


public class PasswordStrengthMeter {

    public PasswordStrength meter(String s) {
        if (s == null || s.isEmpty() || s.isBlank()) {
            return PasswordStrength.INVALID;
        }
        int metCounts = getMetCriteriaCounts(s);

        if (metCounts <= 1) {
            return PasswordStrength.WEAK;
        }
        /* 1개의 조건만 충족하면 WEAK 리팩토링 후 주석 처리
        if (lengthEnough && !s.matches(".*\\d.*") && !s.matches(".*[A-Z].*")) {
            return PasswordStrength.WEAK;
        } else if (s.matches(".*\\d.*") && !lengthEnough && !s.matches(".*[A-Z].*")) {
            return PasswordStrength.WEAK;
        } else if (s.matches(".*[A-Z].*") && !lengthEnough && !s.matches(".*\\d.*")) {
            return PasswordStrength.WEAK;
        }*/
        if (metCounts == 2) {
            return PasswordStrength.NORMAL;
        }
        /* 2개의 조건을 충족하면 NORMAL 리팩토링 후 주석 처리
        if (!lengthEnough || !s.matches(".*\\d.*") || !s.matches(".*[A-Z].*")) {
            return PasswordStrength.NORMAL;
        }*/
        return PasswordStrength.STRONG;
    }

    private int getMetCriteriaCounts(String s) {
        int metCounts = 0;
        boolean lengthEnough = s.length() >= 8;
        if (lengthEnough) {
            metCounts++;
        }
        if (s.matches(".*\\d.*")) {
            metCounts++;
        }
        if (s.matches(".*[A-Z].*")) {
            metCounts++;
        }
        return metCounts;
    }
}
