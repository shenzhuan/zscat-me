package com.mallplus.order.utils.applet;


import cn.hutool.core.util.XmlUtil;
import com.mallplus.order.utils.CharUtil;
import com.mallplus.order.utils.MapUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>Title: 微信退款工具类</p>
 * <p>Description: 微信退款工具类，通过充值客户端的不同初始化不同的工具类，得到相应微信退款相关的appid和muchid</p>
 *
 * @author xubo
 * @date 2017年6月6日  下午5:05:03
 */
public class WechatUtil {
    private static Logger logger = LoggerFactory.getLogger(WechatUtil.class);

    /**
     * 充值客户端类型--微信公众号
     */
    public static Integer CLIENTTYPE_WX = 2;
    /**
     * 充值客户端类型--app
     */
    public static Integer CLIENTTYPE_APP = 1;

    /**
     * 方法描述：微信退款逻辑
     * 创建时间：2017年4月12日  上午11:04:25
     * 作者： xubo
     *
     * @param
     * @return
     */
   /* public static WechatRefundApiResult wxRefund(String out_trade_no, Double orderMoney, Double refundMoney) {
        //初始化请求微信服务器的配置信息包括appid密钥等
        //转换金钱格式
        BigDecimal bdOrderMoney = new BigDecimal(orderMoney, MathContext.DECIMAL32);
        BigDecimal bdRefundMoney = new BigDecimal(refundMoney, MathContext.DECIMAL32);
        //构建请求参数
        Map<Object, Object> params = buildRequsetMapParam(out_trade_no, bdOrderMoney, bdRefundMoney);
        String mapToXml = MapUtils.convertMap2Xml(params);
        //请求微信
        String reponseXml = sendSSLPostToWx(mapToXml, WechatConfig.getSslcsf());
        WechatRefundApiResult result = (WechatRefundApiResult) XmlUtil.xmlStrToBean(reponseXml, WechatRefundApiResult.class);
        return result;
    }*/

    /**
     * 方法描述：得到请求微信退款请求的参数
     * 创建时间：2017年6月8日  上午11:27:02
     * 作者： xubo
     *
     * @param
     * @return
     */
   /* private static Map<Object, Object> buildRequsetMapParam(String out_trade_no, BigDecimal bdOrderMoney, BigDecimal bdRefundMoney) {
        Map<Object, Object> params = new HashMap<Object, Object>();
        //微信分配的公众账号ID（企业号corpid即为此appId）
        params.put("appid", ResourceUtil.getConfigByName("wx.appId"));
        //微信支付分配的商户号
        params.put("mch_id", ResourceUtil.getConfigByName("wx.mchId"));
        //随机字符串，不长于32位。推荐随机数生成算法
        params.put("nonce_str", CharUtil.getRandomString(16));
        //商户侧传给微信的订单号
        params.put("out_trade_no", out_trade_no);
        //商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
        params.put("out_refund_no", getBundleId());
        //订单总金额，单位为分，只能为整数
        params.put("total_fee", bdOrderMoney.multiply(new BigDecimal(100)).intValue());
        //退款总金额，订单总金额，单位为分，只能为整数
        params.put("refund_fee", bdRefundMoney.multiply(new BigDecimal(100)).intValue());
        //操作员帐号, 默认为商户号
        params.put("op_user_id", ResourceUtil.getConfigByName("wx.mchId"));
        //签名前必须要参数全部写在前面
        params.put("sign", arraySign(params, ResourceUtil.getConfigByName("wx.paySignKey")));
        return params;
    }*/

    /**
     * ResourceUtil.getConfigByName("wx.refundUrl")
     * 请求微信https
     **/
    public static String sendSSLPostToWx(String mapToXml, SSLConnectionSocketFactory sslcsf,String refundUrl) {
        logger.info("*******退款（WX Request：" + mapToXml);
        HttpPost httPost = new HttpPost(refundUrl);
        httPost.addHeader("Connection", "keep-alive");
        httPost.addHeader("Accept", "*/*");
        httPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        httPost.addHeader("Host", "api.mch.weixin.qq.com");
        httPost.addHeader("X-Requested-With", "XMLHttpRequest");
        httPost.addHeader("Cache-Control", "max-age=0");
        httPost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
        httPost.setEntity(new StringEntity(mapToXml, "UTF-8"));
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslcsf).build();
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httPost);
            HttpEntity entity = response.getEntity();
            String xmlStr = EntityUtils.toString(entity, "UTF-8");
            logger.info("*******退款（WX Response：" + xmlStr);
            return xmlStr;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    /**
     * 方法描述：微信查询退款逻辑
     * 创建时间：2017年4月12日  上午11:04:25
     * 作者： xubo
     *
     * @param
     * @return
     */


    public Map<String, Object> wxRefundquery(String out_trade_no, String out_refund_no) {
        Map<Object, Object> params = new HashMap<Object, Object>();
        //微信分配的公众账号ID（企业号corpid即为此appId）
        params.put("appid", "xx");
        //微信支付分配的商户号
        params.put("mch_id", "xx");
        //随机字符串，不长于32位。推荐随机数生成算法
        params.put("nonce_str", CharUtil.getRandomString(16));
        //商户侧传给微信的订单号
        params.put("out_trade_no", out_trade_no);
        //签名前必须要参数全部写在前面
        //签名
        params.put("sign", arraySign(params, "wx.paySignKey"));
        String mapToXml = MapUtils.convertMap2Xml(params);
        HttpPost httPost = new HttpPost("refundqueryUrl");
        httPost.addHeader("Connection", "keep-alive");
        httPost.addHeader("Accept", "*/*");
        httPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        httPost.addHeader("Host", "api.mch.weixin.qq.com");
        httPost.addHeader("X-Requested-With", "XMLHttpRequest");
        httPost.addHeader("Cache-Control", "max-age=0");
        httPost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
        httPost.setEntity(new StringEntity(mapToXml, "UTF-8"));
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(WechatConfig.getSslcsf()).build();
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httPost);
            HttpEntity entity = response.getEntity();
            String xmlStr = EntityUtils.toString(entity, "UTF-8");
            System.out.println(xmlStr);
            Map<String, Object> result = XmlUtil.xmlToMap(xmlStr);//.xmlStrToBean(xmlStr, WechatRefundApiResult.class);
            return result;
            //将信息保存到数据库
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    /**
     * 支付交易ID
     */
    public static String getBundleId() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String tradeno = dateFormat.format(new Date());
        String str = "000000" + (int) (Math.random() * 1000000);
        tradeno = tradeno + str.substring(str.length() - 6);
        return tradeno;
    }

    /**
     * 方法描述：根据签名加密请求参数
     * 创建时间：2017年6月8日  上午11:28:52
     * 作者： xubo
     *
     * @param
     * @return
     */
    public static String arraySign(Map<Object, Object> params, String paySignKey) {
        boolean encode = false;
        Set<Object> keysSet = params.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuffer temp = new StringBuffer();
        boolean first = true;
        for (Object key : keys) {
            if (first) {
                first = false;
            } else {
                temp.append("&");
            }
            temp.append(key).append("=");
            Object value = params.get(key);
            String valueString = "";
            if (null != value) {
                valueString = value.toString();
            }
            if (encode) {
                try {
                    temp.append(URLEncoder.encode(valueString, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else {
                temp.append(valueString);
            }
        }
        temp.append("&key=");
        temp.append(paySignKey);
        System.out.println(temp.toString());
        String packageSign = MD5.getMessageDigest(temp.toString());
        return packageSign;
    }

    /**
     * 请求，只请求一次，不做重试
     *
     * @param url
     * @param data
     * @return
     * @throws Exception
     */
    public static String requestOnce(final String url, String data) throws Exception {
        BasicHttpClientConnectionManager connManager;
        connManager = new BasicHttpClientConnectionManager(
                RegistryBuilder.<ConnectionSocketFactory>create()
                        .register("http", PlainConnectionSocketFactory.getSocketFactory())
                        .register("https", SSLConnectionSocketFactory.getSocketFactory())
                        .build(),
                null,
                null,
                null
        );

        HttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(connManager)
                .build();

        HttpPost httpPost = new HttpPost(url);

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(10000).build();

        httpPost.setConfig(requestConfig);

        StringEntity postEntity = new StringEntity(data, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.addHeader("User-Agent", "wxpay sdk java v1.0 " + "mchId");
        httpPost.setEntity(postEntity);

        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        String reusltObj = EntityUtils.toString(httpEntity, "UTF-8");
        logger.info("请求结果:" + reusltObj);
        return reusltObj;

    }
}
