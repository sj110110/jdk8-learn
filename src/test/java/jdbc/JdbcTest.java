package test.java.jdbc;

import java.sql.*;

/**
 * @author sj
 * @Title JdbcTest
 * @description 数据库连接JDBC
 * @date 2021/5/31 18:47
 */
public class JdbcTest {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/micro_operate?characterEncoding=utf-8&useSSL=false&serverTimezone=Hongkong","root", "");
            String sql = "select * from areas where code = ? ";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, 11);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString("code")+","+resultSet.getString("area"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
          /*
                用于加载com.mysql.cj.jdbc.Driver类，并获取该类的class对象；但是在加载某个类时是需要先执行该类中的静态代码块的：
                static {
                    try {
                        java.sql.DriverManager.registerDriver(new Driver());
                    } catch (SQLException E) {
                        throw new RuntimeException("Can't register driver!");
                    }
                }
                这一段静态代码块是将Driver类注册到DriverManager管理器中，这样接下来就可以通过DriverManager获取数据库连接connection了
             */
//            ClassLoader classLoader = DriverManager.class.getClassLoader();
//            System.out.println(classLoader);//null 说明是启动类加载器BootStrapClassLoader