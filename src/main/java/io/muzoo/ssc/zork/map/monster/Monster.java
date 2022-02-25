package io.muzoo.ssc.zork.map.monster;

import io.muzoo.ssc.zork.Mortal;
import io.muzoo.ssc.zork.Player;

import org.apache.commons.lang3.StringUtils;

import javax.sound.midi.Soundbank;
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
        Player player = (Player) enemy;
        int currentPlayerDefense = player.getDefense();
        int damage = new Random().nextInt(this.getAttackPower());
        System.out.println("Your defense is " + currentPlayerDefense);
        System.out.println(String.format("Damage from %s is %x", StringUtils.capitalize(this.getMonsterType().getType()), damage));
        int remainingDefense = currentPlayerDefense - damage;
        if (remainingDefense < 0) {
            int currentPlayerHp = player.getHp();
            int newPlayerHp = currentPlayerHp + remainingDefense;
            enemy.setHp(newPlayerHp);
            System.out.println("Your HP is now " + newPlayerHp);
        }
        player.setDefense(Math.max(remainingDefense, 0));
        System.out.println("Your defense remains " + Math.max(remainingDefense, 0));
    }

}
