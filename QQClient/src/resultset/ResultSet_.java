package resultset;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @auther
 * @verson 1.0
 * 演示select 语句返回 ResultSet, 并取出结果
 */
public class ResultSet_ {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        //通过Properties对象获取配置文件信息
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        //获取相关值
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        //1. 注册驱动
        Class.forName(driver);
        //2. 得到链接
        Connection connection = DriverManager.getConnection(url, user, password);
        //3.得到Statement
        Statement statement = connection.createStatement();
        //4.组织sql语句
        String sql = "select id,name,sex,borndate from actor";
        //执行给定的Sql语句，该语句返回单个 ResultSet对象
        ResultSet resultSet = statement.executeQuery(sql);
        //5. 使用while取出数据
        while (resultSet.next())//next方法让光标向后移动，如果后面没有了就返回false
        {
            int id = resultSet.getInt(1);//获取该行的第一列
            String name = resultSet.getString(2);//获取该行的第二列
            String sex = resultSet.getString(3);//获取该行的第三列
            Date date = resultSet.getDate(4);//获取该行的第四列
            System.out.println(id+"\t"+name+"\t"+sex+"\t"+date);
        }
        //6. 关闭连接
        resultSet.close();
        statement.close();
        connection.close();

    }
}
