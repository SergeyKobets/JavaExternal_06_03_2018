package controller;

import bean.Category;
import bean.News;
import model.FakeModel;
import model.Model;
import model.ModelData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import view.CategoriesView;
import view.NewsView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ControllerTest {

    private Controller controller = new Controller();

    @Before
    public void initController() {
        controller = new Controller();
    }

    @After
    public void cleanController() {
        controller = null;
    }

    @Test
    public void onShowAllCategories() {
        ArrayList<Category> expected = new ArrayList<Category>() {{
            add(new Category("Sport"));
            add(new Category("City"));
        }};

        Model fakeModel = new FakeModel();
        CategoriesView categoriesView = new CategoriesView();
        controller.setModel(fakeModel);
        controller.setCategoriesView(categoriesView);
        controller.onShowAllCategories();

        ModelData modelData = fakeModel.getModelData();
        ArrayList<Category> actual = (ArrayList<Category>) modelData.getCategories();
        for (int i = 0; i < actual.size(); i++) {
            Category categoryExpected = expected.get(i);
            Category categoryActual = expected.get(i);

            if (!categoryActual.getName().equals(categoryExpected.getName())) {
                fail();
            }
        }

    }

    @Test
    public void onShowCategory() {
        Category expected = new Category("City");

        Model fakeModel = new FakeModel();
        NewsView newsView = new NewsView();
        CategoriesView categoriesView = new CategoriesView();
        controller.setCategoriesView(categoriesView);
        controller.setModel(fakeModel);
        controller.setNewsView(newsView);
        controller.onShowAllCategories();
        controller.onShowCategory("City");

        ModelData modelData = fakeModel.getModelData();
        Category actual = modelData.getCategories().get(0);

        assertEquals(expected.getName(), actual.getName());

    }

    @Test
    public void onCategoryAdd() {
        Category expected = new Category("Economy");

        Model fakeModel = new FakeModel();
        CategoriesView categoriesView = new CategoriesView();
        controller.setModel(fakeModel);
        controller.setCategoriesView(categoriesView);
        controller.onCategoryAdd("Economy");

        ModelData modelData = fakeModel.getModelData();
        Category actual = modelData.getCategories().get(0);

        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    public void onShowAllNews() {
        Model fakeModel = new FakeModel();
        NewsView newsView = new NewsView();
        CategoriesView categoriesView = new CategoriesView();
        controller.setModel(fakeModel);
        controller.setNewsView(newsView);
        controller.setCategoriesView(categoriesView);
        controller.onShowAllCategories();
        controller.onShowAllNews();

        ModelData modelData = fakeModel.getModelData();

        int expected = 3;

        int countNews = 0;
        for (Category category : modelData.getCategories()) {
            for (News news : category.getNewsList()) {
                countNews++;
            }
        }

        assertEquals(countNews, expected);
    }

    @Test
    public void onNewsAdd() {
        Model fakeModel = new FakeModel();
        NewsView newsView = new NewsView();
        CategoriesView categoriesView = new CategoriesView();
        controller.setModel(fakeModel);
        controller.setNewsView(newsView);
        controller.setCategoriesView(categoriesView);
        controller.onShowAllCategories();
        controller.onNewsAdd("fromTest", "Sport");

        ModelData modelData = fakeModel.getModelData();

        int expected = 1;

        int countNews = 0;
        for (Category category : modelData.getCategories()) {
            for (News news : category.getNewsList()) {
                countNews++;
            }
        }

        assertEquals(countNews, expected);
    }

    @Test
    public void onNewsDelete() {
        Model fakeModel = new FakeModel();
        NewsView newsView = new NewsView();
        CategoriesView categoriesView = new CategoriesView();
        controller.setModel(fakeModel);
        controller.setNewsView(newsView);
        controller.setCategoriesView(categoriesView);
        controller.onShowAllCategories();
        controller.onNewsAdd("fromTest", "Sport");
        controller.onNewsAdd("fromTest2", "City");
        controller.onNewsDelete(1);

        ModelData modelData = fakeModel.getModelData();

        int expected = 2;

        int countNews = 0;
        for (Category category : modelData.getCategories()) {
            for (News news : category.getNewsList()) {
                countNews++;
            }
        }

        assertEquals(countNews, expected);
    }

    @Test
    public void onNewsUpdate() {
        Model fakeModel = new FakeModel();
        NewsView newsView = new NewsView();
        CategoriesView categoriesView = new CategoriesView();
        controller.setModel(fakeModel);
        controller.setNewsView(newsView);
        controller.setCategoriesView(categoriesView);
        controller.onShowAllCategories();
        controller.onNewsAdd("fromTest", "Sport");
        controller.onNewsAdd("fromTest2", "City");
        controller.onNewsUpdate(0, "updated");

        ModelData modelData = fakeModel.getModelData();

        for (Category category : modelData.getCategories()) {
            for (News news : category.getNewsList()) {
                if (news.getId() == 0 && !news.getContext().equals("updated")) {
                    fail();
                }
            }
        }

        controller.onShowAllNews();

    }
}