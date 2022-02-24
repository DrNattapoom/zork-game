package io.muzoo.ssc.zork;

import io.muzoo.ssc.zork.command.*;
import io.muzoo.ssc.zork.map.ZorkMap;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Game {

    private static String DEFAULT_PATH = "defaultMap.json";

    private Scanner scanner;
    private ZorkMap map;
    private Player player;
    private boolean exit;

    public Game() {
        this.scanner = new Scanner(System.in);
        this.exit = false;
    }

    public void start() {
        printWelcome();
        while (!isExit()) {
            System.out.print("> ");
            String rawInput = scanner.nextLine();
            CommandLine commandLine = CommandParser.parseCommand(rawInput);
            if (commandLine == null) {
                System.out.println("Try again ...");
            } else {
                Command command = CommandFactory.get(commandLine.getCommandType());
                command.execute(this, commandLine.getArguments());
            }
        }
    }

    private void printWelcome() {
        System.out.println("Game Started");
        System.out.println("Welcome to the World of Zork!");
        System.out.println("Type 'help' if you need help.");
    }

    public Player getPlayer() {
        return player;
    }

    public ZorkMap getMap() {
        return map;
    }

    public boolean isExit() {
        return exit;
    }

    public void exit() {
        this.exit = true;
        this.scanner.close();
    }

    public void load(String data) {
        JSONParser parser = new JSONParser();
        Object object = null;
        try {
            object = parser.parse(new FileReader(data));
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
        JSONObject jsonGameObject = (JSONObject) object;
        JSONObject jsonMapObject = (JSONObject) jsonGameObject.get("map");
        this.map = new ZorkMap(jsonMapObject);
        JSONObject jsonPlayerObject = (JSONObject) jsonGameObject.get("player");
        int hp = ((Long) jsonPlayerObject.get("hp")).intValue();
        int attackPower = ((Long) jsonPlayerObject.get("attackPower")).intValue();
        int location = ((Long) jsonPlayerObject.get("location")).intValue();
        this.player = new Player(hp, attackPower, location);
    }

    public String getDefaultPath() {
        return DEFAULT_PATH;
    }

}