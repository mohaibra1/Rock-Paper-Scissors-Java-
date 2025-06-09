package rockpaperscissors;


import java.util.Objects;


// Game.java
public class Game {
    private Player player1;
    private Player player2;

    public Game(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
    }

    public boolean playRound(String userInput) {
        String str = "rock paper scissors";
        if (Objects.equals(userInput, "!exit")) {
            System.out.println("Bye!");
            return false;
        } else if (Objects.equals(userInput, "")) {
            System.out.println("Invalid input.");
            return true;

        }else if (!str.contains(userInput.toLowerCase())){
            System.out.println("Invalid input.");
            return true;
        }else {

            Move move1 = player1.makeMove(userInput);
            Move move2 = player2.makeMove("");

            if (move1 == move2) {
                System.out.println("There is a draw (" + move1.toString().toLowerCase() + ")");
                player1.score(50);
            } else if (move1.beats(move2)) {
                player1.score(100);
                System.out.println("Well done. The computer chose " + move2.toString().toLowerCase() + " and failed");
            } else {
                player2.score(100);
                System.out.println("Sorry, but the " + player2.getName() + " chose " + move2.toString().toLowerCase());
            }
            return true;
        }
    }
}

