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
 */
public class PrepareStatement01 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
//        Scanner sc =new Scanner(System.in);
//        System.out.print("请输入修改后的管理员名字: ");
//        String admin_name = sc.nextLine();
//        System.out.print("请输入要修改名字管理员名字: ");
//        String admin_pwd = sc.nextLine();
        //创建properties 对象
        Properties properties = new Properties();
        //加载properties资源
        properties.load(new FileInputStream("src\\mysql.properties"));
        //得到相关数据
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);


        //String sql = "create table admin(name char(32),pwd char(32))";
        //String sql = "insert into admin values(?,?)";
        //String sql = "update admin set name = ? where name = ?";
        String sql = "select * from admin";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //preparedStatement.setString(1,admin_name);
        //preparedStatement.setString(2,admin_pwd);
        ResultSet resultSet = preparedStatement.executeQuery();
       while (resultSet.next())
       {
           String name = resultSet.getString(1);//获取改行第一列
           String pwd = resultSet.getString(2);//或去改行第二列
           System.out.println(name+"\t"+pwd);
       }

        preparedStatement.close();
        connection.close();
        resultSet.close();
    }
}
