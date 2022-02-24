package io.muzoo.ssc.zork.command.impl;

import io.muzoo.ssc.zork.Game;
import io.muzoo.ssc.zork.Player;
import io.muzoo.ssc.zork.command.Command;

public class InfoCommand implements Command {

    @Override
    public void execute(Game game, String argument) {
        Player player = game.getPlayer();
        System.out.println("Your Max HP is " + player.getMaxHp());
        System.out.println("Your current HP is " + player.getHp());
        System.out.println("Your current Attack Power is " + player.getAttackPower());
        System.out.println("You are in Room Number " + player.getLocation());
    }

}