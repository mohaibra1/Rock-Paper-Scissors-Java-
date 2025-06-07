package rockpaperscissors;

// ComputerPlayer.java
import java.util.Random;

public class ComputerPlayer implements Player {
    private String name;
    private Random random;

    public ComputerPlayer(String name) {
        this.name = name;
        this.random = new Random();
    }

    public Move makeMove() {
        Move[] moves = Move.values();
        return moves[random.nextInt(moves.length)];
    }

    public String getName() {
        return name;
    }
}
