package io.muzoo.ssc.zork.map.item.potion;

import io.muzoo.ssc.zork.map.item.potion.potions.HealingPotion;
import io.muzoo.ssc.zork.map.item.potion.potions.PowerPotion;

public enum PotionType {

    HEALING_POTION(HealingPotion.class, "HEALING_POTION"),
    POWER_POTION(PowerPotion.class, "POWER_POTION");

    private Class<? extends Potion> potionClass;
    private String type;

    PotionType(Class<? extends Potion> potionClass, String type) {
        this.potionClass = potionClass;
        this.type = type;
    }

    public Class<? extends Potion> getPotionClass() {
        return potionClass;
    }

    public String getType() {
        return type;
    }

    public static PotionType getPotionType(String type) {
        for (PotionType potionType : PotionType.values()) {
            if (potionType.getType().equals(type)) {
                return potionType;
            }
        }
        return null;
    }

}
