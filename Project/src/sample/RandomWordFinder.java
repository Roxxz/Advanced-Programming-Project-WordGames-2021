package sample;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class RandomWordFinder{
    private static final String fileName = "res/words.txt";

    private ArrayList<String> words = new ArrayList<String>();

    public RandomWordFinder() {
        try (InputStream in = getClass().getResourceAsStream(fileName);
             BufferedReader bf = new BufferedReader(new InputStreamReader(in))) {

            String line = "";
            while ((line = bf.readLine()) != null)
                words.add(line);
        }
        catch (Exception e) {
            System.out.println("Couldn't find/read file: " + fileName);
            System.out.println("Error message: " + e.getMessage());
        }
    }

    public String getRandomWord(int limit) {
        if (words.isEmpty()) return "NO-DATA";
        String random =  words.get((int)(Math.random()*words.size()));
        while(random.length() > limit || random.length() < (limit - 2)){
            random =  words.get((int)(Math.random()*words.size()));
        }
        return random;
    }
}
