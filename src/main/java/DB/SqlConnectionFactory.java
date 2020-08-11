package DB;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnectionFactory {

    private static final Logger logger = LogManager.getLogger();

    public Connection getConnection(String url, String login, String password) throws ClassNotFoundException {
        try {

            return DriverManager.getConnection(url, login, password);

        } catch (SQLException ex) {
            logger.error("driver didn't find", ex.getMessage());
        }
        return null;
    }
}

