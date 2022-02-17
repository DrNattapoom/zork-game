package io.muzoo.ssc.zork;

import io.muzoo.ssc.zork.command.Command;
import io.muzoo.ssc.zork.command.CommandFactory;
import io.muzoo.ssc.zork.command.CommandParser;
import io.muzoo.ssc.zork.command.CommandType;

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
            CommandType commandType = CommandParser.parseCommand(rawInput);
            Command command = CommandFactory.get(commandType);
            if (command == null) {
                System.out.println("try again");
            } else {
                command.execute(this);
            }
        }
    }

}