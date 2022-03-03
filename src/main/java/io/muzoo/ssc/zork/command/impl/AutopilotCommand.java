package io.muzoo.ssc.zork.command.impl;

import io.muzoo.ssc.zork.Game;
import io.muzoo.ssc.zork.command.Command;

import io.muzoo.ssc.zork.command.CommandFactory;
import io.muzoo.ssc.zork.command.CommandLine;
import io.muzoo.ssc.zork.command.CommandParser;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class AutopilotCommand implements Command {

    @Override
    public void execute(Game game, String argument) {
        if (game.getPlaying()) {
            if (StringUtils.isBlank(argument)) {
                System.out.println("Please provide the file name");
            } else {
                List<String> lines = null;
                try {
                    File file = new File(argument);
                    lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
                } catch (FileNotFoundException e) {
                    // catch an exception thrown by File
                    System.out.println("[Autopilot.execute]");
                    System.out.println("FileNotFoundException: " + e.getMessage());
                    System.out.println("Please provide a valid file path");
                } catch (IOException e) {
                    // catch an exception thrown by FileUtils.readLines
                    System.out.println("[Autopilot.execute]");
                    System.out.println("IOException: " + e.getMessage());
                    e.printStackTrace();
                }
                if (lines != null) {
                    for (String line : lines) {
                        System.out.println("> " + "\u001B[32m" + line + "\u001B[0m");
                        CommandLine commandLine = CommandParser.parseCommand(line);
                        if (commandLine == null) {
                            System.out.println("Try again ...");
                            System.out.println("Type 'help' if you need help");
                        } else {
                            Command command = CommandFactory.get(commandLine.getCommandType());
                            command.execute(game, commandLine.getArguments());
                        }
                    }
                }
            }
        } else {
            System.out.println("This command is only available while playing the game");
        }
    }

}