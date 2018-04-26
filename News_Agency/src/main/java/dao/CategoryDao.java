package dao;

import bean.Category;
import bean.News;
import exception.WrongValueException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao extends AbstractDao {

    final static Logger logger = Logger.getLogger(CategoryDao.class);
    private PreparedStatement statement = null;

    /**
     *
     * @param categoryName
     * @return
     * @throws WrongValueException
     */
    public Category getCategoryByName(String categoryName) throws WrongValueException {

        String sqlAllFromNews = "SELECT * FROM News WHERE CategoryID = ?";
        Connection connection = getConnection();
        Category category = null;
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
            logger.info(String.format("Get category '%s'", categoryName.toUpperCase()));

            return category;
        } catch (SQLException e) {
            System.out.printf("ОШИБКА при получении списка новостей из категории '%s'", categoryName);
            if (saveCategory != null) {
                try {
                    connection.rollback();
                    logger.info("JDBC Transaction rolled back successfully and save category " + category.getName());
                } catch (SQLException e1) {
                    logger.error(e1);
                }
            }
            logger.error(e);
            throw new WrongValueException();
        }
    }

    /**
     *
     * @param name
     */
    public void addCategory(String name) {
        String sql_select = "SELECT ID FROM Categories WHERE name = ?";
        String sql_insert = "INSERT INTO Categories (name) VALUES (?)";
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql_select);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                statement = connection.prepareStatement(sql_insert);
                statement.setString(1, name);
                statement.execute();
            } else {
                System.out.printf("Категория %s уже существует\n", name);
            }

             logger.info(String.format("Added category %s", name));
            stop(connection);
        } catch (SQLException e) {
            System.err.println("Категория " + name + " не добавлена");
            logger.error(e);
        }
    }


    /**
     * @return
     */
    public List<Category> getAllCategories() {
        String sql = "SELECT ID, name FROM Categories";

        Connection connection = getConnection();
        List<Category> categories = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("name");
                categories.add(new Category(id, name));
            }

            logger.info("Get all categories");
            rs.close();
            stop(connection);
        } catch (SQLException e) {
            System.out.println(
                    "ОШИБКА при получении списка категорий");
            logger.error(e);
        }

        return categories;
    }


    /**
     * @param name
     */
    public void removeCategory(String name) {

        String sql_deleteCategory = "DELETE FROM Categories WHERE name = ?";
        Connection connection = getConnection();

        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(3);

            deleteCategoryNews(connection, name);

            statement = connection.prepareStatement(sql_deleteCategory);
            statement.setString(1, name);
            int result = statement.executeUpdate();

            if (result > 0) {
                System.out.println("Категория "
                        + name + " успешно удалена!");
                logger.info(String.format("Removed category '%s'", name));
            } else {
                System.out.println("Категория "
                        + name + " не найдена!");
                logger.info(String.format("Category '%s' not found", name));
            }

            connection.commit();
            stop(connection);

        } catch (SQLException e) {
            System.err.println("ОШИБКА при удалении категории " + name);
            try {
                connection.rollback();
                logger.info("JDBC Transaction rolled back successfully");
            } catch (SQLException e1) {
                logger.error(e1);
            }
            logger.error(e);
            logger.info("Rollback remove category");
        }
    }

    /**
     * @param id
     * @param name
     */
    public void updateNameCategory(int id, String name) {
        String sqlUPDATE = "UPDATE Categories SET name = ? WHERE ID = ?";
        Connection connection = getConnection();

        try {
            statement = connection.prepareStatement(sqlUPDATE);
            statement.setString(1, name);
            statement.setInt(2, id);
            int result = statement.executeUpdate();
            if (result > 0) {
                System.out.println("Успешно обновлена");
                logger.info(String.format("Updated category '%s'", name));
            } else {
                System.out.println("Не удалось найти категорию");
                logger.info(String.format("Category '%s' not found", name));
            }


            stop(connection);
        } catch (SQLException e) {
            System.out.println("ОШИБКА при обновлении категории");
            logger.error(e);
        }
    }


    private void deleteCategoryNews(Connection connection, String name) {
        String sql_selectID = "SELECT ID FROM Categories WHERE name = ?";
        String sql_deleteNews = "DELETE FROM News WHERE CategoryID = ?";

        try {
            statement = connection.prepareStatement(sql_selectID);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int categoryID = resultSet.getInt(1);

            statement = connection.prepareStatement(sql_deleteNews);
            statement.setInt(1, categoryID);
            statement.executeUpdate();
            logger.info("Удалены новости категории " + name);
        } catch (SQLException e) {
            logger.error("Ошибка при удалении новостей из категории " + name);
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

            logger.info("getID category " + name);
            return categoryID;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
            throw new WrongValueException();
        }
    }
}
