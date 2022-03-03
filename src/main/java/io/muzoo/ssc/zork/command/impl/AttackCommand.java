package io.muzoo.ssc.zork.command.impl;

import io.muzoo.ssc.zork.Game;
import io.muzoo.ssc.zork.Player;
import io.muzoo.ssc.zork.command.Command;
import io.muzoo.ssc.zork.map.item.Item;
import io.muzoo.ssc.zork.map.item.weapon.Weapon;
import io.muzoo.ssc.zork.map.monster.Monster;
import io.muzoo.ssc.zork.map.monster.monsters.Dragon;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class AttackCommand implements Command {

    private boolean attackWithPunch = false;

    @Override
    public void execute(Game game, String argument) {
        if (game.getPlaying()) {
            Monster enemy = game.getMap().getRoom(game.getPlayer().getLocation()).getMonster();
            if (enemy == null) {
                System.out.println("There is no monster here");
            } else {
                Player player = game.getPlayer();
                List<Item> usableItems = player.getItems().stream().filter(item -> item instanceof Weapon).collect(Collectors.toList());
                Item tobeUsed = getWantedItem(usableItems, argument, "What do you want to attack with?");
                String monsterName = StringUtils.capitalize(enemy.getMonsterType().getType().toLowerCase());
                if (tobeUsed != null) {
                    Weapon weapon = (Weapon) tobeUsed;
                    System.out.println(String.format("Attack %s with %s", monsterName, weapon));
                    player.attackWith(weapon, enemy);
                    attackConsequence(game, player, enemy, argument);
                } else {
                    if (attackWithPunch) {
                        System.out.println(String.format("Attack %s with Punch", monsterName));
                        player.attack(enemy);
                        attackConsequence(game, player, enemy, argument);
                        this.attackWithPunch = false;
                    } else {
                        System.out.println("There is no such item");
                    }
                }
            }
        } else {
            System.out.println("This command is only available while playing the game");
        }
    }

    private void attackConsequence(Game game, Player player, Monster enemy, String argument) {
        String monsterName = StringUtils.capitalize(enemy.getMonsterType().getType().toLowerCase());
        if (enemy.getHp() <= 0) {
            System.out.println(monsterName + " is dead.");
            int maxExp = Math.round((float) enemy.getAttackPower() / 2);
            int exp = new Random().nextInt(maxExp) + 1;
            game.getMap().getRoom(game.getPlayer().getLocation()).setMonster(null);
            player.setAttackPower(player.getAttackPower() + exp);
            player.setMana(player.getMana() + exp);
            System.out.println("\nLeveled up!");
            System.out.println(
                String.format(
                    "Your Attack Power and Mana are now %d and %d, respectively.",
                    player.getAttackPower(),
                    player.getMana()
                )
            );
            if (enemy instanceof Dragon) {
                System.out.println("\nCongratulations!");
                System.out.println("The dragon has been slayed.");
                System.out.println("The mission is completed. You won!\n");
                new QuitCommand().execute(game, argument);
                new ExitCommand().execute(game, argument);
            }
        } else {
            System.out.println(String.format("%s's HP remains %d. \n", monsterName, enemy.getHp()));
            System.out.println(String.format("%s attacks back", enemy));
            System.out.println("Do you want to use anything? [yes/no]");
            String[] arguments = (argument == null) ? new String[1] : argument.split(" --");
            String answer = "";
            if (arguments.length < 2) {
                Scanner scanner = new Scanner(System.in);
                answer = scanner.nextLine();
            } else {
                answer = StringUtils.substringAfter(arguments[1], "=");
                System.out.println("\u001B[32m" + answer + "\u001B[0m");
            }
            if (answer.equals("yes") || answer.equals("y")) {
                String useArgument = (arguments.length == 3) ? StringUtils.substringAfter(arguments[2], "=") : "";
                new UseCommand().execute(game, useArgument);
            }
            enemy.attack(player);
            if (player.getHp() <= 0) {
                System.out.println("\nYou died.");
                System.out.println("You lose!");
                System.out.println("Try again next time.\n");
                new QuitCommand().execute(game, argument);
            } else {
                System.out.println("Your HP is now " + player.getHp());
            }
        }
    }

    private Item getWantedItem(List<Item> items, String argument, String question) {
        Map<String, Item> itemsMap = items.stream().collect(Collectors.toMap(Item::getName, item -> item));
        if (StringUtils.isBlank(argument)) {
            System.out.println(question);
            for (int i = 0; i < items.size(); i++) {
                System.out.println(String.format("(%d) %s ", i + 1, items.get(i)));
            }
            System.out.println(String.format("(%d) Punch ", items.size() + 1));
            Scanner scanner = new Scanner(System.in);
            argument = scanner.nextLine();
        } else {
            argument = (argument.contains(" --")) ? argument.split(" --")[0] : argument;
        }
        if (argument.equals(String.format("%d", items.size() + 1)) || argument.toLowerCase().equals("punch")) {
            this.attackWithPunch = true;
            return null;
        }
        return (isValidIndex(items, argument)) ? items.get(Integer.parseInt(argument) - 1) : itemsMap.get(argument);
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