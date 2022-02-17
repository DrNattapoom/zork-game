package io.muzoo.ssc.zork.command;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private static Map<String, Command> commandMap = new HashMap<>();

    static {
        commandMap.put("exit", new ExitCommand());
        commandMap.put("info", new InfoCommand());
    }

    public static Command get(String command) {
        return commandMap.get(command);
    }

}
