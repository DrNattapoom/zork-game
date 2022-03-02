package io.muzoo.ssc.zork.command.impl;

import io.muzoo.ssc.zork.Game;
import io.muzoo.ssc.zork.Player;
import io.muzoo.ssc.zork.command.Command;
import io.muzoo.ssc.zork.map.Room;

public class InfoCommand implements Command {

    @Override
    public void execute(Game game, String argument) {
        if (game.getPlaying()) {
            game.printContext();
            Player player = game.getPlayer();
            Room currentRoom = game.getMap().getRoom(player.getLocation());
            System.out.print("Now, you are in ");
            currentRoom.printRoomInfo(false);
            System.out.println();
            System.out.println("Here are your stats");
            player.printStats();
        } else {
            System.out.println("This command is only available while playing the game");
        }
    }

}