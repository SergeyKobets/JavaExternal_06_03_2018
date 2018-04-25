import exception.WrongExpressionException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ReversePolishNotationTest {

    private ReversePolishNotation rpn;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void initReversePolishNotation() throws Exception {
        this.rpn = new ReversePolishNotation("///");
    }

    @After
    public void cleanReversePolishNotation() {
        this.rpn = null;
    }

    @Test
    public void calculateTestForException() throws Exception {
        thrown.expect(NullPointerException.class);
        rpn = new ReversePolishNotation("3/0");
        rpn.parse();
        rpn.calculate();
    }

    @Test
    public void precedenceTestForPlus() {
        int expected = 2;
        int actual = rpn.precedence("+");

        assertEquals(expected, actual);
    }

    @Test
    public void precedenceTestForDivision() {
        int expected = 3;
        int actual = rpn.precedence("/");

        assertEquals(expected, actual);
    }

    @Test
    public void precedenceTestForOtherValue() {
        int expected = 5;
        int actual = rpn.precedence(".");

        assertEquals(expected, actual);
    }

    @Test
    public void parseTest() throws WrongExpressionException {
        thrown.expect(WrongExpressionException.class);
        rpn.parse();
    }

    @Test
    public void calculateTest() throws Exception {
        double expected = 15d;
        rpn = new ReversePolishNotation("10+5");
        rpn.parse();
        double actual = rpn.calculate();

        assertEquals(expected, actual, 0.1);
    }
}