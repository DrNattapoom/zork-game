package io.muzoo.ssc.zork.map.item.weapon;

import io.muzoo.ssc.zork.Player;
import io.muzoo.ssc.zork.map.item.Item;

public abstract class Weapon extends Item {

    private int damage;

    public Weapon(String name, int damage) {
        super(name);
        this.damage = damage;
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

}