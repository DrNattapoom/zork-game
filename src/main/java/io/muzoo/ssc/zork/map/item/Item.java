package io.muzoo.ssc.zork.map.item;

import io.muzoo.ssc.zork.Player;

public abstract class Item {

    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

    public abstract void activate(Player player);

}