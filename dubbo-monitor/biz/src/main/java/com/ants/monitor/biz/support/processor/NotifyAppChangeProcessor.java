package com.ants.monitor.biz.support.processor;

import com.ants.monitor.bean.bizBean.ApplicationChangeBO;
import org.springframework.stereotype.Service;

/**
 * Created by zxg on 16/1/5.
 * 15:20
 * 应用停止和启动事件捕获后的处理机制
 * 可做邮件通知、电话通知、短信通知等相关应用负责人的代码
 */
@Service
public class NotifyAppChangeProcessor {


    public void stopApp(ApplicationChangeBO applicationChangeBO){
        //todo
    }

    public void startApp(ApplicationChangeBO applicationChangeBO){
        //todo
    }
}
