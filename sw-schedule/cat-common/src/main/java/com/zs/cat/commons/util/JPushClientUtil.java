package com.zs.cat.commons.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.jpush.api.ErrorCodeEnum;
import cn.jpush.api.IOSExtra;
import cn.jpush.api.JPushClient;
import cn.jpush.api.MessageResult;

import com.zs.cat.commons.util.mail.BaseController;

@Controller
@RequestMapping(value="/jpush")
public class JPushClientUtil extends BaseController{
	
	private static final String appKey = "737cea7b852bf8c60cecbf51"; // 必填，例如466f7032ac604e02fb7bda89

	private static final String masterSecret = "c0e2c47796f2095cbc56a516";// "13ac09b17715bd117163d8a1";//必填，每个应用都对应一个masterSecret

	private static JPushClient jpush = null;
	
	public static final int MAX = Integer.MAX_VALUE;
	public static final int MIN = (int) MAX / 2;
	
	/**
	 * 保存离线的时长。秒为单位。最多支持10天（864000秒）。 
	 * 0 表示该消息不保存离线。即：用户在线马上发出，当前不在线用户将不会收到此消息。
	 * 此参数不设置则表示默认，默认为保存1天的离线消息（86400秒)。
	 */
	private static long timeToLive = 60 * 60 * 24;
	
	public static void main(String[] args) {
		String msgTitle = "推送测试";
		String msgContent = "看到信息了么，看到就推送成功了！";
		String userid="7753b9c538de44c791bb44eed1980d39";
		pushMessage(msgTitle,msgContent,userid);
	}
	
	private static void init(){
		jpush = new JPushClient(masterSecret, appKey, timeToLive);
	}
	
	/**
	 * 给单个人推送消息
	 * @param msgTitle
	 * @param msgContent
	 * @param userid     用户id（uuid）
	 */
	public static void pushMessage(String msgTitle, String msgContent,String userid) {
		String result = "";
		
		init();
		
		// 在实际业务中，建议 sendNo 是一个你自己的业务可以处理的一个自增数字。
		// 除非需要覆盖，请确保不要重复使用。详情请参考 API 文档相关说明。
		int sendNo = getRandomSendNo();
		
		 // IOS设备扩展参数, 设置badge，设置声音
		Map<String, Object> extra = new HashMap<String, Object>();
		IOSExtra iosExtra = new IOSExtra(10, "WindowsLogonSound.wav");
		extra.put("ios", iosExtra);

		// 对所有用户发送通知     
		//手机端方法：sendNotificationWithAppKey          
		//sendCustomMessageWithAppKey
		//MessageResult msgResult = jpush.sendNotificationWithAppKey(sendNo,msgTitle, msgContent);
		//MessageResult msgResult = jpush.sendNotificationWithAlias(sendNo,userid, msgTitle, msgContent);
		//MessageResult msgResult = jpush.sendCustomMessageWithAppKey(sendNo,msgTitle, msgContent);
		MessageResult msgResult = jpush.sendNotificationWithAppKey(sendNo,msgTitle, msgContent);
		
		if (null != msgResult) {
			result = "服务器返回数据: " + msgResult.toString();
			System.out.println(result);
			
			if (msgResult.getErrcode() == ErrorCodeEnum.NOERROR.value()) {
				result = String.format(
					"发送成功， sendNo= %s,messageId= %s",
					msgResult.getSendno(), 
					msgResult.getMsg_id()
				);
					
				System.out.println(result);
			} else {
				result = "发送失败， 错误代码=" + msgResult.getErrcode() +
						 ", 错误消息=" 		  + msgResult.getErrmsg()  ;
				System.out.println(result);
			}
		} else {
			System.out.println("无法获取数据");
		}

	}
	
	/**
	 * 给多个人推送消息
	 * @param list
	 */
	/*public static void pushManyMessage(List<PageData> list){
		
		for(int i=0;i<list.size();i++){
			pushMessage(
				list.get(i).get("msgTitle").toString(),
				list.get(i).get("msgContent").toString(),
				list.get(i).get("user_id").toString()
			);
		}
		
	}*/
	
	/**
	 * 保持 sendNo 的唯一性是有必要的
	 */
	public static int getRandomSendNo() {
		return (int) (MIN + Math.random() * (MAX - MIN));
	}

}
