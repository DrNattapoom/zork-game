package io.muzoo.ssc.zork;

public class Player extends Mortal {

    private static int MAX_HP = 10;

    private int location;

    public Player(int hp, int attackPower) {
        super(hp, attackPower);
    }

    public Player(int hp, int attackPower, int location) {
        super(hp, attackPower);
        this.location = location;
    }

    public int getMaxHp() {
        return MAX_HP;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

}