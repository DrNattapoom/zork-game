package io.muzoo.ssc.zork.map.item.weapon.impl;

import io.muzoo.ssc.zork.map.item.weapon.Weapon;
import io.muzoo.ssc.zork.map.item.weapon.WeaponType;

public class Arrow extends Weapon {

    public Arrow(String name, int damage, int durability) {
        super(WeaponType.ARROW, name, damage, durability);
    }

}