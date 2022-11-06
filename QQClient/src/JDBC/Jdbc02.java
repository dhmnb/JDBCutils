package JDBC;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @auther
 * @verson 1.0
 */
public class Jdbc02 {

    @Test
    public void test() throws IOException, ClassNotFoundException, SQLException
    {
        Properties properties = new Properties();

        properties.load(new FileInputStream("src\\mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, user, password);
        //String sql = "create table news(id int,content char(32))";
        //String sql = "insert into news values(1,'xinguan')";
        //String sql = "update news set content = 'wandanle'where id = 1";
        String sql = "delete from news where id = 1";
        Statement statement = connection.createStatement();
        int row = statement.executeUpdate(sql);
        System.out.println(row>0? "成功":"失败");
        statement.close();
        connection.close();


    }
}
