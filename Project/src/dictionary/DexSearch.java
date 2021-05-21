package dictionary;

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
    private final static String FIRST_DEFINITION = "1.";
    private final static String COLON_SYMBOL = ":";
    private final static String WORD_NOT_FOUND = "rezultate (0)";
    private ArrayList<String> definition = new ArrayList<>();

    public ArrayList<String> findWord(String word) throws IOException {
        Document doc = Jsoup.connect("https://dexonline.ro/definitie/" +word).get();
        Element step = doc.body();
        String output = step.select("div.defWrapper").text();
        output = Normalizer.normalize(output, Normalizer.Form.NFD);
        output = output.replaceAll("[^\\p{ASCII}]", "");
        if (!output.contains(WORD_NOT_FOUND)) {
            ArrayList<String> buffer = new ArrayList<>();
            buffer.addAll(Arrays.asList(output.split(SPACE_SYMBOL)));
            boolean readytoPrint = false;

            //going through an array of words separated by space
            for (int i = 0; i < buffer.size(); ++i) {
                if (buffer.get(i).contains(FIRST_DEFINITION) || buffer.get(i).contains("s.") || buffer.get(i).contains("m.") ||
                        buffer.get(i).contains("f.") || buffer.get(i).contains("v.") || buffer.get(i).contains("adj.")) {
                    readytoPrint = true;
                    continue;
                }
                if (readytoPrint) {
                    if (buffer.get(i).contains(COLON_SYMBOL)) {
                        String temp = buffer.get(i).replaceAll(COLON_SYMBOL, EMPTY_SYMBOL);
                        definition.add(temp);
                        System.out.print(temp + SPACE_SYMBOL);
                    } else {
                        System.out.print(buffer.get(i) + SPACE_SYMBOL);
                        definition.add(buffer.get(i));
                    }
                }
            }
            System.out.println("\n");
        }
        return definition;


    }
}
