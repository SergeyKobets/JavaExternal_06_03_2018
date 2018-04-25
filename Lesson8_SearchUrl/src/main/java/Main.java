import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class Main {
    public static String URLtest = "https://ru.wikipedia.org/wiki/Java";

    public static void main(String[] args) throws IOException {
        String wordToSearch = "java";
        String content = URLController.getContentFromUrl(URLtest);

        Set<String> setUrl = URLController.selectUrlFromContent(content);

        ConsoleHelper.printUrls(setUrl);

        ConsoleHelper.printMessage("Searching");
        Map<Integer, String> map = URLController.getWordsFromUrl(setUrl, wordToSearch);
        ConsoleHelper.printInfoFromMap(map);
    }


}
