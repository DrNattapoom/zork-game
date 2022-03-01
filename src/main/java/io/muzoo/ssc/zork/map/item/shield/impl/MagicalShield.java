package io.muzoo.ssc.zork.map.item.shield.impl;

import io.muzoo.ssc.zork.map.item.shield.Shield;
import io.muzoo.ssc.zork.map.item.shield.ShieldType;

public class MagicalShield extends Shield {

    public MagicalShield(String name, int defense) {
        super(ShieldType.MAGICAL_SHIELD, name, defense);
    }

}