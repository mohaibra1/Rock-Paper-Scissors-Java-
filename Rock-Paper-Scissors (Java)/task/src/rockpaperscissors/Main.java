package rockpaperscissors;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Player human = new HumanPlayer("You");
        Player computer = new ComputerPlayer("computer");

        Game game = new Game(human, computer);
        while (true) {
            if (!game.playRound()){
                break;
            }
        }
    }
}
