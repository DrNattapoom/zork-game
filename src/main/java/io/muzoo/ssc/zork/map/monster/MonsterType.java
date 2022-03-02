package io.muzoo.ssc.zork.map.monster;

import io.muzoo.ssc.zork.map.monster.impl.Dragon;
import io.muzoo.ssc.zork.map.monster.impl.Goblin;
import io.muzoo.ssc.zork.map.monster.impl.Orc;
import io.muzoo.ssc.zork.map.monster.impl.Slime;

public enum MonsterType {

    SLIME(Slime.class, "SLIME"),
    GOBLIN(Goblin.class, "GOBLIN"),
    ORC(Orc.class, "ORC"),
    DRAGON(Dragon.class, "DRAGON");

    private Class<? extends Monster> monsterClass;
    private String type;
    private int defaultHp;
    private int defaultAttackPower;

    MonsterType(Class<? extends Monster> monsterClass, String type) {
        this.monsterClass = monsterClass;
        this.type = type;
    }

    public Class<? extends Monster> getMonsterClass() {
        return monsterClass;
    }

    public String getType() {
        return type;
    }

    public static MonsterType getMonsterType(String type) {
        for (MonsterType monsterType : MonsterType.values()) {
            if (monsterType.getType().equals(type)) {
                return monsterType;
            }
        }
        return null;
    }

}