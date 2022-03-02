package io.muzoo.ssc.zork.map.item.weapon.impl;

import io.muzoo.ssc.zork.map.item.weapon.Weapon;
import io.muzoo.ssc.zork.map.item.weapon.WeaponType;

public class Sword extends Weapon {

    public Sword(String name, int damage, int durability, boolean isLegendary) {
        super(WeaponType.SWORD, name, damage, durability, isLegendary);
    }

}