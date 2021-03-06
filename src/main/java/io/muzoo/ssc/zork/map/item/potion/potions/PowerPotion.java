package io.muzoo.ssc.zork.map.item.potion.potions;

import io.muzoo.ssc.zork.Player;
import io.muzoo.ssc.zork.map.item.potion.Potion;
import io.muzoo.ssc.zork.map.item.potion.PotionType;
import io.muzoo.ssc.zork.map.monster.Monster;

import java.util.Random;

public class PowerPotion extends Potion {

    public PowerPotion(String name) {
        super(PotionType.POWER_POTION, name);
    }

    @Override
    public void activate(Player player, Monster monster) {
        int originalAttackPower = player.getAttackPower();
        int newAttackPower = new Random().nextInt(originalAttackPower) + originalAttackPower;
        player.setAttackPower(newAttackPower);
    }

}