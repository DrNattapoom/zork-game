package io.muzoo.ssc.zork.map;

import io.muzoo.ssc.zork.map.monster.Monster;
import io.muzoo.ssc.zork.map.monster.MonsterFactory;
import io.muzoo.ssc.zork.map.monster.MonsterType;

import java.util.HashMap;
import java.util.Map;

public class Room {

    private Map<String, Integer> doors;
    private String name;
    private String description;
    private int number;

    private Monster monster;

    public Room(String name, String description, int number, Monster monster) {
        this.name = name;
        this.description = description;
        this.number = number;
        this.monster = monster;
        this.doors = new HashMap<>();
    }

    public void createDoor(String direction, int neighbor) {
        if (neighbor != -1) {
            doors.put(direction, neighbor);
        }
    }

    public Map<String, Integer> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getNumber() {
        return number;
    }

    public Monster getMonster() {
        return monster;
    }

}
