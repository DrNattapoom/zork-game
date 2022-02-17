package io.muzoo.ssc.zork.command;

import io.muzoo.ssc.zork.command.impl.ExitCommand;
import io.muzoo.ssc.zork.command.impl.HelpCommand;
import io.muzoo.ssc.zork.command.impl.InfoCommand;

public enum CommandType {

    HELP(HelpCommand.class, "help", "print all commands"),
    INFO(InfoCommand.class, "info", "print out information of the player and the room that the player is currently in, this command only available while playing game"),
    EXIT(ExitCommand.class, "exit", "exit whole game, this command only available at when start the game");

    private Class<? extends Command> commandClass;
    private String commandWord;
    private String commandDescription;

    CommandType(Class<? extends Command> commandClass, String commandWord, String commandDescription) {
        this.commandClass = commandClass;
        this.commandWord = commandWord;
        this.commandDescription = commandDescription;
    }

    public Class<? extends Command> getCommandClass() {
        return commandClass;
    }

    public String getCommandWord() {
        return commandWord;
    }

    public String getCommandDescription() {
        return commandDescription;
    }

    public boolean match(String rawInput) {
        return rawInput.startsWith(commandWord);
    }

}
