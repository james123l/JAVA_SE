package jdbc;

import java.io.*;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

public class BlobOperation {
    //演示blob数据类型的mysql操作
    //增
    public static void blobInsert()  {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionUtil.getConnection();
            String sql = "insert into customers(name,email,birthday,photo) values(?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,"Jack");
            preparedStatement.setObject(2,"18888885@gmail.com");
            preparedStatement.setObject(3,"1950-12-27");
            //文件流
            //默认当前工程目录,即最大的根目录
            FileInputStream inputStream = new FileInputStream(new File("JDBC\\src\\BlobMaterial\\02.jpg"));
            preparedStatement.setObject(4,inputStream);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.closeResource(connection,preparedStatement);
        }
    }
    //改
    public static void bolbUpdate()  {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        FileInputStream inputStream = null;
        try {
            connection = ConnectionUtil.getConnection();
            String sql = "update customers set `name`=?, photo = ? where id = ?; ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,"Gibson");
            preparedStatement.setObject(3,2);
            inputStream = new FileInputStream(new File("JDBC\\src\\BlobMaterial\\04.jpg"));
            preparedStatement.setObject(2,inputStream);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(inputStream!= null) inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ConnectionUtil.closeResource(connection,preparedStatement);
        }
    }
    //查找并下载图片
    public static void bolbQuery() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            connection = ConnectionUtil.getConnection();
            String sql = "select id,name,email,birthday,photo from customers where id= ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,2);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                Date birthday = resultSet.getDate("birthday");
                //获取blob
                Blob photo = resultSet.getBlob("photo");
                inputStream = photo.getBinaryStream();
                fileOutputStream = new FileOutputStream("copy.jpg");
                byte[] buffer = new byte[1024];
                int length;
                while((length = inputStream.read(buffer))!= -1){
                    fileOutputStream.write(buffer,0,length);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(fileOutputStream!= null) fileOutputStream.close();
                if(inputStream!=null) inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ConnectionUtil.closeResource(connection,preparedStatement,resultSet);
        }

    }

}
