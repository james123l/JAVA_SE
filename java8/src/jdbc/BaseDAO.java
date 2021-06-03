package jdbc;

import jdbc.ConnectionUtil;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDAO {
    //DAO Data Access Object
    //定义一般CRUD方法的抽象类

    //适用于事务的通用增删改操作
    public static void commonIDU(Connection connection, String sql, Object... args)  {
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
    //查询特殊值的方法
    public static <T> T getValue(Connection connection, String sql, Object... args) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return  (T)resultSet.getObject(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionUtil.closeResource(null,preparedStatement,resultSet);

        }
        return null;
    }
}