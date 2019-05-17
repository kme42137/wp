package wpdemo.support.utill;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Kovacs Maria
 */
public class ConnectionUtil {
            
    protected Connection con;

    public ConnectionUtil() {
        try {
            Class.forName("com.mysql.jdbc.Driver");            
            con = DriverManager.getConnection(Const.MYSQL_CONN_STRING, Const.MYSQL_USERNAME, Const.MYSQL_PASSWORD);
        } catch (Exception e) {
            System.exit(100);
        }
    }
    
    
    public static String serchString(String str){
        if(str.isEmpty()){
            return str;
        }
        String[] splStr = str.trim().split("\\s+");
        String schString="";        
        for (String s : splStr) {
            schString+=s+" <"+s+"* ";            
        }
        return schString;
    }
}
