package JDBC;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * @auther
 * @verson 1.0
 * 演示sql注入
 * 管理员名字1' or
 * 管理员密码 or '1'='1
 */
public class Statement_ {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Scanner scanner = new Scanner(System.in);
        //让用户输入账号密码
        System.out.println("请输入管理员的名字: ");
        //next():当接收到 空格  或者 ' 就表示结束了
        String admin_name = scanner.nextLine();//说明，如果希望看到SQL注入，这里需要用nextLine
        System.out.println("请输入管理员密码: ");
        String admin_pwd = scanner.nextLine();
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        //获取相关值
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();

        String sql = "select name , pwd from admin where name = '"+admin_name+"' and pwd = '"+admin_pwd+"'";
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next())
        {
            System.out.println("登陆成功");
        }
        else {
            System.out.println("登录失败");
        }
        //关闭连接
        resultSet.close();
        statement.close();
        connection.close();

    }
}
