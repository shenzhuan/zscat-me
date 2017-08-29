import com.github.pagehelper.PageInfo;

import com.zscat.BlogServiceApplication;
import com.zscat.blog.api.model.Blog;
import com.zscat.blog.api.service.BlogService;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BlogServiceApplication.class)
public class Test {
    @Autowired
    private WebApplicationContext webApplicationContext;


    @Autowired
    BlogService blogService;


    @org.junit.Test
    public void testO(){
        List<Blog> l=blogService.select(new Blog());
        PageInfo<Blog> q =blogService.selectPage(1,10,new Blog(),"uid");
        Blog i = new Blog();
        i.setClickhit(22);
        PageInfo<Blog> qq = blogService.selectPage(1,10,i,"uid");
        System.out.println(l.size());
        System.out.println(q.getList().size());
        System.out.println(qq.getList().size());
        LOGGER.info(l.size()+","+l.get(0).getId());
    }



    private static final Logger LOGGER = LoggerFactory.getLogger(MockMvcBase.class);



}
