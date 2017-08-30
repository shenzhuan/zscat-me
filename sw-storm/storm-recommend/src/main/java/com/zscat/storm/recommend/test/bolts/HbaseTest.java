package com.zscat.storm.recommend.test.bolts;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zscat.storm.recommend.test.hbase.HBaseTest;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseTest {

	private static HBaseConfiguration hbaseConfig = null;
	static {
		Configuration config = new Configuration();
		config.set("hbase.zookeeper.quorum", "10.2.10.61");
		config.set("hbase.zookeeper.property.clientPort", "2181");
		hbaseConfig = new HBaseConfiguration(config);
	}
	 /**
     * 创建一张表
     */
    public static void creatTable(String tableName, String[] familys) throws Exception {
        HBaseAdmin admin = new HBaseAdmin(hbaseConfig);
        if (admin.tableExists(tableName)) {
            System.out.println("table already exists!");
        } else {
            HTableDescriptor tableDesc = new HTableDescriptor(tableName);
            for(int i=0; i<familys.length; i++){
                tableDesc.addFamily(new HColumnDescriptor(familys[i]));
            }
            admin.createTable(tableDesc);
            System.out.println("create table " + tableName + " ok.");
        } 
    }
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		try {
			 String tablename = "scores";
	            String[] familys = {"grade", "course"};
	            HBaseTest.creatTable(tablename, familys);
	             
	            //add record zkb
//	            HBaseTest.addRecord(tablename,"zkb","grade","","5");
//	            HBaseTest.addRecord(tablename,"zkb","course","","90");
//	            HBaseTest.addRecord(tablename,"zkb","course","math","97");
//	            HBaseTest.addRecord(tablename,"zkb","course","art","87");
//	            //add record  baoniu
//	            HBaseTest.addRecord(tablename,"baoniu","grade","","4");
//	            HBaseTest.addRecord(tablename,"baoniu","course","math","89");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 查询一行数据
	public static void selectByRowKey(String tablename, String rowKey)
			throws IOException {
		HTable table = new HTable(hbaseConfig, tablename);
		Get g = new Get(Bytes.toBytes(rowKey));
		Result r = table.get(g);
		for (KeyValue kv : r.raw()) {
			System.out.println("getRow: " + new String(kv.getRow()));// zbk
			System.out.println("getFamily: " + new String(kv.getFamily()));// course
			System.out
					.println("getQualifier: " + new String(kv.getQualifier()));// art
			System.out.println("value: " + new String(kv.getValue()));// 87
		}
	}

	// 查询一行中的family或者qualifier
	public static void selectByRowKeyColumn(String tablename, String rowKey,
			String family, String qualifier) throws IOException {
		HTable table = new HTable(hbaseConfig, tablename);
		Get g = new Get(Bytes.toBytes(rowKey));
		// g.addFamily(family.getBytes());
		g.addColumn(family.getBytes(), qualifier.getBytes());
		Result r = table.get(g);
		for (KeyValue kv : r.raw()) {
			System.out
					.println("getQualifier: " + new String(kv.getQualifier()));
			System.out.println("value: " + new String(kv.getValue()));
		}
	}

	// 指定值过滤
	public static void selectByFilter(String tablename, List<String> arr)
			throws IOException {
		HTable table = new HTable(hbaseConfig, tablename);
		FilterList filterList = new FilterList();
		Scan s1 = new Scan();
		for (String v : arr) { // 各个条件之间是“与”的关系
			String[] s = v.split(",");
			filterList.addFilter(new SingleColumnValueFilter(Bytes
					.toBytes(s[0]), Bytes.toBytes(s[1]), CompareOp.EQUAL, Bytes
					.toBytes(s[2])));
			// 添加下面这一行后，则只返回指定的cell，同一行中的其他cell不返回
			// s1.addColumn(Bytes.toBytes(s[0]), Bytes.toBytes(s[1]));
		}
		s1.setFilter(filterList);
		ResultScanner ResultScannerFilterList = table.getScanner(s1);
		for (Result rr = ResultScannerFilterList.next(); rr != null; rr = ResultScannerFilterList
				.next()) {
			for (KeyValue kv : rr.list()) {
				System.out.print("row:" + new String(kv.getRow()) + "   ||  ");
				System.out.print("family : " + new String(kv.getFamily())
						+ "   ||   ");
				System.out.println("getQualifier : "
						+ new String(kv.getQualifier()));
				System.out.println("value : " + new String(kv.getValue()));
			}
		}
	}
}