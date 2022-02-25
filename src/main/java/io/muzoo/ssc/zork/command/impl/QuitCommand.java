package io.muzoo.ssc.zork.command.impl;

import io.muzoo.ssc.zork.Game;
import io.muzoo.ssc.zork.command.Command;

public class QuitCommand implements Command {

    @Override
    public void execute(Game game, String argument) {
        if (game.getPlaying()) {
            System.out.println("ending the current game ...");
            game.setPlaying(false);
            System.out.println("Game Ended");
        } else {
            System.out.println("This command is only available while playing the game");
        }
    }

}