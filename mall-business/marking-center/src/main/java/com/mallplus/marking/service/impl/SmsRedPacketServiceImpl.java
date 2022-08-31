package com.mallplus.marking.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mallplus.common.entity.sms.SmsRedPacket;
import com.mallplus.common.entity.sms.SmsUserRedPacket;
import com.mallplus.marking.mapper.SmsRedPacketMapper;
import com.mallplus.marking.mapper.SmsUserRedPacketMapper;
import com.mallplus.marking.service.ISmsRedPacketService;
import com.mallplus.marking.service.ISmsUserRedPacketService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * 红包 服务实现类
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Service
public class SmsRedPacketServiceImpl extends ServiceImpl<SmsRedPacketMapper, SmsRedPacket> implements ISmsRedPacketService {
    @Resource
    private SmsRedPacketMapper redPacketMapper;

    @Resource
    private ISmsUserRedPacketService userRedPacketService;
    @Resource
    private SmsUserRedPacketMapper userRedPacketMapper;
    @Override
    @Transactional
    public int createRedPacket(SmsRedPacket redPacket) {
        redPacket.setSendDate(new Date());
       // redPacket.setUserId(UserUtils.getCurrentMember().getId());
        if (redPacket.getType()==2) { //1随机红包 2等额红包
            redPacket.setAmount(new BigDecimal(redPacket.getTotal()).multiply(redPacket.getUnitAmount()));
        }
        redPacketMapper.insert(redPacket);
        List<SmsUserRedPacket> list = new ArrayList<>();
        if (redPacket.getType()==1){ //1随机红包 2等额红包
            list = divide(redPacket);

        }else if (redPacket.getType()==2){ //1随机红包 2等额红包
            for(int i=0;i<redPacket.getTotal();i++) {
                SmsUserRedPacket vo = new SmsUserRedPacket();
                vo.setGrabTime(new Date());
             //   vo.setAdminId(UserUtils.getCurrentMember().getId());
                vo.setAmount(redPacket.getUnitAmount());

                vo.setNote(redPacket.getNote());
                vo.setRedPacketId(redPacket.getId());
                list.add(vo);
            }
        }


        userRedPacketService.saveBatch(list);
        return 1;
    }

    @Override
    public int acceptRedPacket(Integer id,Long userId) {
        int counts = userRedPacketMapper.countOne(id, userId);
        if(counts>0){
            return 2;
        }
        SmsUserRedPacket userRedPacket = userRedPacketMapper.listUserRedOne(id);
        userRedPacket.setUserId(userId);
        userRedPacketMapper.updateById(userRedPacket);
        return 1;
    }

    //二倍均值法

    /**
     * 剩余红包金额M，剩余人数N，那么：每次抢到金额=随机(0，M/N*2)
     保证了每次随机金额的平均值是公平的
     假设10人，红包金额100元
     第一人：100/10*2=20，随机范围(0,20)，平均可以抢到10元
     第二人：90/9*2=20，随机范围(0,20)，平均可以抢到10元
     第三人：80/8*2=20，随机范围(0,20)，平均可以抢到10元
     以此类推，每次随机范围的均值是相等的
     缺点：除了最后一次，任何一次抢到的金额都不会超过人均金额的两倍，并不是任意的随机
     ---------------------
     作者：PreciousLife
     来源：CSDN
     原文：https://blog.csdn.net/q957967519/article/details/84661761
     版权声明：本文为博主原创文章，转载请附上博文链接！
     * @return
     */
    public static List<SmsUserRedPacket> divideRedPackage(SmsRedPacket redPacket) {

        List<SmsUserRedPacket> list = new ArrayList<>();
        //为了使用random.nextInt(Integer)方法不得不先把红包金额放大100倍，最后在main函数里面再除以100
        //这样就可以保证每个人抢到的金额都可以精确到小数点后两位

        Integer restAmount = redPacket.getAmount().intValue() * 100;

        Integer restPeopleNum = redPacket.getTotal();

        Random random = new Random();

        for (int i = 0; i < redPacket.getTotal() - 1; i++) {

            // 随机范围：[1，剩余人均金额的两倍)，左闭右开

            int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
            restAmount -= amount;
            restPeopleNum--;
            SmsUserRedPacket vo = new SmsUserRedPacket();
            vo.setGrabTime(new Date());
            vo.setAdminId(redPacket.getUserId());
            vo.setAmount(new BigDecimal(amount));
            vo.setNote(redPacket.getNote());
            vo.setRedPacketId(redPacket.getId());
            list.add(vo);
        }
        SmsUserRedPacket vo = new SmsUserRedPacket();
        vo.setGrabTime(new Date());
        vo.setAdminId(redPacket.getUserId());
        vo.setAmount(new BigDecimal(restAmount));
        vo.setNote(redPacket.getNote());
        vo.setRedPacketId(redPacket.getId());
        list.add(vo);

        return list;
    }

    //线段分割法

    /**
     * 把红包总金额想象成一条很长的线段，而每个人抢到的金额，则是这条主线段所拆分出的若干子线段。

     当N个人一起抢红包的时候，就需要确定N-1个切割点。

     因此，当N个人一起抢总金额为M的红包时，我们需要做N-1次随机运算，以此确定N-1个切割点。

     随机的范围区间是[1，100* M）。当所有切割点确定以后，子线段的长度也随之确定。这样每个人来抢红包的时候，只需要顺次领取与子线段长度等价的红包金额即可。
     ---------------------
     作者：PreciousLife
     来源：CSDN
     原文：https://blog.csdn.net/q957967519/article/details/84661761
     版权声明：本文为博主原创文章，转载请附上博文链接！
     * @return
     */
    private static List<SmsUserRedPacket> divide(SmsRedPacket redPacket) {
        List<SmsUserRedPacket> list = new ArrayList<>();
        //验证参数合理校验
        //为了使用random.nextInt(Integer)方法不得不先把红包金额放大100倍，最后在main函数里面再除以100
        //这样就可以保证每个人抢到的金额都可以精确到小数点后两位
        int fen = (int) (redPacket.getAmount().doubleValue() * 100);
        int n = redPacket.getTotal();
        if (fen < n || n < 1) {
            return null;
        }
        List<Integer> boards = new ArrayList<>();
        boards.add(0);
        boards.add(fen);
        //红包个数和板砖个数的关系
        while (boards.size() <= n) {
            int index = new Random().nextInt(fen - 1) + 1;
            if (boards.contains(index)) {
                //保证板子的位置不相同
                continue;
            }
            boards.add(index);
        }

        //计算每个红包的金额，将两个板子之间的钱加起来
        Collections.sort(boards);

        for (int i = 0; i < boards.size() - 1; i++) {
            Integer e = boards.get(i + 1) - boards.get(i);
            SmsUserRedPacket vo = new SmsUserRedPacket();
            vo.setGrabTime(new Date());
            vo.setAdminId(redPacket.getUserId());
            vo.setAmount(new BigDecimal(e).divide(new BigDecimal(100)));
            vo.setNote(redPacket.getNote());
            vo.setRedPacketId(redPacket.getId());
            list.add(vo);
        }
        return list;

    }
    public static void main(String[] args) {
//        List<Integer> accountList = divideRedPackage(50, 1000);
        SmsRedPacket redPacket = new SmsRedPacket();
        redPacket.setAmount(new BigDecimal(50));
        redPacket.setTotal(10);
        List<SmsUserRedPacket> accountList = divide(redPacket);
        BigDecimal count = new BigDecimal(0);
        for (SmsUserRedPacket amount : accountList) {
            //将抢到的金额再除以100进行还原
            BigDecimal tmpcount = new BigDecimal(amount.getAmount().doubleValue()).divide(new BigDecimal(100));
            count = count.add(tmpcount);
            System.out.println("抢到金额：" + tmpcount);

        }
        System.out.println("total=" + count);
    }
}

