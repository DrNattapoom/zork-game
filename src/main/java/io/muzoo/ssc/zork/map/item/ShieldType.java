package io.muzoo.ssc.zork.map.item;

public enum ShieldType {

    PHYSICAL_SHIELD(PhysicalShield.class, "physicalShield"),
    MAGICAL_SHIELD(MagicalShield.class, "magicalShield");

    private Class<? extends Shield> shieldClass;
    private String type;

    ShieldType(Class<? extends Shield> shieldClass, String type) {
        this.shieldClass = shieldClass;
        this.type = type;
    }

    public Class<? extends Shield> getShieldClass() {
        return shieldClass;
    }

    public String getType() {
        return type;
    }

    public static ShieldType getShieldType(String type) {
        for (ShieldType shieldType : ShieldType.values()) {
            if (shieldType.getType().equals(type)) {
                return shieldType;
            }
        }
        return null;
    }

}
