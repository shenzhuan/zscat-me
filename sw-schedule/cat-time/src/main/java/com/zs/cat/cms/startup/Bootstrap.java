package com.zs.cat.cms.startup;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.google.common.util.concurrent.AbstractIdleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@WebListener
public class Bootstrap extends AbstractIdleService  implements ServletContextListener{

    private ClassPathXmlApplicationContext context;
    private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrap.class);

    public static void main(String[] args) {
    	Bootstrap bootstrap = new Bootstrap();
        bootstrap.startAsync();
        try {
            Object lock = new Object();
            synchronized (lock) {
                while (true) {
                    lock.wait();
                }
            }
        } catch (InterruptedException ex) {
        	LOGGER.error("ignore interruption",ex);
        }
    } 
    @Override
    protected void startUp() throws Exception {
        LOGGER.info("===================cat-cms START BEGIN==========================");
        Long startTime = System.nanoTime();
        context = new ClassPathXmlApplicationContext(new String[]{"config/spring/time-context.xml"});
        context.start();
        context.registerShutdownHook();
        LOGGER.info("zs-filepush service started successfully");
        Long interval=(System.nanoTime()-startTime)/1000000000;
        LOGGER.info("zs-CORE service STARTED UP successfully in {} seconds...", interval);
        LOGGER.info("===================cat-cms START END==========================");

    } 
    @Override
    protected void shutDown() throws Exception {
        context.stop();
        LOGGER.info("service stopped successfully");
    }
    
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOGGER.info("cat-cms service started ");
     try {
            startUp();
     } catch (Exception ex) {
            ex.printStackTrace();
     LOGGER.error("ignore interruption ");
     }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            shutDown();
     } catch (Exception e) {
            e.printStackTrace();
     }
    }
}
