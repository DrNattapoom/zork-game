package io.muzoo.ssc.zork.map.monster.monsters;

import io.muzoo.ssc.zork.map.monster.Monster;
import io.muzoo.ssc.zork.map.monster.MonsterType;

public class Slime extends Monster {

    public Slime(int hp, int attackPower) {
        super(MonsterType.SLIME, hp, attackPower);
    }

}