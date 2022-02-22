package io.muzoo.ssc.zork;

public class Player extends Mortal {

    private static int MAX_HP = 10;

    public Player() {
        this(MAX_HP, 1);
    }

    public Player(int hp, int attackPower) {
        super(hp, attackPower);
    }

    public int getMaxHp() {
        return MAX_HP;
    }

}