package com.mallplus.common.redis.constant;

/**
 * redis 工具常量
 *
 * @author mall
 * @date 2018/5/21 11:59
 */
public class RedisToolsConstant {
    private RedisToolsConstant() {
        throw new IllegalStateException("Utility class");
    }
    /**
     * single Redis
     */
    public final static int SINGLE = 1 ;

    /**
     * Redis cluster
     */
    public final static int CLUSTER = 2 ;

    public static String GOODSDETAIL = "GOODSDETAIL:%s";

    public static String GOODSHISTORY = "GOODSHISTORY:%s";


    public static final String ARTICLE_VIEWCOUNT_CODE = "ARTICLEVIEWCOUNTCODE_";
    public static final String ARTICLE_VIEWCOUNT_KEY = "ARTICLE_VIEWCOUNT_KEY";

    public static final String STORE_VIEWCOUNT_CODE = "STOREVIEWCOUNTCODE_";
    public static final String STORE_VIEWCOUNT_KEY = "STORE_VIEWCOUNT_KEY";

    public static final String GOODS_VIEWCOUNT_CODE = "GOODSVIEWCOUNTCODE_";
    public static final String GOODS_VIEWCOUNT_KEY = "GOODS_VIEWCOUNT_KEY";


    public static String categoryAndChilds = "categoryAndChilds";
    public static String goodsConsult = "goodsConsult";
    public static String categoryAndGoodsList = "categoryAndGoodsList";

    public static String specialcategoryAndGoodsList = "specialcategoryAndGoodsList";



    public static String orderDetail = "orderDetail";
    public static String getorderstatusnum = "getorderstatusnum";


    /**
     * 会员
     */
    public static String MEMBER = "MEMBER:%s";

    public static String HOMEPAGEmallplus1 = "HOMEPAGEmallplus1";
    public static String HOMEPAGEmallplus2 = "HOMEPAGEmallplus2";
    public static String HOMEPAGE2 = "HOMEPAGECRMWEB";
    public static String HOMEPAGEPC = "HOMEPAGEPC";

    public static String HomeContentResult = "HomeContentResult";
    public static String PmsProductResult = "PmsProductResult";
    public static String orderDetailResult = "orderDetailResult";

    public static String PmsProductConsult = "PmsProductConsult";
}
