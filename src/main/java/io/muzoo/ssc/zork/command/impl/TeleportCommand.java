package io.muzoo.ssc.zork.command.impl;

import io.muzoo.ssc.zork.Game;
import io.muzoo.ssc.zork.Player;
import io.muzoo.ssc.zork.command.Command;
import io.muzoo.ssc.zork.map.Room;

import java.util.Random;

public class TeleportCommand implements Command {

    private static final int MANA_COST = 5;

    @Override
    public void execute(Game game, String argument) {
        if (game.getPlaying()) {
            Player player = game.getPlayer();
            if (player.getMana() < MANA_COST) {
                System.out.println("Unfortunately, your mana is insufficient.");
                if (player.getMana() > 0) {
                    System.out.println("Sadly, you wasted your mana.");
                    player.setMana(0);
                }
            } else {
                Random random = new Random();
                int[] dimension = game.getMap().getDimension();
                int max = dimension[0] * dimension[1];
                int randomLocation = 1;
                Room destination = new Room(null, null, -1, null, null);
                while (destination.getDoors().isEmpty()) {
                    randomLocation = random.nextInt(max) + 1;
                    destination = game.getMap().getRoom(randomLocation);
                }
                player.setLocation(randomLocation);
                player.recover(1);
                System.out.print("Now, you are in ");
                destination.printRoomInfo(true);
                player.setMana(player.getMana() - MANA_COST);
            }
        } else {
            System.out.println("This command is only available while playing the game");
        }
    }

    public static int getManaCost() {
        return MANA_COST;
    }

}