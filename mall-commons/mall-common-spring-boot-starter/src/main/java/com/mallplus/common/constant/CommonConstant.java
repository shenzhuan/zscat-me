package com.mallplus.common.constant;

/**
 * 全局公共常量
 *
 * @author mall
 * @date 2018/10/29
 */
public interface CommonConstant {
    /**
     * token请求头名称
     */
    String TOKEN_HEADER = "Authorization";

    /**
     * 标签 header key
     */
    String HEADER_LABEL = "x-label";

    /**
     * 标签 header 分隔符
     */
    String HEADER_LABEL_SPLIT = ",";

    /**
     * 标签或 名称
     */
    String LABEL_OR = "labelOr";

    /**
     * 标签且 名称
     */
    String LABEL_AND = "labelAnd";

    /**
     * 权重key
     */
    String WEIGHT_KEY = "weight";

    /**
     * 删除
     */
    String STATUS_DEL = "1";

    /**
     * 正常
     */
    String STATUS_NORMAL = "0";

    /**
     * 锁定
     */
    String STATUS_LOCK = "9";

    /**
     * 目录
     */
    Integer CATALOG = -1;

    /**
     * 菜单
     */
    Integer MENU = 1;

    /**
     * 权限
     */
    Integer PERMISSION = 2;

    /**
     * 删除标记
     */
    String DEL_FLAG = "is_del";

    /**
     * 超级管理员用户名
     */
    String ADMIN_USER_NAME = "admin";

    /**
     * 公共日期格式
     */
    String MONTH_FORMAT = "yyyy-MM";
    String DATE_FORMAT = "yyyy-MM-dd";
    String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    String SIMPLE_MONTH_FORMAT = "yyyyMM";
    String SIMPLE_DATE_FORMAT = "yyyyMMdd";
    String SIMPLE_DATETIME_FORMAT = "yyyyMMddHHmmss";

    String DEF_USER_PASSWORD = "123456";

    String LOCK_KEY_PREFIX = "LOCK_KEY:";

    public static final String sampleGoodsList = "id, brand_id, product_category_id, feight_template_id, product_attribute_category_id, name, pic, product_sn,\n" +
            "        delete_status, publish_status, new_status, recommand_status, verify_status, sort, sale, price, promotion_price,\n" +
            "        original_price, stock, low_stock, unit,\n" +
            "        weight, preview_status, service_ids,  brand_name,\n" +
            "        product_category_name, supply_id, create_time, school_id";
}
