package com.zscat.storm.recommend.util;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author likaige
 * @date 2016-04-15
 */
public class PHPSerializerUtil {

    private static class MapperInstance {
        public static ObjectMapper mapper = new ObjectMapper();

        static {
            mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
    }

    public static ObjectMapper getObjectMaper() {
        return PHPSerializerUtil.MapperInstance.mapper;
    }

    /**
     * 将对象序列化成JSON String
     *
     * @param obj Object
     * @return String
     * @throws IOException
     */
    public static String toString(Object obj) {

        try {
            return getObjectMaper().writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将 JSON String 反序列化成 T
     *
     * @param jsonString
     * @param cls
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T toObject(String jsonString, Class<T> cls) {
        try {
            return getObjectMaper().readValue(jsonString, cls);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T toObject(byte[] bytes, Class<T> cls) {
        try {
            return getObjectMaper().readValue(bytes, cls);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 递归处理嵌套对象，最终返回Map
     * <br/>如果是AssocArray对象则转换为Map
     *
     * @param content PHP Serializer 格式的字节数组
     * @return map
     * @throws Exception
     * @author likaige
     */
    public static Map<String, Object> unserializeToMap(byte[] content) throws Exception {
        if (content == null) {
            return null;
        }
        Map map = toObject(content, HashMap.class);
        return map;
    }

    @Deprecated
    public static Map<String, Object> unserialize(String content) throws Exception {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        return toObject(content, HashMap.class);
    }

    @SuppressWarnings("unchecked")
    public static <T> T unserialize(byte[] content, Class c) throws Exception {
        try {
            return (T) getObjectMaper().readValue(content, c);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //business中在使用，勿删
    public static ArrayList unserializeToList(byte[] bytes) throws Exception {
        if (bytes.length < 1) {
            return null;
        }
        return toObject(bytes, ArrayList.class);
    }

    public static String serialize(Map<String, Object> map) throws Exception {
        return toString(map);
    }

    //business中在使用，勿删
    public static String serializeList(List<String> list) throws Exception {
        if (list == null) {
            return null;
        }

        return toString(list);
    }


    public static byte[] serialize(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }
        return getObjectMaper().writeValueAsBytes(obj);
    }


}
