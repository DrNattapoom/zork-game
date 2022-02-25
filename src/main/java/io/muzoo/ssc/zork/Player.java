package io.muzoo.ssc.zork;

import io.muzoo.ssc.zork.map.item.Item;
import io.muzoo.ssc.zork.map.item.weapon.Weapon;
import io.muzoo.ssc.zork.map.monster.Monster;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Player extends Mortal {

    private static int MAX_HP = 10;

    private List<Item> items;
    private int location;

    public Player(int hp, int attackPower, int location) {
        super(hp, attackPower);
        this.items = new ArrayList<>();
        this.location = location;
    }

    public void recover(int recoveredHp) {
        int currentHp = this.getHp();
        int newHp = Math.min(currentHp + recoveredHp, MAX_HP);
        this.setHp(newHp);
    }

    public int getMaxHp() {
        return MAX_HP;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public List<Item> getItems() {
        return items;
    }

    public void takeItem(Item item) {
        items.add(item);
    }

    public void attackWith(Weapon weapon, Mortal enemy) {
        int originalAttackPower = this.getAttackPower();
        int newAttackPower = originalAttackPower + weapon.getDamage();
        this.setAttackPower(newAttackPower);
        attack(enemy);
        this.setAttackPower(originalAttackPower);
    }

    @Override
    public void attack(Mortal enemy) {
        int currentEnemyHp = enemy.getHp();
        int damage = this.getAttackPower();
        enemy.setHp(currentEnemyHp - damage);
    }

}