package io.muzoo.ssc.zork.map.item.potion;

import io.muzoo.ssc.zork.map.item.Item;

public abstract class Potion extends Item {

    private boolean isUsed;

    public Potion(String name) {
        super(name);
        this.isUsed = false;
    }

}