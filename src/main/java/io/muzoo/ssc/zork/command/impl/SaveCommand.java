package io.muzoo.ssc.zork.command.impl;

import io.muzoo.ssc.zork.Game;
import io.muzoo.ssc.zork.command.Command;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;

public class SaveCommand implements Command {

    @Override
    public void execute(Game game, String argument) {
        if (game.getPlaying()) {
            if (StringUtils.isBlank(argument)) {
                System.out.println("Please provide the saved point name");
            } else {
                JSONObject jsonSavePointObject = new JSONObject();
                JSONObject jsonMapObject = getJSONObject(game.getMap());
                JSONObject jsonPlayerObject = getJSONObject(game.getPlayer());
                jsonSavePointObject.put("map", jsonMapObject);
                jsonSavePointObject.put("player", jsonPlayerObject);
                String fileName = argument + ".json";
                FileWriter file = null;
                System.out.println("Saving " + argument + " ...");
                try {
                    file = new FileWriter(fileName);
                    file.write(jsonSavePointObject.toJSONString());
                } catch (IOException e) {
                    System.out.println("[SaveCommand.execute]");
                    System.out.println("IOException: " + e.getMessage());
                    e.printStackTrace();
                } finally {
                    IOUtils.closeQuietly(file);
                }
                game.getSavePoints().put(argument, fileName);
                System.out.println(argument + " saved.");
            }
        } else {
            System.out.println("This command is only available while playing the game");
        }
    }

    private JSONObject getJSONObject(Object object) {
        String jsonString = new Gson().toJson(object);
        try {
            return (JSONObject) new JSONParser().parse(jsonString);
        } catch (ParseException e) {
            System.out.println("[SaveCommand.getJSONObject]");
            System.out.println("ParseException: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}