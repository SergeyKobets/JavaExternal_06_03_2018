import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Sentence extends ArrayList<String> {
    private static Sentence instance;

    private Sentence() {

    }

    public static Sentence getInstance() {
        if (instance == null) {
            instance = new Sentence();
        }

        return instance;
    }

    public void addWords(String[] words) {
        this.addAll(Arrays.asList(words));
    }

    public ArrayList<String> getListWords() {
        return this;
    }

    public void sort(ArrayList<String> list) {
         Collections.sort(list);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
