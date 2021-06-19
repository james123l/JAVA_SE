package jdbc.preparedStatement;

import jdbc.util.ConnectionUtil;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//重点是通用的增删改操作


public class PreparedStatementUpdateDeleteInsert {
    //不通用的增删改操作
    /*
    本类的测试接口
    提供连接，关闭资源
     */
    public static void setupResource() throws Exception {
        ///提供连接，关闭资源
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement= null;
        //不通用的增删改操作
        operationSQL(connection, preparedStatement);
        //关闭资源
        ConnectionUtil.closeResource(connection,preparedStatement);
    }
    public static void operationSQL(Connection connection, PreparedStatement preparedStatement){
        //调试调用的增删改函数
        testInsert(connection, preparedStatement);
        testUpdate(connection, preparedStatement);
    }
    //增
    public static void testInsert(Connection connection, PreparedStatement preparedStatement)  {
        try {
            String sql = "insert into customers(name,email,birthday) values(?,?,?)"; //占位符
            preparedStatement = connection.prepareStatement(sql);

            //填充占位符
            preparedStatement.setString(1,"James");
            preparedStatement.setString(2,"123456abc@qq.com");
            //日期设置
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse("1986-1-5");
            preparedStatement.setDate(3, new java.sql.Date(date.getTime()));
            //执行sql
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    //改
    public static void testUpdate(Connection connection, PreparedStatement preparedStatement) {
        try {
            //获取PreparedStatement实例
            String sql = "update customers set name = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            //填充占位符
            preparedStatement.setObject(1,"Mozart");
            preparedStatement.setObject(2,18);
            //执行
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    //测试通用的增删改操作 input delete update
    public static void testCommonIDU(){
        String sql1 = "delete from customer where id = ?";
        commonIDU(sql1,3);
        String sql2 = "update `order` set order_name = ? where order_id = ?";   //因为order是关键字所以需要``进行包裹， 键盘1旁边
        commonIDU(sql2,"dd","2");
    }
    //通用的增删改操作
    public static void commonIDU(String sql,Object... args)  {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //获取链接
            connection = ConnectionUtil.getConnection();
            //预编译sql语句
            preparedStatement = connection.prepareStatement(sql);
            //填充占位符
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.closeResource(connection,preparedStatement);
        }

    }
}
