package rockpaperscissors;

// ComputerPlayer.java
import java.util.Random;

public class ComputerPlayer implements Player {
    private String name;
    private Random random;
    private int score;

    public ComputerPlayer(String name) {
        this.name = name;
        this.random = new Random();
    }

    public Move makeMove(String ignored) {
        Move[] moves = Move.values();
        return moves[random.nextInt(moves.length)];
    }

    public String getName() {
        return name;
    }

    public int getScore(){
        return score;
    }

    public int score(int score){
        this.score += score;
        return score;
    }
}
