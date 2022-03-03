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

    public void printRoomInfo(boolean brief) {
        System.out.println(this.name);
        System.out.println(this.description);
        System.out.print("Doors: ");
        for (String direction : this.doors.keySet()) {
            System.out.print(direction + " ");
        }
        System.out.println();
        if (this.item != null) {
            System.out.println(String.format("\nThere is the %s dropped here. ", this.item));
        }
        if (this.monster != null) {
            if (item == null) System.out.println();
            System.out.println(String.format("Be careful. There is one %s here. ", this.monster));
            if (!brief) {
                System.out.println("Here are the monster stats");
                this.monster.printStats();
            }
        }
    }

}
