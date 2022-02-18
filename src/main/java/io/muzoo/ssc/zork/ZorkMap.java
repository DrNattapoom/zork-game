package io.muzoo.ssc.zork;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ZorkMap {

    private static String DEFAULT_PATH = "defaultMap.txt";

    private List<Room> rooms;
    private String path;
    private String name;
    private int dimension;

    public ZorkMap() {
        this(DEFAULT_PATH);
    }

    public ZorkMap(String path) {
        this.path = path;
        load();
    }

    private void load() {
        File file = new File(this.path);
        List<String> lines = null;
        try {
            lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("[load]");
            e.printStackTrace();
        }
        this.name = StringUtils.substringAfter(lines.get(0), ": ").trim();
        this.dimension = Integer.parseInt(StringUtils.substringAfter(lines.get(1), "x").trim());
        this.rooms = new ArrayList<>();
        for (int i = 2; i < 2 + dimension*dimension; i++) {
            Room room = new Room(StringUtils.substringBefore(lines.get(i), " connects"));
            String[] neighbors = StringUtils.substringAfter(lines.get(i), " connects").trim().split(",");
            for (String neighbor : neighbors) {
                String direction = StringUtils.substringBefore(neighbor, ":");
                String neighborName = StringUtils.substringAfter(neighbor, ":");
                Room neighborRoom = new Room(neighborName);
                room.createDoor(direction, neighborRoom);
            }
            rooms.add(room);
        }
    }

}
