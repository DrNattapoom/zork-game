package io.muzoo.ssc.zork.command.impl;

import io.muzoo.ssc.zork.Game;
import io.muzoo.ssc.zork.Player;
import io.muzoo.ssc.zork.command.Command;
import io.muzoo.ssc.zork.map.Room;
import io.muzoo.ssc.zork.map.item.Item;
import io.muzoo.ssc.zork.map.monster.Monster;
import org.apache.commons.lang3.StringUtils;

public class InfoCommand implements Command {

    @Override
    public void execute(Game game, String argument) {
        if (game.getPlaying()) {
            System.out.println("Welcome to the World of Zork!\n");
            System.out.println(
                    "\tYou are an adventurer who are hired by a king whose castle was seized by a dragon. Your task is to reclaim the castle. \n" +
                    "This is, however, not an easy feat because the vicious aura of the dragon has lured many monsters into the area. \n"
            );
            System.out.println("Goal: defeat all the monsters and slay the dragon to reclaim the castle \n");
            Player player = game.getPlayer();
            System.out.println("Here are your stats");
            System.out.println(
                    String.format(
                        "\tMax HP: %d \n" +
                        "\tHP: %d \n" +
                        "\tAttack Power: %d \n" +
                        "\tDefense Power: %d \n" +
                        "\tInventory: %s \n",
                        player.getMaxHp(),
                        player.getHp(),
                        player.getAttackPower(),
                        player.getDefense(),
                        player.getItems()
                    )
            );
            Room currentRoom = game.getMap().getRoom(player.getLocation());
            System.out.println(String.format("Now, you are in %s.", currentRoom.getName()));
            System.out.println(currentRoom.getDescription() + "\n");
            Monster monster = currentRoom.getMonster();
            if (monster != null) {
                System.out.println("Here are the monster stats");
                System.out.println(
                        String.format(
                                "\tType: %s \n" +
                                "\tHP: %d \n" +
                                "\tAttack Power: %d \n",
                                StringUtils.capitalize(monster.getMonsterType().getType().toLowerCase()),
                                monster.getHp(),
                                monster.getAttackPower()
                        )
                );
            }
            Item item = currentRoom.getItem();
            if (item != null) {
                System.out.println("Takable Item: " + item.getName());
            }
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