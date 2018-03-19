package csdn.database;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * @Description 开启数据库连接
 */
public class JDBCDatabase {
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    // 加载配置文件
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("jdbc");

    static {
        URL = resourceBundle.getString("jdbc.url");
        USERNAME = resourceBundle.getString("jdbc.username");
        PASSWORD = resourceBundle.getString("jdbc.password");
        String driverClassName = resourceBundle.getString("jdbc.driver");

        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    // 关闭数据库链接
    public static void closeConnection(ResultSet rs, Statement stat, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stat != null) {
                stat.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
