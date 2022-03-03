package io.muzoo.ssc.zork.command;

public class CommandLine {

    private CommandType commandType;

    private String arguments;

    public CommandLine(CommandType commandType, String arguments) {
        this.commandType = commandType;
        this.arguments = arguments;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public String getArguments() {
        return arguments;
    }

}