package model;

import bean.Category;
import bean.News;
import model.service.CategoryService;
import model.service.CategoryServiceImpl;
import model.service.NewsService;
import model.service.NewsServiceImpl;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainModel implements Model {

    final static Logger logger = Logger.getLogger(MainModel.class);

    private ModelData modelData = new ModelData();
    private CategoryService categoryService = new CategoryServiceImpl();
    private NewsService newsService = new NewsServiceImpl();

    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadCategories() {
        List<Category> categoryList = categoryService.getAllCategories();
        modelData.setCategories(categoryList);
    }

    @Override
    public void loadCategoryByName(String name) {
        Category category = categoryService.getCategoryByName(name);
        modelData.setCategories(new ArrayList<>(Collections.singleton(category)));
    }

    @Override
    public void addCategory(String name) {
        categoryService.addCategory(name);
        loadCategories();
    }

    /**
     * change if need
     * @param name
     */
    @Override
    public void deleteCategory(String name) {
        categoryService.removeCategory(name);
        loadCategories();
    }


    @Override
    public void loadNews() {
        List<Category> categoryList = newsService.getAllNews();
        modelData.setCategories(categoryList);
    }

    @Override
    public void addNews(String content, String category) {
        newsService.addNews(content, category);
        List<Category> categories = new ArrayList<>();
        categories.add(categoryService.getCategoryByName(category));
        modelData.setCategories(categories);
    }

    @Override
    public void deleteNews(int id) {
        newsService.removeNews(id);
        modelData.setCategories(newsService.getAllNews());
    }

    @Override
    public void changeNewsData(int id, String content) {
        newsService.updateNews(id, content);
        modelData.setCategories(newsService.getAllNews());
    }

    @Override
    public void addCategoryFromXML(List<Category> categoriesList) {
        for (Category category : categoriesList) {
            String categoryName = category.getName();
            categoryService.addCategory(categoryName);
            for (News news : category.getNewsList()) {
                newsService.addNews(news.getContext(), categoryName);
            }
        }
        loadCategories();
    }


}
