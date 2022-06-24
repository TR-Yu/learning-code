package com.yu.desigin;

import com.yu.entity.InputParam;

import java.math.BigDecimal;

/**
 * @author YU
 * @date 2022-06-17 21:53
 */
public class EntityPrint {

    public static String printEntity(InputParam inputParam, boolean isLowCase){
        Integer id = inputParam.getId();
        String description = inputParam.getDescription();
        BigDecimal price = inputParam.getPrice();
        if(isLowCase){
            description = inputParam.getDescription().toLowerCase();
        }
        String returnValue = String.format("id: %d, price: %s, description: %s", id, price.toString(), description);
        System.out.println(returnValue);
        return returnValue;
    }
}
