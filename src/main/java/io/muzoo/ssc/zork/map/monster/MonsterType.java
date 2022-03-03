package io.muzoo.ssc.zork.map.monster;

import io.muzoo.ssc.zork.map.monster.monsters.Dragon;
import io.muzoo.ssc.zork.map.monster.monsters.Goblin;
import io.muzoo.ssc.zork.map.monster.monsters.Orc;
import io.muzoo.ssc.zork.map.monster.monsters.Slime;

public enum MonsterType {

    SLIME(Slime.class, "SLIME"),
    GOBLIN(Goblin.class, "GOBLIN"),
    ORC(Orc.class, "ORC"),
    DRAGON(Dragon.class, "DRAGON");

    private Class<? extends Monster> monsterClass;
    private String type;

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