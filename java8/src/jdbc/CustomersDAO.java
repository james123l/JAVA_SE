package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public interface CustomersDAO {
    //customer表的常用操作
    //插入
    void insert(Connection connection,Customer customer);
    //根据id删除
    void deleteById(Connection connection,int id);
    //修改
    void updateById(Connection connection,Customer customer);
    //根据id查询
    Customer getCustomerById(Connection connection,int id);
    //查询所有记录
    List<Customer> getAllCustomer(Connection connection);
    //获取最大生日
    Date getMaxBirthday(Connection connection);
}
