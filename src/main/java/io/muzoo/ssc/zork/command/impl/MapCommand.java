package io.muzoo.ssc.zork.command.impl;

import io.muzoo.ssc.zork.Game;
import io.muzoo.ssc.zork.command.Command;

public class MapCommand implements Command {

    @Override
    public void execute(Game game, String argument) {
        if (game.getPlaying()) {
            game.getMap().printMap(game.getPlayer().getLocation());
        } else {
            System.out.println("This command is only available while playing the game");
        }
    }

}