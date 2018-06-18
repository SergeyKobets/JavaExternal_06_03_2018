package dao;

import bean.Category;
import bean.News;
import exception.WrongValueException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsDao extends AbstractDao {
    final static Logger logger = Logger.getLogger(NewsDao.class);
    private PreparedStatement statement = null;


    /**
     * add News
     */
    public void addNews(String content, String category) {
        String sqlGetCategory = "SELECT ID FROM Categories WHERE name = ?";
        String sqlAddNews = "INSERT INTO News (context, CategoryID) VALUES (?, ?)";

        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sqlGetCategory);
            statement.setString(1, category);
            ResultSet rs = statement.executeQuery();
            rs.next();
            int categoryID = rs.getInt("ID");

            statement = connection.prepareStatement(sqlAddNews);
            statement.setString(1, content);
            statement.setInt(2, categoryID);
            statement.execute();

            System.out.println("Новость добавлена успешно");
            logger.info("News added");
            stop(connection);

        } catch (SQLException e) {
            System.err.println("Новость не добавлена");
            logger.error(e);
        }
    }


    /**
     * delete News
     */
    public void removeNews(int id) {
        String sql = "DELETE FROM News WHERE ID = ?";
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int result = statement.executeUpdate();
            if (result > 0) {
                System.out.println("Новость с идентификатором "
                        + id + " успешно удалена!");
                logger.info("News removed");
            } else {
                System.out.println("Новость с идентификатором "
                        + id + " не найдена!");
                logger.info(String.format("News with id = %d not found", id));
            }

            stop(connection);
        } catch (SQLException e) {
            System.err.println(
                    "ОШИБКА при удалении новости с идентификатором " + id);
            logger.error(e);
        }
    }


    /**
     * getNews
     */
    public List<Category> getAllNews() {
        String sqlNews = "SELECT News.* FROM Categories INNER JOIN News ON Categories.ID = News.CategoryID " +
                "WHERE Categories.ID = ?";
        String sqlCategory = "SELECT * FROM Categories";
        Connection connection = getConnection();

        List<Category> categoriesList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sqlCategory);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int categoryID = rs.getInt(1);
                String name = rs.getString(2);
                Category category = new Category(categoryID, name);

                statement = connection.prepareStatement(sqlNews);
                statement.setInt(1, categoryID);
                ResultSet resultNews = statement.executeQuery();
                while (resultNews.next()) {
                    int newsID = resultNews.getInt(1);
                    String content = resultNews.getString(2);
                    category.add(new News(newsID, content));
                }
                categoriesList.add(category);
            }
            logger.info("Get all news");
            stop(connection);

            return categoriesList;
        } catch (SQLException e) {
            System.out.println(
                    "ОШИБКА при получении списка новостей");
            logger.error(e);
            throw new WrongValueException();
        }
    }

    /**
     * getNewsFromCategory
     */
    public Category getNewsFromCategory(String categoryName) {

        String sqlAllFromNews = "SELECT * FROM News WHERE CategoryID = ?";
        Connection connection = getConnection();
        Category category;
        Savepoint saveCategory = null;

        try {
            connection.setAutoCommit(false);
            int categoryID = getIdFromCategory(connection, categoryName);
            category = new Category(categoryID, categoryName);

            saveCategory = connection.setSavepoint("save category");

            statement = connection.prepareStatement(sqlAllFromNews);
            statement.setInt(1, categoryID);
            ResultSet newsSet = statement.executeQuery();
            while (newsSet.next()) {
                int newsID = newsSet.getInt("sss");
                String context = newsSet.getString("context");
                category.add(new News(newsID, context));
            }

            connection.commit();
            stop(connection);
            logger.info(String.format("Get news from category '%s'", categoryName.toUpperCase()));

            return category;
        } catch (SQLException e) {
            System.out.printf("ОШИБКА при получении списка новостей из категории '%s'", categoryName);
            if (saveCategory != null) {
                try {
                    connection.rollback();
                    logger.info("JDBC Transaction rolled back successfully and save category");
                } catch (SQLException e1) {
                    logger.error(e1);
                }
            }
            logger.error(e);
            throw new WrongValueException();
        }
    }

    /**
     * update News
     */
    public void updateNews(int id, String context) {
        String UPDATE = "UPDATE News SET context = ? WHERE ID = ?";
        Connection connection = getConnection();

        try {
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, context);
            statement.setInt(2, id);
            int result = statement.executeUpdate();
            if (result > 0) {
                System.out.println("Новость успешно обновлена");
                logger.info("News updated");
            } else {
                System.out.printf("Новость с идентификатором %d не найдена!\n", id);
                logger.info(String.format("News with id = %d not found", id));
            }

            stop(connection);
        } catch (SQLException e) {
            System.out.println("Ошибка при обновлении новости");
            logger.error(e);
        }
    }


    private int getIdFromCategory(Connection connection, String name) {
        String sqlIdFromCategory = "SELECT ID FROM Categories WHERE name = ?";

        try {
            statement = connection.prepareStatement(sqlIdFromCategory);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            rs.next();
            int categoryID = rs.getInt(1);

            logger.info("getID from category " + name);
            return categoryID;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
            throw new WrongValueException();
        }
    }
}
