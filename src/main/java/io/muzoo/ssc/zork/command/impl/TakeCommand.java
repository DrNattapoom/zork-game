package io.muzoo.ssc.zork.command.impl;

import io.muzoo.ssc.zork.Game;
import io.muzoo.ssc.zork.Player;
import io.muzoo.ssc.zork.command.Command;
import io.muzoo.ssc.zork.map.Room;
import io.muzoo.ssc.zork.map.item.Item;

public class TakeCommand implements Command {

    @Override
    public void execute(Game game, String argument) {
        if (game.getPlaying()) {
            Player player = game.getPlayer();
            Room currentRoom = game.getMap().getRoom(player.getLocation());
            Item item = currentRoom.getItem();
            if (item != null) {
                System.out.println("Taking " + item + " ...");
                player.takeItem(item);
                currentRoom.setItem(null);
                System.out.println(item + " acquired");
            } else {
                System.out.println("There is no item in this room");
            }
        } else {
            System.out.println("This command is only available while playing the game");
        }
    }

}