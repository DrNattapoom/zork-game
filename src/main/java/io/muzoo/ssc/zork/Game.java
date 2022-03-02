package io.muzoo.ssc.zork;

import io.muzoo.ssc.zork.command.*;
import io.muzoo.ssc.zork.map.ZorkMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Game {

    private static final String DEFAULT_PATH;
    private static final Map<String, String> MAP_CHOICES;

    static {
        DEFAULT_PATH = "default.json";
        MAP_CHOICES = new HashMap<>();
        MAP_CHOICES.put("default", DEFAULT_PATH);
    }

    private Map<String, String> savePoints;

    private Scanner scanner;
    private ZorkMap map;
    private Player player;
    private boolean playing;
    private boolean exit;

    public Game() {
        this.scanner = new Scanner(System.in);
        this.savePoints = new HashMap<>();
        this.savePoints.put("default", DEFAULT_PATH);
        this.exit = false;
    }

    public void start() {
        System.out.println("Game Started!");
        System.out.println("Type 'help' if you need help");
        while (!isExit()) {
            System.out.print("> ");
            String rawInput = scanner.nextLine();
            CommandLine commandLine = CommandParser.parseCommand(rawInput);
            if (commandLine == null) {
                System.out.println("Try again ...");
                System.out.println("Type 'help' if you need help");
            } else {
                Command command = CommandFactory.get(commandLine.getCommandType());
                command.execute(this, commandLine.getArguments());
            }
        }
    }

    public void printContext() {
        System.out.println(
            "You are an adventurer who are hired by a king whose castle was seized by a dragon. Your task is to reclaim the castle. \n" +
            "This is, however, not an easy feat because the vicious aura of the dragon has lured many monsters into the area. \n"
        );
        System.out.println("Goal: slay the dragon to reclaim the castle \n");
    }

    public Player getPlayer() {
        return player;
    }

    public ZorkMap getMap() {
        return map;
    }

    public boolean getPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public boolean isExit() {
        return exit;
    }

    public void exit() {
        System.out.println("Exiting Zork ...");
        this.exit = true;
        this.scanner.close();
        System.out.println("Zork exited. Bye!");
    }

    public void load(String filePath) {
        JSONParser parser = new JSONParser();
        Object object = null;
        try {
            object = parser.parse(new FileReader(filePath));
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
        this.player = new Player(jsonPlayerObject);
    }

    public Map<String, String> getSavePoints() {
        return savePoints;
    }

    public static Map<String, String> getMapChoices() {
        return MAP_CHOICES;
    }

}