package io.muzoo.ssc.zork.map.monster;

import io.muzoo.ssc.zork.Mortal;
import io.muzoo.ssc.zork.Player;

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
        System.out.println(String.format("%s attacks back", this));
        Player player = (Player) enemy;
        int currentPlayerDefense = player.getDefense();
        int damage = new Random().nextInt(this.getAttackPower()) + 1;
        int remainingDefense = currentPlayerDefense - damage;
        if (remainingDefense < 0) {
            int currentPlayerHp = player.getHp();
            int newPlayerHp = currentPlayerHp + remainingDefense;
            enemy.setHp(newPlayerHp);
        }
        player.setDefense(Math.max(remainingDefense, 0));
        if (currentPlayerDefense > 0) {
            System.out.println("Your defense remains " + Math.max(remainingDefense, 0));
        }
    }

    @Override
    public void printStats() {
        System.out.println(
            String.format(
                "\tType: %s \n" +
                "\tHP: %d \n" +
                "\tAttack Power: %d",
                this,
                this.getHp(),
                this.getAttackPower()
            )
        );
    }

    @Override
    public String toString() {
        return StringUtils.capitalize(this.monsterType.getType().toLowerCase());
    }

}
