package io.muzoo.ssc.zork.command.impl;

import io.muzoo.ssc.zork.Game;
import io.muzoo.ssc.zork.command.Command;
import io.muzoo.ssc.zork.map.item.Item;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DropCommand implements Command {

    @Override
    public void execute(Game game, String argument) {
        if (game.getPlaying()) {
            List<Item> items = game.getPlayer().getItems();
            if (items.isEmpty()) {
                System.out.println("There is no item to be dropped");
            } else {
                Map<String, Item> itemsMap = items.stream().collect(Collectors.toMap(Item::getName, item -> item));
                if (StringUtils.isBlank(argument)) {
                    System.out.println("What do you want to drop?");
                    for (int i = 0; i < items.size(); i++) {
                        System.out.println(String.format("(%x) %s ", i + 1, items.get(i)));
                    }
                    Scanner scanner = new Scanner(System.in);
                    argument = scanner.nextLine();
                }
                Item toBeDropped = (isValidIndex(items, argument)) ? items.get(Integer.parseInt(argument) - 1) : itemsMap.get(argument);
                if (toBeDropped != null) {
                    System.out.println("Dropping " + toBeDropped + " ...");
                    items.remove(toBeDropped);
                    System.out.println(toBeDropped + " dropped.");
                } else {
                    System.out.println("There is no such item");
                }
            }
        } else {
            System.out.println("This command is only available while playing the game");
        }
    }

    private boolean isValidIndex(List<Item> usableItems, String argument) {
        try {
            int index = Integer.parseInt(argument) - 1;
            return index >= 0 && index < usableItems.size();
        } catch (NumberFormatException e) {
            return false;
        }
    }

}