package jdbc.preparedStatement;

import jdbc.util.ConnectionUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public class GeneralQueryPreparedSatement {
    //通用的查询方法，适用于不同表
    // classReflect是一个对象 classReflect本身也是一个类，用于对应sql的表,如果要返回customer的对象 则需要传入Customer.class
    public static <T> ArrayList<T> GeneralQueryPreparedSatement(Class<T> classReflect, String sql, Object... args){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<T> tArray = null;
        try {
            connection = ConnectionUtil.getConnection();
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
            ConnectionUtil.closeResource(connection,preparedStatement,resultSet);
        }
        return tArray;
    }
}
