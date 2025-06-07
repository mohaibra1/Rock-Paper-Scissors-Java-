package rockpaperscissors;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private String name;
    private Scanner scanner;

    public HumanPlayer(String name) {
        this.name = name;
        this.scanner = new Scanner(System.in);
    }

    public Move makeMove() {
        //System.out.print(name + ", enter your move (ROCK, PAPER, SCISSORS): ");
        while (true) {
            try {
                return Move.valueOf(scanner.nextLine().trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.print("Invalid input. Try again: ");
            }
        }
    }

    public String getName(){
        return name;
    }
}
