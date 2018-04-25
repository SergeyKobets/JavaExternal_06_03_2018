package parser.validator;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorTest {

    @Test
    public void isValid() {
        boolean isValid = Validator.isValid("resources/XMLforUnitTest.xml");
        assertFalse(isValid);
    }
}