package io.muzoo.ssc.zork.map.item;

public enum PotionType {

    HEALING_POTION(HealingPotion.class, "healingPotion"),
    POWER_POTION(PowerPotion.class, "powerPotion");

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
