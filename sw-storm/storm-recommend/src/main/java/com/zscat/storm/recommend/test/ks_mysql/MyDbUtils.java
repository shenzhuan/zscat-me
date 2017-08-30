package com.zscat.storm.recommend.test.ks_mysql;
  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.util.ArrayList;  
import java.util.Date;  
import java.util.List;  
  

  
public class MyDbUtils {  
      
     private static String className = "com.mysql.jdbc.Driver";  
     private static String url = "jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=utf-8";  
     private static String user = "root";  
     private static String password = "root";  

  
     public static final String INSERT_LOG = "insert into log_info(topdomain,usetime,time) values(?,?,?)";  
  
     static{  
          try {  
               Class.forName(className);  
          } catch (ClassNotFoundException e) {  
               e.printStackTrace();  
          }  
     }  
     public static void main(String[] args) throws Exception {  
          String topdomain = "taobao.com";  
          String usetime = "100";  
          String currentTime="1444218216106";  
          MyDbUtils.update(MyDbUtils.INSERT_LOG, topdomain,usetime,new Date());  
          update(INSERT_LOG,topdomain,usetime,new Date());  
     }  
     /** 
     * @param
     * @throws SQLException 
     */  
     public static void update(String sql,Object... params) throws SQLException {  
          Connection connection = getConnection();  
          //更新数据  

          connection.close();  
     }  
      
     public static List<String> executeQuerySql(String sql) {  
           
          List<String> result = new ArrayList<String>();  

          return result;  
     }  
     /** 
     * @throws SQLException 
     * 
     */  
     public static Connection getConnection() throws SQLException {  
          //获取mysql连接  
          return DriverManager.getConnection(url, user, password);  
     }  
}  