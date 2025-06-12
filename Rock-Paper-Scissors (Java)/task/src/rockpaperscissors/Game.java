package rockpaperscissors;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


// Game.java
public class Game {
    private Player player1;
    private Player player2;

    public Game(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
    }

    public boolean playRound(boolean gameExpand, String words) {
        File file = new File("rating.txt");
        Scanner scanner = new Scanner(System.in);
        //Add player if not exists
        addPlayer(player1.getName(), file);
        String userInput = scanner.nextLine();
        String str = "rock paper scissors";

        if (Objects.equals(userInput, "!exit")) {
            System.out.println("Bye!");
            updateScore(player1.getName(), player1.getScore(), file);
            return false;
        }else if (Objects.equals(userInput, "!rating")){
            showScore(player1, file);
            return true;
        }else if (Objects.equals(userInput, "") || (!words.contains(userInput.toLowerCase())
                && !str.contains(userInput.toLowerCase()))){
            System.out.println("Invalid input.");
            return true;
        }else {
            if (gameExpand){
                listOfOption(words, userInput);
            }else {
                rockPaperScissors(userInput);
            }
            return true;
        }
    }

    public void listOfOption(String option, String userChoice){
        List<String> options = new ArrayList<>(List.of(option.split(",")));
        String computerChoice = options.get(new Random().nextInt(options.size()));

        Map<String, List<String>> beatenByMap = generateBeatenByMap(options);
        List<String> loseList = new ArrayList<>(beatenByMap.get(userChoice));
        List<String> winList = new ArrayList<>(getBeatingOptions(beatenByMap, userChoice));

        if (Objects.equals(userChoice, computerChoice)){
            System.out.println("There is a draw (" + userChoice.toLowerCase() + ")");
            player1.score(50);
        }else if(winList.contains(computerChoice)){
            player1.score(100);
            System.out.println("Well done. The computer chose " + computerChoice.toLowerCase() + " and failed");
        } else if (loseList.contains(computerChoice)){
            player2.score(100);
            System.out.println("Sorry, but the " + player2.getName() + " chose " + computerChoice.toLowerCase());
        }

    }

    private   Map<String, List<String>> generateBeatenByMap(List<String> options) {
        Map<String, List<String>> beatenByMap = new HashMap<>();
        int size = options.size();

        for (int i = 0; i < size; i++) {
            String option = options.get(i);
            List<String> defeatedOptions = new ArrayList<>();
            int half = (size - 1) / 2;

            for (int j = 1; j <= half; j++) {
                defeatedOptions.add(options.get((i + j) % size));
            }

            beatenByMap.put(option, defeatedOptions);
        }

        return beatenByMap;
    }

    private List<String> getBeatingOptions(Map<String, List<String>> beatenByMap, String option) {
        List<String> beatingOptions = new ArrayList<>();

        for (Map.Entry<String, List<String>> entry : beatenByMap.entrySet()) {
            if (entry.getValue().contains(option)) {
                beatingOptions.add(entry.getKey());
            }
        }

        return beatingOptions;
    }

    private void rockPaperScissors(String userInput){
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
    }

    private boolean nameExists(String name){
        boolean nameExists = false;
        try{
            File file = createFile();
            Scanner myReader = new Scanner(file);
            while (myReader.hasNext()){
                String data = myReader.nextLine();
                if (data.contains(name)){
                    nameExists = true;
                }
            }
            myReader.close();
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }

        return nameExists;
    }

    private File createFile(){
        //System.out.println(file.getAbsolutePath());
        File file = new File("rating.txt");
        if (!file.exists()){
            try{
                boolean fileCreated = file.createNewFile();
                if (fileCreated){
                    System.out.println("File created1");
                }
            }catch (IOException ex){
                System.out.print("");
            }

        }
        return file;
    }

    private void addPlayer(String name, File file){
        try (FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true)){
            if (!nameExists(name)) {
                fileWriter.write(name + " " + 0 + "\n");
            }
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void updateScore(String name, int score, File file){
        List<String> updateLines = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                String updateLine = scanner.nextLine();
                System.out.println();
                if (updateLine.startsWith(name + " ")){
                    updateLines.add(name + " " + score);
                }else {
                    updateLines.add(updateLine);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (FileWriter fileWriter = new FileWriter(file.getAbsoluteFile())){
            for (String str: updateLines){
                fileWriter.write(str + "\n");
            }
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void showScore(Player player, File file){
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                String data = scanner.nextLine();
                if (data.startsWith(player.getName() + " ")){
                    String[] splt = data.split(" ");
                    int score = player.getScore() + Integer.parseInt(splt[1]);
                    System.out.println("Your rating: " + score);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

