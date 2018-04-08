import java.util.Map;
import java.util.Set;

public class ConsoleHelper {

    public static void printMessage(String message)  {
        System.out.println(message);
    }


    public static void printUrls(Set<String> urls) {
        int i = 1;
        for (String url : urls) {
            System.out.println(i++ + ")" + url);
        }
    }


    public static void printInfoFromMap(Map<Integer, String> map) {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.print("frequency: " + entry.getKey() + "\t");
            System.out.println("URL: " + entry.getValue());
        }
    }
}
