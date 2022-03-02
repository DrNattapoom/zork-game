package io.muzoo.ssc.zork.map;

import io.muzoo.ssc.zork.map.item.*;
import io.muzoo.ssc.zork.map.monster.Monster;
import io.muzoo.ssc.zork.map.monster.MonsterFactory;
import io.muzoo.ssc.zork.map.monster.MonsterType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
                JSONObject jsonDoorsObject = (JSONObject) jsonRoomObject.get("doors");
                for (Object direction : jsonDoorsObject.keySet()) {
                    int neighborRoomNumber = ((Long) jsonDoorsObject.get(direction)).intValue();
                    this.rooms[i][j].createDoor((String) direction, neighborRoomNumber);
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

    public Room getRoom(int roomNumber) {
        int[] indexes = getIndexesFromRoomNumber(roomNumber);
        int row = indexes[0];
        int col = indexes[1];
        return rooms[row][col];
    }

    public Room[][] getRooms() {
        return rooms;
    }

    public int[] getDimension() {
        return dimension;
    }

    public String getName() {
        return name;
    }

    public void printMap(int playerLocation) {
        int width = this.dimension[0];
        int height = this.dimension[1];
        char[][] toPrint = new char[height*4][width*11];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Room room = this.rooms[i][j];
                if (!room.getDoors().keySet().isEmpty()) {
                    // ┌─────────┐
                    // │         │
                    // │         │
                    // └─────────┘
                    char[][] normalRoom = new char[][] {
                            "┌─────────┐".toCharArray(),
                            "│         │".toCharArray(),
                            "│         │".toCharArray(),
                            "└─────────┘".toCharArray()
                    };
                    char[][] currentRoom = new char[][] {
                            "┌─────────┐".toCharArray(),
                            "│ you are │".toCharArray(),
                            "│  here!  │".toCharArray(),
                            "└─────────┘".toCharArray()
                    };
                    int[] indexes = getIndexesFromRoomNumber(playerLocation);
                    for (int k = 0; k < normalRoom.length; k++) {
                        for (int l = 0; l < normalRoom[0].length; l++) {
                            toPrint[i*4 + k][j*11 + l] = (i == indexes[0] && j == indexes[1]) ? currentRoom[k][l] : normalRoom[k][l];
                        }
                    }
//                    // ┌─────────┐
//                    // │ you are │
//                    // │  here!  │
//                    // └─────────┘
//                    int[] indexes = getIndexesFromRoomNumber(playerLocation);
//                    if (i == indexes[0] && j == indexes[1]) {
//                        String[] lines = new String[] {
//                            "│ you are │",
//                            "│  here!  │"
//                        };
//                        for (int k = 0; k < lines.length; k++) {
//                            for (int l = 0; l < lines[0].length()) {
//
//                            }
//                        }
//                        toPrint[i*4 + 1][j*11 + 2] = 'y';
//                        toPrint[i*4 + 1][j*11 + 3] = 'o';
//                        toPrint[i*4 + 1][j*11 + 4] = 'u';
//                        toPrint[i*4 + 1][j*11 + 6] = 'a';
//                        toPrint[i*4 + 1][j*11 + 7] = 'r';
//                        toPrint[i*4 + 1][j*11 + 8] = 'e';
//                        toPrint[i*4 + 2][j*11 + 3] = 'h';
//                        toPrint[i*4 + 2][j*11 + 4] = 'e';
//                        toPrint[i*4 + 2][j*11 + 5] = 'r';
//                        toPrint[i*4 + 2][j*11 + 6] = 'e';
//                        toPrint[i*4 + 2][j*11 + 7] = '!';
//                    }
                    for (String direction : room.getDoors().keySet()) {
                        switch (direction) {
                            case "north":
                                // ┌───┘ └───┐
                                // │         │
                                // │         │
                                // └─────────┘
                                toPrint[i*4][j*11 + 4] = '┘';
                                toPrint[i*4][j*11 + 5] = ' ';
                                toPrint[i*4][j*11 + 6] = '└';
                                break;
                            case "south":
                                // ┌─────────┐
                                // │         │
                                // │         │
                                // └───┐ ┌───┘
                                toPrint[i*4 + 3][j*11 + 4] = '┐';
                                toPrint[i*4 + 3][j*11 + 5] = ' ';
                                toPrint[i*4 + 3][j*11 + 6] = '┌';
                                break;
                            case "west":
                                // ┌─────────┐
                                // ┘         │
                                // ┐         │
                                // └─────────┘
                                toPrint[i*4 + 1][j*11] = '┘';
                                toPrint[i*4 + 2][j*11] = '┐';
                                break;
                            case "east":
                                // ┌─────────┐
                                // │         └
                                // │         ┌
                                // └─────────┘
                                toPrint[i*4 + 1][j*11 + 10] = '└';
                                toPrint[i*4 + 2][j*11 + 10] = '┌';
                                break;
                        }
                    }
                }
            }
        }
        for (char[] row : toPrint) {
            System.out.println(row);
        }
    }

}
