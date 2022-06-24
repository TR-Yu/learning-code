package com.yu.entity.desigin;

import com.sun.javafx.binding.StringFormatter;
import com.yu.desigin.EntityPrint;
import com.yu.entity.InputParam;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author YU
 * @date 2022-06-17 21:59
 */
@RunWith(Parameterized.class)
public class EntityPrintTest {

   private final static String strfmt = "id: %d, price: %s, description: %s";

   @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new InputParam(1, new BigDecimal("2.33"), "test01"), true,
                        String.format(strfmt, 1, "2.33", "test01".toLowerCase()), true },
                {new InputParam(2, new BigDecimal("33.00"), "tEst02"), false,
                        String.format(strfmt, 2, "33.00", "tEst02".toLowerCase()), false },
                {new InputParam(3, new BigDecimal("-234"), "tesT--03"), true,
                        String.format(strfmt, 3, "-234", "tesT--03".toLowerCase()),true },
                {new InputParam(4, new BigDecimal("-12.34"), "tE:st04"), false,
                        String.format(strfmt, 4, "-12.34", "tE:st04".toLowerCase()), false},
                {new InputParam(5, new BigDecimal("0.1234"), "TEST05"), false,
                        String.format(strfmt, 5, "0.1234", "TEST05"), true},
                {new InputParam(6, new BigDecimal("212341234"), "TES-er06"), true,
                        String.format(strfmt, 6, "212341234", "TES-er06".toLowerCase()), true},
        });
    }

    private final InputParam inputParam;

    private final boolean isLowerCase;

    private final String returnValue;

    private final boolean result;

    public EntityPrintTest(InputParam inputParam, boolean isLowerCase, String returnValue, boolean result) {
        this.inputParam = inputParam;
        this.isLowerCase = isLowerCase;
        this.returnValue = returnValue;
        this.result = result;
    }

    @Test
    public void printTest(){
        System.out.println(returnValue);
       Assert.assertSame(returnValue.equals(EntityPrint.printEntity(inputParam,isLowerCase)),result);
    }
}
