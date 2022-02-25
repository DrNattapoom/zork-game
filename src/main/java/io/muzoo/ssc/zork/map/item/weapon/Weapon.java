package io.muzoo.ssc.zork.map.item.weapon;

import io.muzoo.ssc.zork.map.item.Item;

public abstract class Weapon extends Item {

    private int damage;

    public Weapon(String name, int damage) {
        super(name);
        this.damage = damage;
    }

}