package io.muzoo.ssc.zork;

import io.muzoo.ssc.zork.map.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Player extends Mortal {

    private static int MAX_HP = 10;

    private List<Item> items;

    private int location;

    public Player(int hp, int attackPower) {
        super(hp, attackPower);
    }

    public Player(int hp, int attackPower, int location) {
        super(hp, attackPower);
        this.items = new ArrayList<>();
        this.location = location;
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

}