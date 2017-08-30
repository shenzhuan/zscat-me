package com.zscat.storm.recommend.test.hbase;
 
/*
 * 创建一个students表,并进行相关操作
 */
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
public class TestHbase {  
	  
    public static Configuration configuration = null;  
    static {  
        configuration = HBaseConfiguration.create();  
    //    configuration.set("hbase.zookeeper.property.clientPort", "2181");  
        configuration.set("hbase.zookeeper.quorum", "10.2.10.61");  
        configuration.set("hbase.master", "10.2.10.61:600000");  
    }  
  
    public static void main(String[] args) {  
        //TestHbase test = new TestHbase(); 
        try{
        	createTable("wms");  
        // insertData("wms");  
//         QueryAll("wms");  
        // QueryByCondition1("wms");  
        // QueryByCondition2("wms");  
        //QueryByCondition3("wms");  
        //deleteRow("wms","abcdef");  
//        deleteByCondition("wms","abcdef");  
        }catch(Exception e){
        	e.printStackTrace();
        }
        
    }  
  
    /** 
     * 创建表 
     * @param tableName 
     */  
    public static void createTable(String tableName) {  
        System.out.println("start create table ......");  
        try {  
        	System.out.println("1try... ......");
            HBaseAdmin hBaseAdmin = new HBaseAdmin(configuration); 
            System.out.println("2try... ......");
            if (hBaseAdmin.tableExists(tableName)) {// 如果存在要创建的表，那么先删除，再创建  
                hBaseAdmin.disableTable(tableName);  
                hBaseAdmin.deleteTable(tableName);  
                System.out.println(tableName + " is exist,detele....");  
            }  
            HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);  
            tableDescriptor.addFamily(new HColumnDescriptor("column1"));  
            tableDescriptor.addFamily(new HColumnDescriptor("column2"));  
            tableDescriptor.addFamily(new HColumnDescriptor("column3"));  
            hBaseAdmin.createTable(tableDescriptor);  
        } catch (MasterNotRunningException e) {  
            e.printStackTrace();  
        } catch (ZooKeeperConnectionException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        System.out.println("end create table ......");  
    } 
}