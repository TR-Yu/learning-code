package com.yu.command.command;

/**
 * 命令发送方
 *
 * @author YU
 * @date 2022-05-05 11:27
 */
public class Sender implements CommandInterface {
    private CommandInterface command;

    public void setCommand(CommandInterface command) {
        this.command = command;
    }

    @Override
    public void execute() {
      command.execute();
    }
}
