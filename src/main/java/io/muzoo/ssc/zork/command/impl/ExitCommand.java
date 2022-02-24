package io.muzoo.ssc.zork.command.impl;

import io.muzoo.ssc.zork.Game;
import io.muzoo.ssc.zork.command.Command;

public class ExitCommand implements Command {

    @Override
    public void execute(Game game, String argument) {
        if (!game.getPlaying()) {
            game.exit();
        } else {
            System.out.println("This command is not available while playing the game");
        }
    }

}