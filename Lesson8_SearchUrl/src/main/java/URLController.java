import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLController {

     static String regexUrl =
            "((ht|f)tp(s?):\\/\\/|www\\.)"
                    + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                    + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};]*)";


    public static String getContentFromUrl(String stringUrl) throws IOException {
        StringBuilder sb = new StringBuilder();
        URL url = null;
        try {
            url = new URL(stringUrl);
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (MalformedURLException e) {
            System.err.println("Wrong URL");
            throw new IOException();
        }

        return sb.toString();
    }

    public static Set<String> selectUrlFromContent(String content) {
        Set<String> setUrl = new HashSet<>();

        Pattern pattern = Pattern.compile(regexUrl, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(content);

        while (matcher.find() && setUrl.size() != 20) {
            setUrl.add(matcher.group());
        }

        return setUrl;
    }


    public static Map<Integer, String> getWordsFromUrl(Set<String> setUrls, String wordToSearch) throws IOException {
        Pattern pattern = Pattern.compile(wordToSearch);
        Map<Integer, String> resultMap = new TreeMap<>(Collections.reverseOrder());
        for (String url : setUrls) {
            String words = getContentFromUrl(url);
            Matcher matcher = pattern.matcher(words);

            int freq = 0;
            while (matcher.find()) {
                freq++;
            }

            if (freq != 0) {
                resultMap.put(freq, url);
            }

        }

        return resultMap;
    }
}
