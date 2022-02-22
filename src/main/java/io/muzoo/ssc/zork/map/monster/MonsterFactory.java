package io.muzoo.ssc.zork.map.monster;

import java.lang.reflect.InvocationTargetException;

public class MonsterFactory {

    public static Monster createdMonster(MonsterType monsterType, int hp, int attackPower) {
        try {
            Class<? extends Monster> monsterClass = monsterType.getMonsterClass();
            return monsterClass.getDeclaredConstructor(int.class, int.class).newInstance(hp, attackPower);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("unknown monster type");
    }

}