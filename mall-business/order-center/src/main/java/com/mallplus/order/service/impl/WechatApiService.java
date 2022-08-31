

package com.mallplus.order.service.impl;


import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.mallplus.common.lock.DistributedLock;
import com.mallplus.common.redis.template.RedisRepository;
import com.mallplus.order.config.WxAppletProperties;
import com.mallplus.order.utils.MyX509TrustManager;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Map;

/**
 * 对接微信接口服务
 * Created by fei on 2017/4/24.
 */
@Service
public class WechatApiService {
    private static final String WECHAT_API = "https://api.weixin.qq.com/cgi-bin";
    private static final String WECHAT_API_TOKEN = WECHAT_API + "/token";
    private static final String WECHAT_API_TICKET = WECHAT_API + "/ticket/getticket?type=jsapi&access_token=";
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";


@Resource
private WxAppletProperties wxAppletProperties;

    @Resource
    private RedisRepository redisRepository;

    @Autowired
    private DistributedLock lock;

    private final HttpClient httpclient;

    public WechatApiService() {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setSocketTimeout(20000)
                .setConnectionRequestTimeout(3000)
                .build();
        httpclient = HttpClients.custom().setDefaultRequestConfig(config).build();
    }
    /**
     * 获取默认公众号的 access_token
     *
     * @return access_token
     * @throws Exception
     */
    public String getAccessToken() throws Exception {
        return getAccessToken(wxAppletProperties.getAppId(), wxAppletProperties.getSecret());
    }



    /**
     * 获取  access_token
     * https://mp.weixin.qq.com/wiki?action=doc&id=mp1421140183
     *
     * @return access_token
     * @throws Exception
     */
    public String getAccessToken(String appid, String appSecret) throws Exception {

        String key = "access_token:" + appid;
        if (redisRepository.willExpire(key) > 30) {
            return redisRepository.get(key).toString();

        }

        //https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
        String lockKey = "lock_" + key;

        boolean acquired = lock.lock(lockKey);
        if (!acquired) {
            throw new Exception("acquired lock: " + lockKey + " timeout");
        }
        try {
            if (redisRepository.willExpire(key) > 30) {
                return redisRepository.get(key).toString();
            }

            HttpGet get = new HttpGet(WECHAT_API_TOKEN + "?grant_type=client_credential&appid=" + appid + "&secret=" + appSecret);
            HttpResponse response = httpclient.execute(get);
            String text = EntityUtils.toString(response.getEntity());
            Map<String, Object> resultMap = JSONUtil.parseObj(text);
            String accessToken = (String) resultMap.get("access_token");
            int expiresIn = (int) resultMap.get("expires_in");

            //redisRepository.set(key, accessToken);
            redisRepository.setExpire(key, accessToken,expiresIn);
            return accessToken;
        } finally {
            lock.releaseLock(lockKey);
        }
    }




    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSON handleRequest(String requestUrl, String requestMethod, String outputStr) {
        JSON jsonObject = null;

        try {
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            SSLContext ctx = SSLContext.getInstance("SSL", "SunJSSE");
            TrustManager[] tm = {new MyX509TrustManager()};
            ctx.init(null, tm, new SecureRandom());
            SSLSocketFactory sf = ctx.getSocketFactory();
            conn.setSSLSocketFactory(sf);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod(requestMethod);
            conn.setUseCaches(false);

            if ("GET".equalsIgnoreCase(requestMethod)) {
                conn.connect();
            }

            if (StringUtils.isNotEmpty(outputStr)) {
                OutputStream out = conn.getOutputStream();
                out.write(outputStr.getBytes("utf-8"));
                out.close();
            }

            InputStream in = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in,"utf-8"));
            StringBuffer buffer = new StringBuffer();
            String line = null;

            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }

            in.close();
            conn.disconnect();

            jsonObject = JSONUtil.parseObj(buffer.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    /**
     * 获取默认公众号 jsapi_ticket
     *
     * @return jsapi_ticket
     * @throws Exception
     */

    public String getJsTicket() throws Exception {

        return getJsTicket(wxAppletProperties.getAppId(), wxAppletProperties.getSecret());
    }



    /**
     * 获取 jsapi_ticket
     * https://mp.weixin.qq.com/wiki?action=doc&id=mp1421141115
     *
     * @param appid
     * @param appSecret
     * @return ticket
     * @throws Exception
     */
    public String getJsTicket(String appid, String appSecret) throws Exception {

        String key = "jsapi_ticket:" + appid;

        if (redisRepository.willExpire(key) > 30) {
            return redisRepository.get(key).toString();
        }

        String lockKey = "lock_" + key;

        boolean acquired = lock.lock(lockKey);
        if (!acquired) {
            throw new Exception("acquired lock: " + lockKey + " timeout");
        }

        try {
            if (redisRepository.willExpire(key) > 30) {
                return redisRepository.get(key).toString();
            }

            HttpGet get = new HttpGet(WECHAT_API_TICKET + getAccessToken(appid, appSecret));
            HttpResponse response = httpclient.execute(get);
            String text = EntityUtils.toString(response.getEntity());
            Map<String, Object> resultMap = JSONUtil.parseObj(text);
            String ticket = (String) resultMap.get("ticket");
            int expiresIn = (int) resultMap.get("expires_in");

            redisRepository.setExpire(key, ticket,expiresIn);

            return ticket;
        } finally {
            lock.releaseLock(lockKey);

        }
    }
}
