package io.muzoo.ssc.zork.map;

import io.muzoo.ssc.zork.map.item.Item;
import io.muzoo.ssc.zork.map.item.Weapon;
import io.muzoo.ssc.zork.map.item.WeaponFactory;
import io.muzoo.ssc.zork.map.item.WeaponType;
import io.muzoo.ssc.zork.map.monster.Monster;
import io.muzoo.ssc.zork.map.monster.MonsterFactory;
import io.muzoo.ssc.zork.map.monster.MonsterType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class ZorkMap {

    private static String DEFAULT_PATH = "defaultMap.json";

    private Room[][] rooms;
    private String path;
    private String name;
    private int[] dimension;

    public ZorkMap() {
        this(DEFAULT_PATH);
    }

    public ZorkMap(String path) {
        this.path = path;
        load();
    }

    private void load() {
        JSONParser parser = new JSONParser();
        Object object = null;
        try {
            object = parser.parse(new FileReader(this.path));
        } catch (FileNotFoundException e) {
            // catch an exception thrown by FileReader
            System.out.println("[load]");
            System.out.println("FileNotFoundException: " + e.getMessage());
            e.printStackTrace();
        } catch (ParseException e) {
            // catch an exception thrown by parser.parse
            System.out.println("[load]");
            System.out.println("ParseException: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            // catch an exception thrown by parser.parse
            System.out.println("[load]");
            System.out.println("IOException: " + e.getMessage());
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) object;
        JSONObject map = (JSONObject) jsonObject.get("map");
        // set the map name
        this.name = (String) map.get("name");
        int width = ((Long) ((JSONArray) map.get("dimension")).get(0)).intValue();
        int height = ((Long) ((JSONArray) map.get("dimension")).get(1)).intValue();
        // set the map dimension
        this.dimension = new int[] { width, height };
        // initialize 2d grid map
        this.rooms = new Room[height][width];
        JSONArray roomList = (JSONArray) map.get("rooms");
        for (Object obj : roomList) {
            JSONObject jsonRoomObject = (JSONObject) obj;
            // get room name
            String roomName = (String) jsonRoomObject.get("name");
            // get room description
            String roomDescription = (String) jsonRoomObject.get("description");
            // get room number
            int roomNumber = ((Long) jsonRoomObject.get("number")).intValue();
            // get monster inside the room (if any)
            JSONObject jsonRoomMonsterObject = (JSONObject) jsonRoomObject.get("monster");
            Monster monster = null;
            if (jsonRoomMonsterObject.containsKey("type")) {
                MonsterType monsterType = MonsterType.getMonsterType((String) jsonRoomMonsterObject.get("type"));
                int hp = (jsonRoomMonsterObject.containsKey("hp")) ? ((Long) jsonRoomMonsterObject.get("hp")).intValue() : monsterType.getDefaultHp();
                int attackPower = (jsonRoomMonsterObject.containsKey("attackPower")) ? ((Long) jsonRoomMonsterObject.get("attackPower")).intValue() : monsterType.getDefaultAttackPower();
                monster = MonsterFactory.createdMonster(monsterType, hp, attackPower);
            }
            // get monster inside the room (if any)
            JSONObject jsonRoomItemObject = (JSONObject) jsonRoomObject.get("item");
            Item item = null;
            if (jsonRoomItemObject.containsKey("damage")) {
                WeaponType weaponType = WeaponType.getWeaponType((String) jsonRoomItemObject.get("type"));
                String name = (String) jsonRoomItemObject.get("name");
                int damage = ((Long) jsonRoomItemObject.get("damage")).intValue();
                item = WeaponFactory.createdWeapon(weaponType, name, damage);
            }
            // create a room
            Room room = new Room(roomName, roomDescription, roomNumber, monster, item);
            int[] indexes = getIndexesFromRoomNumber(roomNumber);
            int row = indexes[0];
            int col = indexes[1];
            if (this.rooms[row][col] == null) {
                this.rooms[row][col] = room;
                // create doors (if any)
                JSONObject jsonRoomDoorsObject = (JSONObject) jsonRoomObject.get("doors");
                for (Object direction : jsonRoomDoorsObject.keySet()) {
                    int neighborRoomNumber = ((Long) jsonRoomDoorsObject.get(direction)).intValue();
                    this.rooms[row][col].createDoor((String) direction, neighborRoomNumber);
                }
            }
        }
    }

    private int[] getIndexesFromRoomNumber(int roomNumber) {
        int width = this.dimension[0];
        int row = (roomNumber - 1) / width;
        int col = (roomNumber - 1) % width;
        return new int[] { row, col };
    }

    public void printMap() {
        int width = this.dimension[0];
        int height = this.dimension[1];
        char[][] toPrint = new char[height*10][width*10];
        for (char[] row : toPrint) {
            Arrays.fill(row, '=');
        }
        for (int row = 0; row < rooms.length; row++) {
            for (int col = 0; col < rooms[row].length; col++) {
                Room room = this.rooms[row][col];
                for (String direction : room.getDoors().keySet()) {
                    switch (direction) {
                        case "north":
                            for (int i = 0; i < 6; i++) {
                                for (int j = 0; j < 2; j++) {
                                    toPrint[i + row*10][j + 4 + col*10] = ' ';
                                }
                            }
                            break;
                        case "south":
                            for (int i = -2; i < 4; i++) {
                                for (int j = 0; j < 2; j++) {
                                    toPrint[i + row*10 + 6][j + 4 + col*10] = ' ';
                                }
                            }
                            break;
                        case "east":
                            for (int i = 0; i < 2; i++) {
                                for (int j = -2; j < 4; j++) {
                                    toPrint[i + row*10 + 4][j + 6 + col*10] = ' ';
                                }
                            }
                            break;
                        case "west":
                            for (int i = 0; i < 2; i++) {
                                for (int j = 0; j < 6; j++) {
                                    toPrint[i + row*10 + 4][j + col*10] = ' ';
                                }
                            }
                            break;
                    }
                }
            }
        }
        for (char[] row : toPrint) {
            System.out.println(row);
        }
    }

}
