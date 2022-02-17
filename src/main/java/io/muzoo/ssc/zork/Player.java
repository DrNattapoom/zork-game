package io.muzoo.ssc.zork;

public class Player {

    private static int MAX_HP = 10;

    private int hp;
    private int attackPower;

    public Player() {
        this(MAX_HP, 1);
    }

    public Player(int hp, int attackPower) {
        this.hp = hp;
        this.attackPower = attackPower;
    }

    public int getHp() {
        return hp;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public static int getMaxHp() {
        return MAX_HP;
    }

}
