package io.muzoo.ssc.zork.map.item.potion.impl;

import io.muzoo.ssc.zork.Player;
import io.muzoo.ssc.zork.map.item.potion.Potion;
import io.muzoo.ssc.zork.map.item.potion.PotionType;
import io.muzoo.ssc.zork.map.monster.Monster;

public class HealingPotion extends Potion {

    public HealingPotion(String name) {
        super(PotionType.HEALING_POTION, name);
    }

    @Override
    public void activate(Player player, Monster monster) {
        player.setHp(player.getMaxHp());
    }

}