package io.muzoo.ssc.zork;

public abstract class Mortal {

    private int hp;
    private int attackPower;

    public Mortal(int hp, int attackPower) {
        this.hp = hp;
        this.attackPower = attackPower;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public abstract void attack(Mortal enemy);

}