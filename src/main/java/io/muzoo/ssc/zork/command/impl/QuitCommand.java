package io.muzoo.ssc.zork.command.impl;

import io.muzoo.ssc.zork.Game;
import io.muzoo.ssc.zork.command.Command;

public class QuitCommand implements Command {

    @Override
    public void execute(Game game, String argument) {
        if (game.getPlaying()) {
            System.out.println("Terminating the current game ...");
            game.setPlaying(false);
            System.out.println("The current game terminated.");
        } else {
            System.out.println("This command is only available while playing the game");
        }
    }

}