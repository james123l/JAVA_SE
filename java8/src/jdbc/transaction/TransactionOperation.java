package jdbc.transaction;

import jdbc.util.ConnectionUtil;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionOperation {
    //事务处理类
    //测试接口
    public static void testTreasaction() {
        Connection connection = null;
        try {
            connection = ConnectionUtil.getConnection();
            //设置不自动提交便于回滚
            connection.setAutoCommit(false);
            //operation1
            String sql1="update customers set `name` = 'Alex' where id = ?;";
            commonIDU(connection,sql1,1);
            //模拟网络异常
            //System.out.println(10/0);
            //operation2
            String sql2="update customers set `name` = 'James' where id = ?;";
            commonIDU(connection,sql2,2);
            System.out.println("事务操作成功");
            connection.commit();
        } catch (Exception e) {
            //回滚操作
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            //如果使用数据库连接池 则下面的close不是关闭连接，而是把连接还回到数据库连接池,需要设置自动提交为true
            /*
                        try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
             */
            ConnectionUtil.closeResource(connection);
        }

    }
    /*
    适用于事务的增删改查操作需要不关闭Connection
    在测试函数内部进行DML操作
     */
    //适用于事务的通用增删改操作
    public static void commonIDU(Connection connection,String sql,Object... args)  {
        PreparedStatement preparedStatement = null;
        try {
            /*
            //查看隔离级别
            int transactionIsolation = connection.getTransactionIsolation();
            //设置隔离级别
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
             */
            //预编译sql语句
            preparedStatement = connection.prepareStatement(sql);
            //填充占位符
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            //这一步只执行不提交
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.closeResource(null,preparedStatement);
        }
    }
    //通用的查询方法，适用于不同表
    // classReflect是一个对象 classReflect本身也是一个类，用于对应sql的表,如果要返回customer的对象 则需要传入Customer.class
    public static <T> List<T> GeneralQueryPreparedSatement(Connection connection, Class<T> classReflect, String sql, Object... args){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<T> tArray = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            //获取结果集元数据
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            //获取列数
            int columnCount = resultSetMetaData.getColumnCount();
            tArray = new ArrayList();
            while(resultSet.next()){
                T t = classReflect.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue =resultSet.getObject(i+1); //start from 1
                    /*
                    //获取每个列的列名
                    String columnName = resultSetMetaData.getColumnName(i + 1);
                     */
                    //获取列的别名，如果没有起别名则返回列名
                    String columnName = resultSetMetaData.getColumnLabel(i+1);
                    //通过反射给特定的成员赋值
                    //获取类的成员
                    Field field = classReflect.getDeclaredField(columnName);
                    //设置权限
                    field.setAccessible(true);
                    //赋值
                    field.set(t,columnValue);
                }
                tArray.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.closeResource(null,preparedStatement,resultSet);
        }
        return tArray;
    }


}
