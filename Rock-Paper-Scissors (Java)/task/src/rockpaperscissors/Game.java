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

//        if (move1 == move2) {
//            System.out.println("It's a draw!");
//        } else if (move1.beats(move2)) {
//            System.out.println(player1.getName() + " wins!");
//        } else {
//            System.out.println("Sorry, but the " + player2.getName() + " chose scissors");
//        }
        if (move1 == Move.PAPER){
            System.out.println("Sorry, but the " + player2.getName() + " chose scissors");
        }else if(move1 == Move.SCISSORS){
            System.out.println("Sorry, but the " + player2.getName() + " chose rock");
        }else if(move1 == Move.ROCK){
            System.out.println("Sorry, but the " + player2.getName() + " chose paper");
        }
    }
}

