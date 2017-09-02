package com.zsCat.common.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

/**
 * 根据IP地址获取详细的地域信息
 * 淘宝API : http://ip.taobao.com/service/getIpInfo.php?ip=218.192.3.42
 * 新浪API : http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=218.192.3.42
 * @File AddressUtils.java
 * @Package org.gditc.weicommunity.util
 * @Description TODO
 * @Copyright Copyright © 2014
 * @Site https://github.com/Cryhelyxx
 * @Blog http://blog.csdn.net/Cryhelyxx
 * @Email cryhelyxx@gmail.com
 * @Company GDITC
 * @Date 2014年11月6日 下午1:46:37
 * @author Cryhelyxx
 * @version 1.0
 */
public class AddressUtils {
    /**
     *
     * @param content
     *            请求的参数 格式为：name=xxx&pwd=xxx
     * @param encodingString
     *            服务器端请求编码。如GBK,UTF-8等
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getAddresses(String content, String encodingString)
            throws UnsupportedEncodingException {
        // 这里调用淘宝API
        String urlStr = "http://ip.taobao.com/service/getIpInfo.php";
        // 从http://whois.pconline.com.cn取得IP所在的省市区信息
        String returnStr = getResult(urlStr, content, encodingString);
        if (returnStr != null) {
            // 处理返回的省市区信息
          //  System.out.println("(1) unicode转换成中文前的returnStr : " + returnStr);
            returnStr = decodeUnicode(returnStr);
          //  System.out.println("(2) unicode转换成中文后的returnStr : " + returnStr);
            String[] temp = returnStr.split(",");
            if(temp.length<3){
                return "0";//无效IP，局域网测试
            }
            return returnStr;
        }
        return null;
    }
    /**
     * @param urlStr
     *            请求的地址
     * @param content
     *            请求的参数 格式为：name=xxx&pwd=xxx
     * @param encoding
     *            服务器端请求编码。如GBK,UTF-8等
     * @return
     */
    private static String getResult(String urlStr, String content, String encoding) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();// 新建连接实例
            connection.setConnectTimeout(20000);// 设置连接超时时间，单位毫秒
            connection.setReadTimeout(20000);// 设置读取数据超时时间，单位毫秒
            connection.setDoOutput(true);// 是否打开输出流 true|false
            connection.setDoInput(true);// 是否打开输入流true|false
            connection.setRequestMethod("POST");// 提交方法POST|GET
            connection.setUseCaches(false);// 是否缓存true|false
            connection.connect();// 打开连接端口
            DataOutputStream out = new DataOutputStream(connection
                    .getOutputStream());// 打开输出流往对端服务器写数据
            out.writeBytes(content);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
            out.flush();// 刷新
            out.close();// 关闭输出流
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
            // ,以BufferedReader流来读取
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();// 关闭连接
            }
        }
        return null;
    }
    /**
     * unicode 转换成 中文
     *
     * @author fanhui 2007-3-15
     * @param theString
     * @return
     */
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed      encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    outBuffer.append(aChar);
                }
            } else {
                outBuffer.append(aChar);
            }
        }
        return outBuffer.toString();
    }
//	// 测试
//	public static void main(String[] args) {
//		AddressUtils addressUtils = new AddressUtils();
//		// 测试ip 219.136.134.157 中国=华南=广东省=广州市=越秀区=电信
//		String ip = "122.49.20.247";
//		String address = "";
//		try {
//			address = addressUtils.getAddresses("ip="+ip, "utf-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(address);
//		// 输出结果为：广东省,广州市,越秀区
//	}


    public static void main(String[] args)
    {
        try
        {
            getAddressByIp("219.136.134.157");
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static  IpAddress getAddressByIp(String ip) throws Exception {
        // 参数ip
//		String ip = "219.136.134.157";
        // String ip = "221.235.44.19";

        // json_result用于接收返回的json数据
        String json_result = null;
        try {
            json_result = AddressUtils.getAddresses("ip=" + ip, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        JSONObject json = JSONObject.fromObject(json_result);

        System.out.println("json数据： " + json);
        JSONObject obj = JSONObject.fromObject(json.get("data"));
        IpAddress bean = new IpAddress(obj.get("country").toString(),obj.get("country_id").toString(),obj.get("area").toString(),
                obj.get("area_id").toString(),obj.get("region").toString(),obj.get("region_id").toString(),obj.get("city").toString(),
                obj.get("city_id").toString(),obj.get("county").toString(),obj.get("county_id").toString(),obj.get("isp").toString(),obj.get("isp_id").toString()
        );

//        String address = country + "/";
//        address += region + "/";
//        address += city + "/";
//        address += county;
//        System.out.println(address);

        return bean;
    }
    public static  String getCityByIp(String ip) throws Exception {

        String json_result = null;
        try {
            json_result = AddressUtils.getAddresses("ip=" + ip, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("ip数据： " + ip);
        if ("0:0:0:0:0:0:0:1".equals(ip)){
            return "迪拜";
        }
        JSONObject json = JSONObject.fromObject(json_result);
        if (json.isNullObject()){
            return "迪拜";
        }
        System.out.println("json数据： " + json);

        String city = JSONObject.fromObject(json.get("data")).get("city").toString();
        return city;
    }
    public static  String getCityByIp() throws Exception {
        String ip = IpGetter.getLocalIP();
        System.out.println("ip数据： " + ip);
        String json_result = null;
        try {
            json_result = AddressUtils.getAddresses("ip=" + ip, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if ("0:0:0:0:0:0:0:1".equals(ip)){
            return "迪拜";
        }
        System.out.println("json_result： " + json_result);

        if (json_result == null){
            return "迪拜";
        }
        JSONObject json = JSONObject.fromObject(json_result);
        System.out.println("json数据： " + json);
        String city = JSONObject.fromObject(json.get("data")).get("city").toString();
        return city;
    }
}