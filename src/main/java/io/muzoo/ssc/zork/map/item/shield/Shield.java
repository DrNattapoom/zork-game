package io.muzoo.ssc.zork.map.item.shield;

import io.muzoo.ssc.zork.Player;
import io.muzoo.ssc.zork.map.item.Item;

public abstract class Shield extends Item {

    private int defense;

    public Shield(String name, int defense) {
        super(name);
        this.defense = defense;
    }

    @Override
    public void activate(Player player) {
        int originalDefense = player.getDefense();
        player.setDefense(originalDefense + this.getDefense());
    }

    public int getDefense() {
        return defense;
    }

}