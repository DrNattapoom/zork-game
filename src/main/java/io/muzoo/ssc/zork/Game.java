package io.muzoo.ssc.zork;

import java.util.Scanner;

public class Game {

    public void start() {
        System.out.println("Game Started");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String rawInput = scanner.nextLine();
            System.out.println("you enter " + rawInput);
            if (rawInput.equals("exit")) {
                break;
            }
        }
        scanner.close();
    }

}