package jdbc.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
/*
获取数据库连接的工具类
 */

public class ConnectionUtil {
    //获取数据库连接
    public static Connection getConnection() throws Exception{
        InputStream inputStream =ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc\\infoConnection.properties");
        Properties properties = new  Properties();
        properties.load(inputStream);
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");

        //加载驱动
        Class.forName(driverClass);
        //获取链接
        Connection connection = DriverManager.getConnection(url,user,password);
        System.out.println(connection);
        return connection;
    }
    //资源关闭
    public static  void closeResource(Connection connection){
        try {
            if(connection!= null) connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void closeResource(Connection connection, Statement statement){
        try {
            if(statement!= null) statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if(connection!= null) connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void closeResource(Connection connection, Statement statement, ResultSet resultSet){
        try {
            if(resultSet!= null) resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if(statement!= null) statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if(connection!= null) connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
