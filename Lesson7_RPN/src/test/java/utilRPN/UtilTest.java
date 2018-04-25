package utilRPN;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilTest {

    @Test
    public void isDelimiterTestForEmptyString() {
        assertFalse(Util.isDelimiter(""));
    }

    @Test
    public void isDelimiterTestForBracket() {
        assertTrue(Util.isDelimiter(")"));
    }

    @Test
    public void isOperatorForEmptyString() {
        assertFalse(Util.isOperator(""));
    }

    @Test
    public void isOperatorForPlus() {
        assertTrue(Util.isOperator("+"));
    }

    @Test
    public void isOperandForPlus() {
        assertFalse(Util.isOperand("+"));
    }

    @Test
    public void isOperandForNine() {
        assertTrue(Util.isOperand("9"));
    }

    @Test
    public void isFunctionForSin() {
        assertTrue(Util.isFunction("sin"));
    }

    @Test
    public void isFunctionForPlus() {
        assertFalse(Util.isFunction("+"));
    }
}