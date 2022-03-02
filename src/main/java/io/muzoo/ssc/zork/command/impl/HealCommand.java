package io.muzoo.ssc.zork.command.impl;

import io.muzoo.ssc.zork.Game;
import io.muzoo.ssc.zork.Player;
import io.muzoo.ssc.zork.command.Command;

public class HealCommand implements Command {

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
                System.out.println("Healing ...");
                int newHP = Math.min(player.getHp() + MANA_COST, player.getMaxHp());
                player.setHp(newHP);
                player.setMana(player.getMana() - MANA_COST);
                System.out.println("Your HP is now " + player.getHp());
            }
        } else {
            System.out.println("This command is only available while playing the game");
        }
    }

    public static int getManaCost() {
        return MANA_COST;
    }

}