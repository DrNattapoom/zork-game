package io.muzoo.ssc.zork.map.item.shield.impl;

import io.muzoo.ssc.zork.map.item.shield.Shield;
import io.muzoo.ssc.zork.map.item.shield.ShieldType;

public class PhysicalShield extends Shield {

    public PhysicalShield(String name, int defense) {
        super(ShieldType.PHYSICAL_SHIELD, name, defense);
    }

}