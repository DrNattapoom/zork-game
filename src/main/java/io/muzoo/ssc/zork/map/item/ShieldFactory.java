package io.muzoo.ssc.zork.map.item;

import java.lang.reflect.InvocationTargetException;

public class ShieldFactory {

    public static Shield createdShield(ShieldType shieldType, String name, int defense) {
        try {
            Class<? extends Shield> shieldClass = shieldType.getShieldClass();
            return shieldClass.getDeclaredConstructor(String.class, int.class).newInstance(name, defense);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("unknown shield type");
    }

}
