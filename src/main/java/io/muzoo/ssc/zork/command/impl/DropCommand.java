package io.muzoo.ssc.zork.command.impl;

import io.muzoo.ssc.zork.Game;
import io.muzoo.ssc.zork.command.Command;
import io.muzoo.ssc.zork.map.item.Item;

import java.util.List;
import java.util.Scanner;

public class DropCommand implements Command {

    @Override
    public void execute(Game game, String argument) {
        if (game.getPlaying()) {
            System.out.println("What do you want to drop?");
            List<Item> items = game.getPlayer().getItems();
            for (Item item : items) {
                System.out.print(item + " ");
            }
            System.out.println();
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            System.out.println("dropping " + input + " ...");
            items.removeIf(item -> item.getName().equals(input));
        } else {
            System.out.println("This command is only available while playing the game");
        }
    }

}