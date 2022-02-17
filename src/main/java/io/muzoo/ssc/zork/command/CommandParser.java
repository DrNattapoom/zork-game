package io.muzoo.ssc.zork.command;

import org.apache.commons.lang3.StringUtils;

public class CommandParser {

    public static CommandLine parseCommand(String rawInput) {
        for (CommandType commandType : CommandType.values()) {
            if (commandType.match(rawInput)) {
                String argument = rawInput.replace(commandType.getCommandWord(), "").trim();
                argument = (StringUtils.isBlank(argument)) ? null : argument;
                return new CommandLine(commandType, argument);
            }
        }
        return null;
    }

}
