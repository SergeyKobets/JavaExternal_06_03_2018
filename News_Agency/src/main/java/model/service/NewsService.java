package model.service;

import bean.Category;

import java.util.List;

public interface NewsService {

    void addNews(String content, String category);

    void removeNews(int id);

    List<Category> getAllNews();

    Category getNewsFromCategory(String categoryName);

    void updateNews(int id, String content);
}
