package io.muzoo.ssc.zork.command.impl;

import io.muzoo.ssc.zork.Game;
import io.muzoo.ssc.zork.command.Command;
import io.muzoo.ssc.zork.map.Room;
import io.muzoo.ssc.zork.map.item.Item;

public class TakeCommand implements Command {

    @Override
    public void execute(Game game, String argument) {
        if (game.getPlaying()) {
            int currentLocation = game.getPlayer().getLocation();
            Room[][] rooms = game.getMap().getRooms();
            int width = rooms[0].length;
            int row = (currentLocation - 1) / width;
            int col = (currentLocation - 1) % width;
            Room currentRoom = rooms[row][col];
            Item item = currentRoom.getItem();
            if (item != null) {
                System.out.println("taking " + item.getName() + " ...");
                game.getPlayer().takeItem(item);
            }
        } else {
            System.out.println("This command is only available while playing the game");
        }
    }

}