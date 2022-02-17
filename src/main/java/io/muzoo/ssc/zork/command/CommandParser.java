package io.muzoo.ssc.zork.command;

public class CommandParser {

    public static CommandType parseCommand(String rawInput) {
        switch (rawInput) {
            case "exit": return CommandType.EXIT;
            case "info": return CommandType.INFO;
            default:
                return null;
        }
    }

}
