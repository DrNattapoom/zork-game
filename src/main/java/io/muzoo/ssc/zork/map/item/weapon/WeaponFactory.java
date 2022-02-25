package io.muzoo.ssc.zork.map.item.weapon;

import java.lang.reflect.InvocationTargetException;

public class WeaponFactory {

    public static Weapon createdWeapon(WeaponType weaponType, String name, int damage) {
        try {
            Class<? extends Weapon> weaponClass = weaponType.getWeaponClass();
            return weaponClass.getDeclaredConstructor(String.class, int.class).newInstance(name, damage);
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
