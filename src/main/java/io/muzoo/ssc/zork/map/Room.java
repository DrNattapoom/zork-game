package io.muzoo.ssc.zork.map;

import io.muzoo.ssc.zork.map.item.Item;
import io.muzoo.ssc.zork.map.monster.Monster;

import java.util.HashMap;
import java.util.Map;

public class Room {

    private Map<String, Integer> doors;
    private String name;
    private String description;
    private int number;

    private Monster monster;
    private Item item;

    public Room(String name, String description, int number, Monster monster, Item item) {
        this.name = name;
        this.description = description;
        this.number = number;
        this.monster = monster;
        this.item = item;
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

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}
