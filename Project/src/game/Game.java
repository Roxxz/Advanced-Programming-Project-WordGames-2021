package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static controllers.ChooseGameController.typeOfGame;

public class Game {
    public static final int MAX_WRONG_GUESSES = 8;
    private RandomWordFinder randomWordFinder;
    private String randomWord;
    private List<Character> wrongLetters;
    private List<Character> correctLetters;
    public static int wrongGuesses;

    public Game() {
        wrongLetters = new ArrayList<>();
        correctLetters = new ArrayList<>();
        wrongGuesses = 0;
        try {
            randomWordFinder = new RandomWordFinder();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setNewRandomWord(typeOfGame);

    }

    public void setNewRandomWord(int type) {
        try {
            switch (type){
                case 1:
                    randomWord = randomWordFinder.getRandomWord(4);
                    break;
                case 2:
                    randomWord = randomWordFinder.getRandomWord(6);
                    break;
                case 3:
                    randomWord = randomWordFinder.getRandomWord(8);
                    break;
                default:
                    System.out.println("Something went wrong with finding a random numer.");
            }

            System.out.println("Random word: " + randomWord);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getCurrentWord(){
        String currentWord = "";
        for (char c : randomWord.toCharArray()) {
            if (correctLetters.contains(c)) {
                currentWord += c + " ";
            } else {
                currentWord += "_ ";
            }
        }
        return currentWord;
    }

    public String getMissingChars() {
        String missingChars = "";
        for (char c : randomWord.toCharArray()) {
            if (!wrongLetters.contains(c)) {
                missingChars += " ";
            }else{
                missingChars += c + " ";

            }
        }
        return missingChars;
    }

    public void reset() {
        wrongGuesses = 0;
        wrongLetters.clear();
        correctLetters.clear();
        setNewRandomWord(typeOfGame);
    }

    public boolean makeMove(char ch) {
        boolean wrongGuess = false;
        if ((!wrongLetters.stream().anyMatch(i -> i.equals(ch)))) {
            if (!randomWord.contains(String.valueOf(ch))) {
                wrongLetters.add(ch);
                wrongGuess = true;
                wrongGuesses++;
            }
            else{
                correctLetters.add(ch);
            }
        }
        return wrongGuess;
    }

    public List<Character> getWrongLetters() {
        return Collections.unmodifiableList(wrongLetters);
    }

    public boolean isGameOver() {
        return wrongGuesses >= MAX_WRONG_GUESSES;
    }

    public boolean isGameWon() {
        for (char c : randomWord.toCharArray()) {
            if (!correctLetters.contains(c)) {
                return false;
            }
        }

        return true;
    }

    public String getRandomWord() {
        return randomWord;
    }


}
