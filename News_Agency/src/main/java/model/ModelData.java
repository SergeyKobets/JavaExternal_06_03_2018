package model;

import bean.Category;

import java.util.List;

/**
 * Хранит необходимые данные для отображения на клиенте
 */
public class ModelData {
    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
