package game;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;

public class DexSearch {
    private final static String SPACE_SYMBOL = " ";
    private final static String COMMA_SYMBOL = ",";
    private final static String EMPTY_SYMBOL = "";
    private final static String DOT_SYMBOL = ".";
    private final static String FIRST_DEFINITION = "Sinonime: ";
    private final static String COLON_SYMBOL = ":";
    private final static String WORD_NOT_FOUND = "Cuvantul nu a fost gasit.";

    public static void findWord(String word) throws IOException {
        Document doc = Jsoup.connect("https://dexonline.ro/definitie/" +word).get();
        Element step = doc.body();
        String output = step.select("span.def").text();
        output = Normalizer.normalize(output, Normalizer.Form.NFD);
        output = output.replaceAll("[^\\p{ASCII}]", "");
        if (!output.contains(WORD_NOT_FOUND)) {
            ArrayList<String> buffer = new ArrayList<>();
            buffer.addAll(Arrays.asList(output.split(SPACE_SYMBOL)));
            boolean readytoPrint = false;

            //going through an array of words separated by space
            for (int i = 0; i < buffer.size(); ++i) {
                if (buffer.get(i).contains(FIRST_DEFINITION)) {
                    readytoPrint = true;
                    continue;
                }
                if (readytoPrint) {
                    if (buffer.get(i).contains(COLON_SYMBOL)) {
                        String temp = buffer.get(i).replaceAll(COLON_SYMBOL, EMPTY_SYMBOL);
                        System.out.print(temp + SPACE_SYMBOL);
                    } else {
                        System.out.print(buffer.get(i) + SPACE_SYMBOL);
                    }
                }
            }
            System.out.println("\n");
        } else {
            System.out.println("Sorry, the word is not found.\n");
        }


    }
}
