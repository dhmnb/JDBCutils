package JDBC;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @auther
 * @verson 1.0
 * 这是第一个jdbc
 */
public class Jdbc01 {
    public static void main(String[] args) throws SQLException {

        //前置工作：在项目下创建一个文件夹libs
        //将mysql.jar拷贝到该目录下，点击 add to project..加入到项目中
        //1. 注册驱动
        Driver driver = new Driver();//创建Driver对象




        //2. 得到链接
        //(1)jdbc:mysql://固定规定好的  表示协议 通过jdbc的方式连接mysql
        //(2)localhost 主机，也可以是ip地址
        //(3)3306 表示mysql监听的端口
        //(4)dhm01 连接到mysql dbms 的那个数据库
        //(5)mysql连接本质就是前面学过的socket连接
        String url = "jdbc:mysql://localhost:3306/dhm01";
        //将用户名和密码放入到Properties对象中
        Properties properties = new Properties();
        //说明  user 和 password 是规定好的，后面的值根据实际情况写
        properties.setProperty("user","root");//指定用户
        properties.setProperty("password","13849136270");//指定密码
        Connection connect = driver.connect(url, properties);

        //3. 执行sql语句

        //String sql = "insert into actor values(null,'刘德华','boy','1970-11-11','110')";
        String sql = "delete from actor where id = 1";
        //Statement 用于执行静态 sql语句并返回其生成结果的对象
        Statement statement = connect.createStatement();

        int rows = statement.executeUpdate(sql);//如果是dml语句，返回的就是影响的行数
        System.out.println(rows>0? "成功":"失败");
        //4. 关闭连接
        statement.close();
        connect.close();
    }
}
