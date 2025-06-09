package rockpaperscissors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        //C:\Users\mimoh\IdeaProjects\Rock-Paper-Scissors (Java)\Rock-Paper-Scissors (Java)\task\src\rockpaperscissors\
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Hello, " + name);

        Player human = new HumanPlayer(name);
        Player computer = new ComputerPlayer("computer");

        File file = new File("rating.txt");

        //Add player if not exists
        addPlayer(name, file);

        Game game = new Game(human, computer);
        while (true) {
            String userInput = scanner.nextLine();
            if (Objects.equals(userInput, "!rating")){
                showScore(human, file);
                continue;
            }
            if (!game.playRound(userInput)){
                updateScore(human.getName(), human.getScore(), file);
                break;
            }
        }
    }

    private static boolean nameExists(String name){
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

    private static File createFile(){
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

    private static void addPlayer(String name, File file){
        try (FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true)){
            if (!nameExists(name)) {
                fileWriter.write(name + " " + 0 + "\n");
            }
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private static void updateScore(String name, int score, File file){
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

    private static void showScore(Player player, File file){
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
