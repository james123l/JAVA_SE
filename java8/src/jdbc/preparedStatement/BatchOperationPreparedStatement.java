package jdbc.preparedStatement;

import jdbc.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BatchOperationPreparedStatement {
    //批量操作 通过减少io次数来提高效率
    /*
    mysql默认不支持批量操作
    ?rewriteBatchedStatements=true 写在配置文件url后面
     */
    //批量插入
    public static void addByBatch()  {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionUtil.getConnection();
            //禁用自动提交，等待数据传输完毕一起提交，减少io次数
            connection.setAutoCommit(false);
            String sql = "insert into orders (id) values(?)";
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < 20000; i++) {
                preparedStatement.setInt(1,i+1);
                //积累 sql语句
                preparedStatement.addBatch();
                if(i%500 == 0){
                    //执行
                    preparedStatement.execute();
                    //清空
                    preparedStatement.clearBatch();
                }
            }
            //提交
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.closeResource(connection,preparedStatement);
        }

    }
}
