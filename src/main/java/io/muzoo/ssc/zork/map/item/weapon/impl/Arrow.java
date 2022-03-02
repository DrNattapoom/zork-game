package io.muzoo.ssc.zork.map.item.weapon.impl;

import io.muzoo.ssc.zork.Player;
import io.muzoo.ssc.zork.map.item.weapon.Weapon;
import io.muzoo.ssc.zork.map.item.weapon.WeaponType;
import io.muzoo.ssc.zork.map.monster.Monster;
import io.muzoo.ssc.zork.map.monster.impl.Dragon;

public class Arrow extends Weapon {

    public Arrow(String name, int damage, int durability) {
        super(WeaponType.ARROW, name, damage, durability);
    }

    @Override
    public void activate(Player player, Monster monster) {
        super.activate(player, monster);
        if (monster instanceof Dragon) {
            player.setAttackPower(player.getAttackPower() + 3);
        }
    }

}