### 1.安装好kafka

### 2.在对应的模块添加 producer.properties 配置kafaka信息



- partitioner.class=com.sankuai.xm.kafka.client.partitioner.CustomizeCommonPartitioner
- slow.kafka.topic=slow
- slow.metadata.broker.list=192.168.1.238:9092
### 3.将消息打入kafka

         ```
                     try {
				JSONObject data = new JSONObject();
				data.put("action", "sendEmail");

				JSONObject recJsonObj = new JSONObject();
				recJsonObj.put("btype", "fcRecommend");
				recJsonObj.put("data", data);
				slowProducer.sendMessageAsync(recJsonObj, 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
```
### 4.在sw-mq中 KafkaConsumer类中添加相应的消费代码
```
 case "sendEmail": {
       // 处理消息
         break;
      }
```
			