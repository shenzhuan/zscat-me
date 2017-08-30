package com.zscat.storm.recommend.test.redis_storm_mysql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StoreDatabase {
    public static Connection connection;
    public static Statement stmt;
    static {
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbUrl = "jdbc:mysql://localhost/blog";
        String user = "root";
        String password = "root";
        try {
            Class.forName(dbDriver);

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(dbUrl, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int insertRow(String word){
        int effectRows=0;
        String sql=String.format("insert into words(words)values('%s')", word);
        try{
            stmt=connection.createStatement();
            effectRows=stmt.executeUpdate(sql);
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.err.println("数据插入失败");
        }
        return effectRows;
    }
}