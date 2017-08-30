package com.zscat.storm.recommend.test.ka2;

import kafka.admin.TopicCommand;

public class TopicUtil {
	public static void topicCreate(String topic){
	String[] options = new String[]{  
		    "--create",  
		    "--zookeeper",  
		    "10.2.10.61:2181",  
		    "--partitions",  
		    "1",  
		    "--topic",  
		    topic,  
		    "--replication-factor",  
		    "1"
		};  
		TopicCommand.main(options); 
	}

	public static void topicList(){
		String[] options = new String[]{  
			    "--list",  
			    "--zookeeper",  
			    "10.2.10.61:2181"  
			};  
			TopicCommand.main(options);  
	}
	
	public static void topicDetail(String topic){
		String[] options = new String[]{  
			    "--describe",  
			    "--zookeeper",  
			    "10.2.10.61:2181",  
			    "--topic",  
			    topic,  
			};  
			TopicCommand.main(options);  
	}
	/**
	 * bin/kafka-topics.sh --zookeeper zk_host:port/chroot --alter --topic my_topic_name --deleteConfig x
	 */
	public static void topicUpdate(){
		String[] options = new String[]{  
			    "--alter",  
			    "--zookeeper",  
			    "10.2.10.61:2181/chroot",  
			    "--topic",  
			    "test1"
			};  
			TopicCommand.main(options);  
	}
public static void topicDelete(String topic){
	String[] options = new String[]{  
		    "--zookeeper",  
		    "zk_host:port/chroot",  
		    "--topic",  
		    "my_topic_name"  
		};  
	//	DeleteTopicCommand.main(options);  
	}
}
