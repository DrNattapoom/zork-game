package io.muzoo.ssc.zork.map.item.potion.impl;

import io.muzoo.ssc.zork.Player;
import io.muzoo.ssc.zork.map.item.potion.Potion;
import io.muzoo.ssc.zork.map.item.potion.PotionType;

import java.util.Random;

public class PowerPotion extends Potion {

    public PowerPotion(String name) {
        super(PotionType.POWER_POTION, name);
    }

    @Override
    public void activate(Player player) {
        int originalAttackPower = player.getAttackPower();
        int newAttackPower = new Random().nextInt(originalAttackPower) + originalAttackPower;
        player.setAttackPower(newAttackPower);
    }

}