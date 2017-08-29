
import com.zscat.SearchWebApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;


import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SearchWebApplication.class)
public class MockMvcBase {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(MockMvcBase.class);

    public final void handleRequest(String url, Map<String, String> params, String content, String key, String iv) {
        MockHttpServletRequestBuilder mock = post(url);
        mock.contentType(MediaType.APPLICATION_ATOM_XML);


        for (Map.Entry<String, String> param : params.entrySet()) {
            mock.param(param.getKey(), param.getValue());
        }

        try {
            MvcResult result = mockMvc.perform(mock)
                    .andExpect(status().isOk())
                    .andDo(print()).andReturn();
            String resultJson = result.getResponse().getContentAsString();

            //验证非法数据
            LOGGER.info("-------------------------");
            LOGGER.info("resultJsonEncode：" + resultJson);

        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }

    public final void handleRequest(String url, Map<String, String> params) {
        MockHttpServletRequestBuilder mock = post(url);
        mock.contentType(MediaType.APPLICATION_ATOM_XML);
        try {
            MvcResult result = mockMvc.perform(mock)
                    .andExpect(status().isOk())
                    .andDo(print()).andReturn();
            String resultJson = result.getResponse().getContentAsString();

            //验证非法数据
            LOGGER.info("-------------------------");
            LOGGER.info("resultJsonEncode：" + resultJson);
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }

    public final void handleGetRequest(String url, Map<String, String> params) {
        StringBuffer urlBuffer = new StringBuffer(url);
        if (params!=null) {
            urlBuffer.append("?");
            for (Map.Entry<String, String> param : params.entrySet()) {
                urlBuffer.append(param.getKey()).append("=").append(param.getValue()).append("&");
            }
            urlBuffer.setLength(urlBuffer.length() - 1);
        }

        MockHttpServletRequestBuilder mock = get(urlBuffer.toString());
        mock.contentType(MediaType.APPLICATION_ATOM_XML);
        try {
            MvcResult result = mockMvc.perform(mock)
                    .andExpect(status().isOk())
                    .andDo(print()).andReturn();
            String resultJson = result.getResponse().getContentAsString();

            //验证非法数据
            LOGGER.info("-------------------------");
            LOGGER.info("url:{}", urlBuffer.toString());
            LOGGER.info("resultJson：{}", resultJson);
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }

}
