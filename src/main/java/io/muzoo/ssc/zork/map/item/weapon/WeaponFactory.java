package io.muzoo.ssc.zork.map.item.weapon;

import java.lang.reflect.InvocationTargetException;

public class WeaponFactory {

    public static Weapon createdWeapon(WeaponType weaponType, String name, int damage, int durability) {
        try {
            Class<? extends Weapon> weaponClass = weaponType.getWeaponClass();
            return weaponClass.getDeclaredConstructor(String.class, int.class, int.class).newInstance(name, damage, durability);
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
