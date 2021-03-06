package io.muzoo.ssc.zork.map.item;

import io.muzoo.ssc.zork.Player;
import io.muzoo.ssc.zork.map.monster.Monster;

public abstract class Item {

    private ItemType itemType;
    private String name;

    public Item(ItemType itemType, String name) {
        this.itemType = itemType;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public abstract void activate(Player player, Monster monster);

}