package com.rizkirm.challenge.bank.util;

import java.util.Random;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
public class GeneratorUtil {

    public String generate10RandomDigits() {
        final char[] digits = "0123456789".toCharArray();

        Random random = new Random();
        int index = digits.length;

        while (index > 1) {
            final int pos = random.nextInt(index--);
            final char tmp = digits[pos];
            digits[pos] = digits[index];
            digits[index] = tmp;
        }

        return new String(digits);
    }

}