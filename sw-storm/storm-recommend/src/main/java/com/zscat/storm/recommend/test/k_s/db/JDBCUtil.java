package com.zscat.storm.recommend.test.k_s.db;
 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


 
/**
 * 
 * @author 罗盛力 模拟连接池（封装连接池）
 */
public final class JDBCUtil {
    private static DataSourcePool dsp = null;

    
   // public static final String INSERT_LOG = "insert into log_info(topdomain,usetime,time) values(?,?,?)";
    public JDBCUtil() throws Exception {
         
    }
 
    /**
     * 获得连接
     * @return
     * @throws Exception
     */
    public static Connection getConnection( ) throws Exception {
        if (dsp == null) {
            synchronized (DataSourcePool.class) {
                if (dsp == null) {
                    dsp = new DataSourcePool();
                }
            }
        }
        return dsp.getConnection();
    }
 
    // 关闭连接
    public static void freeConnection(ResultSet rs, Statement ps, Connection con)
            throws SQLException {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (con != null) {
                    dsp.freeConnection(con);
                }
            }
        }
    }
    // 关闭连接
        public static void freeConnection(Connection con)
                throws SQLException {
                    if (con != null) {
                        dsp.freeConnection(con);
                    }
        }
        /** 
         * @param
         * @throws Exception 
         */  
         public static void update(String sql,Object... params) throws Exception {  
              Connection connection = getConnection();  
              //更新数据  
             // queryRunner.update(connection,sql, params);
              DataSourcePool.freeConnection(connection);
              connection.close();  
         }  
          
         public static List<String> executeQuerySql(String sql) throws Exception {  
               
              List<String> result = new ArrayList<String>();  

              return result;  
         }  
}