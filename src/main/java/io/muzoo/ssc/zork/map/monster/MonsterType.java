package io.muzoo.ssc.zork.map.monster;

import io.muzoo.ssc.zork.map.monster.impl.Dragon;
import io.muzoo.ssc.zork.map.monster.impl.Goblin;
import io.muzoo.ssc.zork.map.monster.impl.Orc;
import io.muzoo.ssc.zork.map.monster.impl.Slime;

public enum MonsterType {

    SLIME(Slime.class, "SLIME", 10, 1),
    GOBLIN(Goblin.class, "GOBLIN", 10, 2),
    ORC(Orc.class, "ORC", 20, 3),
    DRAGON(Dragon.class, "DRAGON", 50, 5);

    private Class<? extends Monster> monsterClass;
    private String type;
    private int defaultHp;
    private int defaultAttackPower;

    MonsterType(Class<? extends Monster> monsterClass, String type, int defaultHp, int defaultAttackPower) {
        this.monsterClass = monsterClass;
        this.type = type;
        this.defaultHp = defaultHp;
        this.defaultAttackPower = defaultAttackPower;
    }

    public Class<? extends Monster> getMonsterClass() {
        return monsterClass;
    }

    public String getType() {
        return type;
    }

    public int getDefaultHp() {
        return defaultHp;
    }

    public int getDefaultAttackPower() {
        return defaultAttackPower;
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