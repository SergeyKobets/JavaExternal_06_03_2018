package model;

import bean.Category;
import bean.News;

import java.util.ArrayList;
import java.util.List;

public class FakeModel implements Model {

    private ModelData modelData = new ModelData();
    private List<Category> categoriesList = new ArrayList<>();

    @Override
    public ModelData getModelData() {
        return modelData;
    }


    @Override
    public void loadCategories() {
        categoriesList.add(new Category("Sport"));
        categoriesList.add(new Category("City"));

        modelData.setCategories(categoriesList);
    }

    @Override
    public void loadNews() {
        List<Category> categories = modelData.getCategories();
        for (Category category : categories) {
            if (category.getName().equals("Sport")) {
                category.add(new News(1, "Boxing"));
                category.add(new News(2, "Swimming"));
            } else if (category.getName().equals("City")) {
                category.add(new News(3, "Building"));
            }
        }

        modelData.setCategories(categories);
    }

    @Override
    public void addNews(String content, String categoryName) {
        for (Category category : categoriesList) {
            if (category.getName().equals(categoryName)) {
                List<News> list = category.getNewsList();
                list.add(new News(content));
            }
        }
        modelData.setCategories(categoriesList);
    }

    @Override
    public void deleteNews(int id) {
        List<Category> tmp = new ArrayList<>();
        for (Category category : categoriesList) {
            Category currentCategory = new Category();
            for (News news : category.getNewsList()) {
                if (news.getId() != id) {
                    currentCategory.add(new News(news.getContext()));
                }
            }
            tmp.add(currentCategory);
        }
        modelData.setCategories(tmp);
    }

    @Override
    public void changeNewsData(int id, String content) {
        for (Category category : categoriesList) {
            for (News news : category.getNewsList()) {
                if (news.getId() == id) {
                    news.setContext(content);
                }
            }
        }
    }

    @Override
    public void addCategoryFromXML(List<Category> categoriesList) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void loadCategoryByName(String name) {
        List<Category> categories = new ArrayList<>();
        for (Category category : categoriesList) {
            if (category.getName().equals(name)) {
                categories.add(new Category(name));
            }
        }
        modelData.setCategories(categories);
    }

    @Override
    public void addCategory(String name) {
        categoriesList.add(new Category(name));
        modelData.setCategories(categoriesList);
    }

    @Override
    public void deleteCategory(String name) {
        throw new UnsupportedOperationException();
    }

}
