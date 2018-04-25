package model.service;

import bean.Category;
import dao.NewsDao;

import java.util.List;

public class NewsServiceImpl implements NewsService {

    private NewsDao newsDao = new NewsDao();

    @Override
    public void addNews(String content, String category) {
        newsDao.addNews(content, category);
    }

    @Override
    public void removeNews(int id) {
        newsDao.removeNews(id);
    }

    @Override
    public List<Category> getAllNews() {
        List<Category> categoryList = newsDao.getAllNews();
        return categoryList;
    }


    /**
     * change method
     * @param categoryName
     * @return
     */
    @Override
    public Category getNewsFromCategory(String categoryName) {
        Category category = newsDao.getNewsFromCategory(categoryName);
        return category;
    }

    @Override
    public void updateNews(int id, String content) {
        newsDao.updateNews(id, content);
    }
}
