import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author jd.wang
 * @date 2017-04-11
 */
public class AuthControllerTest extends MockMvcBase {


    @Test
    public void getshareurl() {
        String url = "/api/friendcirclev2/getshareurl";

        //参数
        Map<String, String> params = new HashMap<>();
        params.put("token", "m8O/hIH6VErG3QHoaIWvqmF1pYA27eb9RYb5D0rGP7dB1Wee6880gQ==");

        //加、解密
        String key = "46b122e1004d3db38df823fb";
        String iv = "5c0d2133";

        //body参数
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msgid","5");
        jsonObject.put("uid","100510");
        jsonObject.put("type","0");

        super.handleRequest(url, params, jsonObject.toJSONString(), key, iv);
    }
    @Test
    public void hotTopiclist() {
        String url = "/api/friendcirclev2/hotTopiclist";

        //参数
        Map<String, String> params = new HashMap<>();
        params.put("token", "o9ZUD5T1FywrffEUqcPXCVmcXsTzfFFbWz5mmpU6i+F2Zg9TnPyZoA==");

        //加、解密
        String key = "746716a9b494036d1e87d041";
        String iv = "e5ac16ef";

        //body参数
        JSONObject jsonObject = new JSONObject();

        super.handleRequest(url, params, jsonObject.toJSONString(), key, iv);
    }


}
