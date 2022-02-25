package io.muzoo.ssc.zork.map.item.potion.impl;

import io.muzoo.ssc.zork.Player;
import io.muzoo.ssc.zork.map.item.potion.Potion;

public class HealingPotion extends Potion {

    public HealingPotion(String name) {
        super(name);
    }

    @Override
    public void activate(Player player) {
        player.setHp(player.getMaxHp());
    }

}