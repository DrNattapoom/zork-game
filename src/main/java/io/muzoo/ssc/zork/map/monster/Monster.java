package io.muzoo.ssc.zork.map.monster;

import io.muzoo.ssc.zork.Mortal;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;

public abstract class Monster extends Mortal {

    private MonsterType monsterType;

    public Monster(MonsterType monsterType, int hp, int attackPower) {
        super(hp, attackPower);
        this.monsterType = monsterType;
    }

    public MonsterType getMonsterType() {
        return monsterType;
    }

    @Override
    public void attack(Mortal enemy) {
        System.out.println(String.format("%s attacks back", StringUtils.capitalize(this.getMonsterType().getType())));
        int currentEnemyHp = enemy.getHp();
        int damage = new Random().nextInt(this.getAttackPower());
        enemy.setHp(currentEnemyHp - damage);
    }

}
