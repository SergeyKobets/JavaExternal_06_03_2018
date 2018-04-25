package dao;

import bean.Category;
import org.apache.log4j.Logger;

import java.sql.Connection;

public abstract class AbstractDao {

    final static Logger logger = Logger.getLogger(AbstractDao.class);

    private ConnectionPool connectionPool = new ConnectionPool(5);

    public Connection getConnection() {
        logger.info("getConnection()");
        return connectionPool.getConnection();
    }

    public void stop(Connection connection) {
        logger.info("stopConnection()");
        connectionPool.stop(connection);
    }

}
