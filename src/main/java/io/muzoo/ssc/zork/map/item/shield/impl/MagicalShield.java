package io.muzoo.ssc.zork.map.item.shield.impl;

import io.muzoo.ssc.zork.Player;
import io.muzoo.ssc.zork.map.item.shield.Shield;
import io.muzoo.ssc.zork.map.item.shield.ShieldType;

public class MagicalShield extends Shield {

    public MagicalShield(String name, int defense) {
        super(ShieldType.MAGICAL_SHIELD, name, defense);
    }

    @Override
    public void activate(Player player) {
        if (player.getMana() < this.getDefense()) {
            System.out.println("Unfortunately, your mana is not sufficient.");
            System.out.println("This item is wasted.");
        } else {
            int originalMana = player.getMana();
            player.setMana(originalMana - this.getDefense());
            int originalDefense = player.getDefense();
            player.setDefense(originalDefense + this.getDefense());
        }
    }

}