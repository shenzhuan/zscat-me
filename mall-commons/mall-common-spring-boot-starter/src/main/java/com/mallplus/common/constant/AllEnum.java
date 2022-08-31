package com.mallplus.common.constant;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 交易API Constant
 *
 * @author dp
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AllEnum {



    /**
     * 改变类型：1->增加；2->减少
     *
     * @author dp
     */
    public enum ChangeType implements BaseEnum<Integer> {

        /**
         * 限价交易
         */
        Add(1, "add"),

        /**
         * 市价交易
         */
        Min(2, "min"),
        ;

        private int code;
        private String value;

        ChangeType(int code, String value) {
            this.code = code;
            this.value = value;
        }

        @Override
        public Integer code() {
            return code;
        }

        @Override
        public String desc() {
            return value;
        }
    }
    /**
     * 积分来源
     *
     * @author dp
     */
    public enum ChangeSource implements BaseEnum<Integer> {

        /**
         * 下单
         */
        order(1, "order"),

        /**
         * 登录
         */
        login(2, "login"),
        /**
         * 注册
         */
        register(3, "register"),
        ;

        private int code;
        private String value;

        ChangeSource(int code, String value) {
            this.code = code;
            this.value = value;
        }

        @Override
        public Integer code() {
            return code;
        }

        @Override
        public String desc() {
            return value;
        }
    }

    /**
     * 评论类型：1->商品；2->订单
     *
     * @author dp
     */
    public enum ConsultType implements BaseEnum<Integer> {

        /**
         * 限价交易
         */
        GOODS(1, "goods"),

        /**
         * 市价交易
         */
        ORDER(2, "order"),
        ;

        private int code;
        private String value;

        ConsultType(int code, String value) {
            this.code = code;
            this.value = value;
        }

        @Override
        public Integer code() {
            return code;
        }

        @Override
        public String desc() {
            return value;
        }
    }


    /**
     * 订单类型：
     *
     * @author dp
     */
    public enum OrderType implements BaseEnum<Integer> {

        /**
         * 普通订单
         */
        COMMON(1, "common"),
        /**
         * 拼团订单
         */
        PIN_GROUP(2, "pingroup"),
        /**
         * 团购订单
         */
        GROUP_BUY(3, "groupbuy"),
        /**
         * 砍价订单
         */
        KNAN_JIA(4, "kanjia"),
        JIFEN(5, "jifen"),
        /**
         * 秒杀订单
         */
        SKILL(6, "skill"),
        ;

        private int code;
        private String value;

        OrderType(int code, String value) {
            this.code = code;
            this.value = value;
        }

        @Override
        public Integer code() {
            return code;
        }

        @Override
        public String desc() {
            return value;
        }
    }

    /**
     *
     */
    public enum OrderPayType implements BaseEnum<Integer> {

        /**
         *
         */
        weixinAppletPay(1, "weixinAppletPay"),
        /**
         *
         */
        alipay(2, "alipay"),
        /**
         * 余额支付
         */
        balancePay(3, "balancePay"),
        /**
         * 积分兑换
         */
        jifenPay(5, "jifenPay")
        ;

        private int code;
        private String value;

        OrderPayType(int code, String value) {
            this.code = code;
            this.value = value;
        }

        @Override
        public Integer code() {
            return code;
        }

        @Override
        public String desc() {
            return value;
        }
    }
    /**
     * 评论类型：1->商品；2->赠品
     *
     * @author dp
     */
    public enum OrderItemType implements BaseEnum<Integer> {

        /**
         * 商品
         */
        GOODS(1, "goods"),

        /**
         * 礼品
         */
        GIFT(2, "gift"),
        ;

        private int code;
        private String value;

        OrderItemType(int code, String value) {
            this.code = code;
            this.value = value;
        }

        @Override
        public Integer code() {
            return code;
        }

        @Override
        public String desc() {
            return value;
        }
    }
}
