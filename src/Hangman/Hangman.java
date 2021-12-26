package Hangman;
import java.util.*;
import java.lang.*;

public class Hangman {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("HANGMAN");
        while (true){
            System.out.print("Type \"play\" to play the game, \"exit\" to quit: ");
            String userGuess = sc.nextLine();
            if (Objects.equals(userGuess,"play")){
                game();
            }
            if (Objects.equals(userGuess,"exit")){
                break;
            }
        }
    }
    public static void game (){
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        String[] answersArr = {"java", "python", "javascript", "kotlin"};
        String answer = answersArr[rand.nextInt(answersArr.length)];

        ArrayList<String> wordViewer = new ArrayList<String>(Arrays.asList(answer.split("")));
        for (int i=0; i < wordViewer.size(); i++){
            wordViewer.set(i,"-");
        }

        Set<String> rightLetters = new HashSet<String>(Arrays.asList(answer.split("")));
        Set<String> alreadyGuessed = new HashSet<String>();

        int health_points = 8;
        while (health_points != 0){
            System.out.println(String.join("",wordViewer));
            System.out.print("Input a letter: ");
            String userInput = scanner.nextLine();
            if (userInput.length()>1){
                System.out.println("You should input a single letter");
                continue;
            }
            if(!Character.isLowerCase(userInput.charAt(0))){
                System.out.println("Please enter a lowercase English letter");
                continue;
            }
            if(alreadyGuessed.contains(userInput)){
                System.out.println("You've already guessed this letter");
                continue;
            }
            alreadyGuessed.add(userInput);
            if (rightLetters.contains(userInput)){
                if(!wordViewer.contains(userInput)){
                    ArrayList<Integer> indexes = char_switcher(userInput,answer);
                    for(int i : indexes){
                        wordViewer.set(i,userInput);
                    }
                }
                else{
                    System.out.println("No improvements");
                    health_points -= 1;
                }
            }
            else{
                System.out.println("That letter doesn't appear in the word");
                health_points -= 1;
            }

            if(!wordViewer.contains("-")){
                System.out.println(String.join("",wordViewer));
                System.out.println("You guessed the word!\n" +
                        "You survived!\n");
                break;
            }
        }
        if(health_points == 0){
            System.out.println("You lost!");
        }
    }
    public static ArrayList<Integer> char_switcher(String userInput, String rightWord){
        ArrayList<String> wordChars = new ArrayList<String>(Arrays.asList(rightWord.split("")));
        ArrayList<Integer> idxs = new ArrayList<Integer>();
        for (int i=0; i < wordChars.size();i++){
            if(Objects.equals(wordChars.get(i), userInput)){
                idxs.add(i);
            }
        }
        return idxs;
    }
}