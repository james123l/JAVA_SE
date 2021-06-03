package jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionTest {
    public static void main(String[] args) throws Exception {
        //testConnection01();
        testConnection05();
    }
    /*数据库连接方法测试
    需要把connector 导入到当前文件 并作为lib,很多接口都在这里
     */

    //方法1
    public static void testConnection01() throws Exception {
        Driver driver =new com.mysql.jdbc.Driver();    //mysql connector的接口
        /*
        jdbc:mysql://localhost:3306/数据库名
        jdbc:mysql协议
        localhost ip地址
        3306默认端口
         */
        String url ="jdbc:mysql://localhost:3306/classicmodels";
        //把用户名密码封装在properties
        Properties info =new Properties();
        info.setProperty("user","root");
        info.setProperty("password","950606");
        //连接数据库
        Connection conn = driver.connect(url,info);
        System.out.println(conn);
    }

    //方法二
    //对方法一的迭代，因为driver是第三方的api,程序没有更好一致性
    public static void testConnection02() throws Exception {
        //获取Driver实现类对象
        Class class_driver = Class.forName("com.mysql.jdbc.Driver");
        Driver driver =(Driver) class_driver.getDeclaredConstructor().newInstance();

        //提供要连接的数据库
        String url ="jdbc:mysql://localhost:3306/classicmodels";
        Properties info =new Properties();
        info.setProperty("user","root");
        info.setProperty("password","950606");
        Connection conn = driver.connect(url,info);
        System.out.println(conn);
    }

    //方式三
    //使用DriverManager来代替Driver
    public static void testConnection03() throws Exception {
        //提供基本信息
        String url ="jdbc:mysql://localhost:3306/classicmodels";
        String user = "root";
        String password = "950606";

        //获取driver实现类对象
        Class class_driver = Class.forName("com.mysql.jdbc.Driver");
        Driver driver =(Driver) class_driver.getDeclaredConstructor().newInstance();
        //注册驱动
        DriverManager.registerDriver(driver);
        Connection connection = DriverManager.getConnection(url,user,password);
        System.out.println(connection);
    }

    //方式4 是对3的优化，只加载 不注册
    //Driver 类内部有一个静态代码块，在加载的时候就存在了driver的对象
    public static void testConnection04() throws Exception {
        //提供基本信息
        String url ="jdbc:mysql://localhost:3306/classicmodels";
        String user = "root";
        String password = "950606";

        //获取driver实现类对象
        Class.forName("com.mysql.jdbc.Driver");
        //如果是mysql数据库，这步可以省略，在connector包内部META-INF service java.sql.Driver已经储存

        //获取连接
        Connection connection = DriverManager.getConnection(url,user,password);
        System.out.println(connection);
    }

    //方式5 通过加载配置文件中进行信息获取 需要掌握
    /*优点：
    实现了数据和代码的分类，实现了解耦
    如果需要修改配置文件，可以避免重复的打包和编译
     */
    public static void testConnection05() throws Exception {
        //需要现在当前src文件下创建一个配置文件
        /*
        #注意此处的=左右两端无空格 否则有歧义.
        url=jdbc:mysql://localhost:3306/classicmodels
        user=root
        password=950606
        driverClass=com.jdbc.mysql.Driver
         */
        //读取配置文件信息 调用本类，因为本类加载
        InputStream inputStream = ConnectionTest.class.getClassLoader().getResourceAsStream("infoConnection.properties");
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
    }
}
