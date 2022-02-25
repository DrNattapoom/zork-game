package io.muzoo.ssc.zork.command.impl;

import io.muzoo.ssc.zork.Game;
import io.muzoo.ssc.zork.Player;
import io.muzoo.ssc.zork.command.Command;
import io.muzoo.ssc.zork.map.Room;

public class GoCommand implements Command {

    @Override
    public void execute(Game game, String argument) {
        if (game.getPlaying()) {
            Player player = game.getPlayer();
            Room currentRoom = game.getMap().getRoom(player.getLocation());
            if (currentRoom.getDoors().containsKey(argument)) {
                int nextLocation = currentRoom.getDoors().get(argument);
                player.setLocation(nextLocation);
                player.recover(1);
                System.out.println("You are in Room Number " + player.getLocation());
                System.out.print("Doors: ");
                for (String direction : game.getMap().getRoom(player.getLocation()).getDoors().keySet()) {
                    System.out.print(direction + " ");
                }
                System.out.println();
            } else {
                System.out.println(String.format("There is no %s door", argument));
            }
        } else {
            System.out.println("This command is only available while playing the game");
        }
    }

}