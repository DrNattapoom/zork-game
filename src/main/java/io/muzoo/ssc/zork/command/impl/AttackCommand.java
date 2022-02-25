package io.muzoo.ssc.zork.command.impl;

import io.muzoo.ssc.zork.Game;
import io.muzoo.ssc.zork.Player;
import io.muzoo.ssc.zork.command.Command;
import io.muzoo.ssc.zork.map.item.Item;
import io.muzoo.ssc.zork.map.item.weapon.Weapon;
import io.muzoo.ssc.zork.map.monster.Monster;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AttackCommand implements Command {

    @Override
    public void execute(Game game, String argument) {
        if (game.getPlaying()) {
            Monster enemy = game.getMap().getRoom(game.getPlayer().getLocation()).getMonster();
            if (enemy == null) {
                System.out.println("There is no monster here");
            } else {
                List<Item> usableItems = game.getPlayer().getItems().stream().filter(item -> item instanceof Weapon).collect(Collectors.toList());
                if (usableItems.isEmpty()) {
                    System.out.println("There is no item to be used to attack with");
                } else {
                    Map<String, Item> usableItemsMap = usableItems.stream().collect(Collectors.toMap(Item::getName, item -> item));
                    if (StringUtils.isBlank(argument)) {
                        System.out.println("What do you want to attack with?");
                        for (int i = 0; i < usableItems.size(); i++) {
                            System.out.println(String.format("(%x) %s ", i + 1, usableItems.get(i)));
                        }
                        Scanner scanner = new Scanner(System.in);
                        argument = scanner.nextLine();
                    }
                    Item tobeUsed = (isValidIndex(usableItems, argument)) ? usableItems.get(Integer.parseInt(argument) - 1) : usableItemsMap.get(argument);
                    if (tobeUsed != null) {
                        Player player = game.getPlayer();
                        Weapon weapon = (Weapon) tobeUsed;
                        System.out.println(String.format("Attack %s with %s", StringUtils.capitalize(enemy.getMonsterType().getType()), weapon));
                        player.attackWith(weapon, enemy);
                        if (enemy.getHp() <= 0) {
                            System.out.println(StringUtils.capitalize(enemy.getMonsterType().getType()) + " is dead");
                            game.getMap().getRoom(game.getPlayer().getLocation()).setMonster(null);
                        } else {
                            enemy.attack(player);
                        }
                    } else {
                        System.out.println("There is no such item");
                    }
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