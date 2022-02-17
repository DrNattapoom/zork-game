package io.muzoo.ssc.zork;

import io.muzoo.ssc.zork.command.*;

import java.util.Scanner;

public class Game {

    private Scanner scanner = new Scanner(System.in);
    private boolean exit = false;

    public boolean isExit() {
        return this.exit;
    }

    public void exit() {
        this.exit = true;
        this.scanner.close();
    }

    public void start() {
        System.out.println("Game Started");
        while (!isExit() && scanner.hasNextLine()) {
            String rawInput = scanner.nextLine();
            CommandLine commandLine = CommandParser.parseCommand(rawInput);
            Command command = CommandFactory.get(commandLine.getCommandType());
            if (command == null) {
                System.out.println("try again");
            } else {
                command.execute(this, commandLine.getArguments());
            }
        }
    }

}