package jdbc;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class TestMainEntry {
    public static void main(String[] args) {
    }
    public static void testTransaction(){
        //事务测试
        TransactionOperation.testTreasaction();
    }
    public static void testBlob(){
        BlobOperation.bolbQuery();
    }
    public static void testInsertQuery() {
        //测试增删改类
        try {
            PreparedStatementUpdateDeleteInsert.setupResource();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //和特定表customer的通用查询方法
        ArrayList<Customer> arrayList = SpecificTableQuery.queryCustomerGeneral("select* from customers where id = ?", 1);
        Iterator<Customer> customerIterator = arrayList.iterator();
        if(customerIterator.hasNext()) System.out.println(customerIterator.next());
        //测试适合所有表的通用查询
        ArrayList<Customer> customers = GeneralQueryPreparedSatement.GeneralQueryPreparedSatement(Customer.class, "select* from customers where id>?", 0);
        customers.forEach(System.out::println);
    }
}
