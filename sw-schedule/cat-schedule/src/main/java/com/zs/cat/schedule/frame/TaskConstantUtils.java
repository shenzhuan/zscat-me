package com.zs.cat.schedule.frame;
/**
 * 常量类
 * @author 
 * @date 2014-4-15 上午8:57:43
 */
public class TaskConstantUtils {
	
	public final static int TASK_RUNNING =1; //任务运行中
	
	public final static int TASK_COMPLETED =2;//任务正常结束
	
	public final static int TASK_EXCEPTION =3;//任务异常结束
	
	public final static String APPLICATION_CONTEXT_KEY = "applicationContext";//spring上下文
	
	public final static String CSS_ENV_PATH =  System.getenv().get("zs_CONFIG");//读取环境变量
	
	public static final String CONFIG_FILE_PATH_FILESYSTEM = CSS_ENV_PATH + "other.properties";//配置文件文件系统路径
	
}
