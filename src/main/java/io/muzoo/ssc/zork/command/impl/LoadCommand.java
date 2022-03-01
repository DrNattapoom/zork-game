package io.muzoo.ssc.zork.command.impl;

import io.muzoo.ssc.zork.Game;
import io.muzoo.ssc.zork.command.Command;
import org.apache.commons.lang3.StringUtils;

public class LoadCommand implements Command {

    @Override
    public void execute(Game game, String argument) {
        if (!game.getPlaying()) {
            if (StringUtils.isBlank(argument)) {
                System.out.println("Please provide the saved point name");
            } else {
                String savePointPath = game.getSavePoints().get(argument);
                if (savePointPath == null) {
                    System.out.println("There is no such saved point");
                } else {
                    System.out.println("loading " + argument + " ...");
                    game.setPlaying(true);
                    game.load(savePointPath);
                    System.out.println("Game Loaded");
                }
            }
        } else {
            System.out.println("This command is not available while playing the game");
        }
    }

}