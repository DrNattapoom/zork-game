package io.muzoo.ssc.zork.command.impl;

import io.muzoo.ssc.zork.Game;
import io.muzoo.ssc.zork.Player;
import io.muzoo.ssc.zork.command.Command;
import io.muzoo.ssc.zork.map.item.Item;
import io.muzoo.ssc.zork.map.item.weapon.Weapon;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UseCommand implements Command {

    @Override
    public void execute(Game game, String argument) {
        if (game.getPlaying()) {
            Player player = game.getPlayer();
            List<Item> usableItems = player.getItems().stream().filter(item -> !(item instanceof Weapon)).collect(Collectors.toList());
            if (usableItems.isEmpty()) {
                System.out.println("There is no item to be used");
            } else {
                Map<String, Item> usableItemsMap = usableItems.stream().collect(Collectors.toMap(Item::getName, item -> item));
                if (StringUtils.isBlank(argument)) {
                    System.out.println("What do you want to use?");
                    for (int i = 0; i < usableItems.size(); i++) {
                        System.out.println(String.format("(%x) %s ", i + 1, usableItems.get(i)));
                    }
                    Scanner scanner = new Scanner(System.in);
                    argument = scanner.nextLine();
                }
                Item tobeUsed = (isValidIndex(usableItems, argument)) ? usableItems.get(Integer.parseInt(argument) - 1) : usableItemsMap.get(argument);
                if (tobeUsed != null) {
                    System.out.println("Using " + tobeUsed + " ...");
                    System.out.println(tobeUsed + " used.");
                    tobeUsed.activate(player, game.getMap().getRoom(player.getLocation()).getMonster());
                    player.getItems().remove(tobeUsed);
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