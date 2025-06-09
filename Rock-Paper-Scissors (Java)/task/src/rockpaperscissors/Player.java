package rockpaperscissors;


// Player.java
public interface Player {
    Move makeMove(String move);
    String getName();
    int score(int score);

    int getScore();
}