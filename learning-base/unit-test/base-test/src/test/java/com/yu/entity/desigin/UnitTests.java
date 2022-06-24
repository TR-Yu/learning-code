package com.yu.entity.desigin;

import com.yu.TestMethodNameLogger;
import com.yu.desigin.UnitTest;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * 如何进行方法的设计
 * 边界值分析
 * 等价类
 *
 * @author YU
 * @date 2022-06-17 16:49
 */
@RunWith(Parameterized.class)
public class UnitTests {

    @Rule
    public final TestMethodNameLogger logger = new TestMethodNameLogger();

    @Parameterized.Parameters(name = "{index}_input_({0})_should_be_{1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {Integer.MAX_VALUE, false},
                {0, true},
                {5, true},
                {10, false},
                {11, true},
                {11, true},
                {-88, false},
                {Integer.MAX_VALUE, false}});
    }

    private int input;
    private boolean expected;

    public UnitTests(int input, boolean expected) {
        this.input = input;
        this.expected = expected;
    }


    @Test
    public void test() {
        Assert.assertEquals(expected, UnitTest.isPalindrome(input));
    }

}
