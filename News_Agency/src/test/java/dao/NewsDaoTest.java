package dao;

import exception.WrongValueException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class NewsDaoTest {

    private NewsDao newsDao = new NewsDao();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getNewsFromCategory() {
        thrown.expect(WrongValueException.class);
        newsDao.getNewsFromCategory("a1");
    }
}