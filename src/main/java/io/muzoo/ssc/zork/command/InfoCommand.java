package io.muzoo.ssc.zork.command;

import io.muzoo.ssc.zork.Game;

public class InfoCommand implements Command {

    @Override
    public void execute(Game game) {
        System.out.println("print info");
    }

}