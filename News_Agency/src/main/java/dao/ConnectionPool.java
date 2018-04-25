package dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionPool {
    final static Logger logger = Logger.getLogger(ConnectionPool.class);

    private final String URl = "jdbc:mysql://localhost:3306/NEWS_AGENCY";
    private ArrayList<Connection> available = new ArrayList<>();
    private ArrayList<Connection> used = new ArrayList<>();

    public ConnectionPool(int quantity) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            logger.info("Connected, URL = " + URl);
        } catch (ClassNotFoundException e) {
            logger.error(e);
        }

        for (int i = 0; i < quantity; i++) {
            available.add(initConnection());
        }
    }


    public Connection getConnection() {
        Connection connection = null;
        if (available.isEmpty()) {
            connection = initConnection();
        } else {
            connection = available.get(0);
            available.remove(0);
        }

        used.add(connection);

        return connection;
    }

    public void stop(Connection connection) {
        if (connection != null) {
            if (used.remove(connection)) {
                available.add(connection);
            } else {
                throw new RuntimeException();
            }
        }
    }

    private Connection initConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URl, "root", "8998");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
