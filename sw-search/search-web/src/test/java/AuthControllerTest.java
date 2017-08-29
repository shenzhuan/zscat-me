import com.alibaba.fastjson.JSONObject;

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
        String uid="11";
        String msg = String.format("getStat param uid=%s over time=%s",uid,1);
        System.out.println(msg);
     
        List l = new ArrayList();
        l.add("a");l.add("b");l.add("c");
        for (int i =0; i<l.size();i++){
            System.out.println(l.get(i));
        }

    }
}
