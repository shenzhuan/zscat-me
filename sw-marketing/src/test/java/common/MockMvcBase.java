package common;

import com.github.pagehelper.PageInfo;
import com.zsCat.common.utils.DateUtils;
import com.zscat.PromotionApplication;

import com.zscat.marketing.model.ActiveUser;
import com.zscat.marketing.model.Income;
import com.zscat.marketing.model.Withdraw;
import com.zscat.marketing.service.IActiveUserService;
import com.zscat.marketing.service.IWithDrawService;
import com.zscat.marketing.service.IincomeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;

import java.text.ParseException;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PromotionApplication.class)
public class MockMvcBase {
    @Autowired
    private WebApplicationContext webApplicationContext;


    @Autowired
    IincomeService iincomeService;
    @Autowired
    IActiveUserService iActiveUserService;
    @Autowired
    IWithDrawService iWithDrawService;
    @Test
    public void testO(){
        List<Income> l=iincomeService.select(new Income());
        PageInfo<Income> q =iincomeService.selectPage(1,10,new Income(),"uid");
        Income i = new Income();
        i.setType("9");
        PageInfo<Income> qq = iincomeService.selectPage(1,10,i,"uid");
        System.out.println(l.size());
        System.out.println(q.getList().size());
        System.out.println(qq.getList().size());
        LOGGER.info(l.size()+","+l.get(0).getId());
    }

    @Test
    public void testO1(){


      ActiveUser a= iActiveUserService.selectById(100511L);
        LOGGER.info(a.getProIfcode());
        List<ActiveUser> l=iActiveUserService.select(new ActiveUser());
        PageInfo<ActiveUser> q =iActiveUserService.selectPage(1,10,new ActiveUser(),"pro_ifcode,uid");
        System.out.println(l.size());
        System.out.println(q.getList().size());
        a.setUid(100512L);
        iActiveUserService.insert(a);
    }
    @Test
    public void testSelect() throws ParseException {
        String startDate= "2017-05-26";
        String endDate= "2017-05-31";
        PageInfo<Withdraw> list =  iWithDrawService
                .selectPageByDate(1L, 1, 10, DateUtils.formatDate(startDate), DateUtils.formatDate(endDate),"operationtime");
        LOGGER.info(list.getList().size()+"");
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(MockMvcBase.class);



}
