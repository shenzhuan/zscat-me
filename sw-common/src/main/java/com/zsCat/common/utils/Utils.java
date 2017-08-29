package com.zsCat.common.utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class Utils {

	private static final String SEP1 = ","; 
    private static final String SEP2 = "|";
    private static final String SEP3 = "=";
    /**
     * List转换String
     * 
     * @param list
     *            :需要转换的List
     * @return String转换后的字符串
     */ 
    public static String ListToString(List<?> list) { 
        StringBuffer sb = new StringBuffer(); 
        if (list != null && list.size() > 0) { 
            for (int i = 0; i < list.size(); i++) { 
                if (list.get(i) == null || list.get(i) == "") { 
                    continue; 
                } 
                // 如果值是list类型则调用自己 
                if (list.get(i) instanceof List) { 
                    sb.append(ListToString((List<?>) list.get(i))); 
                    sb.append(SEP1); 
                } else if (list.get(i) instanceof Map) { 
                    sb.append(MapToString((Map<?, ?>) list.get(i))); 
                    sb.append(SEP1); 
                } else { 
                    sb.append(list.get(i)); 
                    sb.append(SEP1); 
                } 
            } 
          return  sb.substring(0, sb.length()-1);
        } 
        return sb.toString(); 
    } 
     
    /**
     * Map转换String
     * 
     * @param map
     *            :需要转换的Map
     * @return String转换后的字符串
     */ 
    public static String MapToString(Map<?, ?> map) { 
        StringBuffer sb = new StringBuffer(); 
        // 遍历map 
        for (Object obj : map.keySet()) { 
            if (obj == null) { 
                continue; 
            } 
            Object key = obj; 
            Object value = map.get(key); 
            if (value instanceof List<?>) { 
                sb.append(key.toString() + SEP1 + ListToString((List<?>) value)); 
                sb.append(SEP2); 
            } else if (value instanceof Map<?, ?>) { 
                sb.append(key.toString() + SEP1 
                        + MapToString((Map<?, ?>) value)); 
                sb.append(SEP2); 
            } else { 
                sb.append(key.toString() + SEP3 + value.toString()); 
                sb.append(SEP2); 
            } 
        } 
        return "M" + sb.toString(); 
    } 
   
    /**
     * String转换Map
     * 
     * @param mapText
     *            :需要转换的字符串
     * @param KeySeparator
     *            :字符串中的分隔符每一个key与value中的分割
     * @param ElementSeparator
     *            :字符串中每个元素的分割
     * @return Map<?,?>
     */ 
    public static Map<String, Object> StringToMap(String mapText) { 
   
        if (mapText == null || mapText.equals("")) { 
            return null; 
        } 
        mapText = mapText.substring(1); 
   
        mapText = mapText; 
   
        Map<String, Object> map = new HashMap<String, Object>(); 
        String[] text = mapText.split("\\" + SEP2); // 转换为数组 
        for (String str : text) { 
            String[] keyText = str.split(SEP3); // 转换key与value的数组 
            if (keyText.length < 1) { 
                continue; 
            } 
            String key = keyText[0]; // key 
            String value = keyText[1]; // value 
            if (value.charAt(0) == 'M') { 
                Map<?, ?> map1 = StringToMap(value); 
                map.put(key, map1); 
            } else if (value.charAt(0) == 'L') { 
                List<?> list = StringToList(value); 
                map.put(key, list); 
            } else { 
                map.put(key, value); 
            } 
        } 
        return map; 
    } 
   
    /**
     * String转换List
     * 
     * @param listText
     *            :需要转换的文本
     * @return List<?>
     */ 
    public static List<Object> StringToList(String listText) { 
        if (listText == null || listText.equals("")) { 
            return null; 
        } 
        listText = listText.substring(1); 
   
        listText = listText; 
   
        List<Object> list = new ArrayList<Object>(); 
        String[] text = listText.split(SEP1); 
        for (String str : text) { 
            if (str.charAt(0) == 'M') { 
                Map<?, ?> map = StringToMap(str); 
                list.add(map); 
            } else if (str.charAt(0) == 'L') { 
                List<?> lists = StringToList(str); 
                list.add(lists); 
            } else { 
                list.add(str); 
            } 
        } 
        return list; 
    }
    // 根据Unicode编码完美的判断中文汉字和符号
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }
 
    // 完整的判断中文汉字和符号
    public static boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }
 
    // 只能判断部分CJK字符（CJK统一汉字）
    public static boolean isChineseByREG(String str) {
        if (str == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("[\\u4E00-\\u9FBF]+");
        return pattern.matcher(str.trim()).find();
    }
 
    // 只能判断部分CJK字符（CJK统一汉字）
    public static boolean isChineseByName(String str) {
        if (str == null) {
            return false;
        }
        // 大小写不同：\\p 表示包含，\\P 表示不包含
        // \\p{Cn} 的意思为 Unicode 中未被定义字符的编码，\\P{Cn} 就表示 Unicode中已经被定义字符的编码
        String reg = "\\p{InCJK Unified Ideographs}&&\\P{Cn}";
        Pattern pattern = Pattern.compile(reg);
        return pattern.matcher(str.trim()).find();
    }
    public static String getURL(HttpServletRequest request) {

        String contextPath = request.getContextPath().equals("/") ? "" : request.getContextPath();

        String url = "http://" + request.getServerName();
        if (null2Int(Integer.valueOf(request.getServerPort())) != 80)
            url = url + ":" + null2Int(Integer.valueOf(request.getServerPort())) + contextPath;
        else {
            url = url + contextPath;
        }
        return url;

    }
    public static int null2Int(Object s) {
        int v = 0;
        if (s != null)
            try {
                v = Integer.parseInt(s.toString());
            } catch (Exception localException) {
            }
        return v;
    }

}