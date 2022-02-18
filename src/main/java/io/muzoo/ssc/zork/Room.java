package io.muzoo.ssc.zork;

import java.util.HashMap;
import java.util.Map;

public class Room {

    private Map<String, Room> doors;
    private String name;
    private String description;

    public Room(String name) {
        this(name, "");
    }

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.doors = new HashMap<>();
    }

    public void createDoor(String direction, Room neighbor) {
        if (neighbor != null) {
            doors.put(direction, neighbor);
        }
    }

    public void printRoom() {
        System.out.println("----------");
        System.out.println("----------");
        System.out.println("----------");
        System.out.println("----------");
        System.out.println("----------");
    }

    public String getName() {
        return name;
    }

    public Map<String, Room> getDoors() {
        return doors;
    }

}
