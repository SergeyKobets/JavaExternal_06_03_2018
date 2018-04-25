package parser.builders;

import bean.Category;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCategoriesBuilder {
    protected List<Category> categories;

    public AbstractCategoriesBuilder() {
        categories = new ArrayList<>();
    }

    public AbstractCategoriesBuilder(List<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }

    abstract public void buildSetCategories(String fileName);
}
