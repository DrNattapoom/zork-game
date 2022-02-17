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

}