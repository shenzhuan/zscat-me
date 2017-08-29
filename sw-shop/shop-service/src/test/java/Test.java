import com.github.pagehelper.PageInfo;

import com.zscat.ShopServiceApplication;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ShopServiceApplication.class)
public class Test {
    @Autowired
    private WebApplicationContext webApplicationContext;




    @org.junit.Test
    public void testO(){

    }



    private static final Logger LOGGER = LoggerFactory.getLogger(MockMvcBase.class);



}
