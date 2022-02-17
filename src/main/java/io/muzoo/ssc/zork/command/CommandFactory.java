package io.muzoo.ssc.zork.command;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private static Map<CommandType, Command> commandMap = new HashMap<>();

    static {
        for (CommandType commandType : CommandType.values()) {
            Command command = null;
            try {
                command = commandType.getCommandClass().getDeclaredConstructor().newInstance();
                commandMap.put(commandType, command);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    public static Command get(CommandType command) {
        return commandMap.get(command);
    }

}
