package io.muzoo.ssc.zork.map.monster.impl;

import io.muzoo.ssc.zork.map.monster.Monster;
import io.muzoo.ssc.zork.map.monster.MonsterType;

public class Dragon extends Monster {

    public Dragon(int hp, int attackPower) {
        super(MonsterType.DRAGON, hp, attackPower);
    }

}