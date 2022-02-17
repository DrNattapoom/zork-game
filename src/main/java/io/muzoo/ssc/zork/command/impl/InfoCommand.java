package io.muzoo.ssc.zork.command.impl;

import io.muzoo.ssc.zork.Game;
import io.muzoo.ssc.zork.Player;
import io.muzoo.ssc.zork.command.Command;

public class InfoCommand implements Command {

    @Override
    public void execute(Game game, String argument) {
        System.out.println("Your Max HP is " + Player.getMaxHp());
        System.out.println("Your current HP is " + game.getPlayer().getHp());
        System.out.println("Your current Attack Power is " + game.getPlayer().getAttackPower());
    }

}