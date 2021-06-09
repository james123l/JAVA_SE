package jdbc.DAO.regularDAO;

import java.sql.Date;
public class Customer {
    //本类为sql内部的customers表对应的类
    public int id ;
    public String name ;
    public String email;
    public Date birthday;

    //constructor
    public Customer(){}
    public Customer(int id,String name,String email,Date date){
    this.id = id; this.email=email; this.name = name; this.birthday = date;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
