package io.muzoo.ssc.zork.map.item;

import java.lang.reflect.InvocationTargetException;

public class PotionFactory {

    public static Potion createdPotion(PotionType potionType, String name) {
        try {
            Class<? extends Potion> potionClass = potionType.getPotionClass();
            return potionClass.getDeclaredConstructor(String.class).newInstance(name);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("unknown potion type");
    }

}
