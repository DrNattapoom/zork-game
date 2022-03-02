package io.muzoo.ssc.zork.map.item.weapon;

import io.muzoo.ssc.zork.Player;
import io.muzoo.ssc.zork.map.item.Item;
import io.muzoo.ssc.zork.map.item.ItemType;
import io.muzoo.ssc.zork.map.monster.Monster;

import java.util.Random;

public abstract class Weapon extends Item {

    private WeaponType category;
    private int damage;
    private int durability;

    public Weapon(WeaponType category, String name, int damage, int durability) {
        super(ItemType.WEAPON, name);
        this.category = category;
        this.damage = damage;
        this.durability = durability;
    }

    @Override
    public void activate(Player player, Monster monster) {
        int originalAttackPower = player.getAttackPower();
        int newAttackPower = originalAttackPower + this.getDamage();
        player.setAttackPower(newAttackPower);
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int getDamage() {
        return damage;
    }

}