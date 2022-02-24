package io.muzoo.ssc.zork.command;

import io.muzoo.ssc.zork.command.impl.*;

public enum CommandType {

    INFO(InfoCommand.class, "info", "print out information of the player and the room that the player is currently in, this command only available while playing game"),
    TAKE(TakeCommand.class, "take", "take command is used to pick up the item in the current room, this command only available while playing game"),
    USE(UseCommand.class, "use", "use command is used to use the item that is not weapon in the current room, this command only available while playing game"),
    GO(GoCommand.class, "go", "move player to the room as specified by the direction, e.g. north, east, west, south, this command only available while playing game"),
    MAP(MapCommand.class, "map", "print 2D map using ascii art, this command only available while playing game"),
    HELP(HelpCommand.class, "help", "print all commands"),
    PLAY(PlayCommand.class, "play", "play new game, this command only available at when start the game"),
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
