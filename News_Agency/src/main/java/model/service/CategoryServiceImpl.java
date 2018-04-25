package model.service;

import bean.Category;
import dao.CategoryDao;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDao();

    @Override
    public Category getCategoryByName(String name) {
        Category category  = categoryDao.getCategoryByName(name);
        return category;
    }

    @Override
    public void addCategory(String name) {
        categoryDao.addCategory(name);
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categoryList = categoryDao.getAllCategories();
        return categoryList;
    }

    @Override
    public void removeCategory(String name) {
        categoryDao.removeCategory(name);
    }

    @Override
    public void updateCategory(int id, String name) {
        categoryDao.updateNameCategory(id, name);
    }
}
