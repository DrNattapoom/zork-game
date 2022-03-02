package io.muzoo.ssc.zork.map.item.shield.impl;

import io.muzoo.ssc.zork.Player;
import io.muzoo.ssc.zork.map.item.shield.Shield;
import io.muzoo.ssc.zork.map.item.shield.ShieldType;
import io.muzoo.ssc.zork.map.monster.Monster;

public class MagicalShield extends Shield {

    public MagicalShield(String name, int defense) {
        super(ShieldType.MAGICAL_SHIELD, name, defense);
    }

    @Override
    public void activate(Player player, Monster monster) {
        if (player.getMana() < this.getDefense()) {
            System.out.println("Unfortunately, your mana is insufficient.");
            System.out.println("This item is wasted.");
        } else {
            int originalMana = player.getMana();
            player.setMana(originalMana - this.getDefense());
            int originalDefense = player.getDefense();
            player.setDefense(originalDefense + this.getDefense());
        }
    }

}