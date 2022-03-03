package io.muzoo.ssc.zork.map.item.weapon;

import io.muzoo.ssc.zork.map.item.weapon.weapons.Arrow;
import io.muzoo.ssc.zork.map.item.weapon.weapons.Sword;

public enum WeaponType {

    SWORD(Sword.class, "SWORD"),
    ARROW(Arrow.class, "ARROW");

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
