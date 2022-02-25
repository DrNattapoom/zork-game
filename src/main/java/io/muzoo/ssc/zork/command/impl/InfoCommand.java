package io.muzoo.ssc.zork.command.impl;

import io.muzoo.ssc.zork.Game;
import io.muzoo.ssc.zork.Player;
import io.muzoo.ssc.zork.command.Command;

public class InfoCommand implements Command {

    @Override
    public void execute(Game game, String argument) {
        if (game.getPlaying()) {
            Player player = game.getPlayer();
            System.out.println("Your Max HP is " + player.getMaxHp());
            System.out.println("Your current HP is " + player.getHp());
            System.out.println("Your current Attack Power is " + player.getAttackPower());
            System.out.println("Your item(s) is/are " + player.getItems());
            System.out.println("You are in Room Number " + player.getLocation());
            System.out.print("Doors: ");
            for (String direction : game.getMap().getRoom(player.getLocation()).getDoors().keySet()) {
                System.out.print(direction + " ");
            }
            System.out.println();
        } else {
            System.out.println("This command is only available while playing the game");
        }
    }

}