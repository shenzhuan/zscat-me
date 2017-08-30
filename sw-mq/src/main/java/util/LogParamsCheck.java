package util;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;

/**
 * Created by zscat on 2017/1/13.
 */
public class LogParamsCheck {

    public static JSONObject fromatLogParams(JSONObject jsonObject) {
        if (!jsonObject.containsKey("app_ver")) {
            jsonObject.put("app_ver", "");
        }
        if (!jsonObject.containsKey("broken")) {
            jsonObject.put("broken", "");
        }
        if (!jsonObject.containsKey("carrier")) {
            jsonObject.put("carrier", "");
        }
        if (!jsonObject.containsKey("channel")) {
            jsonObject.put("channel", "");
        }
        if (!jsonObject.containsKey("device_height")) {
            jsonObject.put("device_height", "");
        }
        if (!jsonObject.containsKey("device_id")) {
            jsonObject.put("device_id", "");
        }
        if (!jsonObject.containsKey("device_scale")) {
            jsonObject.put("device_scale", "");
        }
        if (!jsonObject.containsKey("device_width")) {
            jsonObject.put("device_width", "");
        }
        if (!jsonObject.containsKey("model")) {
            jsonObject.put("model", "");
        }
        if (!jsonObject.containsKey("net")) {
            jsonObject.put("net", "");
        }
        if (!jsonObject.containsKey("os_ver")) {
            jsonObject.put("os_ver", "");
        }
        if (!jsonObject.containsKey("platform")) {
            jsonObject.put("platform", "");
        }
        return jsonObject;
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getDate(String cts) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(Long.parseLong(cts + "000"));
    }
}
