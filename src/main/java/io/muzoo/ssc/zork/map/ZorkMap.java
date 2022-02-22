package io.muzoo.ssc.zork.map;

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
        this.name = (String) map.get("name");
        int width = ((Long) ((JSONArray) map.get("dimension")).get(0)).intValue();
        int height = ((Long) ((JSONArray) map.get("dimension")).get(1)).intValue();
        this.dimension = new int[] { width, height };
        this.rooms = new Room[height][width];
        JSONArray roomList = (JSONArray) map.get("rooms");
        for (Object obj : roomList) {
            JSONObject jsonRoomObject = (JSONObject) obj;
            String roomName = (String) jsonRoomObject.get("name");
            String roomDescription = (String) jsonRoomObject.get("description");
            int roomNumber = ((Long) jsonRoomObject.get("number")).intValue();
            Room room = new Room(roomName, roomDescription, roomNumber);
            int[] indexes = getIndexesFromRoomNumber(roomNumber);
            int row = indexes[0];
            int col = indexes[1];
            if (this.rooms[row][col] == null) {
                this.rooms[row][col] = room;
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
                System.out.println(room.getNumber() + " connects to " + room.getDoors());
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
