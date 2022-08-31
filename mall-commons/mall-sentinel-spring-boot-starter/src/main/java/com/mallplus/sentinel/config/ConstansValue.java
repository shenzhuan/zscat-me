package com.mallplus.sentinel.config;



import com.google.common.collect.Lists;
import java.util.List;

/**
 * Created by Administrator on 2019/8/9.
 */
public class ConstansValue {

    public static final List<String> IGNORE_TENANT_TABLES = Lists.newArrayList("sys_admin_log", "sys_web_log", "sys_permission_category", "columns", "tables", "information_schema.columns",
            "information_schema.tables", "oms_payments", "sys_store", "sys_permission", "pms_product_attribute", "pms_product_category_attribute_relation", "pms_product_attribute_value",
            "pms_product_category_attribute_relation", "bak_category", "bak_goods", "bak_brand", "ums_member_level");


    public static final String sampleGoodsList = "id, brand_id, product_category_id, feight_template_id, product_attribute_category_id, name, pic, product_sn,\n" +
            "        delete_status, publish_status, new_status, recommand_status, verify_status, sort, sale, price, promotion_price,\n" +
            "        original_price, stock, low_stock, unit,\n" +
            "        weight, preview_status, service_ids,  brand_name,\n" +
            "        product_category_name, supply_id, create_time, school_id";

    public static final String sampleOrderList = "id,member_id, order_sn, create_time, member_username, total_amount, pay_amount,status, order_type,pay_type,source_type,goods_id,goods_name";

}
