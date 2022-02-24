package io.muzoo.ssc.zork.command.impl;

import io.muzoo.ssc.zork.Game;
import io.muzoo.ssc.zork.command.Command;
import io.muzoo.ssc.zork.map.item.Item;
import io.muzoo.ssc.zork.map.item.Weapon;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AttackCommand implements Command {

    @Override
    public void execute(Game game, String argument) {
        if (game.getPlaying()) {
            System.out.println("What do you want to attack with?");
            List<Item> usableItems = game.getPlayer().getItems().stream().filter(item -> item instanceof Weapon).collect(Collectors.toList());
            for (int i = 0; i < usableItems.size(); i++) {
                System.out.print(i + ". " + usableItems.get(i).getName() + " ");
            }
            System.out.println();
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            System.out.println("attacking with " + input + " ...");
        } else {
            System.out.println("This command is only available while playing the game");
        }
    }

}