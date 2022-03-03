package io.muzoo.ssc.zork.map.monster.monsters;

import io.muzoo.ssc.zork.map.monster.Monster;
import io.muzoo.ssc.zork.map.monster.MonsterType;

public class Goblin extends Monster {

    public Goblin(int hp, int attackPower) {
        super(MonsterType.GOBLIN, hp, attackPower);
    }

}