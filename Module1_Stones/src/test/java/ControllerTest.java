import exception.WrongOperationException;
import model.Gemstone;
import model.SemiPreciousStone;
import model.Stone;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ControllerTest {

    private Controller controller;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void initController() {
        controller = new Controller();
    }

    @After
    public void cleanController() {
        controller = null;
    }

    @Test
    public void executeTestForOtherOperation() throws WrongOperationException {
        thrown.expect(WrongOperationException.class);
        controller.execute(Operation.OTHER);
    }

    @Test
    public void selectNecklaceStonesTest() {
        Stone[] stones = new Stone[6];
        stones[0] = new Gemstone("Dia", 12, 1, 44);
        stones[1] = new SemiPreciousStone("", 70, 0, 0);
        stones[2] = new SemiPreciousStone("", 11, 4, 0);
        stones[3] = new SemiPreciousStone("", 33, 20, 0);
        stones[4] = new Gemstone("Glass", 1, 30, 10);
        stones[5] = new SemiPreciousStone("", 0, 0, 0);

        Stone[] expected = new Stone[2];
        expected[0] = new Gemstone("Dia", 12, 1, 44);
        expected[1] = new Gemstone("Glass", 1, 30, 10);

        Stone[] actual = controller.selectNecklaceStones(stones);

        assertTrue(Arrays.equals(actual, expected));
    }

    @Test
    public void calculateWeight() {
        Stone[] stones = new Stone[2];
        stones[0] = new Gemstone("Dia", 12.5, 100, 44);
        stones[1] = new SemiPreciousStone("", 70, 200, 0);

        double expected = 82.5;
        double actual = controller.calculateWeight(stones);
        assertEquals(actual, expected, 0.1);
    }

    @Test
    public void calculateCost() {
        Stone[] stones = new Stone[3];
        stones[0] = new Gemstone("Dia", 12.5, 542, 44);
        stones[1] = new SemiPreciousStone("AAD", 70, 300.5f, 0);

        double expected = 842.5;
        double actual = controller.calculateCost(stones);

        assertEquals(actual, expected, 0.1);
    }

    @Test
    public void sortStones() {
        Stone[] stones = new Stone[3];
        stones[0] = new Gemstone("Dia", 12.5, 500, 44);
        stones[1] = new SemiPreciousStone("GGH", 23, 12, 0);
        stones[2] = new SemiPreciousStone("AAD", 70, 300.5f, 0);

        Stone[] sortedStones = new Stone[3];
        sortedStones[0] = new SemiPreciousStone("GGH", 23, 12, 0);
        sortedStones[1] = new SemiPreciousStone("AAD", 70, 300.5f, 0);
        sortedStones[2] = new Gemstone("Dia", 12.5, 500, 44);

        Stone[] actual = controller.sortStones(stones);
        assertTrue(Arrays.equals(actual, sortedStones));
    }

    @Test
    public void findStonesForTransparency() {
        Stone[] stones = new Stone[3];
        stones[0] = new Gemstone("Dia", 12.5, 500, 12);
        stones[1] = new SemiPreciousStone("GGH", 23, 12, 8);
        stones[2] = new SemiPreciousStone("AAD", 70, 300.5f, 13);

        Stone[] selectedStone = new Stone[3];
        selectedStone[0] = new Gemstone("Dia", 12.5, 500, 12);
        selectedStone[1] = new SemiPreciousStone("GGH", 23, 12, 8);

        Stone[] actual;
        String  str = "7\n13";
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(str.getBytes()));
            actual = controller.findStonesForTransparency(stones);
        } finally {
            System.setIn(stdin);
        }

        assertTrue(Arrays.equals(actual, selectedStone));
    }

    @Test
    public void selectNotNullStones() {
        Stone[] stones = new Stone[10];
        stones[0] = new Gemstone("ACC", 330, 110, 10);

        int expected = 1;
        int actual = controller.selectNotNullStones(stones).length;
        assertEquals(actual, expected);
    }
}