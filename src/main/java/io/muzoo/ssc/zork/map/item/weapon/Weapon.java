package io.muzoo.ssc.zork.map.item.weapon;

import io.muzoo.ssc.zork.Player;
import io.muzoo.ssc.zork.map.item.Item;

public abstract class Weapon extends Item {

    private int damage;
    private int durability;

    public Weapon(String name, int damage, int durability) {
        super(name);
        this.damage = damage;
        this.durability = durability;
    }

    @Override
    public void activate(Player player) {
        int originalAttackPower = player.getAttackPower();
        int newAttackPower = originalAttackPower + this.getDamage();
        player.setAttackPower(newAttackPower);
    }

    public int getDamage() {
        return damage;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

}