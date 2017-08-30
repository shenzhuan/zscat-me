package com.zscat.storm.recommend.test.k_s.db;
 
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
 
public class DataSourcePool {
 
    static int connectionCurrLink = 0;
    static Map<String, String> map = null;
    private static LinkedList<Connection> datasourcePool = new LinkedList<Connection>();
    // 通过静态代码块注册数据库驱动，保证注册只执行一次
    static {
        map = new HashMap<String, String>();
        Properties p = new Properties();
        try {
            p.loadFromXML(new FileInputStream("src\\ds.xml"));
            Enumeration<Object> dataSourceSet = p.keys();
            while (dataSourceSet.hasMoreElements()) {
                String key = (String) dataSourceSet.nextElement();
                map.put(key, p.getProperty(key));
            }
            Class.forName(map.get("conectionDriver"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    public DataSourcePool() throws Exception {
        createConnection(0);
        // 通过构造函数启动定时器以达到定时释放空闲连接目的
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    // 得到空闲连接，datasourcePool里面有几个对象就表示有几个空闲连接
                    int leisureLink = DataSourcePool.datasourcePool.size();
                    System.out.println(leisureLink);
                    // 最小连接数
                    int connectionMinLink = Integer.parseInt(DataSourcePool.map
                            .get("connectionMinLink"));
                    // 当空闲连接大于DataSourcePool设置的最小连接数时则关闭
                    if (leisureLink > connectionMinLink) {
                        for (int i = 0; i < leisureLink - connectionMinLink; i++) {
                            DataSourcePool.closeConnection(DataSourcePool.getConnection());
                            connectionCurrLink--;
                        }
                    } else {
                        System.out.println("保持最小连接,将继续保持连接池");
                    }
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("设置了无效的最小连接数");
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, Long.parseLong(map.get("connectionTimer")));
 
    }
 
    // 创建连接
    private static void createConnection(int type) throws Exception {
        try {
            int link = 0;
            switch (type) {
            case 0:
                link = Integer.parseInt(map.get("connectionMinLink"));
                break;
            case 1:
                //如果当前连接+增长连接大于设定的最大连接数时，将使用最大连接数-当前连接的数量。以保持平衡
                link = Integer.parseInt(map.get("connectionIncreaseLink"));
                int maxLink = Integer.parseInt(map.get("conectionMaxLink"));
                if (link + connectionCurrLink > maxLink) {
                    link = maxLink - connectionCurrLink;
                }
                break;
            }
            for (int i = 0; i < link; i++) {
                datasourcePool.addLast(DriverManager.getConnection(map.get("connectionUrl"),
                        map.get("connectionName"), map.get("connectionPassword")));
                connectionCurrLink++;
            }
        } catch (NumberFormatException n) {
            throw new NumberFormatException("配置连接参数有误");
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("超过最大连接数 ,无法创建更多连接");
        }
    }
 
    // 获得连接
    public static Connection getConnection() throws Exception {
        // 取连接加锁，防止并发取的同样的连接
        synchronized (datasourcePool) {
            if (datasourcePool.size() > 0) {
                return datasourcePool.removeFirst();
            } else if (connectionCurrLink < Integer.parseInt(map.get("conectionMaxLink"))) {
                createConnection(1);
                return datasourcePool.removeFirst();
            }
        }
        return null;
    }
 
    /**
     * 关闭连接
     * 
     * @param con
     * @throws SQLException
     */
    public static void closeConnection(Connection con) throws SQLException {
        con.close();
    }
 
    // 释放连接
    public static void freeConnection(Connection con) {
        datasourcePool.addLast(con);
    }
 
}