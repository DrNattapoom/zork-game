package io.muzoo.ssc.zork.map.item.potion;

import io.muzoo.ssc.zork.map.item.Item;
import io.muzoo.ssc.zork.map.item.ItemType;

public abstract class Potion extends Item {

    private PotionType category;

    public Potion(PotionType category, String name) {
        super(ItemType.POTION, name);
        this.category = category;
    }

}