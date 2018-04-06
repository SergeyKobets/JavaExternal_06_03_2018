
public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser("/Users/admin/Desktop/test.txt");
        parser.readText();
        parser.printWords();
    }
}
