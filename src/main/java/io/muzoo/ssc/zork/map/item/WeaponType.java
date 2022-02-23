package io.muzoo.ssc.zork.map.item;

public enum WeaponType {

    SWORD(Sword.class, "sword"),
    ARROW(Arrow.class, "arrow");

    private Class<? extends Weapon> weaponClass;
    private String type;

    WeaponType(Class<? extends Weapon> weaponClass, String type) {
        this.weaponClass = weaponClass;
        this.type = type;
    }

    public Class<? extends Weapon> getWeaponClass() {
        return weaponClass;
    }

    public String getType() {
        return type;
    }

    public static WeaponType getWeaponType(String type) {
        for (WeaponType weaponType : WeaponType.values()) {
            if (weaponType.getType().equals(type)) {
                return weaponType;
            }
        }
        return null;
    }

}
