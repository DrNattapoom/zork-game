package io.muzoo.ssc.zork.command.impl;

import io.muzoo.ssc.zork.Game;
import io.muzoo.ssc.zork.command.Command;
import org.apache.commons.lang3.StringUtils;

public class PlayCommand implements Command {

    @Override
    public void execute(Game game, String argument) {
        if (!game.getPlaying()) {
            argument = (StringUtils.isBlank(argument)) ? game.getDefaultPath() : argument;
            System.out.println("loading " + argument + " ...");
            game.setPlaying(true);
            game.load(argument);
        } else {
            System.out.println("This command is not available while playing the game");
        }
    }

}
