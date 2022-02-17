package io.muzoo.ssc.zork;

import io.muzoo.ssc.zork.command.*;

import java.util.Scanner;

public class Game {

    private Scanner scanner;
    private Player player;
    private boolean exit;

    public Game() {
        this.scanner = new Scanner(System.in);
        this.player = new Player();
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

    public boolean isExit() {
        return exit;
    }

    public void exit() {
        this.exit = true;
        this.scanner.close();
    }

}