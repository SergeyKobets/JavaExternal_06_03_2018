import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ParserTest {
    private Parser parser;

    @Before
    public void initParser() {
        parser = new Parser("");
    }

    @After
    public void cleanParser() {
        parser = null;
    }

    @Test
    public void parseLineTest() {
        String text = "hello, abc, load hhhh";
        String[] expect = {"hello", "abc", "load", "hhhh"};
        String[] actual = parser.parseLine(text);

        assertTrue(Arrays.equals(actual, expect));
    }
}