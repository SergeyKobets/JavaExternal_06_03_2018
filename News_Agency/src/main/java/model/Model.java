package model;


import bean.Category;

import java.util.List;

public interface Model {

    ModelData getModelData();

    void loadCategories();

    void loadCategoryByName(String name);

    void addCategory(String name);

    void deleteCategory(String name);

    void loadNews();

    void addNews(String content, String category);

    void deleteNews(int id);

    void changeNewsData(int id, String content);

    void addCategoryFromXML(List<Category> categoriesList);
}
