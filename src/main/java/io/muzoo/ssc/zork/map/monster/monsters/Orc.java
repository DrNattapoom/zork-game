package io.muzoo.ssc.zork.map.monster.monsters;

import io.muzoo.ssc.zork.map.monster.Monster;
import io.muzoo.ssc.zork.map.monster.MonsterType;

public class Orc extends Monster {

    public Orc(int hp, int attackPower) {
        super(MonsterType.ORC, hp, attackPower);
    }

}