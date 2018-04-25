package model.service;

import bean.Category;

import java.util.List;

public interface CategoryService {

    Category getCategoryByName(String name);

    void addCategory(String name);

    List<Category> getAllCategories();

    void removeCategory(String name);

    void updateCategory(int id, String name);

}
