package rockpaperscissors;


public class HumanPlayer implements Player {
    private final String name;
    private int score;

    public HumanPlayer(String name) {
        this.name = name;
    }

    public Move makeMove(String input) {
        //System.out.print(name + ", enter your move (ROCK, PAPER, SCISSORS): ");
        while (true) {
            try {
                return Move.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.print("Invalid input. Try again: ");
            }
        }
    }

    public String getName(){
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
