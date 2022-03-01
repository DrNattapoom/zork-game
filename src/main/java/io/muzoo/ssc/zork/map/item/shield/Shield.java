package io.muzoo.ssc.zork.map.item.shield;

import io.muzoo.ssc.zork.Player;
import io.muzoo.ssc.zork.map.item.Item;
import io.muzoo.ssc.zork.map.item.ItemType;

public abstract class Shield extends Item {

    private ShieldType category;
    private int defense;

    public Shield(ShieldType category, String name, int defense) {
        super(ItemType.SHIELD, name);
        this.category = category;
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