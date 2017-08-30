package com.zscat.mq.kafka;

import com.alibaba.fastjson.JSONObject;

import com.google.common.util.concurrent.RateLimiter;

import com.zscat.mq.utils.JSONSerializerUtil;
import com.sankuai.mms.cfg.Config;
import com.sankuai.xm.kafka.client.IConsumerProcessor;
import com.sankuai.xm.kafka.client.IMessageListener;
import com.sankuai.xm.kafka.client.factory.KafkaConsumerBuildFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Read msg from Kafka
 */
@Named
public class KafkaConsumer {

    private static Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    /*默认消费频率限制*/
    private static final Integer DEFAULT_RATE_LIMITER = 10000;
    /*通讯录频率限制*/
    private static final Integer ADDRESS_BOOK_RATE_LIMITER = 200;




    private IConsumerProcessor slowConsumer;
    private IConsumerProcessor addressBookConsumer;
    private IConsumerProcessor marketmsgConsumer;


    private RateLimiter slowLimiter;
    private RateLimiter addressBookLimiter;
    private RateLimiter marketmsgLimiter;


    private static ThreadPoolExecutor servicePool = (ThreadPoolExecutor) Executors.newFixedThreadPool(32);
    private static AtomicLong count = new AtomicLong();



    @PostConstruct
    private void init() {
        try {
            Config.reload();
            int rateLimiter = Integer.parseInt(Config.get("consumer.rate.limiter"));
            if (rateLimiter <= 0) {
                rateLimiter = DEFAULT_RATE_LIMITER;
            }
            slowLimiter = RateLimiter.create(rateLimiter);
            addressBookLimiter = RateLimiter.create(ADDRESS_BOOK_RATE_LIMITER);
            marketmsgLimiter = RateLimiter.create(rateLimiter);

            slowConsumer = KafkaConsumerBuildFactory.init();
            addressBookConsumer = KafkaConsumerBuildFactory.init("addressBook.kafka.topic", "addressBook.group.id",
                    "addressBook.consumer.thread.num", "addressBook.zookeeper.connect");

            marketmsgConsumer = KafkaConsumerBuildFactory.init("addressBook.kafka.topic", "marketmsg.group.id",
                    "addressBook.consumer.thread.num", "addressBook.zookeeper.connect");

        } catch (Exception e) {
            log.error("KafkaConsumer init error.", e);
            System.exit(-1);
        }
    }

    public void start() {
        //普通慢服务
        slowConsumer.recvMessageWithParallel(byte[].class, new IMessageListener() {
            @Override
            public void recvMessage(final byte[] bytes) {
                slowLimiter.acquire();
                servicePool.execute(new Runnable() {
                    @Override
                    public void run() {

                        count.incrementAndGet();
                        if (count.get() % 1000 == 0) {
                            log.warn("PoolSize={}, QueueSize={}, CompletedTaskCount={}",
                                    servicePool.getPoolSize(), servicePool.getQueue().size(),
                                    servicePool.getCompletedTaskCount());
                        }

                        String btype = null;
                        try {
                            final JSONObject jsonObject = JSONSerializerUtil.unserialize(bytes, JSONObject.class);
                            MDC.put("logId",jsonObject.getString("logId"));
                            if (log.isInfoEnabled()) {
                                log.info("slowConsumer receive: {}", jsonObject);
                            }

                            btype = jsonObject.getString("btype");
                            JSONObject dataJson = jsonObject.getJSONObject("data");

                            switch (btype) {
                                case "regusermatchfriend": {
//                                    reguserMatchFriendService.matchFriend(dataJson);
//                                    if (!StringUtils.isEmpty(dataJson.getString("udid"))) {
//                                        adService.matchUdid(dataJson);
//                                    }

                                    //推荐朋友圈
                                    JSONObject tempData = new JSONObject();
                                    tempData.put("action", "register");
                                    tempData.put("uid", dataJson.getString("uid"));
                                //    fcRecommendService.fcRecommend(tempData);

                                    break;
                                }

                                case "updateUserDeivce": {
                                //    reguserMatchFriendService.updateUserDeivce(dataJson);
                                    break;
                                }


                                default: {
                                    log.error("invalid btype={}", btype);
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            log.error("slowConsumer recvMessage error.", e);
                        }
                    }
                });
            }
        });

        //上传通讯录服务
        addressBookConsumer.recvMessageWithParallel(byte[].class, new IMessageListener() {
            @Override
            public void recvMessage(final byte[] bytes) {
                addressBookLimiter.acquire();

                String btype = null;
                try {
                    final JSONObject jsonObject = JSONSerializerUtil.unserialize(bytes, JSONObject.class);
                    if (log.isInfoEnabled()) {
                        log.info("addressBookConsumer receive: {}", jsonObject);
                    }

                    btype = jsonObject.getString("btype");
                    JSONObject dataJson = jsonObject.getJSONObject("data");

                    if ("saveaddressbookv1".equals(btype)) {
                     //   upAdressBookService.upAddressBookv1(dataJson);
                    }
                } catch (Exception e) {
                    if ("saveaddressbookv1".equals(btype)) {

                    }
                    log.error("addressBookConsumer recvMessage error.", e);
                }
            }
        });


    }
}
