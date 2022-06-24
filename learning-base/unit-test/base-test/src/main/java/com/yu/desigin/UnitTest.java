package com.yu.desigin;

/**
 * @author YU
 * @date 2022-06-17 16:33
 */
public class UnitTest {

    public static boolean isPalindrome(int inputValue) {
        if (inputValue < 0) {
            return false;
        }
        int reverseValue = 0;
        int intermediateValue = inputValue;
        while (intermediateValue != 0) {
            reverseValue = reverseValue * 10 + intermediateValue % 10;
            intermediateValue /= 10;
        }
        return reverseValue == inputValue;
    }
}
