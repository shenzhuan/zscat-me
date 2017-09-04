import com.alibaba.fastjson.JSONObject;

import com.zsCat.common.utils.RedisUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jd.wang
 * @date 2017-04-11
 */
public class AuthControllerTest extends MockMvcBase {


    @Test
    public void querySettings() {

        String url = "/front/blog/test";

        //参数
        Map<String, String> params = new HashMap<>();
        params.put("token", "j8B1u3rnMC7krRDH29oFlHoRDqk+9blFmyy5usjC/pf9ihav5JrdDVMsDDwKrnuPhx7JdygMbFE=");

        //加、解密
        String key = "beee6929c1e939ca5e583697";
        String iv = "491f324e";

        //body参数
        JSONObject jsonObject = new JSONObject();

        super.handleRequest(url, params);
    }

    public static void main(String[] args) {
        RedisUtils r =new RedisUtils();
//        r.set("string","string");
//        r.set("string:12","string:1");
//        r.sadd("sets", "element001");
//        r.sadd("sets", "element002");
//        r.sadd("sets", "element003");
//        r.sadd("sets", "element004");
//
//        r.lpush("stringlists", "vector");
//        r.lpush("stringlists", "ArrayList");
//        r.lpush("stringlists", "vector");
//        r.lpush("stringlists", "vector");
//        r.lpush("stringlists", "LinkedList");
//
//        r.hset("map", "key001", "value001");
//        r.hset("map", "key002", "value002");
//        r.hset("map", "key003", "value003");
//        r.hset("map", "key004", "value004");
        r.zadd("zset", 7.0, "element007");
        r.zadd("zset", 4.0, "element004");
        r.zadd("zset", 3.0, "element003");
        r.zadd("zset", 1.0, "element001");

    }
}
