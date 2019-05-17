package wpdemo.support.utill;

/**
 * @author Kovacs Maria
 */
public class Const {

    public static final String SAVE_DIR = "D:\\Java_probalkozasok\\szemet\\wp\\mimages";
    public static final String MYSQL_USERNAME = "admin";
    public static final String MYSQL_PASSWORD = "admin";
    public static final String MYSQL_CONN_STRING = "jdbc:mysql://localhost/wpdemo?useSSL=false";
    public static final String DERBY_USERNAME = "wpadmin";
    public static final String DERBY_PASSWORD = "wpadmin";
    public static final String DERBY_CONN_STRING = "jdbc:derby://localhost:1527/wpdemo";

    private Const() {
        throw new AssertionError();
    }

}
