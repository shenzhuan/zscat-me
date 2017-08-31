package com.zscat.mq.utils;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基于jackson的json工具类
 *
 * @author : zscat
 * @version : 1.0
 * @created on  : 2016/11/17  下午9:07
 */
public class JSONSerializerUtil {

    private static Logger log = LoggerFactory.getLogger(JSONSerializerUtil.class);

    private static class MapperInstance {
        public static ObjectMapper mapper = new ObjectMapper();

        static {
            mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(DeserializationConfig.Feature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
        }
    }

    public static ObjectMapper getObjectMaper() {
        return MapperInstance.mapper;
    }

    /**
     * 将对象序列化成JSON String
     *
     * @param obj Object
     * @return String
     * @throws IOException
     */
//    public static String toJsonString(Object obj) {
//
//        try {
//            return getObjectMaper().writeValueAsString(obj);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

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
            log.error("JSONSerializerUtil->toObject: error", e);
            return null;
        }
    }


    /**
     * 将对象序列化成JSON String
     *
     * @param obj Object
     * @return String
     * @throws IOException
     */
    public static String toJsonString(Object obj) {

        try {
            return getObjectMaper().writeValueAsString(obj);
        } catch (IOException e) {
            log.error("JSONSerializerUtil->toJsonString: error", e);
            return null;
        }
    }

    public static byte[] toBytes(Object obj) throws IOException {
        return getObjectMaper().writeValueAsBytes(obj);
    }

    public static <T> T toObject(byte[] bytes, Class<T> cls) {
        try {
            return getObjectMaper().readValue(bytes, cls);
        } catch (IOException e) {
            log.error("JSONSerializerUtil->toObject: error", e);
            return null;
        }
    }

    /**
     * 将java bean 转成map
     *
     * @param bean
     * @return
     */
    public static HashMap<String, String> beanToMap(Object bean) {
        HashMap<String, String> beanMap = getObjectMaper().convertValue(bean, HashMap.class);
        return beanMap;
    }


    public static HashMap<String, String> beanToMapNotNull(Object bean) {
        HashMap<String, String> beanMap = getObjectMaper().convertValue(bean, HashMap.class);
        ConcurrentHashMap cm = new ConcurrentHashMap(beanMap);
        Iterator<String> iterator = cm.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            Object value = beanMap.get(key);
            if (value == null || value.equals("null")) {
                beanMap.remove(key);
            }
        }

        return beanMap;
    }

    /**
     * 将java bean 转成map
     *
     * @param bean
     * @return
     */
    public static HashMap<String, Object> beanToMap2(Object bean) {
        HashMap<String, Object> beanMap = getObjectMaper().convertValue(bean, HashMap.class);
        return beanMap;
    }

    public static HashMap<String, Object> beanToMap2NotNull(Object bean) {
        HashMap<String, Object> beanMap = getObjectMaper().convertValue(bean, HashMap.class);
        ConcurrentHashMap cm = new ConcurrentHashMap(beanMap);
        Iterator<String> iterator = cm.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            Object value = beanMap.get(key);
            if (value == null || value.equals("null")) {
                beanMap.remove(key);
            }
        }
        return beanMap;
    }


    /**
     * 增加 将 map 转成java类的方法
     *
     * @param map map
     * @param cls Class
     * @param <T>
     * @return
     */
    public static <T> T mapToBean(Map<String, String> map, Class<T> cls) {
        return getObjectMaper().convertValue(map, cls);
    }

    public static <T> T mapByteToBean(Map<byte[], byte[]> map, Class<T> cls) {
        HashMap<String, String> strMap = new HashMap<>();
        for (byte[] key : map.keySet()) {
            strMap.put(new String(key), new String(map.get(key)));
        }
        return mapToBean(strMap,cls);
    }

    public static <T> T objectToBean(Object obj, Class<T> cls) {
        return getObjectMaper().convertValue(obj, cls);
    }


    public static <T> T mapToBean2(Map<String, Object> map, Class<T> cls) {
        return getObjectMaper().convertValue(map, cls);
    }

    public static <T> T unserialize(byte[] content, Class<T> cls) {
        return toObject(content, cls);
    }

    public static Map<String, Object> unserializeToMap(byte[] content) {
        if (content == null) {
            return null;
        }
        Map map = toObject(content, Map.class);
        return map;
    }


    public static Map<String, Object> unserialize(String content, Class<Map> mapClass) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        return toObject(content, HashMap.class);
    }

    public static Map<String, byte[]> unserialize(byte[] content) {
        if (content == null || content.length < 1) {
            return null;
        }
        return toObject(content, HashMap.class);
    }

    public static Map<String, Object> unserialize(String content) {
        if (content == null || content.length() < 1) {
            return null;
        }
        return toObject(content, HashMap.class);
    }

    public static ArrayList unserialize2(byte[] bytes) {
        if (bytes.length < 1) {
            return null;
        }
        return toObject(bytes, ArrayList.class);
    }

    public static String serialize(Map<String, Object> map) {
        if (map == null) {
            return null;
        }

        return toJsonString(map);
    }

    public static String serialize(Object object) {
        if (object == null) {
            return null;
        }

        return toJsonString(object);
    }

    public static byte[] serializeToBytes(Object obj) throws IOException {
        if (obj == null) {
            return null;
        }
        return toBytes(obj);
    }

    public static byte[] serializemap(Map objmap) throws IOException {
        if (objmap == null) {
            return null;
        }
        return toBytes(objmap);
    }

    public static String serialize2(List<String> list) {
        if (list == null) {
            return null;
        }

        return toJsonString(list);
    }


    //business中在使用，勿删
    public static String serializeList(List<String> list) {
        if (list == null) {
            return null;
        }

        return toJsonString(list);
    }


    //business中在使用，勿删
    public static ArrayList unserializeToList(byte[] bytes) {
        if (bytes.length < 1) {
            return null;
        }
        return toObject(bytes, ArrayList.class);
    }


    public static void main(String[] args) throws Exception {
//        RedisLink redisLink = new RedisLink("192.168.1.231",6379,"redis");
//        int size = 20;
////        ArrayList data = new ArrayList(size);
//        User user = new User();
//        user.setUid(UUID.randomUUID().toJsonString());
//        user.setUsername(UUID.randomUUID().toJsonString());
//        user.setHeadurl(UUID.randomUUID().toJsonString());
//        user.setCreatetime(UUID.randomUUID().toJsonString());
//        user.setFiletoken(UUID.randomUUID().toJsonString());
//        user.setIs_recommend(UUID.randomUUID().toJsonString());
//        user.setLastlogintime(UUID.randomUUID().toJsonString());
//        user.setMatchphone(UUID.randomUUID().toJsonString());
//        for (int i = 0; i < size; i++) {
//            User user = new User();
//            user.setUid(UUID.randomUUID().toJsonString());
//            user.setUsername(UUID.randomUUID().toJsonString());
//            user.setHeadurl(UUID.randomUUID().toJsonString());
//            user.setCreatetime(UUID.randomUUID().toJsonString());
//            user.setFiletoken(UUID.randomUUID().toJsonString());
//            user.setIs_recommend(UUID.randomUUID().toJsonString());
//            user.setLastlogintime(UUID.randomUUID().toJsonString());
//            user.setMatchphone(UUID.randomUUID().toJsonString());
//            data.add(user);
//        }
//
//        User user = new User();
//        user.setUid(UUID.randomUUID().toJsonString());
//        user.setUsername(UUID.randomUUID().toJsonString());
//        user.setHeadurl(UUID.randomUUID().toJsonString());
//        user.setCreatetime(UUID.randomUUID().toJsonString());
//        user.setFiletoken(UUID.randomUUID().toJsonString());
//        user.setIs_recommend(UUID.randomUUID().toJsonString());
//        user.setLastlogintime(UUID.randomUUID().toJsonString());
//        user.setMatchphone(UUID.randomUUID().toJsonString());
//
//
//        String str = JSONSerializerUtil.toJsonString(data);
//        System.out.println(str);
//
//        System.out.println("反序列化java object");
//        ArrayList d = JSONSerializerUtil.toObject(str, ArrayList.class);
//        System.out.println(d);
//
//        JSONSerializerUtil.toJsonString(user);
        System.out.println("-start serialize2 list------------------------------");
//        List<String> strList = new ArrayList<>();
//        strList.add("kkkk");
//        strList.add("zzzz");
//        strList.add("4444");
//        strList.add("kkkk");
//        String str2 = serialize2(strList);
//        System.out.println("serialize2:" + serialize2(strList));
//        System.out.println("unserialize2" + unserialize2(str2.getBytes()));
//        System.out.println("-end serialize2 list------------------------------");

//        System.out.println("-start serializemap------------------------------");
//        Map<String, Object> objectMap = new HashedMap();
//        objectMap.put("u1", new User());
//        objectMap.put("u2", user);
//        System.out.println("serializeMap:" + serializemap(objectMap).length);
//        Map<String, byte[]> bb = unserialize(serializemap(objectMap));
//        System.out.println(bb.get("u2"));
//        System.out.println("-end serializemap------------------------------");

//        System.out.println("-start serialize obj------------------------------");
//        byte[] userBytes = serialize(user);
//        System.out.println(userBytes.length);
//        User user1 = unserialize(userBytes,User.class);
//        System.out.println(user.getUid());
//        System.out.println("-end serialize obj------------------------------");

//        System.out.println("-start serialize Map------------------------------");
//        String strMap = serialize(objectMap);
//        System.out.println(userBytes.length);
//        Map str1Map = unserialize(strMap);
//        System.out.println(str1Map);
//        System.out.println("-end serialize Map------------------------------");

//        String rStr = redisLink.getJedis().get("effectlist:ios:iPhone6,2:10.0.2:0.12.140.320");
//        Map m = PHPSerializerOldUtil.unserializeToMap(rStr.getBytes());
//        System.out.println(m);
//        String str2 = serialize(m);
//        System.out.println(str2);


    }

}
