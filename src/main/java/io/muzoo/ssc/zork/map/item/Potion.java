package io.muzoo.ssc.zork.map.item;

public abstract class Potion extends Item {

    private boolean isUsed;

    public Potion(String name) {
        super(name);
        this.isUsed = false;
    }

}