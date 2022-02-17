package io.muzoo.ssc.zork.command;

import io.muzoo.ssc.zork.command.impl.ExitCommand;
import io.muzoo.ssc.zork.command.impl.InfoCommand;

public enum CommandType {

    INFO(InfoCommand.class, "info"),
    EXIT(ExitCommand.class, "exit");

    private Class<? extends Command> commandClass;
    private String commandWord;

    CommandType(Class<? extends Command> commandClass, String commandWord) {
        this.commandClass = commandClass;
        this.commandWord = commandWord;
    }

    public Class<? extends Command> getCommandClass() {
        return commandClass;
    }

    public String getCommandWord() {
        return commandWord;
    }

    public boolean match(String rawInput) {
        return rawInput.startsWith(commandWord);
    }

}
