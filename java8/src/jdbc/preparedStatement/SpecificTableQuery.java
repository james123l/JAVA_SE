package jdbc.preparedStatement;

import jdbc.DAO.regularDAO.Customer;
import jdbc.util.ConnectionUtil;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

public class SpecificTableQuery {
    //query customer table with certain field
    //标准查询的逻辑
    public static void queryCostomerTable() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionUtil.getConnection();
            String sql = "select id, name,emial,birthday from customers where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            //execute query return set
            resultSet = preparedStatement.executeQuery();
            //result set operation
            //result set的next方法相似于hasnext 可判断移动指针 返回boolean
            if(resultSet.next()){
                //sql的第一个字段
                int id = resultSet.getInt(1);
                //int id = resultSet.getInt("id");//也可以通过lable
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date birthday =(Date) resultSet.getObject(4);
                //构造对象
                Customer customer = new Customer(id,name,email, birthday);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.closeResource(connection,preparedStatement,resultSet);
        }
    }
    //query customer table with uncertain field
    //本例：sql数据库的字段名和java类的对象的成员变量名一致
    /*
    如果数据库中的列名与java类的成员不同名 则需要在sql语句里面起别名 例如id （java）和customerId（数据库）
    select customerId id,name from customers where id =?
     */
    public static ArrayList queryCustomerGeneral(String sql,Object... args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Customer> customers = null;
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
            customers = new ArrayList();
            while(resultSet.next()){
                Customer customer = new Customer();
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
                    Field field = Customer.class.getDeclaredField(columnName);
                    //设置权限
                    field.setAccessible(true);
                    //赋值
                    field.set(customer,columnValue);
                }
                customers.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.closeResource(connection,preparedStatement,resultSet);
        }
        return customers;


    }
}
