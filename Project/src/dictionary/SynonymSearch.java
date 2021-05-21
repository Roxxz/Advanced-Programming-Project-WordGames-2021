package dictionary;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;

public class SynonymSearch {
    private final static String SPACE_SYMBOL = " ";
    private final static String COMMA_SYMBOL = ",";
    private final static String EMPTY_SYMBOL = "";
    private final static String DOT_SYMBOL = ".";
    private final static String FIRST_DEFINITION = "1.";
    private final static String COLON_SYMBOL = ":";
    private final static String WORD_NOT_FOUND = "rezultate (0)";
    ArrayList<String> synonyms = new ArrayList<>();
    public SynonymSearch(){}

    public ArrayList<String> findSynonym(String word) throws IOException {
        Document doc = Jsoup.connect("https://www.dictionardesinonime.ro/?c=" +word).get();
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
                if (buffer.get(i).contains(FIRST_DEFINITION) || buffer.get(i).contains(word) || buffer.get(i).contains("s.")) {
                    readytoPrint = true;
                    continue;
                }
                if (readytoPrint) {
                    if (buffer.get(i).contains(COLON_SYMBOL)) {
                        String temp = buffer.get(i).replaceAll(COLON_SYMBOL, EMPTY_SYMBOL);
                        if(buffer.get(i) == COMMA_SYMBOL && buffer.get(i+1) == COMMA_SYMBOL){
                            temp = buffer.get(i).replaceAll(COMMA_SYMBOL, EMPTY_SYMBOL);
                        }
                        synonyms.add(temp + SPACE_SYMBOL);

                    } else {
                        if(buffer.get(i) == COMMA_SYMBOL && buffer.get(i+1) == COMMA_SYMBOL){
                            String temp = buffer.get(i).replaceAll(COMMA_SYMBOL, EMPTY_SYMBOL);
                        }
                        synonyms.add(buffer.get(i));
                    }
                }
            }
            System.out.println("\n");
        } else {
            System.out.println("Sorry, the word is not found.\n");
        }
        return synonyms;

    }

}
