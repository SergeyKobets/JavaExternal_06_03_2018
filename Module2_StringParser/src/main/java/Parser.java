import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    private String filePath;
    private Sentence lineWithWords = null;

    public Parser(String filePath) {
        this.filePath = filePath;
    }

    public void readText() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            lineWithWords = Sentence.getInstance();

            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] words = parseLine(line);
                lineWithWords.addWords(words);
            }

        } catch (FileNotFoundException e) {
            System.err.println("Файл не может быть создан: " + e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public String[] parseLine(String line) {
        return line.split("[,;:.\'”\"!?\\s]+");
    }

    public void printWords() {
        ArrayList<String> listWords = lineWithWords.getListWords();
        lineWithWords.sort(listWords);

        for (String word : listWords) {
            System.out.println(word);
        }
    }

}
