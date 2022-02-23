package io.muzoo.ssc.zork.map.item;

public abstract class Shield extends Item {

    private int defense;

    public Shield(String name, int defense) {
        super(name);
        this.defense = defense;
    }

}