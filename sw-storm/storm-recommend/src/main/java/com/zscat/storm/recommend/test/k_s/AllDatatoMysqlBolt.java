  
  package com.zscat.storm.recommend.test.k_s;
import com.zscat.storm.recommend.test.k_s.db.JDBCUtil;
import com.zscat.storm.recommend.test.redis_storm_mysql.RedisUtils;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

import java.util.Date;
import java.util.Map;
import java.util.Random;


  
/** 
 * Created by IntelliJ IDEA. User: comaple.zhang Date: 12-8-28 Time: 下午2:11 To 
 * change this template use File | Settings | File Templates. 
 */  
@SuppressWarnings("serial")  
public class AllDatatoMysqlBolt extends BaseRichBolt {
  
    private OutputCollector collector;
    public static final String INSERT_LOG = "INSERT INTO t_log  ( id ,  sn ,  orderby ,  type ,  datetime ,  address ,  user ,  event ,  params ) VALUES(?,?,?,?,?,?,?,?,?)";  
    RedisUtils r;
    Random rr;
    @Override  
    public void prepare(Map stormConf, TopologyContext context,
            OutputCollector collector) {  
        this.collector = collector;  
        r=new RedisUtils();
        rr=new Random();
    }  
  
    @Override  
    public void execute(Tuple input) {
        try {  
        //	Thread.sleep(5000);
        	String mesg = input.getString(0);  
                String [] msgs=mesg.split(" - ");
                if (mesg != null && msgs.length == 9) {  
                	System.out.println("mysql...."+mesg);
                	JDBCUtil.update(INSERT_LOG, mesg.split(" - ")[0],mesg.split(" - ")[1],mesg.split(" - ")[2],mesg.split(" - ")[3],
                             mesg.split(" - ")[4],mesg.split(" - ")[5],mesg.split(" - ")[6],mesg.split(" - ")[7],mesg.split(" - ")[8]); 
                }
  
        } catch (Exception e) {  
            e.printStackTrace(); // To change body of catch statement use File |  
            collector.fail(input);                  // Settings | File Templates.  
        }  
        collector.ack(input);  
    }  
  
    @Override  
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("id","data"));
    }  
}  