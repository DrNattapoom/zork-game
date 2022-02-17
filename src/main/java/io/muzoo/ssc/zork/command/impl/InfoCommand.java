package io.muzoo.ssc.zork.command.impl;

import io.muzoo.ssc.zork.Game;
import io.muzoo.ssc.zork.command.Command;

public class InfoCommand implements Command {

    @Override
    public void execute(Game game, String argument) {
        System.out.println("print info");
    }

}