package jdbc.DAO.regularDAO;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class CustomersDAOImplements extends BaseDAO implements CustomersDAO {
    //使用BaseDAO的方法进行操作

    @Override
    public void insert(Connection connection, Customer customer) {
        String sql = "insert into customers(name,email,birthday) values(?,?,?)";
        commonIDU(connection,sql,customer.name,customer.email,customer.birthday);
    }

    @Override
    public void deleteById(Connection connection, int id) {
        String sql = "delete from customers where id = ?";
        commonIDU(connection,sql,id);
    }

    @Override
    public void updateById(Connection connection, Customer customer) {
        String sql = "update customers set name=?,email=?,birthday=? where id =?";
        commonIDU(connection,sql,customer.name,customer.email,customer.birthday,customer.id);
    }

    @Override
    public Customer getCustomerById(Connection connection, int id) {
        String sql = "select id ,name , email , birthday from customers where id = ?";
        List<Customer> customers = GeneralQueryPreparedSatement(connection, Customer.class, sql, id);
        //结果集只有一个
        if(!customers.isEmpty()) return customers.get(0);
        else return null;
    }

    @Override
    public List<Customer> getAllCustomer(Connection connection) {
        String sql = "select id ,name , email , birthday from customers";
        List<Customer> customers = GeneralQueryPreparedSatement(connection, Customer.class, sql);
        return null;
    }

    @Override
    public Date getMaxBirthday(Connection connection) {
        String sql = "select max(birthday) from customers";
        return getValue(connection,sql);
    }
}
