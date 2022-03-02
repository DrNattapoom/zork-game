package io.muzoo.ssc.zork.command.impl;

import io.muzoo.ssc.zork.Game;
import io.muzoo.ssc.zork.Player;
import io.muzoo.ssc.zork.command.Command;

import org.apache.commons.lang3.StringUtils;

public class PlayCommand implements Command {

    @Override
    public void execute(Game game, String argument) {
        if (!game.getPlaying()) {
            argument = (StringUtils.isBlank(argument)) ? "default" : argument;
            String mapPath = Game.getMapChoices().get(argument);
            if (mapPath == null) {
                System.out.println("There is no such map");
            } else {
                System.out.println("Loading " + argument + " ...");
                game.setPlaying(true);
                game.load(mapPath);
                System.out.println("Welcome to the World of Zork!\n");
                game.printContext();
                Player player = game.getPlayer();
                System.out.print("Now, you are in ");
                game.getMap().getRoom(player.getLocation()).printRoomInfo(true);
            }
        } else {
            System.out.println("This command is not available while playing the game");
        }
    }

}
