package JDBC;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * @auther
 * @verson 1.0
 */
public class PreparedStatement_ {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入管理员名字: ");
        String admin_name = sc.nextLine();
        System.out.println("请输入管理员密码: ");
        String admin_password = sc.nextLine();


        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        //sql语句 ？就相当于占位符
        String sql = "select name,pwd from admin where name =? and pwd = ?";
        //preparedStatement对象实现了PreparedStatement接口的实现类的一个对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //给?赋值
        preparedStatement.setString(1,admin_name);
        preparedStatement.setString(2,admin_password);
        //执行 select语句要使用   executeQuery()   方法
        //如果执行的是 dml(update,insert,delete)使用executeUpdate()
        //这里执行  executeQuery(), 不要再写成  executeQuery(sql)
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
        {
            System.out.println("登录成功");

        }
        else {
            System.out.println("登陆失败");
        }
        //关闭连接
        connection.close();
        preparedStatement.close();
        resultSet.close();

    }


}
