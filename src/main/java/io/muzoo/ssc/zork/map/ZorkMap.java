package io.muzoo.ssc.zork.map;

import io.muzoo.ssc.zork.map.item.*;
import io.muzoo.ssc.zork.map.monster.Monster;
import io.muzoo.ssc.zork.map.monster.MonsterFactory;
import io.muzoo.ssc.zork.map.monster.MonsterType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Arrays;

public class ZorkMap {

    private Room[][] rooms;
    private String name;
    private int[] dimension;

    public ZorkMap(JSONObject jsonMapObject) {
        loadMap(jsonMapObject);
    }

    private void loadMap(JSONObject jsonObject) {
        this.name = (String) jsonObject.get("name");
        int width = ((Long) ((JSONArray) jsonObject.get("dimension")).get(0)).intValue();
        int height = ((Long) ((JSONArray) jsonObject.get("dimension")).get(1)).intValue();
        this.dimension = new int[] { width, height };
        this.rooms = new Room[height][width];
        JSONArray roomGrid = (JSONArray) jsonObject.get("rooms");
        for (int i = 0; i < height; i++) {
            JSONArray row = (JSONArray) roomGrid.get(i);
            for (int j = 0; j < width; j++) {
                JSONObject jsonRoomObject = (JSONObject) row.get(j);
                int roomNumber = ((Long) jsonRoomObject.get("number")).intValue();
                String roomName = (String) jsonRoomObject.get("name");
                String roomDescription = (String) jsonRoomObject.get("description");
                Monster monster = null;
                if (jsonRoomObject.containsKey("monster")) {
                    JSONObject jsonMonsterObject = (JSONObject) jsonRoomObject.get("monster");
                    MonsterType monsterType = MonsterType.getMonsterType((String) jsonMonsterObject.get("monsterType"));
                    int hp = ((Long) jsonMonsterObject.get("hp")).intValue();
                    int attackPower = ((Long) jsonMonsterObject.get("attackPower")).intValue();
                    monster = MonsterFactory.createdMonster(monsterType, hp, attackPower);
                }
                Item item = null;
                if (jsonRoomObject.containsKey("item")) {
                    JSONObject jsonItemObject = (JSONObject) jsonRoomObject.get("item");
                    item = ItemFactory.createdItem(jsonItemObject);
                }
                this.rooms[i][j] = new Room(roomName, roomDescription, roomNumber, monster, item);
            }
        }
    }

    private int[] getIndexesFromRoomNumber(int roomNumber) {
        int width = this.dimension[0];
        int row = (roomNumber - 1) / width;
        int col = (roomNumber - 1) % width;
        return new int[] { row, col };
    }

    public Room[][] getRooms() {
        return rooms;
    }

    public Room getRoom(int roomNumber) {
        int[] indexes = getIndexesFromRoomNumber(roomNumber);
        int row = indexes[0];
        int col = indexes[1];
        return rooms[row][col];
    }

    public String getName() {
        return name;
    }

    public int[] getDimension() {
        return dimension;
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
