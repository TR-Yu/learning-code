package com.yu.command.command.command;

import com.yu.command.command.ActalOperator;
import com.yu.command.command.CommandInterface;

/**
 * 具体的命令: 包括了有具体的实现者，需要入参的参数
 *
 * @author YU
 * @date 2022-05-05 11:06
 */
public class ConcreteCommand implements CommandInterface {

    private final ActalOperator actalOperator;

    private final String  numParam;

    public ConcreteCommand(ActalOperator actalOperator,String numParam) {
        this.actalOperator = actalOperator;
        this.numParam = numParam;
    }

    @Override
    public void execute() {
        this.actalOperator.print(this.numParam);
    }
}
