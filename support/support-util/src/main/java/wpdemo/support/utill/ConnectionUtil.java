package wpdemo.support.utill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Kovacs Maria
 */
public class ConnectionUtil {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    private static final String CONN_STRING = "jdbc:mysql://localhost/wpdemo?useSSL=false";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
    }
}
