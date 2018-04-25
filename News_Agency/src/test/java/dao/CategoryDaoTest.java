package dao;

import exception.WrongValueException;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class CategoryDaoTest {

    private CategoryDao categoryDao = new CategoryDao();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getCategoryByName() {
        thrown.expect(WrongValueException.class);
        categoryDao.getCategoryByName("a");
    }
}