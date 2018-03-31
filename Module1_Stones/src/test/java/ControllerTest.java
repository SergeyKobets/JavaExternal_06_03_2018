import exception.WrongOperationException;
import model.Gemstone;
import model.Stone;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static org.junit.Assert.*;

public class ControllerTest {
    private Controller controller;

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void initController() {
        controller = new Controller();
    }

    @After
    public void clearController() {
        controller = null;
    }

    @Test
    public void executeForOtherTest() throws WrongOperationException, IOException {
        thrown.expect(WrongOperationException.class);
        controller.execute(Operation.OTHER);
    }

    @Test
    public void selectNotNullStonesTest() {
        Stone[] stones = {new Gemstone("", 3, 4, 5), null, null, null};
        int expect = 1;
        Stone[] actual = controller.selectNotNullStones(stones);
        assertEquals(expect, actual.length);
    }

    @Test
    public void selectNecklaceStonesTest() {

    }
}