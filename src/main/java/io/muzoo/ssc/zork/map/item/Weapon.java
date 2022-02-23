package io.muzoo.ssc.zork.map.item;

public abstract class Weapon extends Item {

    private int damage;

    public Weapon(String name, int damage) {
        super(name);
        this.damage = damage;
    }

}