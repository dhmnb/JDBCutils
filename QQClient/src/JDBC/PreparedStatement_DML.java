package JDBC;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

/**
 * @auther
 * @verson 1.0
 */
public class PreparedStatement_DML {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入删除管理员名字:");
        String admin_name = sc.nextLine();
//        System.out.println("请输入管理员密码:");
//        String admin_password = sc.nextLine();
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        //添加用户
        //String sql = "insert into admin values(?,?)";
        //修改用户
        //String sql = "update admin set pwd = ? where name = ?";
        //删除用户
        String sql = "delete from admin where name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //给？赋值
        //preparedStatement.setString(1,admin_password);
        preparedStatement.setString(1,admin_name);
        //执行 dml 语句使用 executeUpdate()
        int rows = preparedStatement.executeUpdate();
        System.out.println(rows>0?"添加成功":"添加失败");
        //关闭连接
        preparedStatement.close();
        connection.close();


    }
}
