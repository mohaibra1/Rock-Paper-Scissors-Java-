package rockpaperscissors;

// Game.java
public class Game {
    private Player player1;
    private Player player2;

    public Game(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
    }

    public void playRound() {
        Move move1 = player1.makeMove();
        Move move2 = player2.makeMove();

        //System.out.println(player1.getName() + " played " + move1);
        //System.out.println(player2.getName() + " played " + move2);

        if (move1 == move2) {
            System.out.println("There is a draw (" + move1 + ")");
        } else if (move1.beats(move2)) {
            System.out.println("Well done. The computer chose " + move2 + " and failed");
        } else {
            System.out.println("Sorry, but the " + player2.getName() + " chose " + move2);
        }
    }
}

