package io.muzoo.ssc.zork.command.impl;

import io.muzoo.ssc.zork.Game;
import io.muzoo.ssc.zork.command.Command;
import io.muzoo.ssc.zork.command.CommandType;

public class HelpCommand implements Command {

    @Override
    public void execute(Game game, String argument) {
        System.out.println("Your command words are: ");
        for (CommandType commandType : CommandType.values()) {
            System.out.println("\t" + commandType.getCommandWord() + " - " + commandType.getCommandDescription());
        }
    }
}
