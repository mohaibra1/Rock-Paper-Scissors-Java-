package rockpaperscissors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Hello, " + name);

        Player human = new HumanPlayer(name);
        Player computer = new ComputerPlayer("computer");

        String words = scanner.nextLine();
        System.out.println("Okay, let's start");
        boolean expandGame = !Objects.equals(words, "");

        Game game = new Game(human, computer);
        while (true) {
            if (!game.playRound(expandGame, words)){
                break;
            }
        }
    }
}
