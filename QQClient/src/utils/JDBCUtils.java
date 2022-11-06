package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @auther
 * @verson 1.0
 * 这时一个工具类，完成mysql的连接和关闭资源
 */
public class JDBCUtils {
    //定义相关属性(4个)，因为只需要一份，因此我们做成 static
    private static String user;//用户名
    private static String password;//密码
    private static String url;//url
    private static String driver;//驱动名

    //在static代码块初始化
    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src\\mysql.properties"));
            //读取相关属性值
             user = properties.getProperty("user");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
            driver = properties.getProperty("driver");

        } catch (IOException e) {
            //在实际开发中，我们可以这样处理
            throw new RuntimeException(e);
        }

    }
    //连接数据库，返回Connection
    public static Connection getConnection()
    {
        try {
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //关闭相关资源
    /*
        1.ResultSet 结果集
        2.statement 或者 PreparedStatement
        3.Connection
        4.如果需要关闭对象，就传入对象，否则传入null
     */
    public static void close(ResultSet set, Statement statement,Connection connection)
    {
        try {
            if (set!=null)
            {
                set.close();
            }
            if (statement!=null)
            {
                statement.close();
            }
            if (connection!=null)
            {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
