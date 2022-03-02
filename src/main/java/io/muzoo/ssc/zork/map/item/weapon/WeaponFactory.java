package io.muzoo.ssc.zork.map.item.weapon;

import java.lang.reflect.InvocationTargetException;

public class WeaponFactory {

    public static Weapon createdWeapon(WeaponType weaponType, String name, int damage, int durability, boolean isLegendary) {
        try {
            Class<? extends Weapon> weaponClass = weaponType.getWeaponClass();
            return weaponClass.getDeclaredConstructor(String.class, int.class, int.class, boolean.class).newInstance(name, damage, durability, isLegendary);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("unknown weapon type");
    }

}
