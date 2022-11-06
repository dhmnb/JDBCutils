package utils;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

/**
 * @auther
 * @verson 1.0
 */
public class Utils_User {
    @Test
    public void testDML(){//insert  update  delete
        //1.得到链接
        Connection connection = null;
        //2.组织sql语句
        String sql = "update actor set name = ? where id = ?";
        PreparedStatement preparedStatement = null;
        //3.创建PreparedStatement 对象
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            //给占位符赋值
            preparedStatement.setString(1,"zhouxingchi");
            preparedStatement.setInt(2,1);
            //执行
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭资源
            JDBCUtils.close(null,preparedStatement,connection);
        }
    }
}
