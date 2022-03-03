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

    public String getName() {
        return name;
    }

    public int[] getDimension() {
        return dimension;
    }

    public void printMap(int playerLocation) {
        int width = this.dimension[0];
        int height = this.dimension[1];
        int xScale = 11;
        int yScale = 4;
        char[][] toPrint = new char[height*yScale][width*xScale];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Room room = this.rooms[i][j];
                if (!room.getDoors().keySet().isEmpty()) {
                    // ┌─────────┐
                    // │ you are │
                    // │  here!  │
                    // └─────────┘
                    // ┌─────────┐
                    // │         │
                    // │         │
                    // └─────────┘
                    int[] indexes = getIndexesFromRoomNumber(playerLocation);
                    String upperCenterRow = (i == indexes[0] && j == indexes[1]) ? "│ you are │" : "│         │";
                    String lowerCenterRow = (i == indexes[0] && j == indexes[1]) ? "│  here!  │" : "│         │";
                    char[][] normalRoom = new char[][] {
                            "┌─────────┐".toCharArray(),
                            upperCenterRow.toCharArray(),
                            lowerCenterRow.toCharArray(),
                            "└─────────┘".toCharArray()
                    };
                    for (int k = 0; k < normalRoom.length; k++) {
                        for (int l = 0; l < normalRoom[0].length; l++) {
                            toPrint[i*yScale + k][j*xScale + l] = normalRoom[k][l];
                        }
                    }
                    for (String direction : room.getDoors().keySet()) {
                        switch (direction) {
                            case "north":
                                // ┌───┘ └───┐
                                // │         │
                                // │         │
                                // └─────────┘
                                toPrint[i*yScale][j*xScale + (xScale / 2) - 1] = '┘';
                                toPrint[i*yScale][j*xScale + (xScale / 2)] = ' ';
                                toPrint[i*yScale][j*xScale + (xScale / 2) + 1] = '└';
                                break;
                            case "south":
                                // ┌─────────┐
                                // │         │
                                // │         │
                                // └───┐ ┌───┘
                                toPrint[i*yScale + (yScale - 1)][j*xScale + (xScale / 2) - 1] = '┐';
                                toPrint[i*yScale + (yScale - 1)][j*xScale + (xScale / 2)] = ' ';
                                toPrint[i*yScale + (yScale - 1)][j*xScale + (xScale / 2) + 1] = '┌';
                                break;
                            case "west":
                                // ┌─────────┐
                                // ┘         │
                                // ┐         │
                                // └─────────┘
                                toPrint[i*yScale + (yScale / 2) - 1][j*xScale] = '┘';
                                toPrint[i*yScale + (yScale / 2)][j*xScale] = '┐';
                                break;
                            case "east":
                                // ┌─────────┐
                                // │         └
                                // │         ┌
                                // └─────────┘
                                toPrint[i*yScale + (yScale / 2) - 1][j*xScale + (xScale - 1)] = '└';
                                toPrint[i*yScale + (yScale / 2)][j*xScale + (xScale - 1)] = '┌';
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
