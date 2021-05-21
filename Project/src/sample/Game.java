package sample;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private static final int MAX_WRONG_GUESSES = 7;
    private RandomWordFinder randomWordFinder;
    private String randomWord;

    private List<Character> wrongLetters;
    private int wrongGuesses;

    Game() {
        wrongLetters = new ArrayList<>();
        wrongGuesses = 0;
        try {
            randomWordFinder = new RandomWordFinder();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setNewRandomWord();
    }

    private void setNewRandomWord() {
        try {
            randomWord = randomWordFinder.getRandomWord();
            System.out.println("Random word: " + randomWord);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String getCurrentWord(){
        String currentWord = "";
        for (char c : randomWord.toCharArray()) {
            if (wrongLetters.contains(c)) {
                currentWord += c + " ";
            } else {
                currentWord += "_ ";
            }
        }
        return currentWord;
    }

    String getMissingChars() {
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

    String getWord() {
        String word = "";
        for (char c : randomWord.toCharArray()) {
            word += c + " ";
        }
        return word;
    }

    void reset() {
        wrongGuesses = 0;
        wrongLetters.clear();
        setNewRandomWord();
    }

    int getWrongGuesses() {
        return wrongGuesses;
    }

    boolean addChar(char ch) {
        boolean wrongGuess = false;
        if ((!wrongLetters.stream().anyMatch(i -> i.equals(ch)))) {
            wrongLetters.add(ch);
            if (!randomWord.contains(String.valueOf(ch))) {
                wrongGuess = true;
                wrongGuesses++;
            }
        }
        return wrongGuess;
    }

    List<Character> getEnteredChars() {
        return Collections.unmodifiableList(wrongLetters);
    }

    boolean isGameOver() {
        return wrongGuesses >= MAX_WRONG_GUESSES;
    }

    boolean isGameWon() {
        for (char c : randomWord.toCharArray()) {
            if (!wrongLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }
}
