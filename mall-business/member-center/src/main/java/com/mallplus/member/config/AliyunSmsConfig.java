package com.mallplus.member.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * * 程序名 : AliyunSmsConfig
 * 建立日期: 2018-07-09
 * 作者 : someday
 * 模块 : 短信中心
 * 描述 : 阿里云短信配置
 * 备注 : version20180709001
 * <p>
 * 修改历史
 * 序号 	       日期 		        修改人 		         修改原因
 */
@Configuration
public class AliyunSmsConfig {
	
	private Logger logger = LoggerFactory.getLogger(AliyunSmsConfig.class);

	@Value("${aliyun.sms.accessKeyId:xxxxx}")
	private String accessKeyId;
	@Value("${aliyun.sms.accessKeySecret:xxxxx}")
	private String accessKeySecret;

	@Bean
	public IAcsClient iAcsClient() throws ClientException {
		
		// 设置超时时间-可自行调整
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		// 初始化ascClient需要的几个参数
		logger.info("初始化阿里云接口:" + this);
		final String product = "Dysmsapi";// 短信API产品名称（短信产品名固定，无需修改）
		final String domain = "dysmsapi.aliyuncs.com";// 短信API产品域名（接口地址固定，无需修改）
		// 替换成你的AK
		// 初始化ascClient,暂时不支持多region（请勿修改）
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e) {
            
            logger.error("初始化阿里云客户端失败：" + e);
        }
		
		IAcsClient acsClient = new DefaultAcsClient(profile);

		return acsClient;
	}

}
